package me.derpy.bosses.mobs.tier2.abilities;

import org.bukkit.attribute.Attribute;
import org.bukkit.entity.LivingEntity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;

import me.derpy.bosses.Morebosses;
import me.derpy.bosses.events.EntityDamageByBossEvent;
import me.derpy.bosses.mobs.tier2.BStray;

public class AbilityStray implements Listener {
	private final boolean ENABLED = Morebosses.getConfigurationHandler().openBossConfiguration("Tier2\\Stray.yml")
			.getBoolean("Stray.ability.enabled");

	@EventHandler
	public void onDamage(EntityDamageByBossEvent e) {
		if (e.getBoss() instanceof BStray) {
			if (this.ENABLED) {
				e.getEntity().getWorld().strikeLightning(e.getEntity().getLocation());
			}
		}
	}

	@EventHandler
	public void onDamagedBoss(EntityDamageEvent e) {
		if (e.getCause() == DamageCause.LIGHTNING) {
			if (e.getEntity().hasMetadata("Morebosses-BossId")) {
				if (e.getEntity().getMetadata("Morebosses-BossId").get(0).asInt() == 5) {
					e.setCancelled(true);
					if (e.getEntity() instanceof LivingEntity) {
						((LivingEntity) e.getEntity()).setHealth(
								((LivingEntity) e.getEntity()).getAttribute(Attribute.GENERIC_MAX_HEALTH).getValue());
					}
				}
			}
		}
	}
}
