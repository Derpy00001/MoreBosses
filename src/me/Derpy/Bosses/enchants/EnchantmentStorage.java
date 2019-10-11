package me.Derpy.Bosses.enchants;

import org.bukkit.Bukkit;
import org.bukkit.NamespacedKey;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginManager;

import me.Derpy.Bosses.MoreBosses;

public class EnchantmentStorage {
	private static PluginManager gm = Bukkit.getServer().getPluginManager();
	private static fleet fleet;
	private static ember ember;
	private static lifesteal lifesteal;
	private static undying undying;
	private static replenish replenish;
	private static mites mites;
	private static smelt smelt;
	private static Plugin plugin = MoreBosses.getPlugin(MoreBosses.class);
	public static void registerenchantsability() {
		if(plugin.getConfig().getBoolean("enabled_enchants.fleet")) {
			gm.registerEvents(fleet, MoreBosses.getPlugin(MoreBosses.class));
		}
		if(plugin.getConfig().getBoolean("enabled_enchants.ember")) {
			gm.registerEvents(ember, MoreBosses.getPlugin(MoreBosses.class));
		}
		if(plugin.getConfig().getBoolean("enabled_enchants.lifesteal")) {
			gm.registerEvents(lifesteal, MoreBosses.getPlugin(MoreBosses.class));
		}
		if(plugin.getConfig().getBoolean("enabled_enchants.undying")) {
			gm.registerEvents(undying, MoreBosses.getPlugin(MoreBosses.class));
		}
		if(plugin.getConfig().getBoolean("enabled_enchants.replenish")) {
			gm.registerEvents(replenish, MoreBosses.getPlugin(MoreBosses.class));
		}
		if(plugin.getConfig().getBoolean("enabled_enchants.mites")) {
			gm.registerEvents(mites, MoreBosses.getPlugin(MoreBosses.class));
		}
		if(plugin.getConfig().getBoolean("enabled_enchants.smelt")) {
			gm.registerEvents(smelt, MoreBosses.getPlugin(MoreBosses.class));
		}
	}
	public static void setfleet(NamespacedKey key) {
		EnchantmentStorage.fleet=new fleet(key);
	}
	public static Enchantment getfleet() {
		return EnchantmentStorage.fleet;
	}
	public static void setember(NamespacedKey key) {
		EnchantmentStorage.ember=new ember(key);
	}
	public static Enchantment getember() {
		return EnchantmentStorage.ember;
	}
	public static void setlifesteal(NamespacedKey key) {
		EnchantmentStorage.lifesteal=new lifesteal(key);
	}
	public static Enchantment getlifesteal() {
		return EnchantmentStorage.lifesteal;
	}
	public static void setundying(NamespacedKey key) {
		EnchantmentStorage.undying=new undying(key);
	}
	public static Enchantment getundying() {
		return EnchantmentStorage.undying;
	}
	public static void setreplenish(NamespacedKey key) {
		EnchantmentStorage.replenish=new replenish(key);
	}
	public static Enchantment getreplenish() {
		return EnchantmentStorage.replenish;
	}
	public static void setmites(NamespacedKey key) {
		EnchantmentStorage.mites=new mites(key);
	}
	public static Enchantment getmites() {
		return EnchantmentStorage.mites;
	}
	public static void setsmelt(NamespacedKey key) {
		EnchantmentStorage.smelt=new smelt(key);
	}
	public static Enchantment getsmelt() {
		return EnchantmentStorage.smelt;
	}
}
