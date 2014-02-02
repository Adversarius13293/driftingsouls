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
package net.driftingsouls.ds2.server.bases;

import java.util.HashMap;
import java.util.Map;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

import com.google.gson.JsonObject;
import net.driftingsouls.ds2.server.cargo.Cargo;
import net.driftingsouls.ds2.server.cargo.UnmodifiableCargo;
import net.driftingsouls.ds2.server.framework.Common;
import net.driftingsouls.ds2.server.framework.Context;
import net.driftingsouls.ds2.server.framework.ContextMap;
import net.driftingsouls.ds2.server.framework.templates.TemplateEngine;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.DiscriminatorFormula;
import org.hibernate.annotations.Type;

// TODO: Warum Verbrauch/Produktion unterscheiden?
/**
 * Basisklasse fuer alle Gebaeudetypen.
 *
 * @author Christopher Jung
 *
 */
@Entity
@Table(name="buildings")
@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
@DiscriminatorFormula("module")
@Cache(usage=CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public abstract class Building {

	/**
	 * Die ID des Kommandozentralen-Gebaeudes.
	 */
	public static final int KOMMANDOZENTRALE = 1;

	/**
	 * Gibt eine Instanz der Gebaudeklasse des angegebenen Gebaeudetyps zurueck.
	 * Sollte kein passender Gebaeudetyp existieren, wird <code>null</code> zurueckgegeben.
	 *
	 * @param id Die ID des Gebaudetyps
	 * @return Eine Instanz der zugehoerigen Gebaeudeklasse
	 */
	public static Building getBuilding(int id) {
		org.hibernate.Session db = ContextMap.getContext().getDB();

		return (Building)db.get(Building.class, id);
	}

	@Id
	private int id;
	@SuppressWarnings("unused")
	private String module;
	private int bewohner;
	private int arbeiter;
	private String name;
	private String picture;
	@ElementCollection
	@CollectionTable(name="building_alternativebilder")
	private Map<Integer,String> alternativeBilder;
	@Type(type="cargo")
	@Column(name="buildcosts")
	private Cargo buildCosts;
	@Type(type="cargo")
	private Cargo produces;
	@Type(type="cargo")
	private Cargo consumes;
	@Column(name="ever")
	private int eVerbrauch;
	@Column(name="eprodu")
	private int eProduktion;
	@Column(name="techreq")
	private int techReq;
	private int eps;
	@Column(name="perplanet")
	private int perPlanet;
	@Column(name="perowner")
	private int perOwner;
	private int category;
	private boolean ucomplex;
	private boolean deakable;
	private int race;
	private boolean shutdown;
	private String terrain;
	private String chanceress;

	/**
	 * Konstruktor.
	 *
	 */
	public Building() {
		this.alternativeBilder = new HashMap<Integer,String>();
	}

	/**
	 * Gibt die ID des Gebaeudetyps zurueck.
	 * @return Die ID des Gebaeudetyps
	 */
	public int getId() {
		return this.id;
	}

	/**
	 * Gibt den Namen des Gebaeudetyps zurueck.
	 * @return Der Name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Gibt das Default-Bild des Gebaeudes zurueck.
	 * @return Das Bild
	 */
	public String getDefaultPicture()
	{
		return this.picture;
	}

	/**
	 * Gibt die Map aller rassenspezifischen alternativen
	 * Bilder des Gebaeudes zurueck. Key der Map ist die ID
	 * der Rasse.
	 * @return Die Map mit den alternativen Bildern
	 */
	public Map<Integer,String> getAlternativeBilder()
	{
		return this.alternativeBilder;
	}

	/**
	 * Gibt das Bild des Gebaeudes fuer die angegebene Rasse zurueck.
	 * @param rasse Die Rasse
	 * @return Das Bild
	 */
	public String getPictureForRace(int rasse) {
		if( this.alternativeBilder.containsKey(rasse) )
		{
			return this.alternativeBilder.get(rasse);
		}
		return this.picture;
	}

	/**
	 * Gibt die fuer das Gebaeude anfallenden Baukosten zurueck.
	 * @return Die Baukosten
	 */
	public Cargo getBuildCosts() {
		return new UnmodifiableCargo(buildCosts);
	}

	/**
	 * Gibt die Ressourcen zurueck die zufaellig gespawnt werden.
	 * @return die Zufalls-Ressourcen
	 */
	public String getChanceRess() {
		if( this.chanceress == null || this.chanceress.equals("null"))
		{
			return "";
		}
		return chanceress;
	}

	/**
	 * Gibt die Produktion des Gebaeudes pro Tick zurueck.
	 * @return Die Produktion
	 */
	public Cargo getProduces() {
		return new UnmodifiableCargo(produces);
	}

	/**
	 * Gibt die Produktion des Gebaeudes pro Tick zurueck. (Extra Funktion fuer Buddelstaetten).
	 * @return Die Produktion
	 */
	public Cargo getAllProduces() {
		return getProduces();
	}

	/**
	 * Gibt den Verbrauch des Gebaeudes pro Tick zurueck.
	 * @return Der Verbrauch
	 */
	public Cargo getConsumes() {
		return new UnmodifiableCargo(consumes);
	}

	/**
	 * Gibt die Anzahl Wohnraum zurueck, die das Gebaeude schafft.
	 * @return Der Wohnraum
	 */
	public int getBewohner() {
		return bewohner;
	}

	/**
	 * Gibt die Anzahl der Arbeiter zurueck, die das Gebaeude fuer den Betrieb benoetigt.
	 * @return Die Anzahl der Arbeiter
	 */
	public int getArbeiter() {
		return arbeiter;
	}

	/**
	 * Gibt den Energieverbrauch des Gebaeudes pro Tick zurueck.
	 * @return Der Energieverbrauch
	 */
	public int getEVerbrauch() {
		return eVerbrauch;
	}

	/**
	 * Gibt die Energieproduktion des Gebaeudes pro Tick zurueck.
	 * @return die Energieproduktion
	 */
	public int getEProduktion() {
		return eProduktion;
	}

	/**
	 * Gibt die ID der zum Bau benoetigten Forschung zurueck.
	 * @return die benoetigte Forschung
	 */
	public int getTechRequired() {
		return techReq;
	}

	/**
	 * Gibt zureuck, ob das Gebaeude bei nicht vorhandenen Voraussetzungen abschaltet.
	 * @return <code>true</code>, wenn das Gebaeude abschaltet
	 */
	public boolean isShutDown() {
		return shutdown;
	}

	/**
	 * Setzt, ob das Gebaeude bei nicht vorhandenen Voraussetzungen abschaltet.
	 * @param shutdown <code>true</code> wenn das Gebaeude abschalten soll
	 */
	public void setShutDown(boolean shutdown) {
		this.shutdown = shutdown;
	}

	/**
	 * Unbekannt (?????) - Wird aber auch nicht verwendet.
	 * @return ????
	 */
	public int getEPS() {
		return eps;
	}

	/**
	 * Gibt die maximale Anzahl des Gebaeudes pro Basis zurueck.
	 * Sollte es keine Beschraenkung geben, so wird 0 zurueckgegeben.
	 * @return Die max. Anzahl pro Basis
	 */
	public int getPerPlanetCount() {
		return perPlanet;
	}

	/**
	 * Gibt die maximale Anzahl des Gebaeudes pro Benutzer zurueck.
	 * Sollte es keine Beschraenkung geben, so wird 0 zurueckgegeben.
	 * @return Die max. Anzahl pro Benutzer
	 */
	public int getPerUserCount() {
		return perOwner;
	}

	/**
	 * Gibt die ID der Kategorie des Gebaeudes zurueck.
	 * @return die ID der Kategorie
	 */
	public int getCategory() {
		return category;
	}

	/**
	 * Gibt <code>true</code> zurueck, falls das Gebaeude ein unterirdischer Komplex ist.
	 * @return <code>true</code>, falls es ein unterirdischer Komplex ist
	 */
	public boolean isUComplex() {
		return ucomplex;
	}

	/**
	 * Gibt <code>true</code> zurueck, falls das Gebaeude eine Fabrik ist.
	 * @return <code>true</code>, falls es sich um eine Fabrik handelt
	 */
	public boolean isFabrik() {
		return (this instanceof Fabrik);
	}

	/**
	 * Gibt <code>true</code> zurueck, falls das Gebaeude deaktivierbar ist.
	 * @return <code>true</code>, falls das Gebaeude deaktivierbar ist
	 */
	public boolean isDeakAble() {
		return deakable;
	}

	/**
	 * Gibt die ID der Spezieszugehoerigkeit des Gebaeudes zurueck.
	 * @return die ID der Spezies
	 */
	public int getRace() {
		return race;
	}

	/**
	 * Setzt die Id.
	 *
	 * @param id id
	 */
	public void setId(int id)
	{
		this.id = id;
	}

	/**
	 * Setzt den Wohnraum.
	 *
	 * @param bewohner Wohnraum
	 */
	public void setBewohner(int bewohner)
	{
		this.bewohner = bewohner;
	}

	/**
	 * Setzt die Arbeiter.
	 *
	 * @param arbeiter Arbeiter
	 */
	public void setArbeiter(int arbeiter)
	{
		this.arbeiter = arbeiter;
	}

	/**
	 * Setzt den Namen.
	 *
	 * @param name Name
	 */
	public void setName(String name)
	{
		this.name = name;
	}

	/**
	 * Setzt den Pfad zum Bild.
	 *
	 * @param picture Bildpfad
	 */
	public void setDefaultPicture(String picture)
	{
		this.picture = picture;
	}

	/**
	 * Setzt die Baukosten..
	 *
	 * @param buildCosts Baukosten
	 */
	public void setBuildCosts(Cargo buildCosts)
	{
		this.buildCosts = buildCosts;
	}

	/**
	 * Setzt die zufaellig gespawnten Ressourcen.
	 * @param chanceress Zufalls-Ressourcen
	 */
	public void setChanceRess(String chanceress)
	{
		this.chanceress = chanceress;
	}

	/**
	 * Setzt die Produktion.
	 *
	 * @param produces Produktion
	 */
	public void setProduces(Cargo produces)
	{
		this.produces = produces;
	}

	/**
	 * Setzt den Verbrauch.
	 *
	 * @param consumes Verbrauch
	 */
	public void setConsumes(Cargo consumes)
	{
		this.consumes = consumes;
	}

	/**
	 * Setzt den Energieverbrauch.
	 *
	 * @param verbrauch Energieverbrauch
	 */
	public void setEVerbrauch(int verbrauch)
	{
		eVerbrauch = verbrauch;
	}

	/**
	 * Setzt die Energieproduktion.
	 *
	 * @param produktion Energieproduktion
	 */
	public void setEProduktion(int produktion)
	{
		eProduktion = produktion;
	}

	/**
	 * Setzt die notwendige Forschung.
	 *
	 * @param techReq Forschung
	 */
	public void setTechReq(int techReq)
	{
		this.techReq = techReq;
	}

	/**
	 * Setzt den Energiespeicher.
	 *
	 * @param eps Energiespeicher
	 */
	public void setEps(int eps)
	{
		this.eps = eps;
	}

	/**
	 * Setzt die max. Anzahl pro Planet.
	 *
	 * @param perPlanet max. Anzahl pro Planet
	 */
	public void setPerPlanet(int perPlanet)
	{
		this.perPlanet = perPlanet;
	}

	/**
	 * Setzt die max. Anzahl pro Spieler
	 *
	 * @param perOwner max. Anzahl pro Spieler
	 */
	public void setPerOwner(int perOwner)
	{
		this.perOwner = perOwner;
	}

	/**
	 * Setzt die Kategorie.
	 *
	 * @param category Kategorie
	 */
	public void setCategory(int category)
	{
		this.category = category;
	}

	/**
	 * Setzt, ob das Gebaeude ein unterirdischer Komplex ist.
	 *
	 * @param ucomplex 0 nein, 1 ja
	 */
	public void setUcomplex(boolean ucomplex)
	{
		this.ucomplex = ucomplex;
	}

	/**
	 * Setzt, ob das Gebaeude abgeschaltet werden kann.
	 *
	 * @param deakable Abschaltbar
	 */
	public void setDeakable(boolean deakable)
	{
		this.deakable = deakable;
	}

	/**
	 * Setzt die Spezieszugehoerigkeit.
	 *
	 * @param race Spezies
	 */
	public void setRace(int race)
	{
		this.race = race;
	}

	/**
	 * Gibt die Terrainarten zurueck auf denen das Gebauede baubar ist. (Leer oder NULL == auf allen baubar).
	 * @return die Terrainarten auf denen das Gebaeude baubar ist
	 */
	public String getTerrain()
	{
		if( this.terrain == null || this.terrain.equals("null"))
		{
			return "";
		}
		return this.terrain;
	}

	/**
	 * Setzt die Terrainarten auf denen das Gebaeude baubar ist.
	 * @param terrain die Terrainarten auf denen das Gebaeude baubar ist
	 */
	public void setTerrain(String terrain)
	{
		this.terrain = terrain;
	}

	/**
	 * Prueft, ob das Gebaeude auf dem Terrain baubar ist.
	 * @param type der Terraintyp auf den geprueft werden soll
	 * @return <code>true</code>, wenn das Gebaeude auf diesem Terrain gebaut werden darf
	 */
	public boolean hasTerrain(int type)
	{
		if(this.terrain == null || this.terrain.equals(""))
		{
			return true;
		}
		int[] terrains = Common.explodeToInt(";", this.terrain);

		for(int i = 0; i < terrains.length; i++)
		{
			if(terrains[i] == type)
			{
				return true;
			}
		}

		return false;
	}

	/**
	 * Wird aufgerufen, wenn das Gebaeude auf einer Basis gebaut wurde.
	 * @param base Die Basis
	 * @param buildingid Die ID des Gebaeudes auf dem Feld
	 */
	public abstract void build( Base base, int buildingid );

	/**
	 * Wird aufgerufen, wenn das Gebaeude auf einer Basis abgerissen wurde.
	 * @param context Der aktive Kontext
	 * @param base Die Basis
	 * @param buildingid Die Id des Gebaeudes an der Stelle
	 */
	public abstract void cleanup( Context context, Base base, int buildingid );

	/**
	 * Modifiziert das stats-objekt der Basis um die Stats dieses Gebaeudes.
	 * @param base Die Basis
	 * @param stats Ein Cargo-Objekt mit den aktuellen Stats
	 * @param building Die Id des Gebaeudes an dieser Stelle
	 * @return Warnungen fuer den Benutzer/Fuers Log
	 */
	public abstract String modifyStats( Base base, Cargo stats, int building );

	/**
	 * Modifiziert das stats-objekt der Basis um die Produktion dieses Gebaeudes.
	 * @param base Die Basis
	 * @param stats Ein Cargo-Objekt mit den aktuellen Stats
	 * @param building Die Id des Gebaeudes an dieser Stelle
	 * @return Warnungen fuer den Benutzer/Fuers Log
	 */
	public abstract String modifyProductionStats( Base base, Cargo stats, int building );

	/**
	 * Modifiziert das stats-objekt der Basis um den Verbrauch dieses Gebaeudes.
	 * @param base Die Basis
	 * @param stats Ein Cargo-Objekt mit den aktuellen Stats
	 * @param building Die Id des Gebaeudes an dieser Stelle
	 * @return Warnungen fuer den Bnutzer/Fuers Log
	 */
	public abstract String modifyConsumptionStats(Base base, Cargo stats, int building );

	/**
	 * Gibt <code>true</code> zurueck, wenn das Gebaeude aktiv ist.
	 * @param base Die Basis
	 * @param status der aktuelle Aktivierungsstatus (0 oder 1)
	 * @param field Das Feld, auf dem das Gebaeude steht
	 * @return <code>true</code>, falls das Gebaeude aktiv ist
	 */
	public abstract boolean isActive( Base base, int status, int field );

	/**
	 * Generiert einen Shortcut-Link (String) sofern notwendig. Sollte das Gebaeude keinen haben
	 * wird ein leerer String zurueckgegeben.
	 * @param context der aktive Kontext
	 * @param base Die Basis
	 * @param field Das Feld, auf dem das Gebaeude steht
	 * @param building die ID des Gebaeudetyps
	 * @return Ein HTML-String, welcher den Shortcut enthaelt
	 */
	public abstract String echoShortcut( Context context, Base base, int field, int building );

	/**
	 * Gibt <code>true</code> zurueck, wenn eine Kopfzeile ausgegeben werden soll (Enthaelt den Namen des Gebaeudes und ggf dessen Bild.
	 * Dies ist jedoch von {@link #classicDesign()} abhaengig).
	 * @return <code>true</code>, falls der Header ausgegeben werden soll
	 */
	public abstract boolean printHeader();

	/**
	 * Gibt <code>true</code> zurueck, wenn das klassische Design fuer die Gebaeudeseite verwendet werden soll.
	 * @return <code>true</code>,falls das klassische Design verwendet werden soll
	 */
	public abstract boolean classicDesign();

	/**
	 * Gibt die eigendliche UI des Gebaeudes aus.
	 * @param context Der aktive Kontext
	 * @param t Eine Instanz des zu verwendenden TemplateEngines
	 * @param base Die ID der Basis
	 * @param field Das Feld, auf dem das Gebaeude steht
	 * @param building die ID des Gebaeudetyps
	 * @return Ein HTML-String, der die Gebaeudeseite einhaelt
	 */
	public abstract String output( Context context, TemplateEngine t, Base base, int field, int building );

	/**
	 * Gibt zurueck, ob das Gebaeude die Darstellung via Javascript/Json unterstuetzt.
	 * @return <code>true</code> falls dem so ist
	 */
	public abstract boolean isSupportsJson();

	/**
	 * Gibt alle zur jeweiligen Anzeige relevanten Informationen als JSON-Objekt zurueck.
	 *
	 * @param context Der aktive Kontext
	 * @param base Die ID der Basis
	 * @param field Das Feld, auf dem das Gebaeude steht
	 * @param building die ID des Gebaeudetyps
	 * @return Ein HTML-String, der die Gebaeudeseite einhaelt
	 * @see #isSupportsJson()
	 */
	public JsonObject outputJson(Context context, Base base, int field, int building)
	{
		throw new UnsupportedOperationException();
	}
}
