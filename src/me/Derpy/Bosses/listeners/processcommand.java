package me.Derpy.Bosses.listeners;


import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.event.server.ServerCommandEvent;

import me.Derpy.Bosses.MoreBosses;
import me.Derpy.Bosses.enchants.EnchantmentStorage;
import me.Derpy.Bosses.enchants.LoadEnchants;

public class processcommand implements Listener{
	private MoreBosses plugin;
	public processcommand(MoreBosses main) {
		// TODO Auto-generated constructor stub
		this.plugin = main;
	}
	@EventHandler
	private void processcommands(PlayerCommandPreprocessEvent event) {
		if(event.getMessage().toLowerCase().contains("/reload")||event.getMessage().toLowerCase().contains("/rl")) {
			if(event.getPlayer().hasPermission(plugin.getConfig().getString("reload.reload_permission"))) {
				MoreBosses.Reload=true;
				Bukkit.broadcastMessage(ChatColor.GOLD+"[MoreBosses]: "+ChatColor.YELLOW+""+ChatColor.BOLD+"Detected /reload command from player with permission: "+plugin.getConfig().getString("reload.reload_permission"));
				Bukkit.broadcastMessage(ChatColor.GOLD+"[MoreBosses]: "+ChatColor.YELLOW+""+ChatColor.BOLD+"Reloading plugin before full reload and plugin disable!");
				me.Derpy.Bosses.utilities.BossbarStorage.removeall();
				LoadEnchants.UnloadEnchantment(EnchantmentStorage.getfleet());
				LoadEnchants.UnloadEnchantment(EnchantmentStorage.getember());
				LoadEnchants.UnloadEnchantment(EnchantmentStorage.getlifesteal());
				LoadEnchants.UnloadEnchantment(EnchantmentStorage.getundying());
				LoadEnchants.UnloadEnchantment(EnchantmentStorage.getreplenish());
				LoadEnchants.UnloadEnchantment(EnchantmentStorage.getmites());
				LoadEnchants.UnloadEnchantment(EnchantmentStorage.getsmelt());
				for(World world : Bukkit.getWorlds()) {
					if(world.getName().startsWith("MoreBosses")) {
						for(Player p : world.getPlayers()) {
							p.teleport(Bukkit.getWorlds().get(0).getSpawnLocation());
						}
						Bukkit.getConsoleSender().sendMessage(ChatColor.GOLD+"[MoreBosses]: Unloading "+world.getName());
						Bukkit.unloadWorld(world, false);
					}
				}
				plugin.getConfig().set("raids.gladiator.active", false);
				plugin.getConfig().set("ghastevent.active", false);
				plugin.saveConfig();
			}
		}
	}
	@EventHandler
	private void consoleprocesscommand(ServerCommandEvent event) {
		if(event.getCommand().equals("reload")||event.getCommand().equals("rl")) {
			MoreBosses.Reload=true;
			Bukkit.broadcastMessage(ChatColor.GOLD+"[MoreBosses]: "+ChatColor.YELLOW+""+ChatColor.BOLD+"Detected /reload command from console!");
			Bukkit.broadcastMessage(ChatColor.GOLD+"[MoreBosses]: "+ChatColor.YELLOW+""+ChatColor.BOLD+"Reloading plugin before full reload and plugin disable!");
			me.Derpy.Bosses.utilities.BossbarStorage.removeall();
			LoadEnchants.UnloadEnchantment(EnchantmentStorage.getfleet());
			LoadEnchants.UnloadEnchantment(EnchantmentStorage.getember());
			LoadEnchants.UnloadEnchantment(EnchantmentStorage.getlifesteal());
			LoadEnchants.UnloadEnchantment(EnchantmentStorage.getundying());
			LoadEnchants.UnloadEnchantment(EnchantmentStorage.getreplenish());
			LoadEnchants.UnloadEnchantment(EnchantmentStorage.getmites());
			LoadEnchants.UnloadEnchantment(EnchantmentStorage.getsmelt());
			for(World world : Bukkit.getWorlds()) {
				if(world.getName().startsWith("MoreBosses")) {
					for(Player p : world.getPlayers()) {
						p.teleport(Bukkit.getWorlds().get(0).getSpawnLocation());
					}
					Bukkit.getConsoleSender().sendMessage(ChatColor.GOLD+"[MoreBosses]: Unloading "+world.getName());
					Bukkit.unloadWorld(world, false);
				}
			}
			plugin.getConfig().set("raids.gladiator.active", false);
			plugin.getConfig().set("ghastevent.active", false);
			plugin.saveConfig();
		}
	}
}
