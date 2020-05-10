package me.derpy.bosses.mobs.tier2;

import java.util.Arrays;
import java.util.List;

import org.bukkit.Material;
import org.bukkit.entity.EntityType;

import me.derpy.bosses.Morebosses;
import me.derpy.bosses.items.ItemType;
import me.derpy.bosses.mobs.blueprints.BEquipable;
import me.derpy.bosses.mobs.interfaces.IAbility;

public class BStray extends BEquipable implements IAbility {
	private final double RATE = Morebosses.getConfigurationHandler().openBossConfiguration("Tier2\\Stray.yml") != null
			? Morebosses.getConfigurationHandler().openBossConfiguration("Tier2\\Stray.yml").getDouble("Stray.rate")
			: 0.01;

	public BStray() {
		this.setExperience(17);
		this.addSpoil(ItemType.SPOILS_TIER2.getInterface());
	}

	@Override
	public EntityType getEntityType() {
		// TODO Auto-generated method stub
		return EntityType.STRAY;
	}

	@Override
	public int getBossId() {
		// TODO Auto-generated method stub
		return 5;
	}

	@Override
	public double getHealthMultiplier() {
		return 5.2;
	}

	@Override
	public double getSpeedMultiplier() {
		return 1.95;
	}

	@Override
	public double getArmorMultiplier() {
		return 3.1;
	}

	@Override
	public double getArmorStrengthMultiplier() {
		return 2.1;
	}

	@Override
	public double getKnockbackResistance() {
		return 1;
	}

	@Override
	public double getDamageMultiplier() {
		return 1.2;
	}

	@Override
	public double getAttackSpeedMultiplier() {
		return 1;
	}

	@Override
	public double getFollowRange() {
		return 7.5;
	}

	@Override
	public double getSpawnChance() {
		return this.RATE;

	}

	@Override
	public List<Material> getWeaponChoices() {
		return Arrays.asList(Material.BOW);
	}

	@Override
	public List<Material> getHelmetChoices() {
		return Arrays.asList(Material.IRON_HELMET, Material.CHAINMAIL_HELMET, Material.GOLDEN_HELMET);
	}

	@Override
	public List<Material> getChestplateChoices() {
		return Arrays.asList(Material.DIAMOND_CHESTPLATE, Material.IRON_CHESTPLATE, Material.CHAINMAIL_CHESTPLATE,
				Material.GOLDEN_CHESTPLATE);
	}

	@Override
	public List<Material> getLeggingChoices() {
		return Arrays.asList(Material.DIAMOND_LEGGINGS, Material.IRON_LEGGINGS, Material.CHAINMAIL_LEGGINGS,
				Material.GOLDEN_LEGGINGS);
	}

	@Override
	public List<Material> getBootChoices() {
		return Arrays.asList(Material.IRON_BOOTS, Material.CHAINMAIL_BOOTS, Material.GOLDEN_BOOTS);
	}
}
