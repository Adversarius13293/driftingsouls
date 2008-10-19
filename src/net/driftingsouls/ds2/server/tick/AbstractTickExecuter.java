/*
 *	Drifting Souls 2
 *	Copyright (c) 2006 Christopher Jung
 *
 *	This library is free software; you can redistribute it and/or
 *	modify it under the terms of the GNU Lesser General Public
 *	License as published by the Free Software Foundation; either
 *	version 2.1 of the License, or (at your option) any later version.
 *
 *	This library is distributed in the hope that it will be useful,
 *	but WITHOUT ANY WARRANTY; without even the implied warranty of
 *	MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 *	Lesser General Public License for more details.
 *
 *	You should have received a copy of the GNU Lesser General Public
 *	License along with this library; if not, write to the Free Software
 *	Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA  02110-1301  USA
 */
package net.driftingsouls.ds2.server.tick;

import java.io.File;
import java.io.IOException;

import net.driftingsouls.ds2.server.ContextCommon;
import net.driftingsouls.ds2.server.framework.BasicContext;
import net.driftingsouls.ds2.server.framework.CmdLineRequest;
import net.driftingsouls.ds2.server.framework.Common;
import net.driftingsouls.ds2.server.framework.Configuration;
import net.driftingsouls.ds2.server.framework.DriftingSouls;
import net.driftingsouls.ds2.server.framework.SimpleResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.support.FileSystemXmlApplicationContext;

/**
 * Klasse zur Ausfuehrung von mehreren Ticks
 * @author Christopher Jung
 *
 */
public abstract class AbstractTickExecuter extends TickController {
	private static final Log log = LogFactory.getLog(AbstractTickExecuter.class);
	
	private String loxpath = Configuration.getSetting("LOXPATH");
	private String name = "";
	private String status = null;
	
	private static BasicContext basicContext;
	
	/**
	 * Bootet DS
	 * Als Kommandozeilenparameter <code>--config</code> wird der Konfigurationspfad erwartet
	 * @param args Die Kommandozeilenargumente
	 * @throws Exception
	 */
	public static final void boot(String[] args) throws Exception {
		log.info("Booting DS...");
		
		CmdLineRequest request = new CmdLineRequest(args);
		
		try {
			new DriftingSouls(log, request.getParameterString("config"), true);
		}
		catch( Exception e ) {
			log.fatal(e, e);
			throw new Exception(e);
		}
		
		new FileSystemXmlApplicationContext("/"+request.getParameterString("config")+"/spring.xml");
		
		SimpleResponse response = new SimpleResponse();
		basicContext = new BasicContext(request, response);
	}
	
	/**
	 * Gibt alle noch belegten Resourcen frei
	 *
	 */
	public static final void free() {
		basicContext.free();
	}
	
	/**
	 * Setzt den Namen des Ticks. Dieser wird in LOXPATH/tix.log festgehalten
	 * @param name Name des Ticks
	 */
	protected void setName( String name ) {
		this.name = name;
	}
	
	/**
	 * Setzt den Basispfad fuer alle Logdateien. Default
	 * ist LOXPATH.
	 * 
	 * @param path Basispfad fuer Logs
	 */
	protected void setLogPath( String path ) {
		this.loxpath = path;
		if( loxpath.charAt(loxpath.length()-1) != '/' ) {
			loxpath += '/';
		}
		if( !new File(loxpath).isDirectory() ) {
			log.error("Der Log-Pfad '"+loxpath+"' existiert nicht");
		}
	}
	
	/**
	 * Fuehrt ein Tickscript aus
	 * @param tickname Eine Instanz des Tickscripts
	 * @param useSTDOUT Soll STDOUT oder eine Logdatei mit dem Namen des Ticks verwendet werden?
	 */
	protected void execTick( Class<? extends TickController> tickname, boolean useSTDOUT ) {
		try {
			TickController tick = tickname.newInstance();
				
			if( !useSTDOUT ) {
				tick.addLogTarget(loxpath+tickname.getSimpleName().toLowerCase()+".log", false);
			}
			else {
				tick.addLogTarget(STDOUT, false);
			}
					
			tick.execute();
			tick.dispose();
		}
		catch( Exception e ) {
			// Alle Exceptions hier fangen und lediglich ausgeben
			e.printStackTrace();
		}
		finally {
			// Der Inhalt des Ticks wurde geschrieben (commit) oder zurueckgesetzt (rollback)
			// Daher ist ein clear nun unproblematisch
			getDB().clear();
		}
	}
	
