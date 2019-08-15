package me.Derpy.Bosses.utilities.items;


import org.bukkit.Material;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import net.md_5.bungee.api.ChatColor;

public class soul {
	public static ItemStack get() {
		ItemStack item = new ItemStack(Material.PHANTOM_MEMBRANE);
		ItemMeta meta = item.getItemMeta();
		meta.setDisplayName(ChatColor.DARK_PURPLE+"Soul");
		meta.setUnbreakable(true);
		meta.addItemFlags(ItemFlag.HIDE_UNBREAKABLE);
		meta.addItemFlags(ItemFlag.HIDE_PLACED_ON);
		item.setItemMeta(meta);
		return item;
	}
	public static ItemStack get(Integer amt) {
		ItemStack item = new ItemStack(Material.PHANTOM_MEMBRANE, amt);
		ItemMeta meta = item.getItemMeta();
		meta.setDisplayName(ChatColor.DARK_PURPLE+"Soul");
		meta.setUnbreakable(true);
		meta.addItemFlags(ItemFlag.HIDE_UNBREAKABLE);
		meta.addItemFlags(ItemFlag.HIDE_PLACED_ON);
		item.setItemMeta(meta);
		return item;
	}
}
