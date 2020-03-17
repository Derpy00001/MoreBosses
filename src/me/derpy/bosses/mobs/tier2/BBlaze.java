package me.derpy.bosses.mobs.tier2;

import org.bukkit.entity.EntityType;

import me.derpy.bosses.items.spoils.SpoilTier2;
import me.derpy.bosses.mobs.blueprints.BHostile;
import me.derpy.bosses.mobs.interfaces.IAbility;
import me.derpy.bosses.mobs.interfaces.IAvian;

public class BBlaze extends BHostile implements IAbility, IAvian {
	public BBlaze() {
		this.setExperience(17);
		this.addSpoil(new SpoilTier2());
	}

	@Override
	public EntityType getEntityType() {
		// TODO Auto-generated method stub
		return EntityType.BLAZE;
	}

	@Override
	public int getBossId() {
		// TODO Auto-generated method stub
		return 3;
	}

	@Override
	public double getFlightMultiplier() {
		// TODO Auto-generated method stub
		return 1.15;
	}

	@Override
	public void setFlightMultiplier(double d) {
		// TODO Auto-generated method stub

	}

	@Override
	public double getHealthMultiplier() {
		return 4.6;
	}

	@Override
	public double getSpeedMultiplier() {
		return 1.2;
	}

	@Override
	public double getArmorMultiplier() {
		return 3.2;
	}

	@Override
	public double getArmorStrengthMultiplier() {
		return 2.5;
	}

	@Override
	public double getKnockbackResistance() {
		return 1;
	}

	@Override
	public double getDamageMultiplier() {
		return 1.4;
	}

	@Override
	public double getAttackSpeedMultiplier() {
		return 1;
	}

	@Override
	public double getFollowRange() {
		return 4;
	}
}
