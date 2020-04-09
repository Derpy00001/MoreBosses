package me.derpy.bosses.mobs.tier1;

import java.util.Arrays;
import java.util.List;

import org.bukkit.Material;
import org.bukkit.entity.EntityType;

import me.derpy.bosses.Morebosses;
import me.derpy.bosses.items.ItemType;
import me.derpy.bosses.mobs.blueprints.BEquipable;

public class BSkeleton extends BEquipable {

	public BSkeleton() {
		// TODO Auto-generated constructor stub
		this.setExperience(13);
		this.addSpoil(ItemType.SPOILS_TIER1.getInterface());
	}

	@Override
	public EntityType getEntityType() {
		return EntityType.SKELETON;
	}

	@Override
	public int getBossId() {
		return 2;
	}

	@Override
	public double getHealthMultiplier() {
		return 3;
	}

	@Override
	public double getSpeedMultiplier() {
		return 1.7;
	}

	@Override
	public double getArmorMultiplier() {
		return 2;
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
		return 3.5;
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
	public double getSpawnChance() {
		return Morebosses.getConfigurationHandler().openBossConfiguration("Tier1\\Skeleton.yml")
				.getDouble("Skeleton.rate");

	}

	@Override
	public List<Material> getWeaponChoices() {
		return Arrays.asList(Material.STONE_SWORD, Material.STONE_AXE, Material.IRON_SWORD, Material.IRON_AXE,
				Material.BOW);
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
