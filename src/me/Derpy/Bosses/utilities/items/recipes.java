package me.Derpy.Bosses.utilities.items;

import java.util.ArrayList;

import org.bukkit.Material;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import net.md_5.bungee.api.ChatColor;

public class recipes {
	public static ItemStack cursed() {
		ItemStack item = new ItemStack(Material.BOOK);
		ItemMeta meta = item.getItemMeta();
		ArrayList<String> lore = new ArrayList<String>();
		lore.add(ChatColor.BLUE+"Cursed Diamond");
		meta.setLore(lore);
		meta.setDisplayName(ChatColor.LIGHT_PURPLE+"Recipe");
		meta.setUnbreakable(true);
		meta.addItemFlags(ItemFlag.HIDE_UNBREAKABLE);
		meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
		meta.addItemFlags(ItemFlag.HIDE_PLACED_ON);
		item.setItemMeta(meta);
		return item;
	}
	public static ItemStack ichorstick() {
		ItemStack item = new ItemStack(Material.BOOK);
		ItemMeta meta = item.getItemMeta();
		ArrayList<String> lore = new ArrayList<String>();
		lore.add(ChatColor.GOLD+"Ichor Stick");
		meta.setLore(lore);
		meta.setDisplayName(ChatColor.LIGHT_PURPLE+"Recipe");
		meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
		meta.addItemFlags(ItemFlag.HIDE_PLACED_ON);
		item.setItemMeta(meta);
		return item;
	}
	public static ItemStack ichorsword() {
		ItemStack item = new ItemStack(Material.BOOK);
		ItemMeta meta = item.getItemMeta();
		ArrayList<String> lore = new ArrayList<String>();
		lore.add(ChatColor.GOLD+"Ichor Sword");
		meta.setLore(lore);
		meta.setDisplayName(ChatColor.LIGHT_PURPLE+"Recipe");
		meta.setUnbreakable(true);
		meta.addItemFlags(ItemFlag.HIDE_UNBREAKABLE);
		meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
		meta.addItemFlags(ItemFlag.HIDE_PLACED_ON);
		item.setItemMeta(meta);
		return item;
	}
	public static ItemStack ichorboots() {
		ItemStack item = new ItemStack(Material.BOOK);
		ItemMeta meta = item.getItemMeta();
		ArrayList<String> lore = new ArrayList<String>();
		lore.add(ChatColor.GOLD+"Ichor Boots");
		meta.setLore(lore);
		meta.setDisplayName(ChatColor.LIGHT_PURPLE+"Recipe");
		meta.setUnbreakable(true);
		meta.addItemFlags(ItemFlag.HIDE_UNBREAKABLE);
		meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
		meta.addItemFlags(ItemFlag.HIDE_PLACED_ON);
		item.setItemMeta(meta);
		return item;
	}
	public static ItemStack ichorlegs() {
		ItemStack item = new ItemStack(Material.BOOK);
		ItemMeta meta = item.getItemMeta();
		ArrayList<String> lore = new ArrayList<String>();
		lore.add(ChatColor.GOLD+"Ichor Leggings");
		meta.setLore(lore);
		meta.setDisplayName(ChatColor.LIGHT_PURPLE+"Recipe");
		meta.setUnbreakable(true);
		meta.addItemFlags(ItemFlag.HIDE_UNBREAKABLE);
		meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
		meta.addItemFlags(ItemFlag.HIDE_PLACED_ON);
		item.setItemMeta(meta);
		return item;
	}
	public static ItemStack ichorchest() {
		ItemStack item = new ItemStack(Material.BOOK);
		ItemMeta meta = item.getItemMeta();
		ArrayList<String> lore = new ArrayList<String>();
		lore.add(ChatColor.GOLD+"Ichor Chestplate");
		meta.setLore(lore);
		meta.setDisplayName(ChatColor.LIGHT_PURPLE+"Recipe");
		meta.setUnbreakable(true);
		meta.addItemFlags(ItemFlag.HIDE_UNBREAKABLE);
		meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
		meta.addItemFlags(ItemFlag.HIDE_PLACED_ON);
		item.setItemMeta(meta);
		return item;
	}
	public static ItemStack ichorhelm() {
		ItemStack item = new ItemStack(Material.BOOK);
		ItemMeta meta = item.getItemMeta();
		ArrayList<String> lore = new ArrayList<String>();
		lore.add(ChatColor.GOLD+"Ichor Helmet");
		meta.setLore(lore);
		meta.setDisplayName(ChatColor.LIGHT_PURPLE+"Recipe");
		meta.setUnbreakable(true);
		meta.addItemFlags(ItemFlag.HIDE_UNBREAKABLE);
		meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
		meta.addItemFlags(ItemFlag.HIDE_PLACED_ON);
		item.setItemMeta(meta);
		return item;
	}
	public static ItemStack elytra() {
		ItemStack item = new ItemStack(Material.BOOK);
		ItemMeta meta = item.getItemMeta();
		ArrayList<String> lore = new ArrayList<String>();
		lore.add(ChatColor.DARK_PURPLE+"Elytra");
		meta.setLore(lore);
		meta.setDisplayName(ChatColor.LIGHT_PURPLE+"Recipe");
		meta.setUnbreakable(true);
		meta.addItemFlags(ItemFlag.HIDE_UNBREAKABLE);
		meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
		meta.addItemFlags(ItemFlag.HIDE_PLACED_ON);
		item.setItemMeta(meta);
		return item;
	}
}
