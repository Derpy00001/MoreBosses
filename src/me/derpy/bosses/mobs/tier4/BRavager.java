package me.derpy.bosses.mobs.tier4;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.entity.EntityType;

import me.derpy.bosses.Morebosses;
import me.derpy.bosses.items.ItemType;
import me.derpy.bosses.mobs.blueprints.BHostile;
import me.derpy.bosses.mobs.interfaces.IMount;

public class BRavager extends BHostile implements IMount {
	private List<EntityType> mounts = new ArrayList<EntityType>();

	public BRavager() {
		// TODO Auto-generated constructor stub
		this.setExperience(13);
		this.addMount(EntityType.PILLAGER, EntityType.VINDICATOR);
		this.addSpoil(ItemType.SPOILS_TIER4.getInterface().getFinalizedItem());
	}

	@Override
	public EntityType getEntityType() {
		return EntityType.RAVAGER;
	}

	@Override
	public int getBossId() {
		return 11;
	}

	@Override
	public double getHealthMultiplier() {
		return 12.1;
	}

	@Override
	public double getSpeedMultiplier() {
		return 0.1;
	}

	@Override
	public double getArmorMultiplier() {
		return 5.62;
	}

	@Override
	public double getArmorStrengthMultiplier() {
		return 20;
	}

	@Override
	public double getKnockbackResistance() {
		return 20;
	}

	@Override
	public double getDamageMultiplier() {
		return 10.2;
	}

	@Override
	public double getAttackSpeedMultiplier() {
		return 1;
	}

	@Override
	public double getFollowRange() {
		return 5.2;
	}

	@Override
	public int getMinions() {
		return 0;
	}

	@Override
	public double getSpawnChance() {
		return Morebosses.getConfigurationHandler().openBossConfiguration("Tier4\\Ravager.yml")
				.getDouble("Ravager.rate");

	}

	@Override
	public List<EntityType> getMounts() {
		// TODO Auto-generated method stub
		return this.mounts;
	}

	@Override
	public void addMount(EntityType... type) {
		// TODO Auto-generated method stub
		for (EntityType entitytype : type) {
			this.mounts.add(entitytype);
		}
	}
}
