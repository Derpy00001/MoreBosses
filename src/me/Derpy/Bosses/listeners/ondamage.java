package me.Derpy.Bosses.listeners;

import java.util.Arrays;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.GameRule;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Animals;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Blaze;
import org.bukkit.entity.Creeper;
import org.bukkit.entity.EnderDragon;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Ghast;
import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Monster;
import org.bukkit.entity.Player;
import org.bukkit.entity.Projectile;
import org.bukkit.entity.Slime;
import org.bukkit.entity.Villager;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.util.Vector;

import me.Derpy.Bosses.MoreBosses;
import me.Derpy.Bosses.events.ghastevent;
import me.Derpy.Bosses.events.gladiator;
import me.Derpy.Bosses.utilities.Random;
import me.Derpy.Bosses.utilities.translate;
import me.Derpy.Bosses.utilities.items.infused_diamond;
import me.Derpy.Bosses.utilities.items.village;
import net.md_5.bungee.api.ChatColor;

public class ondamage implements Listener{
	private static MoreBosses plugin;
	public ondamage(MoreBosses plugin) {
		// TODO Auto-generated constructor stub
		ondamage.plugin = plugin;
	}
	@EventHandler
	public static void onbreak(BlockBreakEvent event) {
		if(event.getBlock().getType()==Material.END_GATEWAY) {
			if(!(event.getBlock().getBiome().name().toLowerCase().contains("end"))) {
				if(plugin.getConfig().getBoolean("tier4_bosses.Undeadwither_Killed")) {
					if(event.getPlayer().hasPermission("bosses.event")) {
						plugin.getConfig().set("tier4_bosses.Undeadwither_Killed", false);
						Bukkit.getServer().broadcastMessage(ChatColor.RED+""+ChatColor.BOLD+translate.get("breachend", plugin));
						event.getPlayer().sendMessage(ChatColor.GREEN+"You have forced the breach event to end");
					}else {
						event.getPlayer().sendMessage(ChatColor.RED+"You do not have the permissions required to force this event to end!");
						event.setCancelled(true);
					}
				}
			}
		}
	}
	@SuppressWarnings("deprecation")
	@EventHandler
	public static void Ondamage(final EntityDamageEvent event) throws InterruptedException {
		if(event.getEntityType()==EntityType.GHAST) {
			if(event.getEntity().getWorld().getName().contains("MoreBosses")) {
				plugin.getServer().getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() {
					public void run() {
						event.getEntity().setVelocity(new Vector(0,0,0));
					}
				}, 20L);
				if(Random.random(0.65)) {
					Integer min = 1;
					Integer max = 4;
					Integer range = max-min+1;
					int num = (int) ((int)(Math.random()*range)+min);
					if(num==4) {
						event.getEntity().teleport((Location) plugin.getConfig().get("raids.ghast_raid"));
					}else {
						event.getEntity().teleport((Location) plugin.getConfig().get("raids.teleports.ghast."+Integer.toString(num)));
					}
				}
			}
		}
		if(event.getEntityType()==EntityType.BLAZE) {
			if(event.getEntity().getWorld().getName().contains("MoreBosses")) {
				plugin.getServer().getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() {
					public void run() {
						event.getEntity().setVelocity(new Vector(0,0,0));
					}
				}, 20L);
			}
		}
		if(event.getEntityType()==EntityType.PLAYER) {
			if(event.getEntity().getWorld().getName().startsWith("MoreBosses")) {
				if(!(event.getEntity().getWorld().getName().contains("Addon"))) {
					if(!(event.getEntity().isDead())) {
						Player p = (Player) event.getEntity();
						if((p.getHealth()-event.getDamage())<=0) {
							if(!(p.getInventory().getItemInOffHand().getType()==Material.TOTEM_OF_UNDYING)) {
								event.setCancelled(true);
								p.getWorld().strikeLightningEffect(p.getLocation());
								p.teleport(p.getWorld().getSpawnLocation());
								if(!event.getEntity().getWorld().getGameRuleValue(GameRule.KEEP_INVENTORY)||!plugin.getConfig().getBoolean("world.keep_inventory")) {
									for (ItemStack itemStack : p.getInventory().getContents()) {
										if(!(itemStack==null)) {
											p.getWorld().dropItemNaturally(p.getLocation(), itemStack);
											p.getInventory().remove(itemStack);
										}
				                    }
								}
								p.getInventory().clear();
								p.setExp(0);
								p.setFoodLevel(20);
								p.setHealth(p.getMaxHealth());
								((HumanEntity) p).setGameMode(GameMode.SPECTATOR);
								if(p.getWorld().getName().equals("MoreBosses-void")) {
									ghastevent.check();
								}else if(p.getWorld().getName().equals("MoreBosses-Colosseum")){
										gladiator.checkwave(p);
								}
							}
						}
					}
				}
			}
		}
		if(event.getEntityType()==EntityType.BLAZE || event.getEntityType()==EntityType.ENDER_DRAGON) {
			if(event.getEntityType()==EntityType.BLAZE) {
				Blaze entity = (Blaze) event.getEntity();
				if (entity.isSilent()) {
					if(event.getCause()==DamageCause.ENTITY_ATTACK) {
						for(Entity entity1:entity.getNearbyEntities(10.0, 10.0, 10.0)) {
							if(entity1 instanceof Monster || entity1 instanceof Animals || entity1 instanceof Player)
							if(!(entity1.getType()==EntityType.BLAZE)) {
								entity1.setFireTicks(100);
							}
						}
					}
				}
			}
			if(event.getEntityType()==EntityType.ENDER_DRAGON) {
				EnderDragon entity = (EnderDragon) event.getEntity();
				if(entity.isSilent()) {
						for(int i = 0; i < 12 ; i++) {
							Entity entity2 = (Entity) entity.getWorld().spawnEntity(entity.getLocation(), EntityType.ENDERMITE);
							ItemStack armour = new ItemStack(Material.DIAMOND_BOOTS);
							armour.addUnsafeEnchantment(Enchantment.PROTECTION_FALL, 10000);
							((LivingEntity) entity2).getEquipment().setBoots(armour);
							((LivingEntity) entity2).getEquipment().setBootsDropChance(0);
							((LivingEntity) entity2).addPotionEffect(new PotionEffect(PotionEffectType.WITHER, 999999, 1, true));
						}
				}
			}
		}
		
	}
	
	@SuppressWarnings("deprecation")
	@EventHandler
	public static void Ondamage2(EntityDamageByEntityEvent event) {
		if(event.getDamager().getType()==EntityType.FIREBALL) {
			if(event.getEntity().getWorld().getName().contains("MoreBosses")) {
				if(event.getEntity().getType()==EntityType.GHAST) {
					event.setDamage(60);
				}else if(event.getEntity().getType()==EntityType.PIG_ZOMBIE || event.getEntityType()==EntityType.WITHER_SKELETON) {
					event.setDamage(1000);
				}else if(event.getEntity().getType()==EntityType.PLAYER) {
					if(((Entity) ((Projectile) event.getDamager()).getShooter()).getType()==EntityType.PLAYER) {
						event.setDamage(0);
					}
				}
			}
		}
		if(event.getDamager().getType()==EntityType.GUARDIAN||event.getDamager().getType()==EntityType.WITHER_SKELETON||event.getDamager().getType()==EntityType.ELDER_GUARDIAN) {
			if(event.getDamager().isSilent()) {
				if(event.getCause()==DamageCause.ENTITY_ATTACK) {
					Entity entity = (Entity) event.getEntity();
					((LivingEntity) entity).addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, 200, 2, true), true);
					if(event.getDamager().getType()==EntityType.WITHER_SKELETON||event.getDamager().getType()==EntityType.ELDER_GUARDIAN) {
						((LivingEntity) entity).addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 200, 2, true), true);
					}
				}
			}
		}
		if(event.getEntity().getType()==EntityType.SLIME||event.getEntity().getType()==EntityType.PLAYER) {
			if (event.getEntity().getType()==EntityType.SLIME) {
				Slime entity = (Slime) event.getEntity();
				if(entity.isSilent()) {
						if(event.getCause()==DamageCause.BLOCK_EXPLOSION||event.getCause()==DamageCause.ENTITY_EXPLOSION) {
							Slime newslime = (Slime) entity.getWorld().spawnEntity(entity.getLocation(), entity.getType());
							me.Derpy.Bosses.mobs.tier3.Slime.newslime((LivingEntity) newslime, plugin);
					}
				}
			}else if(event.getEntity().getType()==EntityType.PLAYER) {
				if(event.getEntity().getWorld().getName().contains("MoreBosses-void")) {
					if(event.getDamager().getType()==EntityType.EVOKER_FANGS) {
						for(Entity entitys : event.getEntity().getNearbyEntities(100, 100, 100)) {
							if (entitys instanceof Ghast) {
								if((((Ghast) entitys).getHealth()+event.getDamage())>((Ghast)entitys).getMaxHealth()) {
									((Ghast) entitys).setHealth(((Ghast) entitys).getMaxHealth());
								}else {
									((Ghast) entitys).setHealth(((Ghast) entitys).getHealth()+event.getDamage());
								}
							}
						}
					}
				}
				if(event.getCause()==DamageCause.PROJECTILE) {
					if(event.getDamager() instanceof Arrow) {
						LivingEntity shooter = (LivingEntity) ((Arrow) event.getDamager()).getShooter();
						if (shooter.getType()==EntityType.STRAY) {
							if(shooter.isSilent()) {
									shooter.getWorld().strikeLightning(event.getEntity().getLocation());
							}
						}
					}
				}
			}
		}
	}
	@EventHandler
	public void oninteractentity(PlayerInteractEntityEvent event) throws InterruptedException {
		Player p = (Player) event.getPlayer();
		if(event.getRightClicked()==null) {
			return;
		}
		if(event.getRightClicked().getType()==EntityType.CREEPER) {
			if(p.getInventory().getItemInMainHand().getType()==Material.DIAMOND) {
				Creeper creeper = (Creeper) event.getRightClicked();
				if(creeper.isPowered()) {
					if(!(p.getInventory().getItemInMainHand().getItemMeta().isUnbreakable())) {
						creeper.setPowered(false);
						ItemStack item = (ItemStack) p.getInventory().getItemInMainHand();
						item.setAmount(item.getAmount()-1);
						ItemStack item2 = infused_diamond.get();
						if(Arrays.asList(p.getInventory().getStorageContents()).contains(null)) {
							p.getInventory().addItem(item2);
						}else {
							p.getWorld().dropItem(event.getPlayer().getLocation(), item2);
						}
					}
				}
			}
		}else if(event.getRightClicked().getType()==EntityType.WANDERING_TRADER) {
			if(!(event.getPlayer().getWorld().getName().contains("MoreBosses"))) {
				try {
					if(event.getRightClicked().getCustomName().equals(ChatColor.GOLD+"Merchant")) {
						if(event.getPlayer().hasPotionEffect(PotionEffectType.HERO_OF_THE_VILLAGE)) {
							event.setCancelled(true);
							if(p.getInventory().getItemInMainHand().getType()==Material.EMERALD) {
								if(p.getInventory().getItemInMainHand().getAmount()>=43) {
									p.getInventory().getItemInMainHand().setAmount(p.getInventory().getItemInMainHand().getAmount()-43);
									p.getWorld().dropItemNaturally(event.getRightClicked().getLocation(), village.token());
								}else {
									p.sendMessage("<"+ChatColor.GOLD+"Merchant"+ChatColor.RESET+">"+ChatColor.GREEN+" The price of a challengers token is priced at 43 emeralds.");
								}
							}else {
								p.sendMessage("<"+ChatColor.GOLD+"Merchant"+ChatColor.RESET+">"+ChatColor.GREEN+" The price of a challengers token is priced at 43 emeralds.");
							}
						}else {
							p.sendMessage("<"+ChatColor.GOLD+"Merchant"+ChatColor.RESET+">"+ChatColor.GREEN+" Find me once you have been declared as a village's hero for the sale of an item that will test if you are a true warrior");
						}
					}
				}catch(NullPointerException e) {}
			}
		}else if(event.getRightClicked().getType()==EntityType.VILLAGER) {
			Villager vil = (Villager) event.getRightClicked();
			if(vil.isSleeping()) {
				return;
			}
			if(event.getPlayer().getInventory().getItemInMainHand().isSimilar(village.token())) {
				if(plugin.getConfig().getBoolean("worlds.enabled_worlds.MoreBossesColosseum")) {
					Integer ditems = 0;
					if(event.getPlayer().getInventory().getHelmet()!=null) {
						if(event.getPlayer().getInventory().getHelmet().getType()==Material.DIAMOND_HELMET) {
							ditems++;
						}
					}
					if(event.getPlayer().getInventory().getChestplate()!=null) {
						if(event.getPlayer().getInventory().getChestplate().getType()==Material.DIAMOND_CHESTPLATE) {
							ditems++;
						}
					}
					if(event.getPlayer().getInventory().getLeggings()!=null) {
						if(event.getPlayer().getInventory().getLeggings().getType()==Material.DIAMOND_LEGGINGS) {
							ditems++;
						}
					}
					if(event.getPlayer().getInventory().getBoots()!=null) {
						if(event.getPlayer().getInventory().getBoots().getType()==Material.DIAMOND_BOOTS) {
							ditems++;
						}
					}
					if(event.getPlayer().getInventory().contains(Material.DIAMOND_HELMET)) {
						ditems++;
					}
					if(event.getPlayer().getInventory().contains(Material.DIAMOND_CHESTPLATE)) {
						ditems++;
					}
					if(event.getPlayer().getInventory().contains(Material.DIAMOND_LEGGINGS)) {
						ditems++;
					}
					if(event.getPlayer().getInventory().contains(Material.DIAMOND_BOOTS)) {
						ditems++;
					}
					if(ditems>=2) {
						event.getPlayer().sendMessage("<"+ChatColor.YELLOW+"Villager"+ChatColor.RESET+"> "+ChatColor.YELLOW+"You are equipped with too many strong items");
						return;
					}
					if(!(plugin.getConfig().getBoolean("raids.gladiator.active"))) {
						event.getPlayer().getInventory().getItemInMainHand().setAmount(event.getPlayer().getInventory().getItemInMainHand().getAmount()-1);
						plugin.getConfig().set("raids.gladiator.active", true);
						plugin.saveConfig();
						gladiator.start(event.getPlayer());
					}else {
						p.sendMessage("<"+ChatColor.YELLOW+"Villager"+ChatColor.RESET+"> "+ChatColor.YELLOW+"Please come back later.");
					}
				}
			}
		}
	}

}
