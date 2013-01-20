package net.driftingsouls.ds2.server.units;

import net.driftingsouls.ds2.server.framework.Common;
import net.driftingsouls.ds2.server.framework.ContextMap;
import net.driftingsouls.ds2.server.ships.ShipUnitCargoEntry;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.List;

/**
 * Repraesentation eines ungebundenen, nicht persistierbaren UnitCargos.
 */
public class TransientUnitCargo extends UnitCargo
{
	private static final Log LOG = LogFactory.getLog(TransientUnitCargo.class);

	/**
	 * Erzeugt einen leeren Unitcargo.
	 */
	public TransientUnitCargo()
	{
	}

	/**
	 * Erzeugt einen Unitcargo mit dem gegebenen Inhalt.
	 * @param unitcargo Der Inhalt
	 */
	public TransientUnitCargo(UnitCargo unitcargo)
	{
		super(unitcargo);
	}

	/**
	 * Speichert das aktuelle UnitCargoObjekt.
	 */
	@Override
	public void save()
	{
		throw new UnsupportedOperationException("Speichern wird nicht unterstuetzt");
	}

	@Override
	protected UnitCargoEntry createUnitCargoEntry(UnitType unitid, long count)
	{
		UnitCargoEntry entry = new TransientUnitCargoEntry(unitid, count);
		return entry;
	}

	@Override
	protected UnitCargo createEmptyCargo()
	{
		return new TransientUnitCargo();
	}

	@Override
	public TransientUnitCargo clone()
	{
		return (TransientUnitCargo)super.clone();
	}
}