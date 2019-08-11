package me.Derpy.Bosses.events;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Blaze;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Evoker;
import org.bukkit.entity.Ghast;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.PigZombie;
import org.bukkit.entity.Player;
import org.bukkit.entity.Vex;
import org.bukkit.entity.WitherSkeleton;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;

import me.Derpy.Bosses.mobs.raids.ghast;
import me.Derpy.Bosses.utilities.Random;
import me.Derpy.Bosses.utilities.raid_inventories.raid_inventories;
import net.md_5.bungee.api.ChatColor;


//import org.bukkit.craftbukkit.v1_14_R1.entity.CraftEntity;
//import org.bukkit.craftbukkit.v1_14_R1.entity.CraftItem;
//import net.minecraft.server.v1_14_R1.EntityPlayer;
//import net.minecraft.server.v1_14_R1.Item;
//import net.minecraft.server.v1_14_R1.PacketPlayOutSetCooldown;
//import net.minecraft.server.v1_14_R1.PlayerConnection;
//import org.bukkit.craftbukkit.v1_14_R1.entity.CraftPlayer;
//
//EntityPlayer cPlayer = ((CraftPlayer) event.getPlayer()).getHandle();
//PlayerConnection playerConnection = cPlayer.playerConnection;
//Item item = Item.getById(event.getPlayer().getInventory().getItemInMainHand().getType().getId());
//PacketPlayOutSetCooldown itemPacket = new PacketPlayOutSetCooldown(item, 100);
//playerConnection.sendPacket(itemPacket);

public class ghastevent {
	private static Plugin plugin = me.Derpy.Bosses.Main.getPlugin(me.Derpy.Bosses.Main.class);
	private static ArrayList<Player> playerlist;
	private static Ghast theghast;
	private static boolean active;
	private static boolean said = false;
	public static void start(ArrayList<Player> players) {
		playerlist=players;
		final Location loc = (Location) plugin.getConfig().get("raids.ghast_raid");
		theghast= ghast.newghast(loc, plugin);
		setActive(true);
		Integer amt = 400;
		final Integer moblimit = 10;
		Integer mul;
		if(playerlist.size()>=5) {
			mul = 5;
		}else {
			mul = playerlist.size();
		}
		for(Player entity : playerlist) {
  		  Player p = (Player) entity;
  		  p.setGameMode(GameMode.ADVENTURE);
  		  p.teleport(Bukkit.getWorld("MoreBosses-void").getSpawnLocation());
  	  	}
		Integer time = amt/mul;
		plugin.getServer().getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() {
			  public void run() {
				  if(theghast.isValid()) {
					  for(Player p : playerlist) {
						  p.damage(10000);
					  }
				  }
			 }
//		}, 6000L);
		}, 36000L);
		new BukkitRunnable(){
			@Override
			public void run(){
				if(isActive()) {
					for(Entity entity3 : theghast.getNearbyEntities(100, 100, 100)) {
						if(entity3 instanceof PigZombie) {
							((PigZombie) entity3).damage(1);
						}
					}
					for(int i = 1; i < 5 ; i++) {
						Integer numofpig = 0;
						Integer numofplayer = 0;
						for(Entity entity2 : theghast.getNearbyEntities(100, 100, 100)){
							if(entity2 instanceof PigZombie) {
								numofpig++;
							}
						}
						for(Player p : playerlist) {
							if(p.getGameMode()==GameMode.ADVENTURE) {
								numofplayer++;
							}
						}
						if(!(numofpig>=moblimit*numofplayer)) {
							PigZombie pig = (PigZombie) loc.getWorld().spawnEntity((Location) plugin.getConfig().get("raids.ghast_raid_minion_spawns."+Integer.toString(i)), EntityType.PIG_ZOMBIE);
							pig.setAngry(true);
							pig.setAnger(999999);
						}
						if(Random.random(0.1)) {
							 loc.getWorld().spawnEntity((Location) plugin.getConfig().get("raids.ghast_raid_minion_spawns."+Integer.toString(i)), EntityType.BLAZE);
						}
						if(Random.random(0.05)) {
							loc.getWorld().spawnEntity((Location) plugin.getConfig().get("raids.ghast_raid_minion_spawns."+Integer.toString(i)), EntityType.WITHER_SKELETON);
						}
					}
				}else {
					ArmorStand stand = (ArmorStand) plugin.getServer().getWorld("MoreBosses-void").spawnEntity(plugin.getServer().getWorld("MoreBosses-void").getSpawnLocation(), EntityType.ARMOR_STAND);
					stand.setVisible(false);
					for(Entity mob : stand.getNearbyEntities(1000, 1000, 1000)) {
						if(mob instanceof PigZombie || mob instanceof Vex || mob instanceof Evoker || mob instanceof Blaze || mob instanceof WitherSkeleton) {
							LivingEntity living = (LivingEntity) mob;
							living.setHealth(0);
						}
					}
					stand.remove();
					this.cancel();
				}
			}
		}.runTaskTimer(plugin, time, time);
		new BukkitRunnable(){
			@Override
			public void run(){
				if(isActive()) {
					theghast.teleport(loc);
				}else {
					this.cancel();
				}
			}
		}.runTaskTimer(plugin, 600, 600);
		new BukkitRunnable(){
			@SuppressWarnings("deprecation")
			@Override
			public void run(){
				if(isActive()) {
					if(theghast.getHealth()<=theghast.getMaxHealth()/2) {
						if(!(said)) {
							for(Player p : playerlist) {
								p.sendTitle(ChatColor.GOLD+theghast.getCustomName(), ChatColor.RED+"Has enraged");
								p.sendMessage(ChatColor.RED+"The spikes heal the overlord");
							}
							ghastevent.said = true;
						}
						for(Player p : playerlist) {
							if(p.getGameMode()==GameMode.ADVENTURE) {
								p.getWorld().spawnEntity(p.getLocation(), EntityType.EVOKER_FANGS);
							}
						}
					}
				}else {
					this.cancel();
				}
			}
		}.runTaskTimer(plugin, 60, 60);
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
					player.setExp((float) (player.getExp()*1.5));
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
