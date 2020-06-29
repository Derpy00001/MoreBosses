package me.derpy.bosses.mobs.tier3.abilities;

import org.bukkit.entity.EntityType;
import org.bukkit.entity.Slime;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import me.derpy.bosses.Morebosses;
import me.derpy.bosses.events.BossDamageByEntityEvent;
import me.derpy.bosses.mobs.tier3.BSlime;

public class AbilitySlime implements Listener {
	private final boolean ENABLED = Morebosses.getConfigurationHandler().openBossConfiguration("Tier3\\Slime.yml")
			.getBoolean("Slime.ability.enabled");
	private final int SLIME_COUNT = Morebosses.getConfigurationHandler().openBossConfiguration("Tier3\\Slime.yml")
			.getInt("Slime.ability.slime_count");
	private final int SLIME_SIZE = Morebosses.getConfigurationHandler().openBossConfiguration("Tier3\\Slime.yml")
			.getInt("Slime.size");

	@EventHandler
	public void onDamage(BossDamageByEntityEvent e) {
		if (this.ENABLED) {
			if (e.getBoss() instanceof BSlime) {
				if (!e.getEntity().isDead()) {
					for (int i = 1; i <= this.SLIME_COUNT; i++) {
						((Slime) e.getEntity().getWorld().spawnEntity(e.getEntity().getLocation(), EntityType.SLIME))
								.setSize(this.SLIME_SIZE / 2);
					}
				}
			}
		}
	}
}
