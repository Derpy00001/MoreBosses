package me.Derpy.Bosses.events;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Ghast;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Vex;
import org.bukkit.entity.WitherSkeleton;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;

import me.Derpy.Bosses.mobs.raids.ghast;
import me.Derpy.Bosses.utilities.Random;
import me.Derpy.Bosses.utilities.raid_inventories.raid_inventories;
import net.md_5.bungee.api.ChatColor;

public class ghastevent {
	private static Plugin plugin = me.Derpy.Bosses.Main.getPlugin(me.Derpy.Bosses.Main.class);
	private static ArrayList<Player> playerlist;
	private static Ghast theghast;
	private static boolean active;
	public static void start(ArrayList<Player> players) {
		playerlist=players;
		final Location loc = (Location) plugin.getConfig().get("raids.ghast_raid");
		theghast= ghast.newghast(loc, plugin);
		setActive(true);
		new BukkitRunnable(){
			@Override
			public void run(){
				if(isActive()) {
					for(int i = 1; i < 5 ; i++) {
						loc.getWorld().spawnEntity((Location) plugin.getConfig().get("raids.ghast_raid_minion_spawns."+Integer.toString(i)), EntityType.WITHER_SKELETON);
						if(Random.random(0.05)) {
							loc.getWorld().spawnEntity((Location) plugin.getConfig().get("raids.ghast_raid_minion_spawns."+Integer.toString(i)), EntityType.VEX);
						}
					}
				}else {
					ArmorStand stand = (ArmorStand) plugin.getServer().getWorld("MoreBosses-void").spawnEntity(plugin.getServer().getWorld("MoreBosses-void").getSpawnLocation(), EntityType.ARMOR_STAND);
					stand.setVisible(false);
					for(Entity mob : stand.getNearbyEntities(1000, 1000, 1000)) {
						if(mob instanceof WitherSkeleton || mob instanceof Vex) {
							LivingEntity living = (LivingEntity) mob;
							living.setHealth(0);
						}
					}
					stand.remove();
					this.cancel();
				}
			}
		}.runTaskTimer(plugin, 200, 200);
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
					plugin.getConfig().set("ghastevent.active", false);
					setActive(false);
					plugin.saveConfig();
					theghast.remove();
					plugin.getServer().getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() {
						public void run() {
							ArmorStand stand = (ArmorStand) plugin.getServer().getWorld("MoreBosses-void").spawnEntity(plugin.getServer().getWorld("MoreBosses-void").getSpawnLocation(), EntityType.ARMOR_STAND);
							stand.setVisible(false);
							for(Entity p: stand.getNearbyEntities(1000, 1000, 1000)) {
								if(p instanceof Player) {
									Player pl = (Player) p;
									pl.setGameMode(GameMode.SURVIVAL);
									pl.setFireTicks(0);
									if(pl.getBedSpawnLocation()==null) {
										pl.teleport(Bukkit.getWorlds().get(0).getSpawnLocation());
									}else {
										pl.teleport(pl.getBedSpawnLocation());
									}
								}
							}
							stand.remove();
						}
					}, 120L);
				}
			}
		}
	}
	public static void defeated() {
		if(!(theghast==null)) {
			if(theghast.isDead()) {
				ghastevent.theghast=null;
				setActive(false);
				for(Player player : playerlist) {
					player.getWorld().playSound(player.getLocation(), Sound.AMBIENT_UNDERWATER_LOOP_ADDITIONS_ULTRA_RARE, 1, 1);
					player.openInventory(raid_inventories.ghast_get());
					player.sendMessage(ChatColor.GOLD+"Returning in 1 minute");
				}
				plugin.getServer().getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() {
					  public void run() {
						  ArmorStand stand = (ArmorStand) plugin.getServer().getWorld("MoreBosses-void").spawnEntity(plugin.getServer().getWorld("MoreBosses-void").getSpawnLocation(), EntityType.ARMOR_STAND);
							stand.setVisible(false);
							for(Entity p: stand.getNearbyEntities(1000, 1000, 1000)) {
								if(p instanceof Player) {
									Player pl = (Player) p;
									pl.setGameMode(GameMode.SURVIVAL);
									pl.setFireTicks(0);
									if(pl.getBedSpawnLocation()==null) {
										pl.teleport(Bukkit.getWorlds().get(0).getSpawnLocation());
									}else {
										pl.teleport(pl.getBedSpawnLocation());
									}
								}
							}
							stand.remove();
					      plugin.getConfig().set("ghastevent.active", false);
					      plugin.saveConfig();
					  }
					}, 1200L);
			}
		}
	}
	public static boolean isActive() {
		return active;
	}
	public static void setActive(boolean active) {
		ghastevent.active = active;
	}
}
