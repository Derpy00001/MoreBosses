package me.derpy.bosses.items.interfaces;

import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;

public interface ILootable {
	public ItemStack getItem();

	public NamespacedKey getNamespacedKey();

	public ItemStack getFinalizedItem();
}