	/**
	 * Kopiert alle Logs im Tick-Log-Verzeichnis in ein Unterverzeichnis mit der Tick-Nr
	 * @param ticknr Nummer des Ticks
	 */
	protected void copyLogs( int ticknr ) {
		log.info("Kopiere alle Logs von "+loxpath+" nach "+loxpath+ticknr+"/");
		File[] files = new File(loxpath).listFiles();
		for( int i=0; i < files.length; i++ ) {
			if( files[i].getName().endsWith(".log") ) {
				String filename = files[i].getName();
				log.info("Kopiere "+filename);
				
				if( filename.lastIndexOf('/') > -1 ) {
					filename = filename.substring(filename.lastIndexOf('/')+1);
				}
				Common.copyFile(files[i].getAbsolutePath(), loxpath+ticknr+"/"+filename);
			}
		}
	}
	
	/**
	 * Schreibt den aktuellen Status nach ticktime.log (befindet sich unter LOXPATH
	 * und nicht im Basisverzeichnis der Tick-Logs!)
	 * @param status Der zu schreibende Status
	 */
	protected void publishStatus( String status ) {
		try {
			int handle = addLogTarget(Configuration.getSetting("LOXPATH")+"ticktime.log", false);
			log("Bitte warten - "+status);
			removeLogTarget(handle);
			
			this.status = status;
		}
		catch( IOException e ) {
			System.err.println("Tickstatus konnte nicht publiziert werden: "+e);
		}
	}
	
	/**
	 * Gibt den aktuell veroeffentlichten Status des Ticks zurueck. Wenn noch kein Status gesetzt wurde,
	 * dann wird <code>null</code> zurueckgegeben
	 * @return Der aktuelle Status oder <code>null</code>
	 */
	protected String getStatus() {
		return this.status;
	}
	
	/**
	 * Vor- und Nachbereitung der Tickausfuehrung
	 */
	@Override
	protected final void tick() {
		if( getContext().getRequest().getParameterString("only").length() > 0 ) {
			
			try {
				execTick(Class.forName(getContext().getRequest().getParameterString("only")).asSubclass(TickController.class), true);
			} catch (Exception e) {
				System.err.println("Ausfuehrung des Ticks "+getContext().getRequest().getParameterString("only")+" fehlgeschlagen: "+e);
				e.printStackTrace();
			}
			return;
		}
		
		int ticknr = getContext().get(ContextCommon.class).getTick()+1;
		
		if( !new File(loxpath+"/"+ticknr).isDirectory() ) {
			boolean result = new File(loxpath+"/"+ticknr).mkdir();
			if( !result ) {
				log.error("Kann Verzeichnis '"+loxpath+"/"+ticknr+"' nicht anlegen");
			}
		}
		
		executeTicks();
		
		copyLogs(ticknr);
		
		try {
			int handle = addLogTarget(Configuration.getSetting("LOXPATH")+"tix.log", true);
			if( name.length() != 0 ) {
				slog(name+": ");
			}
			int handle2 = addLogTarget(Configuration.getSetting("LOXPATH")+"ticktime.log", false);
			log(Common.date("d.m.Y H:i:s"));
			removeLogTarget(handle2);
			removeLogTarget(handle);
		}
		catch( Exception e ) {
			System.err.println("Fehler bei der Ticknachbereitung: "+e);
		}
	}
	
	/**
	 * Fuehrt alle Einzelticks aus
	 */
	protected abstract void executeTicks();
	
	/**
	 * Erlaubt das Behandeln von Timeouts
	 *
	 */
	protected abstract static class TimeoutChecker extends Thread {
		private long timeout;
		private boolean hasTimedOut;
		
		/**
		 * Konstruktor
		 * @param timeout Die Anzahl an Millisekunden, die nach dem Start gewartet werden soll
		 */
		protected TimeoutChecker(long timeout) {
			this.timeout = timeout;
			this.hasTimedOut = false;
		}
		
		@Override
		public void run() {
			long start = Common.time();
			
			try {
				while( start+timeout > Common.time() ) {
					synchronized(this) {
						wait(100);
					}
				}
				if( start+timeout <= Common.time() ) {
					this.hasTimedOut = true;
					timeout();
				}
			}
			catch( InterruptedException e ) {
				// Exit
			}
		}
		
		/**
		 * Wird aufgerufen, wenn ein Timeout geschieht
		 *
		 */
		protected abstract void timeout();
		
		/**
		 * Gibt zurueck, ob ein Timeout stattgefunden hat
		 * @return <code>true</code>, falls ein Timeout stattgefunden hat
		 */
		public boolean hasTimedOut() {
			return this.hasTimedOut;
		}
	}
}
