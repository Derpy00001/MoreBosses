package me.derpy.bosses.items.interfaces;

import org.bukkit.enchantments.Enchantment;

public interface IEnchantmentBook extends ILootable {
	public Enchantment getEnchantment();

	public int getLevel();
}
