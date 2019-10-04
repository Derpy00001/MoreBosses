package me.Derpy.Bosses.commands;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;


import me.Derpy.Bosses.MoreBosses;
import net.md_5.bungee.api.ChatColor;

public class event implements CommandExecutor, TabCompleter{

	private MoreBosses plugin;
	
	public event(MoreBosses plugin) {
		this.plugin = plugin;
		plugin.getCommand("bevent").setExecutor(this);
		
	}
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (!(sender instanceof Player)) {
			sender.sendMessage("Inable to execute command as console");
			return true;
		}
		final Player p = (Player) sender;
		if(p.hasPermission("bosses.event")) {
		    if(args[0].toLowerCase().equals("breach")) {
		    	if(args[1].toLowerCase().equals("true")){
		    		plugin.getConfig().set("tier4_bosses.Undeadwither_Killed", 1);
		    		p.sendMessage(ChatColor.GOLD+"Event started!");
		    	}else if(args[1].toLowerCase().equals("false")) {
		    		plugin.getConfig().set("tier4_bosses.Undeadwither_Killed", 0);
		    		p.sendMessage(ChatColor.GOLD+"Event stopped!");
		    	}
		    	plugin.saveConfig();
		    	return true;
		    }
		}else {
			p.sendMessage("You do not have the required permissions to execute this command");
			return true;
		}
		return false;
	}
	@Override
	public List<String> onTabComplete(CommandSender sender, Command cmd, String label, String[] args) {
		if (cmd.getName().equalsIgnoreCase("bevent")) {
			if (args.length == 1) {
				ArrayList<String> available = new ArrayList<String>();
				available.add("breach");
				Collections.sort(available);
				return available;
			}
			if(args.length == 2) {
				if(args[0].toLowerCase().equals("breach")) {
					ArrayList<String> available1 = new ArrayList<String>();
					available1.add("true");
					available1.add("false");
					Collections.sort(available1);
					return available1;
				}
			}
		}
		
		return null;
	}
}