package me.Derpy.Bosses.listeners;

import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.CreatureSpawnEvent;
import org.bukkit.event.entity.CreatureSpawnEvent.SpawnReason;

import me.Derpy.Bosses.Main;
import me.Derpy.Bosses.mobs.tier0.Cow;
import me.Derpy.Bosses.mobs.tier0.Phantom;
import me.Derpy.Bosses.mobs.tier0.Pig;
import me.Derpy.Bosses.mobs.tier0.Sheep;
import me.Derpy.Bosses.mobs.tier0.Tropic;
import me.Derpy.Bosses.mobs.tier1.Drowned;
import me.Derpy.Bosses.mobs.tier1.Skeleton;
import me.Derpy.Bosses.mobs.tier1.Zombie;
import me.Derpy.Bosses.mobs.tier2.Blaze;
import me.Derpy.Bosses.mobs.tier2.Creeper;
import me.Derpy.Bosses.mobs.tier2.Guardian;
import me.Derpy.Bosses.mobs.tier2.Stray;
import me.Derpy.Bosses.mobs.tier3.ElderGuardian;
import me.Derpy.Bosses.mobs.tier3.Slime;
import me.Derpy.Bosses.mobs.tier3.WitherSkeleton;
import me.Derpy.Bosses.utilities.Random;
import net.md_5.bungee.api.ChatColor;

public class onspawn implements Listener{
	private Main plugin;
	private double tier0;
	private double tier2;
	private double tier1;
	private double tier3;
	private double fish;
	// Ending boss
	// Chance to spawn after enderman spawns
	// spawn ravanger and kills the enderman
	// places ravanger in its place
	// speed and health
	// (Raid boss)
	private double merchant;
	
	// Mega slime for ending boss?
	// maybe learn how to spawn an entire arena?
	// spawns waves of enemies 
	// defeat waves to beat boss
	// boss bar shows enemies left in wave like village raid
	
