package me.derpy.bosses.mobs.tier4.abilities;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import me.derpy.bosses.Morebosses;
import me.derpy.bosses.events.EntityDamageByBossEvent;
import me.derpy.bosses.mobs.tier4.BMagma;

public class AbilityMagma implements Listener {
	private final boolean ENABLED = Morebosses.getConfigurationHandler().openBossConfiguration("Tier4\\MagmaCube.yml")
			.getBoolean("Magma.ability.enabled");
	private final int FIRE_SECONDS = Morebosses.getConfigurationHandler().openBossConfiguration("Tier4\\MagmaCube.yml")
			.getInt("Magma.ability.fire_seconds");

	@EventHandler
	public void OnDamage(EntityDamageByBossEvent e) {
		if (this.ENABLED) {
			if (e.getBoss() instanceof BMagma) {
				if (!e.getEntity().isDead()) {
					e.getEntity().setFireTicks(20 * this.FIRE_SECONDS);
				}
			}
		}
	}
}
