package me.Derpy.Bosses.mobs.merchants;

import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;

import net.md_5.bungee.api.ChatColor;

public class merchant {
	@SuppressWarnings("deprecation")
	public static void newmerchant(LivingEntity entity) {
		entity.setCustomName(ChatColor.GOLD+"Merchant");
		for(Entity ent : entity.getNearbyEntities(20, 20, 20)) {
			if(ent instanceof Player) {
				Player p = (Player) ent;
				p.sendMessage("<"+ChatColor.GOLD+"Merchant"+ChatColor.RESET+"> Hello warrior.");
			}
			if(ent.getType()==EntityType.TRADER_LLAMA) {
				ent.remove();
			}
		}
		entity.getWorld().spawnEntity(entity.getLocation(), EntityType.TRADER_LLAMA).setPassenger(entity);
	}
}
