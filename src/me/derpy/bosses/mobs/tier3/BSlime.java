package me.derpy.bosses.mobs.tier3;

import org.bukkit.entity.EntityType;

import me.derpy.bosses.Morebosses;
import me.derpy.bosses.items.ItemType;
import me.derpy.bosses.mobs.blueprints.BHostile;
import me.derpy.bosses.mobs.interfaces.ISizeable;
import me.derpy.bosses.utilities.Random;

public class BSlime extends BHostile implements ISizeable {
	private final double RATE = Morebosses.getConfigurationHandler().openBossConfiguration("Tier3\\Slime.yml") != null
			? Morebosses.getConfigurationHandler().openBossConfiguration("Tier3\\Slime.yml").getDouble("Slime.rate")
			: 0.01;
	private final int SIZE = Morebosses.getConfigurationHandler().openBossConfiguration("Tier3\\Slime.yml") != null
			? Morebosses.getConfigurationHandler().openBossConfiguration("Tier3\\Slime.yml").getInt("Slime.size")
			: 8;

	public BSlime() {
		this.setExperience(Random.random(18, 27));
		this.addSpoil(ItemType.SPOILS_TIER3.getInterface());
	}

	@Override
	public EntityType getEntityType() {
		return EntityType.SLIME;
	}

	@Override
	public int getBossId() {
		return 7;
	}

	@Override
	public int getSize() {
		// TODO Auto-generated method stub
		return this.SIZE;
	}

	@Override
	public double getSpawnChance() {
		return this.RATE;

	}

}
