// MoreBosses Recode
// Derpy00001 | Derpy#5247
// Discord.gg/bQxBB89
package me.derpy.bosses;

import java.io.IOException;

import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import me.derpy.bosses.commands.CommandArena;
import me.derpy.bosses.commands.CommandSpawn;
import me.derpy.bosses.enchantments.EnchantmentHandler;
import me.derpy.bosses.inventory.Holder;
import me.derpy.bosses.listeners.Anvil;
import me.derpy.bosses.listeners.Damaged;
import me.derpy.bosses.listeners.Death;
import me.derpy.bosses.listeners.OnInventoryClose;
import me.derpy.bosses.listeners.OnJoin;
import me.derpy.bosses.listeners.OnSpawn;
import me.derpy.bosses.listeners.Pickup;
import me.derpy.bosses.listeners.UseItem;
import me.derpy.bosses.mobs.BarHandler;
import me.derpy.bosses.mobs.MobHandler;
import me.derpy.bosses.mobs.tier1.abilities.AbilityBee;
import me.derpy.bosses.mobs.tier2.abilities.AbilityBlaze;
import me.derpy.bosses.mobs.tier2.abilities.AbilityCreeper;
import me.derpy.bosses.mobs.tier2.abilities.AbilityStray;
import me.derpy.bosses.mobs.tier3.abilities.AbilitySlime;
import me.derpy.bosses.mobs.tier3.abilities.AbilityWitherSkeleton;
import me.derpy.bosses.mobs.tier4.abilities.AbilityMagma;
import me.derpy.bosses.raids.GhastRaid;
import me.derpy.bosses.raids.GladiatorRaid;
import me.derpy.bosses.raids.WorldHandler;
import me.derpy.bosses.utilities.ConfigurationHandler;
import me.derpy.bosses.utilities.Console;
import me.derpy.bosses.utilities.UpdateChecker;
import net.md_5.bungee.api.ChatColor;

public class Morebosses extends JavaPlugin {
	private static BarHandler barHandler;
	private static ConfigurationHandler configHandler;
	private static EnchantmentHandler enchantmentHandler;
	@SuppressWarnings("unused")
	private static WorldHandler worldHandler;

	@Override
	public void onEnable() {
		this.saveDefaultConfig();
		try {
			configHandler = new ConfigurationHandler();
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			enchantmentHandler = new EnchantmentHandler();
		} catch (NoSuchFieldException | SecurityException | IllegalArgumentException | IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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
		pm.registerEvents(new OnJoin(), this);
		pm.registerEvents(new OnInventoryClose(), this);

		// Raids
		pm.registerEvents(GhastRaid.getInstance(), this);
		pm.registerEvents(GladiatorRaid.getInstance(), this);

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
		Console.print("Morebosses-recode Loaded!");
		Console.print(ChatColor.RED + "Plugin DOES NOT support server reloads!");
		Console.print("Version " + this.getDescription().getVersion());
		if (this.getConfig().getBoolean("plugin.check_version")) {
			Console.print("Checking for updates...");
			try {
				if (UpdateChecker.isUpdateAvailable()) {
					Console.print("> New version available!");
					Console.print("> Latest Release: " + UpdateChecker.getCurrentSpigotVersion());
					Console.print("> " + UpdateChecker.getSpigotUrl());
				} else {
					Console.print("> Plugin up to date!");
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		Console.print("https://github.com/Derpy00001/MoreBosses");
	}

	@Override()
	public void onDisable() {
		// Clear bosses
		try {
			for (World world : Bukkit.getWorlds()) {
				for (Entity entity : world.getEntities()) {
					if (entity.hasMetadata("MoreBosses-BossId")) {
						entity.remove();
					}
				}
			}
		} catch (Exception e) {
			Console.print(ChatColor.RED + "Failed to remove bosses");
		}
		// Close illegal inventories
		try {
			if (Bukkit.getOnlinePlayers().size() > 0) {
				for (Player player : Bukkit.getOnlinePlayers()) {
					if (player.getOpenInventory() != null) {
						if (player.getOpenInventory().getTopInventory().getHolder() instanceof Holder) {
							player.closeInventory();
						}
					}
				}
			}
		} catch (Exception e) {
			Console.print(ChatColor.RED + "Failed to close inventories");
		}
		getBarHandler().removeAllBars();

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

	public static BarHandler getBarHandler() {
		return barHandler;
	}

	public static WorldHandler getWorldHandler() {
		return worldHandler;
	}
}
