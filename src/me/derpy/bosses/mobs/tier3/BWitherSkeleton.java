package me.derpy.bosses.mobs.tier3;

import java.util.Arrays;
import java.util.List;

import org.bukkit.Material;
import org.bukkit.entity.EntityType;

import me.derpy.bosses.items.spoils.SpoilTier2;
import me.derpy.bosses.mobs.blueprints.BEquipable;
import me.derpy.bosses.mobs.interfaces.IAbility;
import me.derpy.bosses.utilities.Random;

public class BWitherSkeleton extends BEquipable implements IAbility {
	public BWitherSkeleton() {
		this.setExperience(Random.random(18, 27));
		this.addSpoil(new SpoilTier2());
	}

	@Override
	public EntityType getEntityType() {
		// TODO Auto-generated method stub
		return EntityType.WITHER_SKELETON;
	}

	@Override
	public int getBossId() {
		// TODO Auto-generated method stub
		return 8;
	}

	@Override
	public double getHealthMultiplier() {
		return 5.2;
	}

	@Override
	public double getSpeedMultiplier() {
		return 0.75;
	}

	@Override
	public double getArmorMultiplier() {
		return 7.5;
	}

	@Override
	public double getArmorStrengthMultiplier() {
		return 7.5;
	}

	@Override
	public double getKnockbackResistance() {
		return 7;
	}

	@Override
	public double getDamageMultiplier() {
		return 3.8;
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
	public List<Material> getWeaponChoices() {
		return Arrays.asList(Material.IRON_SWORD, Material.DIAMOND_SWORD, Material.DIAMOND_AXE, Material.IRON_AXE);
	}

	@Override
	public List<Material> getHelmetChoices() {
		return Arrays.asList(Material.DIAMOND_HELMET, Material.IRON_HELMET, Material.CHAINMAIL_HELMET,
				Material.GOLDEN_HELMET);
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
		return Arrays.asList(Material.DIAMOND_AXE, Material.IRON_BOOTS, Material.CHAINMAIL_BOOTS,
				Material.GOLDEN_BOOTS);
	}
}
