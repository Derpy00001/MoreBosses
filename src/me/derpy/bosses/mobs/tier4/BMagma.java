package me.derpy.bosses.mobs.tier4;

import org.bukkit.entity.EntityType;

import me.derpy.bosses.Morebosses;
import me.derpy.bosses.items.enchantments.EmberBook;
import me.derpy.bosses.mobs.blueprints.BHostile;
import me.derpy.bosses.mobs.interfaces.ISizeable;
import me.derpy.bosses.utilities.Random;

public class BMagma extends BHostile implements ISizeable {
	private final int SIZE = Morebosses.getConfigurationHandler().openBossConfiguration("Tier4\\MagmaCube.yml")
			.getInt("Magma.size");

	public BMagma() {
		this.setExperience(Random.random(27, 35));
		// this.addSpoil();
		this.addSpoil(new EmberBook());

	}

	@Override
	public EntityType getEntityType() {
		return EntityType.MAGMA_CUBE;
	}

	@Override
	public int getBossId() {
		return 10;
	}

	@Override
	public int getSize() {
		// TODO Auto-generated method stub
		return this.SIZE;
	}

}
