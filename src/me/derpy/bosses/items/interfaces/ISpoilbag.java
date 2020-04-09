package me.derpy.bosses.items.interfaces;

import java.util.List;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;

public interface ISpoilbag extends ILootable {
	List<Object> getDrops();

	int getTagId();

	int getDropCount();

	void editSpoil(Material material, int newMax, int newMin);

	void incrementSpoil(Material material, int amountMax, int amountMin);

	void addItem(ItemStack item);

	void addItem(Material material, int count);

	void addItem(Material material, int minAmount, int maxAmount);

	void addItemWithEnchants(Material material, int limit, boolean forcedEnchant, Enchantment[] enchantments);

	void addItem(ItemStack item, boolean guaranteed, int minAmount, int maxAmount);
}
