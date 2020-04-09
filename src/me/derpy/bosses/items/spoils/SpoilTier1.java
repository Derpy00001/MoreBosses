package me.derpy.bosses.items.spoils;

import org.bukkit.Material;
import org.bukkit.NamespacedKey;

import me.derpy.bosses.items.blueprints.BSpoilbag;

public class SpoilTier1 extends BSpoilbag {
	public SpoilTier1() {
		this.addItem(Material.IRON_INGOT, 2, 16);
		this.addItem(Material.COAL, 2, 33);
		this.addItem(Material.DIAMOND, 1, 3);
		this.addItem(Material.EXPERIENCE_BOTTLE, 2, 4);
		this.addItem(Material.GOLDEN_APPLE, 1, 4);
		this.addItem(Material.EMERALD, 6, 17);
		this.addItem(Material.GOLD_INGOT, 2, 6);
		this.addItem(Material.HONEY_BOTTLE, 6, 12);
		this.addItem(Material.WITHER_SKELETON_SKULL, 1);
		this.addItemWithEnchants(Material.CHAINMAIL_CHESTPLATE, 2, false,
				this.getEnchantmentsFor(Material.CHAINMAIL_CHESTPLATE));
		this.addItemWithEnchants(Material.CHAINMAIL_LEGGINGS, 2, false,
				this.getEnchantmentsFor(Material.CHAINMAIL_LEGGINGS));
		this.addItemWithEnchants(Material.CHAINMAIL_HELMET, 2, false,
				this.getEnchantmentsFor(Material.CHAINMAIL_HELMET));
		this.addItemWithEnchants(Material.CHAINMAIL_BOOTS, 2, false, this.getEnchantmentsFor(Material.CHAINMAIL_BOOTS));
		this.addItemWithEnchants(Material.BOW, 2, false, this.getEnchantmentsFor(Material.BOW));
		this.addItemWithEnchants(Material.CROSSBOW, 2, false, this.getEnchantmentsFor(Material.CROSSBOW));
		this.addItemWithEnchants(Material.IRON_PICKAXE, 2, false, this.getEnchantmentsFor(Material.IRON_PICKAXE));
		this.addItemWithEnchants(Material.IRON_AXE, 2, false, this.getEnchantmentsFor(Material.IRON_AXE));
		this.addItemWithEnchants(Material.IRON_SHOVEL, 2, false, this.getEnchantmentsFor(Material.IRON_SHOVEL));
		this.addItemWithEnchants(Material.IRON_SWORD, 2, false, this.getEnchantmentsFor(Material.IRON_SWORD));
	}

	@Override
	public NamespacedKey getNamespacedKey() {
		return NamespacedKey.minecraft("spoilbag-tier1");
	}

	@Override
	public int getDropCount() {
		return 4;
	}

	@Override
	public int getTagId() {
		return 1;
	}
}