package me.derpy.bosses.mobs.tier2;

import org.bukkit.entity.EntityType;

import me.derpy.bosses.items.spoils.SpoilTier2;
import me.derpy.bosses.mobs.blueprints.BHostile;
import me.derpy.bosses.mobs.interfaces.IAbility;

public class BCreeper extends BHostile implements IAbility {
	public BCreeper() {
		this.setExperience(17);
		this.addSpoil(new SpoilTier2());
	}

	@Override
	public EntityType getEntityType() {
		// TODO Auto-generated method stub
		return EntityType.CREEPER;
	}

	@Override
	public int getBossId() {
		// TODO Auto-generated method stub
		return 4;
	}

	@Override
	public double getHealthMultiplier() {
		return 1.2;
	}

	@Override
	public double getSpeedMultiplier() {
		return 1.85;
	}

	@Override
	public double getArmorMultiplier() {
		return 1.1;
	}

	@Override
	public double getArmorStrengthMultiplier() {
		return 1.1;
	}

	@Override
	public double getKnockbackResistance() {
		return 1;
	}

	@Override
	public double getDamageMultiplier() {
		return 1;
	}

	@Override
	public double getAttackSpeedMultiplier() {
		return 1;
	}

	@Override
	public double getFollowRange() {
		return 4.5;
	}
}
