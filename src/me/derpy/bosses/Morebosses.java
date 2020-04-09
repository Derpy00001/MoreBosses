// MoreBosses Recode
// Derpy00001 | Derpy#5247
package me.derpy.bosses;

import java.io.IOException;

import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import me.derpy.bosses.commands.CommandArena;
import me.derpy.bosses.commands.CommandSpawn;
import me.derpy.bosses.enchantments.EnchantmentHandler;
import me.derpy.bosses.inventory.objects.Holder;
import me.derpy.bosses.listeners.Anvil;
import me.derpy.bosses.listeners.Damaged;
import me.derpy.bosses.listeners.Death;
import me.derpy.bosses.listeners.OnSpawn;
import me.derpy.bosses.listeners.Pickup;
import me.derpy.bosses.listeners.UseItem;
import me.derpy.bosses.mobs.BarHandler;
import me.derpy.bosses.mobs.MobHandler;
import me.derpy.bosses.mobs.TitleHandler;
import me.derpy.bosses.mobs.tier1.abilities.AbilityBee;
import me.derpy.bosses.mobs.tier2.abilities.AbilityBlaze;
import me.derpy.bosses.mobs.tier2.abilities.AbilityCreeper;
import me.derpy.bosses.mobs.tier2.abilities.AbilityStray;
import me.derpy.bosses.mobs.tier3.abilities.AbilitySlime;
import me.derpy.bosses.mobs.tier3.abilities.AbilityWitherSkeleton;
import me.derpy.bosses.mobs.tier4.abilities.AbilityMagma;
import me.derpy.bosses.raids.WorldHandler;
import me.derpy.bosses.utilities.ConfigurationHandler;
import me.derpy.bosses.utilities.Console;
import me.derpy.bosses.utilities.UpdateChecker;
import net.md_5.bungee.api.ChatColor;

public class Morebosses extends JavaPlugin {
	private static TitleHandler titleHandler;
	private static BarHandler barHandler;
	private static ConfigurationHandler configHandler;
	private static EnchantmentHandler enchantmentHandler;
	private static WorldHandler worldHandler;

	@Override
	public void onEnable() {
		this.saveDefaultConfig();
		configHandler = new ConfigurationHandler();
		try {
			enchantmentHandler = new EnchantmentHandler();
		} catch (NoSuchFieldException | SecurityException | IllegalArgumentException | IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		titleHandler = new TitleHandler();
		barHandler = new BarHandler();
		worldHandler = new WorldHandler();
		// Events
		PluginManager pm = Bukkit.getServer().getPluginManager();
		pm.registerEvents(new Death(), this);
		pm.registerEvents(new Damaged(), this);
		pm.registerEvents(new Anvil(), this);
		pm.registerEvents(new UseItem(), this);
		pm.registerEvents(new OnSpawn(), this);
		pm.registerEvents(new Pickup(), this);
		// Boss abilities
		pm.registerEvents(new AbilityBee(), this);
		pm.registerEvents(new AbilityBlaze(), this);
		pm.registerEvents(new AbilityCreeper(), this);
		pm.registerEvents(new AbilityStray(), this);
		pm.registerEvents(new AbilitySlime(), this);
		pm.registerEvents(new AbilityWitherSkeleton(), this);
		pm.registerEvents(new AbilityMagma(), this);
		// Commands
		new CommandSpawn();
		new CommandArena();
		// Arenas
		Console.print("MoreBosses-recode Loaded!");
		Console.print(ChatColor.RED + "Plugin DOES NOT support server reloads!");
		Console.print("Version " + this.getDescription().getVersion());
		if (this.getConfig().getBoolean("plugin.check_version")) {
			Console.print("Checking for updates...");
			try {
				if (UpdateChecker.isUpdateAvailable()) {
					Console.print("New version available!");
					Console.print("Latest Release: " + UpdateChecker.getCurrentSpigotVersion());
					Console.print(UpdateChecker.getSpigotUrl());
				} else {
					Console.print("Plugin up to date!");
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		Console.print("https://github.com/Derpy00001/MoreBosses");

	}

	@SuppressWarnings("deprecation")
	@Override
	public void onDisable() {
		getWorldHandler().unregisterAllArenas();
		getBarHandler().removeAllBars();
		if (getEnchantmentHandler().getEnchantments().size() > 0) {
			for (Enchantment enchantment : getEnchantmentHandler().getEnchantments().values()) {
				getEnchantmentHandler().unregisterEnchantment(enchantment);
				Console.print("Unregistered Enchantment: " + enchantment.getName().toUpperCase());
			}
		}
		if (getEnchantmentHandler().getCustomEnchantments().size() > 0) {
			for (Enchantment enchantment : getEnchantmentHandler().getCustomEnchantments().values()) {
				getEnchantmentHandler().unregisterEnchantment(enchantment);
				Console.print("Unregistered Enchantment: " + enchantment.getName().toUpperCase());
			}
		}
		// Clear bosses
		for (World world : Bukkit.getWorlds()) {
			for (Entity entity : world.getEntities()) {
				if (entity.hasMetadata("MoreBosses-BossId")) {
					entity.remove();
				}
			}
		}
		// Close illegal inventories
		if (Bukkit.getOnlinePlayers().size() > 0) {
			for (Player player : Bukkit.getOnlinePlayers()) {
				if (player.getOpenInventory() != null) {
					if (player.getOpenInventory().getTopInventory().getHolder() instanceof Holder) {
						player.closeInventory();
					}
				}
			}
		}
	}

	public static EnchantmentHandler getEnchantmentHandler() {
		return enchantmentHandler;
	}

	public static MobHandler getMobHandler() {
		return new MobHandler();
	}

	public static ConfigurationHandler getConfigurationHandler() {
		return configHandler;
	}

	public static TitleHandler getTitleHandler() {
		return titleHandler;
	}

	public static BarHandler getBarHandler() {
		return barHandler;
	}

	public static WorldHandler getWorldHandler() {
		return worldHandler;
	}
}
