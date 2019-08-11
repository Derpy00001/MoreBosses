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
				Collections.sort(available);
				return available;
			}
		}
		
		return null;
	}
}