package me.derpy.bosses.mobs.tier1;

import org.bukkit.entity.EntityType;

import me.derpy.bosses.Morebosses;
import me.derpy.bosses.items.ItemType;
import me.derpy.bosses.mobs.blueprints.BHostile;
import me.derpy.bosses.mobs.interfaces.IAbility;
import me.derpy.bosses.mobs.interfaces.IAvian;

public class BBee extends BHostile implements IAbility, IAvian {
	private final double RATE = Morebosses.getConfigurationHandler().openBossConfiguration("Tier1\\Bee.yml") != null
			? Morebosses.getConfigurationHandler().openBossConfiguration("Tier1\\Bee.yml").getDouble("Bee.rate")
			: 0.1;

	public BBee() {
		this.setExperience(3);
		this.addSpoil(ItemType.SPOILS_TIER1.getInterface());
	}

	@Override
	public EntityType getEntityType() {
		// TODO Auto-generated method stub
		return EntityType.BEE;
	}

	@Override
	public int getBossId() {
		// TODO Auto-generated method stub
		return 6;
	}

	@Override
	public double getFlightMultiplier() {
		// TODO Auto-generated method stub
		return 1.25;
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
		return 5.2;
	}

	@Override
	public double getArmorStrengthMultiplier() {
		return 1.5;
	}

	@Override
	public double getKnockbackResistance() {
		return 5;
	}

	@Override
	public double getDamageMultiplier() {
		return 4.3;
	}

	@Override
	public double getAttackSpeedMultiplier() {
		return 1;
	}

	@Override
	public double getFollowRange() {
		return 4;
	}

	@Override
	public int getMinions() {
		return 4;
	}

	@Override
	public double getSpawnChance() {
		return this.RATE;
	}
}
