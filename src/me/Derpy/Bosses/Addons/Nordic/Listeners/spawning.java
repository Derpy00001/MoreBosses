package me.Derpy.Bosses.Addons.Nordic.Listeners;

import java.util.ArrayList;

import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.craftbukkit.v1_14_R1.CraftWorld;
import org.bukkit.craftbukkit.v1_14_R1.entity.CraftEntity;
import org.bukkit.craftbukkit.v1_14_R1.entity.CraftPlayer;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Tameable;
import org.bukkit.entity.Wolf;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.CreatureSpawnEvent;
import org.bukkit.event.entity.CreatureSpawnEvent.SpawnReason;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ItemStack;

import me.Derpy.Bosses.MoreBosses;
import me.Derpy.Bosses.Addons.Nordic.Items.runes;
import me.Derpy.Bosses.Addons.Nordic.Items.shards;
import me.Derpy.Bosses.Addons.Nordic.Items.spirit;
import me.Derpy.Bosses.Addons.Nordic.Items.wolfram;
import me.Derpy.Bosses.Addons.Nordic.Mobs.Warlock;
import me.Derpy.Bosses.Addons.Nordic.Mobs.boar;
import me.Derpy.Bosses.Addons.Nordic.Mobs.bomber;
import me.Derpy.Bosses.Addons.Nordic.Mobs.fenrir;
import me.Derpy.Bosses.Addons.Nordic.Mobs.giant;
import me.Derpy.Bosses.Addons.Nordic.Mobs.iscustom;
import me.Derpy.Bosses.Addons.Nordic.Mobs.undead_viking_0;
import me.Derpy.Bosses.Addons.Nordic.Mobs.undead_viking_1;
import me.Derpy.Bosses.utilities.ConfigManager;
import me.Derpy.Bosses.utilities.Random;
import me.Derpy.Bosses.utilities.Refs;
import net.minecraft.server.v1_14_R1.Entity;
import net.minecraft.server.v1_14_R1.EntityPlayer;
import net.minecraft.server.v1_14_R1.PacketPlayOutNamedEntitySpawn;
import net.minecraft.server.v1_14_R1.PacketPlayOutPlayerInfo;
import net.minecraft.server.v1_14_R1.PacketPlayOutPlayerInfo.EnumPlayerInfoAction;
import net.minecraft.server.v1_14_R1.PlayerConnection;


