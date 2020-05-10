package me.derpy.bosses.mobs.tier1;

import java.util.Arrays;
import java.util.List;

import org.bukkit.Material;
import org.bukkit.entity.EntityType;

import me.derpy.bosses.Morebosses;
import me.derpy.bosses.items.ItemType;
import me.derpy.bosses.mobs.blueprints.BEquipable;

public class BDrowned extends BEquipable {
	private final double RATE = Morebosses.getConfigurationHandler().openBossConfiguration("Tier1\\Drowned.yml") != null
			? Morebosses.getConfigurationHandler().openBossConfiguration("Tier1\\Drowned.yml").getDouble("Drowned.rate")
			: 0.052;

	public BDrowned() {
		// TODO Auto-generated constructor stub
		this.setExperience(13);
		this.addSpoil(ItemType.SPOILS_TIER1.getInterface());
	}

	@Override
	public EntityType getEntityType() {
		return EntityType.DROWNED;
	}

	@Override
	public int getBossId() {
		return 1;
	}

	@Override
	public double getHealthMultiplier() {
		return 5.5;
	}

	@Override
	public double getSpeedMultiplier() {
		return 0.6;
	}

	@Override
	public double getArmorMultiplier() {
		return 4;
	}

	@Override
	public double getArmorStrengthMultiplier() {
		return 4;
	}

	@Override
	public double getKnockbackResistance() {
		return 3;
	}

	@Override
	public double getDamageMultiplier() {
		return 4.5;
	}

	@Override
	public double getAttackSpeedMultiplier() {
		return 1;
	}

	@Override
	public double getFollowRange() {
		return 6;
	}

	@Override
	public double getSpawnChance() {
		return this.RATE;

	}

	@Override
	public List<Material> getWeaponChoices() {
		return Arrays.asList(Material.TRIDENT, Material.STONE_SWORD, Material.STONE_AXE, Material.IRON_SWORD,
				Material.IRON_AXE);
	}

	@Override
	public List<Material> getHelmetChoices() {
		return Arrays.asList(Material.IRON_HELMET, Material.CHAINMAIL_HELMET, Material.GOLDEN_HELMET);
	}

	@Override
	public List<Material> getChestplateChoices() {
		return Arrays.asList(Material.IRON_CHESTPLATE, Material.CHAINMAIL_CHESTPLATE, Material.GOLDEN_CHESTPLATE);
	}

	@Override
	public List<Material> getLeggingChoices() {
		return Arrays.asList(Material.IRON_LEGGINGS, Material.CHAINMAIL_LEGGINGS, Material.GOLDEN_LEGGINGS);
	}

	@Override
	public List<Material> getBootChoices() {
		return Arrays.asList(Material.IRON_BOOTS, Material.CHAINMAIL_BOOTS, Material.GOLDEN_BOOTS);
	}
}
