package me.derpy.bosses.mobs.tier4;

import java.util.Arrays;
import java.util.List;

import org.bukkit.Material;
import org.bukkit.entity.EntityType;

import me.derpy.bosses.Morebosses;
import me.derpy.bosses.items.ItemType;
import me.derpy.bosses.mobs.blueprints.BEquipable;
import me.derpy.bosses.utilities.Random;

public class BPigman extends BEquipable {
	private final int MINION_MIN = Morebosses.getConfigurationHandler().openBossConfiguration("Tier4\\Pigman.yml")
			.getInt("Pigman.minion_min");
	private final int MINION_MAX = Morebosses.getConfigurationHandler().openBossConfiguration("Tier4\\Pigman.yml")
			.getInt("Pigman.minion_max");

	public BPigman() {
		// TODO Auto-generated constructor stub
		this.setExperience(13);
		this.addSpoil(ItemType.SPOILS_TIER4.getInterface().getFinalizedItem());
	}

	@Override
	public EntityType getEntityType() {
		return EntityType.PIG_ZOMBIE;
	}

	@Override
	public int getBossId() {
		return 9;
	}

	@Override
	public double getHealthMultiplier() {
		return 6.8;
	}

	@Override
	public double getSpeedMultiplier() {
		return 0.3;
	}

	@Override
	public double getArmorMultiplier() {
		return 4.2;
	}

	@Override
	public double getArmorStrengthMultiplier() {
		return 8;
	}

	@Override
	public double getKnockbackResistance() {
		return 7;
	}

	@Override
	public double getDamageMultiplier() {
		return 5.2;
	}

	@Override
	public double getAttackSpeedMultiplier() {
		return 1;
	}

	@Override
	public double getFollowRange() {
		return 3.2;
	}

	@Override
	public double getSpawnChance() {
		return Morebosses.getConfigurationHandler().openBossConfiguration("Tier4\\Pigman.yml").getDouble("Pigman.rate");

	}

	@Override
	public int getMinions() {
		return Random.random(this.MINION_MIN, this.MINION_MAX);
	}

	@Override
	public List<Material> getWeaponChoices() {
		return Arrays.asList(Material.DIAMOND_SWORD, Material.DIAMOND_AXE, Material.IRON_SWORD, Material.IRON_AXE);
	}

	@Override
	public List<Material> getHelmetChoices() {
		return Arrays.asList(Material.IRON_HELMET, Material.CHAINMAIL_HELMET, Material.DIAMOND_HELMET);
	}

	@Override
	public List<Material> getChestplateChoices() {
		return Arrays.asList(Material.IRON_CHESTPLATE, Material.CHAINMAIL_CHESTPLATE, Material.DIAMOND_CHESTPLATE);
	}

	@Override
	public List<Material> getLeggingChoices() {
		return Arrays.asList(Material.IRON_LEGGINGS, Material.CHAINMAIL_LEGGINGS, Material.DIAMOND_LEGGINGS);
	}

	@Override
	public List<Material> getBootChoices() {
		return Arrays.asList(Material.IRON_BOOTS, Material.CHAINMAIL_BOOTS, Material.DIAMOND_BOOTS);
	}
}
