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
package net.driftingsouls.ds2.server.modules.ks;

import java.util.List;

import net.driftingsouls.ds2.server.ContextCommon;
import net.driftingsouls.ds2.server.battles.Battle;
import net.driftingsouls.ds2.server.battles.BattleShip;
import net.driftingsouls.ds2.server.cargo.Cargo;
import net.driftingsouls.ds2.server.cargo.Resources;
import net.driftingsouls.ds2.server.framework.Common;
import net.driftingsouls.ds2.server.framework.Context;
import net.driftingsouls.ds2.server.framework.ContextMap;
import net.driftingsouls.ds2.server.ships.ShipTypeData;

/**
 * Entlaedt alle Batterien auf Schiffen der eigenen Seite
 * @author Christopher Jung
 *
 */
public class KSDischargeBatteriesAllAction extends BasicKSAction {
	/**
	 * Konstruktor
	 *
	 */
	public KSDischargeBatteriesAllAction() {
	}
	
	/**
	 * Prueft, ob das Schiff seine Battieren entladen soll oder nicht
	 * @param ship Das Schiff
	 * @param shiptype Der Schiffstyp
	 * @return <code>true</code>, wenn das Schiff seine Batterien entladen soll
	 */
	protected boolean validateShipExt( BattleShip ship, ShipTypeData shiptype ) {
		// Extension Point
		return true;
	}

	@Override
	public final int execute(Battle battle) {
		int result = super.execute(battle);
		if( result != RESULT_OK ) {
			return result;
		}
		
		Context context = ContextMap.getContext();

		int shipcount = 0;
		StringBuilder ebattslog = new StringBuilder();
		
		List<BattleShip> ownShips = battle.getOwnShips();
		for( int i=0; i < ownShips.size(); i++ ) {
			BattleShip aship = ownShips.get(i);
			
			ShipTypeData ownShipType = aship.getTypeData();
			
			if( aship.getShip().getEnergy() >= ownShipType.getEps() ) {
				continue;
			}
			
			if( !validateShipExt(aship, ownShipType) ) {
				continue;
			}
			
			Cargo mycargo = aship.getCargo();
			if( !mycargo.hasResource( Resources.BATTERIEN ) ) {
				continue;
			}

			long batterien = mycargo.getResourceCount( Resources.BATTERIEN );

			if( batterien > ownShipType.getEps()-aship.getShip().getEnergy() ) {
				batterien = ownShipType.getEps()-aship.getShip().getEnergy();
			}	

			aship.getShip().setEnergy((int)(aship.getShip().getEnergy()+batterien));
			aship.getShip().setBattleAction(true);
		
			mycargo.substractResource( Resources.BATTERIEN, batterien );
			mycargo.addResource( Resources.LBATTERIEN, batterien );
			
			aship.getShip().setCargo(mycargo);
			
			battle.logme( aship.getName()+": "+batterien+" Reservebatterien entladen\n" );
			ebattslog.append(Battle.log_shiplink(aship.getShip())+": Reservebatterien entladen\n");
			
			aship.getShip().recalculateShipStatus();
			shipcount++;
		}

		if( shipcount > 0 ) {	
			battle.logenemy("<action side=\""+battle.getOwnSide()+"\" time=\""+Common.time()+"\" tick=\""+context.get(ContextCommon.class).getTick()+"\"><![CDATA[\n");
			battle.logenemy(ebattslog.toString());
			battle.logenemy("]]></action>\n");
			
			battle.resetInactivity();
		}

		return RESULT_OK;
	}
}
