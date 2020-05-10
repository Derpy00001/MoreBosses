package me.derpy.bosses.mobs.tier2.abilities;

import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Monster;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import me.derpy.bosses.Morebosses;
import me.derpy.bosses.events.BossDamageByEntityEvent;
import me.derpy.bosses.mobs.tier2.BBlaze;
import me.derpy.bosses.utilities.Console;

public class AbilityBlaze implements Listener {
	private final YamlConfiguration CONFIG = Morebosses.getConfigurationHandler()
			.openBossConfiguration("Tier2\\Blaze.yml");
	private final double DISTANCE = this.CONFIG != null ? this.CONFIG.getDouble("Blaze.ability.distance") : 10.0;
	private final boolean ENABLED = this.CONFIG != null ? this.CONFIG.getBoolean("Blaze.ability.enabled") : true;
	private final double FIRE_SECONDS = this.CONFIG != null ? this.CONFIG.getDouble("Blaze.ability.fire-seconds")
			: 10.0;

	@EventHandler
	public void onDamage(BossDamageByEntityEvent e) {
		Console.print(Boolean.toString(this.CONFIG != null));
		Console.print(Double.toString(this.DISTANCE) + " | " + Boolean.toString(this.ENABLED) + " | "
				+ Double.toString(this.FIRE_SECONDS));
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
