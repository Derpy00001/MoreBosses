package me.derpy.bosses.mobs.tier2.abilities;

import org.bukkit.entity.Entity;
import org.bukkit.entity.Monster;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import me.derpy.bosses.Morebosses;
import me.derpy.bosses.events.BossDamageByEntityEvent;
import me.derpy.bosses.mobs.tier2.BBlaze;

public class AbilityBlaze implements Listener {
	private final double DISTANCE = Morebosses.getConfigurationHandler().openBossConfiguration("Tier2\\Blaze.yml")
			.getDouble("Blaze.ability.distance");
	private final boolean ENABLED = Morebosses.getConfigurationHandler().openBossConfiguration("Tier2\\Blaze.yml")
			.getBoolean("Blaze.ability.enabled");
	private final double FIRE_SECONDS = Morebosses.getConfigurationHandler().openBossConfiguration("Tier2\\Blaze.yml")
			.getDouble("Blaze.ability.fire-seconds");

	@EventHandler
	public void onDamage(BossDamageByEntityEvent e) {
		if (e.getBoss() instanceof BBlaze) {
			if (this.ENABLED) {
				for (Entity entity : e.getEntity().getNearbyEntities(this.DISTANCE, this.DISTANCE, this.DISTANCE)) {
					if (!(entity instanceof Monster)) {
						entity.setFireTicks((int) (20 * this.FIRE_SECONDS));
					}
				}
			}
		}
	}
}
