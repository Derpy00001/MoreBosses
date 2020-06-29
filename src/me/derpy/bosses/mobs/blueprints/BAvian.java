package me.derpy.bosses.mobs.blueprints;

import org.bukkit.entity.EntityType;

import me.derpy.bosses.mobs.interfaces.IAvian;

public class BAvian extends BBoss implements IAvian {
	double flightMultiplier = 1;

	public BAvian() {

	}

	@Override
	public EntityType getEntityType() {
		return EntityType.BAT;
	}

	@Override
	public double getFlightMultiplier() {
		// TODO Auto-generated method stub
		return this.flightMultiplier;
	}

	@Override
	public void setFlightMultiplier(double d) {
		// TODO Auto-generated method stub
		this.flightMultiplier = d;
	}

}
