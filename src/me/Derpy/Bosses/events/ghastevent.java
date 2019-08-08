package me.Derpy.Bosses.events;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.entity.Ghast;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import me.Derpy.Bosses.mobs.raids.ghast;
import net.md_5.bungee.api.ChatColor;

public class ghastevent {
	private static Plugin plugin = me.Derpy.Bosses.Main.getPlugin(me.Derpy.Bosses.Main.class);
	private static ArrayList<Player> playerlist;
	private static Ghast theghast;
	public static void start(ArrayList<Player> players) {
		playerlist=players;
		Location loc = (Location) plugin.getConfig().get("raids.ghast_raid");
		theghast= ghast.newghast(loc, plugin);
	}
	public static void check() {
		Boolean stillalive = false;
		if(!(playerlist==null)) {
			for(Player player : playerlist) {
				if(player.getGameMode()==GameMode.ADVENTURE) {
					stillalive=true;
				}
			}
			if(!(stillalive)) {
				if(!(theghast==null)) {
					theghast.remove();
					for(final Player player : playerlist) {
						if(player.getBedSpawnLocation()!=null) {
							plugin.getServer().getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() {
								  public void run() {
									  player.teleport(player.getBedSpawnLocation());
									  player.setGameMode(GameMode.SURVIVAL);
									  player.setFireTicks(0);
								  }
							}, 120L);
						}else {
							plugin.getServer().getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() {
								  public void run() {
									  player.teleport(Bukkit.getWorld(plugin.getConfig().getString("world.default_world_name")).getSpawnLocation());
									  player.setGameMode(GameMode.SURVIVAL);
									  player.setFireTicks(0);
								  }
							}, 120L);
						}
					}
					plugin.getConfig().set("ghastevent.active", false);
					plugin.saveConfig();
				}
			}
		}
	}
	public static void defeated() {
		if(!(theghast==null)) {
			if(theghast.isDead()) {
				theghast=null;
				for(Player player : playerlist) {
					player.getWorld().playSound(player.getLocation(), Sound.MUSIC_CREDITS, 0, 0);
					player.sendMessage(ChatColor.GOLD+"Returning in 1 minute");
				}
				plugin.getServer().getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() {
					  public void run() {
					      for(Player player : playerlist) {
					    	  player.setGameMode(GameMode.SURVIVAL);
					    	  if(player.getBedLocation()!=null) {
					    		  player.teleport(player.getBedSpawnLocation());
					    	  }else {
					    		  player.teleport(Bukkit.getWorld(plugin.getConfig().getString("world.default_world_name")).getSpawnLocation());
					    	  }
					      }
					      plugin.getConfig().set("ghastevent.active", false);
							plugin.saveConfig();
					  }
					}, 1200L);
			}
		}
	}
}
