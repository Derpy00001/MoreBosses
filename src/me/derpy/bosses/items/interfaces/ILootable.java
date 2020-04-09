package me.derpy.bosses.items.interfaces;

import org.bukkit.ChatColor;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;

public interface ILootable {
	public ItemStack getItem();

	public NamespacedKey getNamespacedKey();

	public ItemStack getFinalizedItem();

	public ChatColor getNameColor();

	public ChatColor getLoreColor();

	public boolean hasCustomColor();
}
