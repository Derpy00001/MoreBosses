package me.Derpy.Bosses.Addons.Utilities;

import org.bukkit.Bukkit;
import org.bukkit.NamespacedKey;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.plugin.PluginManager;

import me.Derpy.Bosses.MoreBosses;
import me.Derpy.Bosses.Addons.Nordic.enchants.bleed;

public class AddonEnchantStorage {
	private static PluginManager gm = Bukkit.getServer().getPluginManager();
	public static class nordic{
		public static me.Derpy.Bosses.Addons.Nordic.enchants.bleed bleed = new bleed(new NamespacedKey(MoreBosses.getPlugin(MoreBosses.class), "bleed"));
		public static void registerenchantsability() {
			gm.registerEvents(bleed, MoreBosses.getPlugin(MoreBosses.class));
		}
		public static Enchantment getbleed() {
			return bleed;
		}
	}
}
