package me.Derpy.Bosses.listeners;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityTransformEvent;
import org.bukkit.event.entity.EntityTransformEvent.TransformReason;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.event.player.PlayerTeleportEvent;
import org.bukkit.event.server.ServerListPingEvent;
import org.spigotmc.event.entity.EntityDismountEvent;

import me.Derpy.Bosses.Main;
import me.Derpy.Bosses.events.ghastevent;
import me.Derpy.Bosses.events.gladiator;
import me.Derpy.Bosses.mobs.tier1.Drowned;

public class ondismount implements Listener{
	private static Main plugin;
	public ondismount(Main plugin) {
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
	@EventHandler
	public static void inv(InventoryClickEvent event) {
		if(event.getInventory().getType()==InventoryType.WORKBENCH) {
			if(event.getView().getTitle().equals(ChatColor.DARK_RED+"Recipe")) {
				event.setCancelled(true);
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
       if(plugin.getDescription().getVersion().equals("TEST_VERSION")) {
	        event.setMotd(ChatColor.GOLD+"        | Running Dev_Version MoreBosses |\n                      Have fun :D");
	        event.setMaxPlayers(420);
       }
    }
	@EventHandler
	public void onteleport(PlayerTeleportEvent event) {
		if(event.getTo().getWorld().getName().equals("MoreBosses-void")) {
			if(!(plugin.getConfig().getBoolean("ghastevent.active"))) {
				if(!(event.getPlayer().hasPermission("bosses.raids.teleport.bypass"))) {
					event.setCancelled(true);
					event.getPlayer().setHealth(0);
				}
			}
		}else if(event.getTo().getWorld().getName().equals("MoreBosses-Colosseum")) {
			if(gladiator.getPlayer()==event.getPlayer()) {
				if(!(event.getPlayer().hasPermission("bosses.raids.teleport.bypass"))) {
					event.setCancelled(true);
					event.getPlayer().setHealth(0);
				}
			}
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
		if(loc.getWorld().getName().equals("MoreBosses-void")) {
			if(!(plugin.getConfig().getBoolean("ghastevent.active"))) {
				if(!(e.getPlayer().hasPermission("bosses.raids.teleport.bypass"))) {
					e.getPlayer().setHealth(0);
				}
			}
		}else if(loc.getWorld().getName().equals("MoreBosses-Colosseum")) {
			if(!(plugin.getConfig().getBoolean("raids.gladiator.active"))) {
				if(!(e.getPlayer().hasPermission("bosses.raids.teleport.bypass"))) {
					e.getPlayer().setHealth(0);
				}
			}
		}
	}
	@EventHandler
	public void onquit(PlayerQuitEvent event) throws InterruptedException {
		if(event.getPlayer().getWorld().getName().contains("MoreBosses")) {
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
