package me.Derpy.Bosses.listeners;

import org.bukkit.ChatColor;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityTransformEvent;
import org.bukkit.event.entity.EntityTransformEvent.TransformReason;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.event.server.ServerListPingEvent;
import org.spigotmc.event.entity.EntityDismountEvent;

import me.Derpy.Bosses.Main;
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
				event.setCancelled(true);
				event.getEntity().remove();
				LivingEntity drowned = (LivingEntity) entity1.getWorld().spawnEntity(entity1.getLocation(), EntityType.DROWNED);
				Drowned.newdrowned(drowned, plugin, true);
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
}
