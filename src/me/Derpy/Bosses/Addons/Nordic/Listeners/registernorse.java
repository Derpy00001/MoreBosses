package me.Derpy.Bosses.Addons.Nordic.Listeners;

import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;

import me.Derpy.Bosses.MoreBosses;
import me.Derpy.Bosses.Addons.Nordic.Items.spirit;
import me.Derpy.Bosses.Addons.Nordic.Items.wolfram;
import me.Derpy.Bosses.Addons.Utilities.AddonEnchantStorage;
import me.Derpy.Bosses.enchants.LoadEnchants;

public class registernorse {
	public static void listeners(MoreBosses plugin) {
		PluginManager gm= Bukkit.getServer().getPluginManager();
		gm.registerEvents(new spawning(), plugin);
		gm.registerEvents(new Interact(), plugin);
		gm.registerEvents(new worldevents(), plugin);
		gm.registerEvents(new oncombust(), plugin);
	}
	public static void recipes() {
		wolfram.recipes.refined();
		wolfram.recipes.ingot();
		wolfram.recipes.wolfram_boots();
		wolfram.recipes.wolfram_leggings();
		wolfram.recipes.wolfram_chestplate();
		wolfram.recipes.wolfram_helmet();
		wolfram.recipes.wolfram_pick_mattock();
		spirit.recipes.core_charged_wolf_recipe();
	}
	public static void enchants() {
		AddonEnchantStorage.nordic.registerenchantsability();
		LoadEnchants.registerEnchantment(AddonEnchantStorage.nordic.getbleed());
	}
}
