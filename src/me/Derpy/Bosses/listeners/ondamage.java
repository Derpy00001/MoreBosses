package me.Derpy.Bosses.listeners;

import java.util.Arrays;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Animals;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Blaze;
import org.bukkit.entity.Creeper;
import org.bukkit.entity.EnderDragon;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Monster;
import org.bukkit.entity.Player;
import org.bukkit.entity.Slime;
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
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

import me.Derpy.Bosses.Main;
import me.Derpy.Bosses.events.ghastevent;
import me.Derpy.Bosses.utilities.translate;
import me.Derpy.Bosses.utilities.items.infused_diamond;
import net.md_5.bungee.api.ChatColor;

public class ondamage implements Listener{
	private static Main plugin;

	public ondamage(Main plugin) {
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
	public static void Ondamage(final EntityDamageEvent event) {
		if(event.getEntityType()==EntityType.GHAST) {
			if(event.getEntity().getWorld().getName().contains("MoreBosses")) {
				new BukkitRunnable(){
					@Override
					public void run(){
						event.getEntity().setVelocity(new Vector(0,0,0));
					}
				}.runTaskTimer(ondamage.plugin, 1, 1);
			}
		}
		if(event.getEntityType()==EntityType.PLAYER) {
			if(event.getEntity().getWorld().getName().startsWith("MoreBosses")) {
				if(!(event.getEntity().isDead())) {
					Player p = (Player) event.getEntity();
					if((p.getHealth()-event.getDamage())<=0) {
						event.setCancelled(true);
						p.getWorld().strikeLightningEffect(p.getLocation());
						for (ItemStack itemStack : p.getInventory().getContents()) {
							if(!(itemStack==null)) {
								p.getWorld().dropItemNaturally(p.getLocation(), itemStack);
								p.getInventory().remove(itemStack);
							}
	                    }
						p.getInventory().clear();
						p.setExp(0);
						p.setFoodLevel(20);
						p.setHealth(p.getMaxHealth());
						((HumanEntity) p).setGameMode(GameMode.SPECTATOR);
						ghastevent.check();
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
						}
				}
			}
		}
		
	}
	
	@EventHandler
	public static void Ondamage2(EntityDamageByEntityEvent event) {
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
	public static void oninteractentity(PlayerInteractEntityEvent event) {
		Player p = (Player) event.getPlayer();
		if(p.getInventory().getItemInMainHand().getType()==Material.DIAMOND) {
			if(event.getRightClicked().getType()==EntityType.CREEPER) {
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
		}
	}

}
