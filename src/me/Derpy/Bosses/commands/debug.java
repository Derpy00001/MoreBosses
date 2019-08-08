package me.Derpy.Bosses.commands;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.boss.BossBar;
import org.bukkit.boss.KeyedBossBar;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

import com.google.common.collect.Lists;

import me.Derpy.Bosses.Main;

public class debug implements CommandExecutor, TabCompleter{

	public debug(Main plugin) {
		plugin.getCommand("bdebug").setExecutor(this);
		
	}
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (!(sender instanceof Player)) {
			sender.sendMessage("Inable to execute command as console");
			return true;
		}
		final Player p = (Player) sender;
	    if(args[0].toLowerCase().equals("bar")) {
	    	if(args[1].isEmpty()) {
	    		return false;
	    	}else {
		    	if(args[1].toLowerCase().equals("all")) {
		    		if(p.hasPermission("bosses.debug.all")) {
				    	Iterator<KeyedBossBar> myIterator = Bukkit.getBossBars(); //some iterator
				    	List<KeyedBossBar> myList = Lists.newArrayList(myIterator);
				    	for(BossBar bar:myList) {
				    		bar.removeAll();
				    		bar.setVisible(false);
				    	}
				    	return true;
		    		}else {
		    			return false;
		    		}
		    	}else {
		    		if(!(p.hasPermission("bosses.debug.all"))) {
		    			if(!(args[1].toLowerCase().equals(sender.getName()))) {
		    				return false;
		    			}
		    		}
		    		Iterator<KeyedBossBar> myIterator = Bukkit.getBossBars(); //some iterator
		    		List<KeyedBossBar> myList = Lists.newArrayList(myIterator);
		    		List<BossBar> list = new ArrayList<BossBar>();
		    		for(BossBar bar:myList) {
		    			if(bar.getPlayers().contains(Bukkit.getPlayer(args[1].toLowerCase()))) {
		    				list.add((BossBar) bar);
		    			}
		    		}
		    		for(BossBar bar:list) {
		    			bar.removePlayer(Bukkit.getPlayer(args[1].toLowerCase()));
		    			if(bar.getPlayers().size()==0) {
		    				bar.removeAll();
		    			}
		    		}
		    	}
	    	}
	    }
	return false;
	}
	@Override
	public List<String> onTabComplete(CommandSender sender, Command cmd, String label, String[] args) {
		if (cmd.getName().equalsIgnoreCase("bdebug")) {
			if (args.length == 1) {
				ArrayList<String> available = new ArrayList<String>();
				available.add("bar");
				Collections.sort(available);
				return available;
			}
			if(args.length == 2) {
				if(sender.hasPermission("bosses.debug.all")) {
					ArrayList<String> available = new ArrayList<String>();
					available.add("all");
					for(Player p : Bukkit.getServer().getOnlinePlayers()) {
						available.add(p.getName());
					}
					Collections.sort(available);
					return available;
				}else {
					ArrayList<String> available = new ArrayList<String>();
					available.add(sender.getName());
					return available;
				}
			}
		}
		
		return null;
	}
}