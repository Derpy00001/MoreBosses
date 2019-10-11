package me.Derpy.Bosses.mobs.merchants;

import java.util.ArrayList;

import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Llama;
import org.bukkit.entity.Monster;
import org.bukkit.entity.Player;
import org.bukkit.entity.Vindicator;
import org.bukkit.entity.WanderingTrader;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;

import me.Derpy.Bosses.Addons.Nordic.Items.shards;
import me.Derpy.Bosses.utilities.Random;
import me.Derpy.Bosses.utilities.Refs;
import me.Derpy.Bosses.utilities.items.village;
import net.md_5.bungee.api.ChatColor;

public class createNomad implements Listener{
	private static ArrayList<nomad> nomads = new ArrayList<nomad>();
	private static Plugin plugin = me.Derpy.Bosses.MoreBosses.getPlugin(me.Derpy.Bosses.MoreBosses.class);
	public static void start(LivingEntity entity) {
		nomad nomad = new nomad(entity);
		ArrayList<Player> players = new ArrayList<Player>();
		ArrayList<Entity> entities = new ArrayList<Entity>();
		for(Entity ent : entity.getNearbyEntities(15, 15, 15)) {
			if(ent instanceof Player) {
				Player p = (Player) ent;
				players.add(p);
			}
		}
		Entity llama = entity.getWorld().spawnEntity(entity.getLocation(), EntityType.LLAMA);
		((LivingEntity) llama).setLeashHolder(entity);
		nomad.setPlayers(players);
		for(int i = 0;i<14;i++) {
			Monster entt = (Monster) entity.getWorld().spawnEntity(Random.randomlocation(entity.getLocation(), 40, 35), EntityType.VINDICATOR);
			entt.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 1000000, 0, true));
			entt.setTarget(entity);
			entt.setGlowing(true);
			entities.add(entt);
		}
		entity.setGlowing(true);
		entity.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 100000, 3, true));
		WanderingTrader tr = (WanderingTrader) entity;
		tr.getInventory().setItem(0, new ItemStack(Material.TOTEM_OF_UNDYING));
		nomad.setMobs(entities);
		nomad.setNomad(entity);
		nomad.setLlama((LivingEntity)llama);
		nomads.add(nomad);
		startaggro(nomad);
	}
	private static void startaggro(final nomad nomad) {
		new BukkitRunnable(){
			  public void run() {
				  if(nomad.getNomad().isValid()) {
					  if(nomad.getMobs().size()!=0) {
						  try {
							  for(Entity mon : nomad.getMobs()) {
								  Monster mons = (Monster) mon;
								  if(mons.getTarget()==null) {
									  mons.setTarget(nomad.getNomad());
								  }
							  }
							  ArrayList<Entity> nearby = new ArrayList<Entity>();
							  for(Entity mons : nomad.getNomad().getNearbyEntities(70, 70, 70)) {
								  if(nomad.getMobs().contains(mons)) {
									  nearby.add(mons);
								  }
							  }
							  for(Entity mons : nomad.getMobs()) {
								  if(!(nearby.contains(mons))) {
									  LivingEntity monst = (LivingEntity) mons;
									  monst.addPotionEffect(new PotionEffect(PotionEffectType.WITHER, 100000, 1, true));
								  }
							  }
						  }catch(Exception ignored) {}
						  }else {
							badend(nomad);  
						  }
					  }else {
						  this.cancel();
					  }
				  }
//		}, 6000L);
		}.runTaskTimer(plugin, 20*5, 20*5);
	}
	private static void badend(nomad nomad) {
		if(nomad.getMobs().size()!=0) {
			for(Entity mon : nomad.getMobs()) {
				mon.remove();
				nomad.getLlama().remove();
				nomad=null;
			}
		}
	}
	@EventHandler
	public static void death(EntityDeathEvent event) {
		if(event.getEntity() instanceof Vindicator) {
			if(!(nomads.size()==0)) {
				try {
					for(nomad nomad : nomads) {
						if(nomad.checkMob(event.getEntity())) {
							nomads.remove(nomad);
							for(Player p : nomad.getPlayers()) {
								if(Refs.getNordicEnabled()) {
									p.getWorld().dropItemNaturally(p.getLocation(), shards.Alfheimrshard());
									p.sendMessage("<"+ChatColor.GOLD+"Nomad"+ChatColor.RESET+">"+ChatColor.RED+" For your kindness, you have been gifted a shard of Yggdrasill");
								}else {
									p.getWorld().dropItemNaturally(p.getLocation(), village.token());
									p.sendMessage("<"+ChatColor.GOLD+"Nomad"+ChatColor.RESET+">"+ChatColor.RED+" For your kindness, you have been gifted a token");
								}
								p.playSound(p.getLocation(), Sound.UI_TOAST_CHALLENGE_COMPLETE, 1, 1);
							}
							nomad.getLlama().remove();
							nomad.getNomad().remove();
							nomad = null;
						}
					}
				}catch(Exception ignored) {
					
				}
			}
		}else if(event.getEntity() instanceof WanderingTrader) {
			if(!(nomads.size()==0)) {
				try {
					for(nomad nomad : nomads) {
						if(nomad.getNomad()==event.getEntity()) {
							nomads.remove(nomad);
							badend(nomad);
						}
					}
				}catch(Exception e) {}
			}
		}else if(event.getEntity() instanceof Llama) {
			for(nomad nomad:nomads) {
				if(nomad.getLlama()==event.getEntity()) {
					for(Player p : nomad.getPlayers()) {
						p.sendMessage("<"+ChatColor.GOLD+"Nomad"+ChatColor.RESET+">"+ChatColor.WHITE+" NOOO MY LLAMA");
					}
				}
			}
		}
	}
}