public class spawning implements Listener{
	@EventHandler
	public static void onjoin(PlayerJoinEvent ple) {
		for(EntityPlayer pls : Refs.npcs)
        {
            PlayerConnection connection = ((CraftPlayer) ple.getPlayer()).getHandle().playerConnection;
            connection.sendPacket(new PacketPlayOutPlayerInfo(EnumPlayerInfoAction.ADD_PLAYER, ((EntityPlayer)pls)));
            connection.sendPacket(new PacketPlayOutNamedEntitySpawn(pls));
        }
	}
	@EventHandler
	public static void ondamage(EntityDamageEvent event) {
		if(event.getEntity().getWorld().getName().contains("Addon")) {
			if(event.getCause()==DamageCause.SUFFOCATION) {
				if(((CraftEntity) event.getEntity()).getHandle() instanceof giant) {
					event.setCancelled(true);
					event.getEntity().remove();
				}
			}
			if(event.getCause()==DamageCause.FIRE||event.getCause()==DamageCause.FIRE_TICK) {
				if(event.getEntity().getType()==EntityType.WOLF) {
					if(!(((Tameable) event.getEntity()).isTamed())) {
						event.setCancelled(true);
					}
				}
			}
			if(event.getCause()==DamageCause.MAGIC||event.getCause()==DamageCause.POISON) {
				if(event.getEntity().getType()==EntityType.WOLF) {
					if(!(((Tameable) event.getEntity()).isTamed())) {
						event.setCancelled(true);
					}
				}
			}
		}
		if(((CraftEntity) event.getEntity()).getHandle() instanceof fenrir) {
			if(event.getCause()==DamageCause.FIRE||event.getCause()==DamageCause.FIRE_TICK||event.getCause()==DamageCause.MAGIC||event.getCause()==DamageCause.POISON) {
				event.setCancelled(true);
			}
		}
	}
	@EventHandler
	public static void onentity(EntityDamageByEntityEvent event) {
		if(((CraftEntity)event.getDamager()).getHandle() instanceof giant) {
			event.getDamager().getWorld().playSound(event.getDamager().getLocation(), Sound.ENTITY_DRAGON_FIREBALL_EXPLODE, 5F, 0.01F);
			for(org.bukkit.entity.Entity ent: event.getEntity().getNearbyEntities(5, 5, 5)) {
				if(ent instanceof LivingEntity) {
					if(!(ent ==event.getDamager())) {
						((LivingEntity) ent).damage(event.getDamage()/2);
						event.getDamager().getWorld().spawnParticle(Particle.CAMPFIRE_COSY_SMOKE, event.getEntity().getLocation(), 20, 1, 1, 1);
					}
				}
			}
		}
	}
	@EventHandler
	public static void onspawn(CreatureSpawnEvent event) {
		if(event.getEntity().getWorld().getName().contains("MoreBosses-Addon-Nordic-Alfheimr")) {
			if(event.getSpawnReason() == SpawnReason.NATURAL||event.getSpawnReason()==SpawnReason.CUSTOM) {
				Location loc = event.getEntity().getLocation();
				EntityType type = event.getEntity().getType();
				Entity ent = ((CraftEntity) event.getEntity()).getHandle();
//				Bukkit.broadcastMessage(type.toString());
				if(type==EntityType.PILLAGER||type==EntityType.VINDICATOR||type==EntityType.BAT||type==EntityType.SALMON||type==EntityType.WOLF) {
					if(type==EntityType.WOLF) {
						if(!(((Wolf) event.getEntity()).isAngry())) {
							((Wolf) event.getEntity()).setAngry(true);
							return;
						}
						return;
					}else {
						return;
					}
				}
				if(!(iscustom.check(ent))) {
					event.setCancelled(true);
					if(type==EntityType.ZOMBIE||type==EntityType.PIG_ZOMBIE) {
						if(Random.random(0.01)) {
							giant.spawn(loc);
						}else {
							undead_viking_0.spawn(loc);
						}
					}else if(type==EntityType.SKELETON) {
						undead_viking_1.spawn(loc);
					}else if(type==EntityType.PIG||type==EntityType.SPIDER) {
						if(Random.random(0.50)) {
							boar.spawn(loc);
						}
					}else if(type==EntityType.VILLAGER) {
						if(Random.random(0.5)) {
							CraftEntity enttt = (CraftEntity) event.getEntity().getWorld().spawnEntity(loc, EntityType.PILLAGER);
							CraftWorld worlds = (CraftWorld) loc.getWorld();
							worlds.addEntity(enttt.getHandle(), SpawnReason.NATURAL);
						}else {
							CraftEntity enttt = (CraftEntity) event.getEntity().getWorld().spawnEntity(loc, EntityType.VINDICATOR);
							CraftWorld worlds = (CraftWorld) loc.getWorld();
							worlds.addEntity(enttt.getHandle(), SpawnReason.NATURAL);
						}
					}else if(type==EntityType.ENDERMAN) {
						if(Random.random(0.4)) {
							giant.spawn(loc);
						}else {
							bomber.spawn(loc);
						}
					}
				}
			}
		}
	}
	@EventHandler
	public static void ondeath(EntityDeathEvent event) {
		if(((CraftEntity)event.getEntity()).getHandle() instanceof giant) {
			if(event.getEntity().getKiller() instanceof Player) {
				if(Random.random(0.5)) {
					event.getDrops().clear();
					event.getDrops().add(wolfram.refined());
				}
				if(Random.random(0.1)) {
					event.getDrops().add(shards.Midgardshard());
				}
			}
		}else if(((CraftEntity) event.getEntity()).getHandle() instanceof bomber){
			event.getEntity().getWorld().createExplosion(event.getEntity().getLocation(), 5, true);
			if(event.getEntity().getNearbyEntities(5, 5, 5).contains(event.getEntity().getKiller())) {
				for(ItemStack item : event.getEntity().getKiller().getInventory().getContents()) {
					if(item.isSimilar(wolfram.ingot())) {
						item.setAmount(item.getAmount()-1);
						event.getEntity().getWorld().dropItemNaturally(event.getEntity().getLocation(), wolfram.ingot_charged());
					}
				}
			}
		}else if(event.getEntityType()==EntityType.WOLF) {
			if(Interact.special_wolfs.containsValue(event.getEntity())) {
				Interact.special_wolfs.remove(((Tameable) event.getEntity()).getOwner(), event.getEntity());
			}
		}
		if(((CraftEntity) event.getEntity()).getHandle() instanceof fenrir) {
			if(event.getEntity().getKiller()!=null) {
				event.getDrops().clear();
				if(Random.random(0.5)) {
					if(!MoreBosses.getPlugin(MoreBosses.class).getConfig().getBoolean("bosses.custom_loot_pools.addon.nordic.fenrir")) {
						ArrayList<ItemStack> items = new ArrayList<ItemStack>();
						items.add(spirit.boots());
						items.add(spirit.chestplate());
						items.add(spirit.helmet());
						items.add(spirit.pants());
						items.add(spirit.spirit_item());
						items.add(spirit.blood_fenrir());
						event.getDrops().add(items.get(Random.random(items)));
						event.setDroppedExp(event.getDroppedExp()*10);
					}else {
						@SuppressWarnings("unchecked")
						ArrayList<ItemStack> a = (ArrayList<ItemStack>) ConfigManager.pools.getPools().get("MoreBosses.Pool.fenrir");
						while(a.remove(null)) {};
						event.getDrops().add(a.get(Random.random(a)));
						event.setDroppedExp(event.getDroppedExp()*10);
					}
				}
				if(Random.random(0.1)) {
					event.getDrops().add(shards.Midgardshard());
				}
			}
		}else if(((CraftEntity) event.getEntity()).getHandle() instanceof Warlock) {
			if(event.getEntity().getKiller()!=null) {
				event.getDrops().clear();
				event.setDroppedExp(event.getDroppedExp()*15);
				if(Random.random(0.25)) {
					if(!MoreBosses.getPlugin(MoreBosses.class).getConfig().getBoolean("bosses.custom_loot_pools.addon.nordic.warlock")) {
						event.getDrops().add(spirit.core());
					}else {
						@SuppressWarnings("unchecked")
						ArrayList<ItemStack> a = (ArrayList<ItemStack>) ConfigManager.pools.getPools().get("MoreBosses.Pool.warlock");
						while(a.remove(null)) {};
						event.getDrops().add(a.get(Random.random(a)));
						event.setDroppedExp(event.getDroppedExp()*10);
					}
				}
				if(Random.random(0.1)) {
					event.getDrops().add(shards.Midgardshard());
				}
			}
		}else if(((CraftEntity) event.getEntity()).getHandle() instanceof undead_viking_0||((CraftEntity) event.getEntity()).getHandle() instanceof undead_viking_1) {
			if(Random.random(0.01)) {
				ArrayList<ItemStack> prizes = new ArrayList<ItemStack>();
				prizes.add(runes.speed());
				prizes.add(runes.damage());
				prizes.add(runes.health());
				prizes.add(runes.knockback());
				event.getDrops().add(prizes.get(Random.random(prizes)));
			}
			if(Random.random(0.1)) {
				event.getDrops().add(shards.Midgardshard());
			}
		}
	}
}
