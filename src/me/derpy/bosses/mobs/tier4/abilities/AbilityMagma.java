package me.derpy.bosses.mobs.tier4.abilities;

import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import me.derpy.bosses.Morebosses;
import me.derpy.bosses.events.EntityDamageByBossEvent;
import me.derpy.bosses.mobs.tier4.BMagma;

public class AbilityMagma implements Listener {
	private final YamlConfiguration CONFIG = Morebosses.getConfigurationHandler()
			.openBossConfiguration("Tier4\\MagmaCube.yml");
	private final boolean ENABLED = this.CONFIG != null ? this.CONFIG.getBoolean("Magma.ability.enabled") : true;
	private final int FIRE_SECONDS = this.CONFIG != null ? this.CONFIG.getInt("Magma.ability.fire_seconds") : 6;

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
