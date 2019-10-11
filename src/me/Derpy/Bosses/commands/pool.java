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
import me.Derpy.Bosses.utilities.ConfigManager;

public class pool implements CommandExecutor, TabCompleter{

	
	public pool(MoreBosses plugin) {
		plugin.getCommand("blootpool").setExecutor(this);
		
	}
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (!(sender instanceof Player)) {
			sender.sendMessage("Inable to execute command as console");
			return true;
		}
		if(sender.isOp()) {
			if(args[0].toLowerCase().equals("morebosses.pool.tier0")) {
				((Player) sender).openInventory(ConfigManager.tier0pool);
			}
			if(args[0].toLowerCase().equals("morebosses.pool.tier1")) {
				((Player) sender).openInventory(ConfigManager.tier1pool);
			}
			if(args[0].toLowerCase().equals("morebosses.pool.tier2")) {
				((Player) sender).openInventory(ConfigManager.tier2pool);
			}
			if(args[0].toLowerCase().equals("morebosses.pool.tier3")) {
				((Player) sender).openInventory(ConfigManager.tier3pool);
			}
			if(args[0].toLowerCase().equals("morebosses.pool.tier4")) {
				((Player) sender).openInventory(ConfigManager.tier4pool);
			}
			if(args[0].toLowerCase().equals("morebosses.pool.tier5")) {
				((Player) sender).openInventory(ConfigManager.tier5pool);
			}
			if(args[0].toLowerCase().equals("morebosses.pool.ghast_raid")) {
				((Player) sender).openInventory(ConfigManager.ghast_raid_pool);
			}
			if(args[0].toLowerCase().equals("morebosses.pool.gladiator")) {
				((Player) sender).openInventory(ConfigManager.gladiator_pool);
			}
			if(args[0].toLowerCase().equals("morebosses.pool.fenrir")) {
				((Player) sender).openInventory(ConfigManager.fenrir_pool);
			}
			if(args[0].toLowerCase().equals("morebosses.pool.warlock")) {
				((Player) sender).openInventory(ConfigManager.warlock_pool);
			}
		}else {
			return true;
		}
		return false;
	}
	@Override
	public List<String> onTabComplete(CommandSender sender, Command cmd, String label, String[] args) {
		if (cmd.getName().equalsIgnoreCase("blootpool")) {
			if (args.length == 1) {
				ArrayList<String> available = new ArrayList<String>();
				available.add("MoreBosses.Pool.tier0");
				available.add("MoreBosses.Pool.tier1");
				available.add("MoreBosses.Pool.tier2");
				available.add("MoreBosses.Pool.tier3");
				available.add("MoreBosses.Pool.tier4");
				available.add("MoreBosses.Pool.tier5");
				available.add("MoreBosses.Pool.ghast_raid");
				available.add("MoreBosses.Pool.gladiator");
				available.add("MoreBosses.Pool.fenrir");
				available.add("MoreBosses.Pool.warlock");
				Collections.sort(available);
				return available;
			}
		}
		
		return null;
	}
}