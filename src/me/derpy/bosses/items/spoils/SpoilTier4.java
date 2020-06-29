package me.derpy.bosses.items.spoils;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;

public class SpoilTier4 extends SpoilTier3 {
	public SpoilTier4() {
		this.incrementSpoil(Material.DIAMOND, 6, 2);
		this.incrementSpoil(Material.EXPERIENCE_BOTTLE, 5, 5);
		this.incrementSpoil(Material.GOLDEN_APPLE, 2, 2);
		this.incrementSpoil(Material.EMERALD, 4, 4);
		this.incrementSpoil(Material.GOLD_INGOT, 5, 5);
		this.incrementSpoil(Material.REDSTONE, 0, 22);
		this.incrementSpoil(Material.ENCHANTED_GOLDEN_APPLE, 1, 0);
		this.addItemWithEnchants(Material.DIAMOND_HELMET, 5, false, this.getEnchantmentsFor(Material.DIAMOND_HELMET));
		this.addItemWithEnchants(Material.DIAMOND_CHESTPLATE, 5, false,
				this.getEnchantmentsFor(Material.DIAMOND_CHESTPLATE));
		this.addItemWithEnchants(Material.DIAMOND_LEGGINGS, 5, false,
				this.getEnchantmentsFor(Material.DIAMOND_LEGGINGS));
		this.addItemWithEnchants(Material.DIAMOND_BOOTS, 5, false, this.getEnchantmentsFor(Material.DIAMOND_BOOTS));
		this.addItemWithEnchants(Material.ENCHANTED_BOOK, 5, true, this.getEnchantmentsFor(Material.ENCHANTED_BOOK));
	}

	@Override
	public NamespacedKey getNamespacedKey() {
		return NamespacedKey.minecraft("spoilbag-tier4");
	}

	@Override
	public int getDropCount() {
		return 9;
	}

	@Override
	public ChatColor getNameColor() {
		return ChatColor.GOLD;
	}

	@Override
	public int getTagId() {
		return 4;
	}
}
