package me.Derpy.Bosses.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.Derpy.Bosses.Main;

public class saveworld implements CommandExecutor{

	
	public saveworld(Main plugin) {
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
}