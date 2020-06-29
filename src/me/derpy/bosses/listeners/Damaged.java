package me.derpy.bosses.listeners;

import org.bukkit.Bukkit;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;

import me.derpy.bosses.events.BossDamageByEntityEvent;
import me.derpy.bosses.events.EntityDamageByBossEvent;
import me.derpy.bosses.mobs.BossType;

public class Damaged implements Listener {
	@EventHandler
	public void onDamaged(EntityDamageByEntityEvent e) {
		if (e.getEntity().hasMetadata("Morebosses-BossId")) {
			int id = e.getEntity().getMetadata("Morebosses-BossId").get(0).asInt();
			for (BossType boss : BossType.values()) {
				if (boss.getId() == id) {
					BossDamageByEntityEvent event = new BossDamageByEntityEvent(e.getDamager(), e.getEntity(),
							boss.getInterface(), boss.getId(), e.getCause(), e.getDamage());
					Bukkit.getServer().getPluginManager().callEvent(event);
				}
			}
			// EntityDamageByBoss
		} else if (e.getDamager().hasMetadata("Morebosses-BossId")) {
			int id = e.getDamager().getMetadata("Morebosses-BossId").get(0).asInt();
			for (BossType boss : BossType.values()) {
				if (boss.getId() == id) {
					EntityDamageByBossEvent event = new EntityDamageByBossEvent(e.getDamager(), e.getEntity(),
							boss.getInterface(), boss.getId(), e.getCause(), e.getDamage());
					Bukkit.getServer().getPluginManager().callEvent(event);
				}
			}
		} else if (e.getCause() == DamageCause.PROJECTILE) {
			if (e.getDamager().getType() == EntityType.ARROW) {
				Arrow damager = (Arrow) e.getDamager();
				if (damager.getShooter() instanceof Entity) {
					Entity entity = (Entity) damager.getShooter();
					if (entity.hasMetadata("Morebosses-BossId")) {
						int id = entity.getMetadata("Morebosses-BossId").get(0).asInt();
						for (BossType boss : BossType.values()) {
							if (boss.getId() == id) {
								EntityDamageByBossEvent event = new EntityDamageByBossEvent(entity, e.getEntity(),
										boss.getInterface(), id, e.getCause(), e.getDamage());
								Bukkit.getServer().getPluginManager().callEvent(event);
							}
						}
					}
				}
			}
		}
	}
}
