package me.derpy.bosses.mobs.tier2;

import org.bukkit.entity.EntityType;

import me.derpy.bosses.Morebosses;
import me.derpy.bosses.items.ItemType;
import me.derpy.bosses.mobs.blueprints.BHostile;
import me.derpy.bosses.mobs.interfaces.IAbility;
import me.derpy.bosses.mobs.interfaces.IAvian;

public class BPhantom extends BHostile implements IAbility, IAvian {
	private final double RATE = Morebosses.getConfigurationHandler().openBossConfiguration("Tier2\\Phantom.yml") != null
			? Morebosses.getConfigurationHandler().openBossConfiguration("Tier2\\Phantom.yml").getDouble("Phantom.rate")
			: 0.005;
	private final int MINION_COUNT = Morebosses.getConfigurationHandler()
			.openBossConfiguration("Tier2\\Phantom.yml") != null
					? Morebosses.getConfigurationHandler().openBossConfiguration("Tier2\\Phantom.yml")
							.getInt("Phantom.minions")
					: 8;

	public BPhantom() {
		this.setExperience(5);
		this.addSpoil(ItemType.SPOILS_TIER2.getInterface());
	}

	@Override
	public EntityType getEntityType() {
		// TODO Auto-generated method stub
		return EntityType.PHANTOM;
	}

	@Override
	public int getBossId() {
		// TODO Auto-generated method stub
		return 13;
	}

	@Override
	public double getFlightMultiplier() {
		// TODO Auto-generated method stub
		return 1.65;
	}

	@Override
	public void setFlightMultiplier(double d) {
		// TODO Auto-generated method stub

	}

	@Override
	public double getHealthMultiplier() {
		return 9.2;
	}

	@Override
	public double getSpeedMultiplier() {
		return 2.5;
	}

	@Override
	public double getArmorMultiplier() {
		return 2.1;
	}

	@Override
	public double getArmorStrengthMultiplier() {
		return 2;
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
		return 4;
	}

	@Override
	public int getMinions() {
		return this.MINION_COUNT;
	}

	@Override
	public double getSpawnChance() {
		return this.RATE;

	}
}
