package me.Derpy.Bosses.listeners;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map.Entry;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.AreaEffectCloud;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.TNTPrimed;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityTransformEvent;
import org.bukkit.event.entity.EntityTransformEvent.TransformReason;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.event.server.ServerListPingEvent;
import org.bukkit.inventory.meta.EnchantmentStorageMeta;
import org.bukkit.inventory.meta.ItemMeta;
import org.spigotmc.event.entity.EntityDismountEvent;

import me.Derpy.Bosses.MoreBosses;
import me.Derpy.Bosses.enchants.EnchantLevel;
import me.Derpy.Bosses.enchants.LoadEnchants;
import me.Derpy.Bosses.events.ghastevent;
import me.Derpy.Bosses.events.gladiator;
import me.Derpy.Bosses.mobs.tier1.Drowned;
import me.Derpy.Bosses.utilities.items.nukes;

public class ondismount implements Listener{
	private static MoreBosses plugin;
	public ondismount(MoreBosses plugin) {
		// TODO Auto-generated constructor stub
		ondismount.plugin = plugin;
	}
	@EventHandler
	public static void dismount(EntityDismountEvent event) {
		LivingEntity entity = (LivingEntity) event.getEntity();
		if(entity.getType()==EntityType.HUSK) {
			if(entity.isInvulnerable()) {
				if(entity.isSilent()) {
					event.setCancelled(true);
				}
			}
		}
	}
	@EventHandler
	public static void zombieconversion(EntityTransformEvent event) {
		Entity entity = event.getEntity();
		if(!(entity.isDead())) {
			LivingEntity entity1 = (LivingEntity) entity;
			if(event.getTransformReason()==TransformReason.DROWNED) {
				if(entity.isSilent()) {
					event.setCancelled(true);
					event.getEntity().remove();
					LivingEntity drowned = (LivingEntity) entity1.getWorld().spawnEntity(entity1.getLocation(), EntityType.DROWNED);
					Drowned.newdrowned(drowned, plugin, true);
				}
			}
		}
	}
	@SuppressWarnings("deprecation")
	@EventHandler
	public static void inv(InventoryClickEvent event) {
		if(event.getInventory().getType()==InventoryType.WORKBENCH) {
			if(event.getView().getTitle().equals(ChatColor.DARK_RED+"Recipe")) {
				event.setCancelled(true);
			}
		}
		if(event.getClick()==ClickType.RIGHT) {
			if(event.getCursor().getType()==Material.ENCHANTED_BOOK) {
				EnchantmentStorageMeta storage = (EnchantmentStorageMeta) event.getCursor().getItemMeta();
				for(Entry<Enchantment, Integer> nums : storage.getStoredEnchants().entrySet()) {
					if(LoadEnchants.isenchantment(nums.getKey())) {
						Enchantment ench = nums.getKey();
						if(event.getCurrentItem().getType()==Material.ENCHANTED_BOOK) {
							EnchantmentStorageMeta storage2 = (EnchantmentStorageMeta) event.getCurrentItem().getItemMeta();
							for(Entry<Enchantment, Integer> nums2 : storage2.getStoredEnchants().entrySet()) {
								if(nums2.getKey()==ench) {
									if(nums.getValue()==nums2.getValue()) {
										if(nums.getValue()+1<=ench.getMaxLevel()) {
											event.setCursor(null);
											storage2.removeStoredEnchant(ench);
											storage2.addStoredEnchant(ench, nums.getValue()+1, false);
											storage2.setLore(Arrays.asList(EnchantLevel.returnEnchantmentName(ench, nums.getValue()+1, ChatColor.GRAY), ChatColor.RESET+""+ChatColor.RED+"Right click an item with this on your cursor"));
											event.getCurrentItem().setItemMeta(storage2);
										}
									}
								}
							}
						}else {
							if(ench.canEnchantItem(event.getCurrentItem())) {
								for(Enchantment encha : event.getCurrentItem().getItemMeta().getEnchants().keySet()) {
									if(ench.conflictsWith(encha)) {
										return;
									}
								}
								if(!(event.getCurrentItem().getEnchantments().containsKey(ench))) {
									event.setCursor(null);
									event.getCurrentItem().addUnsafeEnchantment(ench, nums.getValue());
									if(event.getCurrentItem().getItemMeta().hasLore()) {
										ItemMeta meta = event.getCurrentItem().getItemMeta();
										List<String> oldlore = event.getCurrentItem().getItemMeta().getLore();
										oldlore.add(0, EnchantLevel.returnEnchantmentName(ench, storage.getStoredEnchantLevel(ench), ChatColor.GRAY));
										meta.setLore(oldlore);
										event.getCurrentItem().setItemMeta(meta);
								
									}else {
										ItemMeta meta = event.getCurrentItem().getItemMeta();
										ArrayList<String> lore = new ArrayList<String>();
										lore.add(EnchantLevel.returnEnchantmentName(ench, storage.getStoredEnchantLevel(ench), ChatColor.GRAY));
										meta.setLore(lore);
										event.getCurrentItem().setItemMeta(meta);
									}
								}
							}
						}
					}
				}
			}
		}
//		}else if(event.getInventory().getType()==InventoryType.ANVIL) {
//			if(event.getCurrentItem().getType()!=Material.AIR&&event.getCurrentItem().getItemMeta().hasLore()) {
//				if(event.getCurrentItem().getItemMeta().isUnbreakable()&&event.getCurrentItem().getItemMeta().hasItemFlag(ItemFlag.HIDE_PLACED_ON)) {
//					event.setCancelled(true);
//				}
//			}
//		}
	}
	@EventHandler
    public void on(ServerListPingEvent event) {
       if(plugin.getDescription().getVersion().contains("TEST_VERSION")) {
	        event.setMotd(ChatColor.GOLD+"        | Running Dev_Version MoreBosses |\n             Version: "+plugin.getDescription().getVersion());
       }
    }
	@EventHandler
	public void onPlayerMove(PlayerMoveEvent e) {
		Location loc = new Location(e.getTo().getWorld(), e.getTo().getX(), e.getTo().getY(), e.getTo().getZ());
		loc.setY(loc.getY()-1);
		if(loc.getBlock().getType()==Material.BARRIER) {
			if(loc.getBlock().getWorld().getName().contains("MoreBosses")) {
				if(e.getPlayer().getGameMode()==GameMode.ADVENTURE) {
					e.getPlayer().damage(1000);
				}
			}
		}
	}
	@EventHandler
	public void onquit(PlayerQuitEvent event) throws InterruptedException {
		if(event.getPlayer().getWorld().getName().contains("MoreBosses")) {
			if(!(event.getPlayer().getWorld().getName().contains("Addon"))) {
				if(event.getPlayer().getWorld().getName().equals("MoreBosses-Colosseum")) {
					gladiator.checkwave(event.getPlayer());
					event.getPlayer().teleport(Bukkit.getWorlds().get(0).getSpawnLocation());
				}else if(event.getPlayer().getWorld().getName().equals("MoreBosses-void")) {
					ghastevent.check();
					event.getPlayer().teleport(Bukkit.getWorlds().get(0).getSpawnLocation());
				}
			}
		}
	}
	@EventHandler
	public void onplace(BlockPlaceEvent event) {
		if(event.getItemInHand().isSimilar(nukes.nuke0())) {
			event.setCancelled(true);
			event.getItemInHand().setAmount(event.getItemInHand().getAmount()-1);
			final TNTPrimed tnt= (TNTPrimed) event.getBlock().getWorld().spawnEntity(event.getBlock().getLocation(), EntityType.PRIMED_TNT);
			tnt.setFuseTicks(20*30);
			tnt.setYield(100);
			tnt.setIsIncendiary(true);
			plugin.getServer().getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() {
				  public void run() {
					  AreaEffectCloud cloud = (AreaEffectCloud) tnt.getWorld().spawnEntity(tnt.getLocation(), EntityType.AREA_EFFECT_CLOUD);
					  cloud.setDuration(20*10);
					  cloud.setParticle(Particle.FLASH);
					  cloud.setRadius(10);
				 }
//			}, 6000L);
			}, 20*30);
		}
	}
}
