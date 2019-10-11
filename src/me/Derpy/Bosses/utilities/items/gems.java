package me.Derpy.Bosses.utilities.items;

import java.util.ArrayList;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class gems {
	public static ItemStack levitiationget() {
		ItemStack item = new ItemStack(Material.EMERALD, 1);
		ItemMeta meta = item.getItemMeta();
		ArrayList<String> lore = new ArrayList<String>();
		lore.add(ChatColor.GREEN+"Levitation");
		meta.setLore(lore);
		meta.setDisplayName(ChatColor.RESET+""+ChatColor.DARK_RED+"Gem of Levitation");
		meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
		meta.setUnbreakable(true);
		item.setItemMeta(meta);
		item.addUnsafeEnchantment(Enchantment.PIERCING, 0);
		return item;
	}
	public static ItemStack water_breathingget() {
		ItemStack item2 = new ItemStack(Material.EMERALD, 1);
		ItemMeta meta2 = item2.getItemMeta();
		ArrayList<String> lore2 = new ArrayList<String>();
		lore2.add(ChatColor.AQUA+"Water Breathing");
		meta2.setLore(lore2);
		meta2.setDisplayName(ChatColor.RESET+""+ChatColor.DARK_RED+"Gem of Water Breathing");
		meta2.addItemFlags(ItemFlag.HIDE_ENCHANTS);
		meta2.setUnbreakable(true);
		item2.setItemMeta(meta2);
		item2.addUnsafeEnchantment(Enchantment.PIERCING, 0);
		return item2;
	}
	public static ItemStack speedget() {
		ItemStack item3 = new ItemStack(Material.EMERALD, 1);
		ItemMeta meta3 = item3.getItemMeta();
		ArrayList<String> lore3 = new ArrayList<String>();
		lore3.add(ChatColor.WHITE+"Speed");
		meta3.setLore(lore3);
		meta3.setDisplayName(ChatColor.RESET+""+ChatColor.DARK_RED+"Gem of Speed");
		meta3.addItemFlags(ItemFlag.HIDE_ENCHANTS);
		meta3.setUnbreakable(true);
		item3.setItemMeta(meta3);
		item3.addUnsafeEnchantment(Enchantment.PIERCING, 0);
		return item3;
	}
	public static ItemStack jumpget() {
		ItemStack item3 = new ItemStack(Material.EMERALD, 1);
		ItemMeta meta3 = item3.getItemMeta();
		ArrayList<String> lore3 = new ArrayList<String>();
		lore3.add(ChatColor.WHITE+"Jumpboost");
		meta3.setLore(lore3);
		meta3.setDisplayName(ChatColor.RESET+""+ChatColor.DARK_RED+"Gem of Jumpboost");
		meta3.addItemFlags(ItemFlag.HIDE_ENCHANTS);
		meta3.setUnbreakable(true);
		item3.setItemMeta(meta3);
		item3.addUnsafeEnchantment(Enchantment.PIERCING, 0);
		return item3;
	}
}
