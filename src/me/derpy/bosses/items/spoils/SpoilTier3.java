package me.derpy.bosses.items.spoils;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;

public class SpoilTier3 extends SpoilTier2 {
	public SpoilTier3() {
		this.incrementSpoil(Material.DIAMOND, 4, 4);
		this.incrementSpoil(Material.COAL, 10, 10);
		this.incrementSpoil(Material.EXPERIENCE_BOTTLE, 12, 6);
		this.incrementSpoil(Material.GOLDEN_APPLE, 11, 4);
		this.incrementSpoil(Material.EMERALD, 16, 4);
		this.incrementSpoil(Material.GOLD_INGOT, 10, 5);
		this.incrementSpoil(Material.REDSTONE, 22, 22);
		this.addItem(Material.ENCHANTED_GOLDEN_APPLE, 1, 3);
		this.addItem(Material.HEART_OF_THE_SEA, 1);
		this.addItem(Material.COAL_BLOCK, 3, 13);
		this.addItemWithEnchants(Material.ENCHANTED_BOOK, 2, true, this.getEnchantmentsFor(Material.ENCHANTED_BOOK));
	}

	@Override
	public NamespacedKey getNamespacedKey() {
		return NamespacedKey.minecraft("spoilbag-tier3");
	}

	@Override
	public int getDropCount() {
		return 6;
	}

	@Override
	public ChatColor getNameColor() {
		return ChatColor.BLUE;
	}

	@Override
	public int getTagId() {
		return 3;
	}
}
