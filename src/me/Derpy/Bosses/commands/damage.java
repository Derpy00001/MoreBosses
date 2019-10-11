package me.Derpy.Bosses.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;


import me.Derpy.Bosses.MoreBosses;

public class damage implements CommandExecutor{

	
	public damage(MoreBosses plugin) {
		plugin.getCommand("bdamage").setExecutor(this);
		
	}
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (!(sender instanceof Player)) {
			sender.sendMessage("Inable to execute command as console");
			return true;
		}
		final Player p = (Player) sender;
		if(p.hasPermission("bosses.event")) {
		    p.damage(Double.parseDouble(args[0]));
		}else {
			p.sendMessage("You do not have the required permissions to execute this command");
			return true;
		}
		return false;
	}
}