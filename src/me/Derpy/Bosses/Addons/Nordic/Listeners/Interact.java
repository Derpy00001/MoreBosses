package me.Derpy.Bosses.Addons.Nordic.Listeners;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.bukkit.Bukkit;
import org.bukkit.DyeColor;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.craftbukkit.v1_14_R1.entity.CraftPlayer;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Tameable;
import org.bukkit.entity.Wolf;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import me.Derpy.Bosses.Addons.Nordic.Items.shards;
import me.Derpy.Bosses.Addons.Nordic.Items.spirit;
import me.Derpy.Bosses.Addons.Nordic.Items.wolfram;
import me.Derpy.Bosses.Addons.Utilities.LoadAddonWorlds;
import me.Derpy.Bosses.utilities.Random;
import net.minecraft.server.v1_14_R1.EnumHand;
import net.minecraft.server.v1_14_R1.PacketPlayInArmAnimation;
import net.minecraft.server.v1_14_R1.PacketPlayOutAnimation;
import net.minecraft.server.v1_14_R1.PlayerConnection;

public class Interact implements Listener{
	public static Map<Player, Wolf> special_wolfs = new HashMap<Player, Wolf>();
	@EventHandler
	public static void use(PlayerInteractEvent event) {
		if(event.getAction()==Action.RIGHT_CLICK_AIR||event.getAction()==Action.RIGHT_CLICK_BLOCK) {
			Player p = event.getPlayer();
			if(p.getInventory().getItemInMainHand().isSimilar(shards.Alfheimrshard())) {
				p.getInventory().getItemInMainHand().setAmount(p.getInventory().getItemInMainHand().getAmount()-1);
				p.teleport(LoadAddonWorlds.Alfheimr.getSpawnLocation());
			}else if(p.getInventory().getItemInMainHand().isSimilar(shards.Midgardshard())) {
				p.getInventory().getItemInMainHand().setAmount(p.getInventory().getItemInMainHand().getAmount()-1);
				p.teleport(Bukkit.getWorlds().get(0).getSpawnLocation());
			}
		}
		if(event.getAction()==Action.RIGHT_CLICK_BLOCK) {
			Player p = event.getPlayer();
			try {
				if(p.getInventory().getBoots().isSimilar(spirit.boots())&&p.getInventory().getLeggings().isSimilar(spirit.pants())&&p.getInventory().getChestplate().isSimilar(spirit.chestplate())&&p.getInventory().getHelmet().isSimilar(spirit.helmet())) {
					if(p.getInventory().getItemInMainHand().getType()==Material.AIR) {
						PlayerConnection con = ((CraftPlayer) p).getHandle().playerConnection;
						con.sendPacket(new PacketPlayOutAnimation(((CraftPlayer) p).getHandle(), 0));
						con.a(new PacketPlayInArmAnimation(EnumHand.MAIN_HAND));
						if(p.getFoodLevel()>=1) {
							Location loc = event.getClickedBlock().getLocation();
							loc.setY(loc.getY()+1);
							if(loc.getBlock().getType()==Material.AIR) {
								loc.getBlock().setType(Material.FIRE);
								p.setFoodLevel(p.getFoodLevel()-1);
							}
						}
					}
				}
			}catch(NullPointerException ignored) {};
			if(p.getInventory().getItemInMainHand().isSimilar(spirit.core_charged_wolf())) {
				PlayerConnection con = ((CraftPlayer) p).getHandle().playerConnection;
				con.sendPacket(new PacketPlayOutAnimation(((CraftPlayer) p).getHandle(), 0));
				con.a(new PacketPlayInArmAnimation(EnumHand.MAIN_HAND));
				if(!special_wolfs.containsKey(p)) {
					for(int i=0;i<=4;i++) {
						if(p.getFoodLevel()>=1) {
							if(p.getNearbyEntities(10, 10, 10).size()>0) {
								Wolf wolf = (Wolf) p.getWorld().spawnEntity(Random.randomlocation(p.getLocation(), 5, 1), EntityType.WOLF);
								wolf.setOwner(p);
								wolf.setAngry(true);
								ArrayList<LivingEntity> ents = new ArrayList<LivingEntity>();
								for(Entity e : p.getNearbyEntities(10, 10, 10)) {
									if(e instanceof LivingEntity) {
										if(!(e instanceof Wolf)) {
											ents.add((LivingEntity) e);
										}else {
											if(!(((Tameable) e).getOwner() == p)) {
												ents.add((LivingEntity) e);
											}
										}
									}
								}
								if(ents.size()>0) {
									wolf.setTarget(ents.get(Random.random(ents)));
								}
								wolf.setAdult();
								wolf.setBreed(false);
								wolf.setCollarColor(DyeColor.BLACK);
								wolf.addPotionEffect(new PotionEffect(PotionEffectType.WITHER, 100000000, 0, false, false, false));
								special_wolfs.put(p, wolf);
								p.setFoodLevel(p.getFoodLevel()-1);
							}
						}
					}
				}else {
					if(!special_wolfs.get(p).isValid()) {
						special_wolfs.remove(p);
					}
				}
			}
		}
//		if(event.getAction()==Action.LEFT_CLICK_AIR) {
//			Player p = event.getPlayer();
//			if(p.getInventory().getBoots().isSimilar(spirit.boots())&&p.getInventory().getLeggings().isSimilar(spirit.pants())&&p.getInventory().getChestplate().isSimilar(spirit.chestplate())&&p.getInventory().getHelmet().isSimilar(spirit.helmet())) {
//				if(p.getInventory().getItemInMainHand().getType()==Material.AIR) {
//					PlayerConnection con = ((CraftPlayer) p).getHandle().playerConnection;
//					con.sendPacket(new PacketPlayOutAnimation(((CraftPlayer) p).getHandle(), 0));
//					con.a(new PacketPlayInArmAnimation(EnumHand.MAIN_HAND));
//					if(p.getFoodLevel()>=1) {
//						SmallFireball ball = p.launchProjectile(SmallFireball.class);
//						ball.setVelocity(p.getLocation().getDirection());
//						p.setFoodLevel(p.getFoodLevel()-1);
//					}
//				}
//			}
//		}
	}
	@SuppressWarnings("deprecation")
	@EventHandler
	public static void onbreak(BlockBreakEvent event) {
		if(event.getBlock().getType()==Material.IRON_ORE) {
			if(event.getBlock().getWorld().getName().contains("MoreBosses-Addon-Nordic-Alfheimr")) {
				if(event.getPlayer().getInventory().getItemInMainHand().isSimilar(wolfram.wolfram_pickmattock_durability(event.getPlayer().getInventory().getItemInMainHand().getDurability()))) {
					event.setDropItems(false);
					if(Random.random(0.15)) {
						event.getBlock().getWorld().dropItemNaturally(event.getBlock().getLocation(), wolfram.ore());
					}
				}else {
					if(event.getPlayer().getGameMode()==GameMode.SURVIVAL) {
						event.setCancelled(true);
						Location loc = event.getBlock().getLocation();
						loc.setY(loc.getY()+1);
						loc.setZ(loc.getZ()+0.5);
						loc.setX(loc.getX()+0.5);
						event.getBlock().getWorld().spawnParticle(Particle.FLASH, loc, 3, 0, 0.5, 0, 0);
					}
				}
			}
		}
		if(event.getBlock().getWorld().getName().contains("MoreBosses-Addon-Nordic")) {
			List<Material> leaves = Arrays.asList(Material.ACACIA_LEAVES, Material.BIRCH_LEAVES, Material.DARK_OAK_LEAVES, Material.JUNGLE_LEAVES, Material.OAK_LEAVES, Material.SPRUCE_LEAVES);
			if(leaves.contains(event.getBlock().getType())) {
				if(Random.random(0.01)) {
					event.getBlock().getDrops().add(shards.Midgardshard());
				}
			}
		}
	}
}
