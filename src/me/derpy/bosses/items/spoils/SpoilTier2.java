package me.derpy.bosses.items.spoils;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;

public class SpoilTier2 extends SpoilTier1 {
	public SpoilTier2() {
		this.incrementSpoil(Material.IRON_INGOT, 13, 13);
		this.incrementSpoil(Material.COAL, 22, 21);
		this.incrementSpoil(Material.DIAMOND, 4, 2);
		this.incrementSpoil(Material.EXPERIENCE_BOTTLE, 10, 4);
		this.incrementSpoil(Material.GOLDEN_APPLE, 10, 3);
		this.incrementSpoil(Material.EMERALD, 8, 6);
		this.incrementSpoil(Material.GOLD_INGOT, 21, 7);
		this.addItem(Material.TOTEM_OF_UNDYING, 1);
		this.addItem(Material.NAUTILUS_SHELL, 1, 3);
		this.addItem(Material.REDSTONE, 32, 42);
		this.addItemWithEnchants(Material.IRON_HELMET, 3, true, this.getEnchantmentsFor(Material.IRON_HELMET));
		this.addItemWithEnchants(Material.IRON_CHESTPLATE, 3, true, this.getEnchantmentsFor(Material.IRON_CHESTPLATE));
		this.addItemWithEnchants(Material.IRON_LEGGINGS, 3, true, this.getEnchantmentsFor(Material.IRON_LEGGINGS));
		this.addItemWithEnchants(Material.IRON_BOOTS, 3, true, this.getEnchantmentsFor(Material.IRON_BOOTS));
	}

	@Override
	public NamespacedKey getNamespacedKey() {
		return NamespacedKey.minecraft("spoilbag-tier2");
	}

	@Override
	public int getDropCount() {
		return 5;
	}

	@Override
	public ChatColor getNameColor() {
		return ChatColor.GREEN;
	}

	@Override
	public int getTagId() {
		return 2;
	}
}
