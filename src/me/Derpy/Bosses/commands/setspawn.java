package me.Derpy.Bosses.commands;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

import me.Derpy.Bosses.Main;

public class setspawn implements CommandExecutor, TabCompleter{

	
	private Main plugin;
	public setspawn(Main plugin) {
		this.plugin = plugin;
		plugin.getCommand("bsetspawn").setExecutor(this);
		
	}
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (!(sender instanceof Player)) {
			sender.sendMessage("Inable to execute command as console");
			return true;
		}
		if(!(sender.hasPermission("bosses.raid.ghastspawn"))) {
			sender.sendMessage("You do not have the required permissions to perform this task");
			return true;
		}
		Player p = (Player) sender;
		if(args[0].equals("gladiator.beacon_glass")) {
			plugin.getConfig().set("raids.gladiator.specialblocks.beacon_glass", p.getLocation());
			plugin.saveConfig();
			plugin.saveDefaultConfig();
			p.sendMessage("set spawn to current location!");
		}else if(args[0].equals("gladiator.king")) {
			plugin.getConfig().set("raids.gladiator.spawns.king", p.getLocation());
			plugin.saveConfig();
			plugin.saveDefaultConfig();
			p.sendMessage("set spawn to current location!");
			return true;
		}else if(args[0].equals("gladiator.gate1")) {
			plugin.getConfig().set("raids.gladiator.spawns.gate1", p.getLocation());
			plugin.saveConfig();
			plugin.saveDefaultConfig();
			p.sendMessage("set spawn to current location!");
		}else if(args[0].equals("gladiator.gate2")) {
			plugin.getConfig().set("raids.gladiator.spawns.gate2", p.getLocation());
			plugin.saveConfig();
			plugin.saveDefaultConfig();
			p.sendMessage("set spawn to current location!");
		}else if(args[0].equals("gladiator.gate3")) {
			plugin.getConfig().set("raids.gladiator.spawns.gate3", p.getLocation());
			plugin.saveConfig();
			plugin.saveDefaultConfig();
			p.sendMessage("set spawn to current location!");
		}
		if(args[0].equals("ghast_raidboss")) {
			plugin.getConfig().set("raids.ghast_raid", p.getLocation());
			plugin.saveConfig();
			plugin.saveDefaultConfig();
			p.sendMessage("set spawn to current location!");
			return true;
		}else if(args[0].equals("ghast_raid.minion.1")) {
			plugin.getConfig().set("raids.ghast_raid_minion_spawns.1", p.getLocation());
			plugin.saveConfig();
			plugin.saveDefaultConfig();
			p.sendMessage("set spawn to current location!");
		}else if(args[0].equals("ghast_raid.minion.2")) {
			plugin.getConfig().set("raids.ghast_raid_minion_spawns.2", p.getLocation());
			plugin.saveConfig();
			plugin.saveDefaultConfig();
			p.sendMessage("set spawn to current location!");
		}else if(args[0].equals("ghast_raid.minion.3")) {
			plugin.getConfig().set("raids.ghast_raid_minion_spawns.3", p.getLocation());
			plugin.saveConfig();
			plugin.saveDefaultConfig();
			p.sendMessage("set spawn to current location!");
		}else if(args[0].equals("ghast_raid.minion.4")) {
			plugin.getConfig().set("raids.ghast_raid_minion_spawns.4", p.getLocation());
			plugin.saveConfig();
			plugin.saveDefaultConfig();
			p.sendMessage("set spawn to current location!");
		}else if(args[0].equals("ghast_raid.boss.teleport.1")) {
			plugin.getConfig().set("raids.teleports.ghast.1", p.getLocation());
			plugin.saveConfig();
			plugin.saveDefaultConfig();
			p.sendMessage("set spawn to current location!");
		}else if(args[0].equals("ghast_raid.boss.teleport.2")) {
			plugin.getConfig().set("raids.teleports.ghast.2", p.getLocation());
			plugin.saveConfig();
			plugin.saveDefaultConfig();
			p.sendMessage("set spawn to current location!");
		}else if(args[0].equals("ghast_raid.boss.teleport.3")) {
			plugin.getConfig().set("raids.teleports.ghast.3", p.getLocation());
			plugin.saveConfig();
			plugin.saveDefaultConfig();
			p.sendMessage("set spawn to current location!");
		}
		return false;
	}
	@Override
	public List<String> onTabComplete(CommandSender sender, Command cmd, String label, String[] args) {
		if (cmd.getName().equalsIgnoreCase("bsetspawn")) {
			if (args.length == 1) {
				ArrayList<String> available = new ArrayList<String>();
				available.add("ghast_raidboss");
				available.add("ghast_raid.minion.1");
				available.add("ghast_raid.minion.2");
				available.add("ghast_raid.minion.3");
				available.add("ghast_raid.minion.4");
				available.add("ghast_raid.boss.teleport.1");
				available.add("gladiator.king");
				available.add("gladiator.gate1");
				available.add("gladiator.gate2");
				available.add("gladiator.gate3");
				available.add("gladiator.beacon_glass");
				Collections.sort(available);
				ArrayList<String> query = new ArrayList<String>();
				if(!(args[0].equals(""))) {
					for(String items : available) {
						if(items.contains(args[0])) {
							query.add(items);
						}
					}
					Collections.sort(query);
					return query;
				}else {
					return available;
				}
				
			}
		}
		
		return null;
	}
}