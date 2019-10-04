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

public class saveworld implements CommandExecutor, TabCompleter{

	
	public saveworld(MoreBosses plugin) {
		plugin.getCommand("bsaveworld").setExecutor(this);
		
	}
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (!(sender instanceof Player)) {
			sender.sendMessage("Inable to execute command as console");
			return true;
		}
		if(!(sender.hasPermission("bosses.raid.saveworld"))) {
			sender.sendMessage("You do not have the required permissions to perform this task");
			return true;
		}
		Player p = (Player) sender;
		p.sendMessage("Saving");
		p.getLocation().getWorld().save();
		p.sendMessage("Saved");
		return false;
	}
	@Override
	public List<String> onTabComplete(CommandSender sender, Command cmd, String label, String[] args) {
		if (cmd.getName().equalsIgnoreCase("bsaveworld")) {
			if (args.length == 1) {
				ArrayList<String> available = new ArrayList<String>();
				available.add("void");
				Collections.sort(available);
				return available;
			}
		}
		
		return null;
	}
}