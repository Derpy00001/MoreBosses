package me.Derpy.Bosses.utilities.items;

import java.util.ArrayList;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.EnchantmentStorageMeta;

import me.Derpy.Bosses.Addons.Utilities.AddonEnchantStorage;
import me.Derpy.Bosses.enchants.EnchantLevel;
import me.Derpy.Bosses.enchants.EnchantmentStorage;

public class Enchants {
	public static ItemStack fleet(Integer level) {
		ItemStack item = new ItemStack(Material.ENCHANTED_BOOK);
		EnchantmentStorageMeta meta = (EnchantmentStorageMeta) item.getItemMeta();
		meta.addStoredEnchant(EnchantmentStorage.getfleet(), level, true);
		ArrayList<String> lore = new ArrayList<String>();
		lore.add(EnchantLevel.returnEnchantmentName(EnchantmentStorage.getfleet(), level, ChatColor.GRAY));
		lore.add(ChatColor.RESET+""+ChatColor.RED+"Right click an item with this on your cursor");
		lore.add(ChatColor.RESET+""+ChatColor.RED+"[Boots]");
		meta.setLore(lore);
		item.setItemMeta(meta);
		return item;
	}
	public static ItemStack ember() {
		ItemStack item = new ItemStack(Material.ENCHANTED_BOOK);
		EnchantmentStorageMeta meta = (EnchantmentStorageMeta) item.getItemMeta();
		meta.addStoredEnchant(EnchantmentStorage.getember(), 1, true);
		ArrayList<String> lore = new ArrayList<String>();
		lore.add(EnchantLevel.returnEnchantmentName(EnchantmentStorage.getember(), 1, ChatColor.GRAY));
		lore.add(ChatColor.RESET+""+ChatColor.RED+"Right click an item with this on your cursor");
		lore.add(ChatColor.RESET+""+ChatColor.RED+"[Armor]");
		meta.setLore(lore);
		item.setItemMeta(meta);
		return item;
	}
	public static ItemStack lifesteal() {
		ItemStack item = new ItemStack(Material.ENCHANTED_BOOK);
		EnchantmentStorageMeta meta = (EnchantmentStorageMeta) item.getItemMeta();
		meta.addStoredEnchant(EnchantmentStorage.getlifesteal(), 1, true);
		ArrayList<String> lore = new ArrayList<String>();
		lore.add(EnchantLevel.returnEnchantmentName(EnchantmentStorage.getlifesteal(), 1, ChatColor.GRAY));
		lore.add(ChatColor.RESET+""+ChatColor.RED+"Right click an item with this on your cursor");
		lore.add(ChatColor.RESET+""+ChatColor.RED+"[Sword]");
		meta.setLore(lore);
		item.setItemMeta(meta);
		return item;
	}
	public static ItemStack undying() {
		ItemStack item = new ItemStack(Material.ENCHANTED_BOOK);
		EnchantmentStorageMeta meta = (EnchantmentStorageMeta) item.getItemMeta();
		meta.addStoredEnchant(EnchantmentStorage.getundying(), 1, true);
		ArrayList<String> lore = new ArrayList<String>();
		lore.add(EnchantLevel.returnEnchantmentName(EnchantmentStorage.getundying(), 1, ChatColor.GRAY));
		lore.add(ChatColor.RESET+""+ChatColor.RED+"Right click an item with this on your cursor");
		lore.add(ChatColor.RESET+""+ChatColor.RED+"[Chestplate]");
		meta.setLore(lore);
		item.setItemMeta(meta);
		return item;
	}
	public static ItemStack undying(Integer level) {
		ItemStack item = new ItemStack(Material.ENCHANTED_BOOK);
		EnchantmentStorageMeta meta = (EnchantmentStorageMeta) item.getItemMeta();
		meta.addStoredEnchant(EnchantmentStorage.getundying(), level, true);
		ArrayList<String> lore = new ArrayList<String>();
		lore.add(EnchantLevel.returnEnchantmentName(EnchantmentStorage.getundying(), level, ChatColor.GRAY));
		lore.add(ChatColor.RESET+""+ChatColor.RED+"Right click an item with this on your cursor");
		lore.add(ChatColor.RESET+""+ChatColor.RED+"[Chestplate]");
		meta.setLore(lore);
		item.setItemMeta(meta);
		return item;
	}
	public static ItemStack replenish() {
		ItemStack item = new ItemStack(Material.ENCHANTED_BOOK);
		EnchantmentStorageMeta meta = (EnchantmentStorageMeta) item.getItemMeta();
		meta.addStoredEnchant(EnchantmentStorage.getreplenish(), 1, true);
		ArrayList<String> lore = new ArrayList<String>();
		lore.add(EnchantLevel.returnEnchantmentName(EnchantmentStorage.getreplenish(), 1, ChatColor.GRAY));
		lore.add(ChatColor.RESET+""+ChatColor.RED+"Right click an item with this on your cursor");
		lore.add(ChatColor.RESET+""+ChatColor.RED+"[Chestplate]");
		meta.setLore(lore);
		item.setItemMeta(meta);
		return item;
	}
	public static ItemStack mites() {
		ItemStack item = new ItemStack(Material.ENCHANTED_BOOK);
		EnchantmentStorageMeta meta = (EnchantmentStorageMeta) item.getItemMeta();
		meta.addStoredEnchant(EnchantmentStorage.getmites(), 1, true);
		ArrayList<String> lore = new ArrayList<String>();
		lore.add(EnchantLevel.returnEnchantmentName(EnchantmentStorage.getmites(), 1, ChatColor.GRAY));
		lore.add(ChatColor.RESET+""+ChatColor.RED+"Right click an item with this on your cursor");
		lore.add(ChatColor.RESET+""+ChatColor.RED+"[Armor]");
		meta.setLore(lore);
		item.setItemMeta(meta);
		return item;
	}
	public static ItemStack smelt() {
		ItemStack item = new ItemStack(Material.ENCHANTED_BOOK);
		EnchantmentStorageMeta meta = (EnchantmentStorageMeta) item.getItemMeta();
		meta.addStoredEnchant(EnchantmentStorage.getsmelt(), 1, true);
		ArrayList<String> lore = new ArrayList<String>();
		lore.add(EnchantLevel.returnEnchantmentName(EnchantmentStorage.getsmelt(), 1, ChatColor.GRAY));
		lore.add(ChatColor.RESET+""+ChatColor.RED+"Right click an item with this on your cursor");
		lore.add(ChatColor.RESET+""+ChatColor.RED+"[Tool]");
		meta.setLore(lore);
		item.setItemMeta(meta);
		return item;
	}
	public static ItemStack smelt(Integer level) {
		ItemStack item = new ItemStack(Material.ENCHANTED_BOOK);
		EnchantmentStorageMeta meta = (EnchantmentStorageMeta) item.getItemMeta();
		meta.addStoredEnchant(EnchantmentStorage.getsmelt(), level, true);
		ArrayList<String> lore = new ArrayList<String>();
		lore.add(EnchantLevel.returnEnchantmentName(EnchantmentStorage.getsmelt(), level, ChatColor.GRAY));
		lore.add(ChatColor.RESET+""+ChatColor.RED+"Right click an item with this on your cursor");
		lore.add(ChatColor.RESET+""+ChatColor.RED+"[Tool]");
		meta.setLore(lore);
		item.setItemMeta(meta);
		return item;
	}
	public static ItemStack bleed(Integer level) {
		ItemStack item = new ItemStack(Material.ENCHANTED_BOOK);
		EnchantmentStorageMeta meta = (EnchantmentStorageMeta) item.getItemMeta();
		meta.addStoredEnchant(AddonEnchantStorage.nordic.getbleed(), level, true);
		ArrayList<String> lore = new ArrayList<String>();
		lore.add(EnchantLevel.returnEnchantmentName(AddonEnchantStorage.nordic.getbleed(), level, ChatColor.GRAY));
		lore.add(ChatColor.RESET+""+ChatColor.RED+"Right click an item with this on your cursor");
		lore.add(ChatColor.RESET+""+ChatColor.RED+"[Tool]");
		meta.setLore(lore);
		item.setItemMeta(meta);
		return item;
	}
}