	// FOR END BOSS MAKE SURE TO PLAY SOUND MUSIC.END_CREDITS
	public onspawn(Main plugin) {
		// TODO Auto-generated constructor stub
		this.plugin = plugin;
		this.tier0 = plugin.getConfig().getDouble("spawnrates.tier0spawnrate");
		this.tier1 = plugin.getConfig().getDouble("spawnrates.tier1spawnrate");
		this.tier2 = plugin.getConfig().getDouble("spawnrates.tier2spawnrate");
		this.tier3 = plugin.getConfig().getDouble("spawnrates.tier3spawnrate");
		this.fish = plugin.getConfig().getDouble("spawnrates.fish");
		this.merchant = plugin.getConfig().getDouble("spawnrates.merchant.rate");
	}
	@SuppressWarnings("deprecation")
	@EventHandler
	public boolean onSpawn(CreatureSpawnEvent event) throws InterruptedException {
		Entity entity = event.getEntity();
		EntityType type = entity.getType();
		// TIER 0
		if(type==EntityType.VEX&&entity.getWorld().getName().equals("MoreBosses-Colosseum")){
			entity.remove();
		}else {
			if(entity.getWorld().getName().contains("MoreBosses")) {
				return false;
			}
		}
		if(event.getSpawnReason()==SpawnReason.NATURAL||event.getSpawnReason()==SpawnReason.BUILD_WITHER) {
			if(type==EntityType.WANDERING_TRADER) {
				if(plugin.getConfig().getBoolean("spawnrates.merchant.enabled")) {
					if(Random.random(merchant)) {
						entity.setCustomName(ChatColor.GOLD+"Merchant");
						entity.getWorld().spawnEntity(entity.getLocation(), EntityType.HORSE).setPassenger(entity);
						for(Entity ent : entity.getNearbyEntities(20, 20, 20)) {
							if(ent instanceof Player) {
								Player p = (Player) ent;
								p.sendMessage("<"+ChatColor.GOLD+"Merchant"+ChatColor.RESET+"> Hello warrior.");
							}
							if(ent.getType()==EntityType.TRADER_LLAMA) {
								ent.remove();
							}
						}
					}
					
				}
			}
			if(type==EntityType.PHANTOM) {
				if(plugin.getConfig().getBoolean("world.disable_phantoms")) {
					entity.remove();
				}else {
					if(plugin.getConfig().getBoolean("tier0_bosses.phantomboss_enabled")) {
						if(Random.random(tier0)) {
							Phantom.newphantom((LivingEntity) entity, plugin);
						}
					}
				}
			}
			if(type==EntityType.TROPICAL_FISH) {
				if(plugin.getConfig().getBoolean("tier0_bosses.fishboss_enabled")) {
					if(event.getSpawnReason()==SpawnReason.NATURAL) {
						if(Random.random(fish)) {
							Tropic.newtropic((LivingEntity) entity, plugin);
						}
					}
				}
			}
			if(type==EntityType.SHEEP) {
				if(Random.random(tier0)) {
					Sheep.newsheep((LivingEntity) entity, plugin);
				}else {
					if(plugin.getConfig().getInt("tier4_bosses.Undeadwither_Killed")==1) {
						if(Random.random(0.25)) {
							if(event.getSpawnReason()==SpawnReason.NATURAL) {
								entity.getWorld().spawnEntity(entity.getLocation(), EntityType.GHAST);
								entity.remove();
							}
						}
					}
				}
			}
			if(type==EntityType.COW) {
				if(Random.random(tier0)) {
					Cow.newcow((LivingEntity) entity, plugin);
				}
			}
			if(type==EntityType.PIG) {
				if(Random.random(tier0)) {
					Pig.newpig((LivingEntity) entity, plugin);
				}else {
					if(plugin.getConfig().getInt("tier4_bosses.Undeadwither_Killed")==1) {
						if(Random.random(0.25)) {
							if(event.getSpawnReason()==SpawnReason.NATURAL) {
								entity.getWorld().strikeLightning(entity.getLocation());
							}
						}
					}
				}
			}
	
			// TIER 1
			if(type==EntityType.ZOMBIE) {
				if(Random.random(tier1)) {
					if(!(entity.isInvulnerable())) {
						Zombie.newzombie((LivingEntity) entity, plugin);
					}
				}else {
					if(plugin.getConfig().getInt("tier4_bosses.Undeadwither_Killed")==1) {
						if(Random.random(0.25)) {
							if(event.getSpawnReason()==SpawnReason.NATURAL) {
								entity.getWorld().spawnEntity(entity.getLocation(), EntityType.BLAZE);
								entity.remove();
							}
						}
					}
				}
			}
			if(type==EntityType.SKELETON) {
				if(Random.random(tier1)) {
					if(!(entity.isInvulnerable())) {
						Skeleton.newskeleton((LivingEntity) entity, plugin);
					}
				}else {
					if(plugin.getConfig().getInt("tier4_bosses.Undeadwither_Killed")==1) {
						if(Random.random(0.25)) {
							if(event.getSpawnReason()==SpawnReason.NATURAL) {
								entity.getWorld().spawnEntity(entity.getLocation(), EntityType.WITHER_SKELETON);
								entity.remove();
							}
						}
					}
				}
			}
			if(type==EntityType.DROWNED) {
				if(Random.random(tier1)) {
					if(!(entity.isInvulnerable())) {
						Drowned.newdrowned((LivingEntity) entity, plugin, false);
					}
				}
			}
			
			// TIER 2
			if(type==EntityType.STRAY) {
				if(Random.random(tier2)){
					if(!(entity.isInvulnerable())) {
						if(!(entity.isGlowing())) {
							Stray.newstray((LivingEntity) entity, plugin);
						}
					}
				}
			}
			if(type==EntityType.CREEPER) {
				if(Random.random(tier2)) {
					if(!(entity.isInvulnerable())) {
						if(!(entity.isGlowing())) {
							if(!(event.getSpawnReason()==CreatureSpawnEvent.SpawnReason.CUSTOM)) {
								Creeper.newcreeper((LivingEntity) entity, plugin);
							}
						}
					}
				}
			}
			if(type==EntityType.BLAZE) {
				if(Random.random(tier2)) {
					if(!(entity.isInvulnerable())) {
						Blaze.newblaze((LivingEntity) entity, plugin);
					}
				}
			}
			if(type==EntityType.GUARDIAN) {
				if(Random.random(tier2)) {
					if(!(entity.isInvulnerable())) {
						Guardian.newguardian((LivingEntity) entity, plugin);
					}
				}
			}
			// TIER 3
			if(type==EntityType.WITHER_SKELETON) {
				if(Random.random(tier3)) {
					if(!(entity.isInvulnerable())) {
						if(!(entity.getWorld().getName().contains("MoreBosses"))) {
							WitherSkeleton.newwitherskeleton((LivingEntity) entity, plugin);
						}
					}
				}
			}
			if(type==EntityType.ELDER_GUARDIAN) {
				if(Random.random(tier3)) {
					if(!(entity.isInvulnerable())) {
						ElderGuardian.newelderguardian((LivingEntity) entity, plugin);
					}
				}
			}
			if(type==EntityType.SLIME) {
				if(Random.random(tier3)) {
					if(!(entity.isInvulnerable())) {
						Slime.newslime((LivingEntity) entity, plugin);
					}
				}
			}
			// Tier 4
			if(type==EntityType.ENDER_DRAGON) {
				if(plugin.getConfig().getBoolean("tier4_bosses.enabled")) {
					if(plugin.getConfig().getBoolean("tier4_bosses.Enderdragon_Killed")) {
						if(!(entity.isInvulnerable())) {
							me.Derpy.Bosses.mobs.tier4.EnderDragon.newenderdragon((LivingEntity) entity, plugin);
						}
					}
				}
			}
			if(type==EntityType.WITHER) {
				if(plugin.getConfig().getBoolean("tier4_bosses.enabled")) {
					if(plugin.getConfig().getBoolean("tier4_bosses.Wither_Killed")) {
						if(!(entity.isInvulnerable())) {
							me.Derpy.Bosses.mobs.tier4.Wither.newwither((LivingEntity) entity, plugin);
						}
					}
				}
			}
			return true;
		}else {
			return true;
		}
	}

}
