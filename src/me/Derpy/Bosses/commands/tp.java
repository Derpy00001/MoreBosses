package me.Derpy.Bosses.commands;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;

import me.Derpy.Bosses.Main;

public class tp implements CommandExecutor, TabCompleter{

	@SuppressWarnings("unused")
	private Main plugin;
	@SuppressWarnings("unused")
	private ArrayList<UUID> list;
	
	public tp(Main plugin) {
		this.plugin = plugin;
		plugin.getCommand("barena").setExecutor(this);
		ArrayList<UUID> list = new ArrayList<UUID>();
		this.list = list;
		
	}
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (!(sender instanceof Player)) {
			sender.sendMessage("Inable to execute command as console");
			return true;
		}
		if(args[0].equals("void")) {
			((Entity) sender).teleport(Bukkit.getWorld("MoreBosses-void").getSpawnLocation());
			return true;
		}else if(args[0].equals("bed")) {
			((Entity) sender).teleport(((Player) sender).getBedSpawnLocation());
			return true;
		}else {
			((Player) sender).teleport(Bukkit.getWorld(args[0]).getSpawnLocation());
		}
		return false;
	}
	@Override
	public List<String> onTabComplete(CommandSender sender, Command cmd, String label, String[] args) {
		if (cmd.getName().equalsIgnoreCase("barena")) {
			if (args.length == 1) {
				ArrayList<String> available = new ArrayList<String>();
				available.add("void");
				available.add("bed");
				available.add("\"World Name\"");
				Collections.sort(available);
				return available;
			}
		}
		
		return null;
	}
}