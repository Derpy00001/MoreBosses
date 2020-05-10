package me.derpy.bosses.mobs.tier3.abilities;

import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Slime;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import me.derpy.bosses.Morebosses;
import me.derpy.bosses.events.BossDamageByEntityEvent;
import me.derpy.bosses.mobs.tier3.BSlime;

public class AbilitySlime implements Listener {
	private final YamlConfiguration CONFIG = Morebosses.getConfigurationHandler()
			.openBossConfiguration("Tier3\\Slime.yml");
	private final boolean ENABLED = this.CONFIG != null ? this.CONFIG.getBoolean("Slime.ability.enabled") : true;
	private final int SLIME_COUNT = this.CONFIG != null ? this.CONFIG.getInt("Slime.ability.slime_count") : 2;
	private final int SLIME_SIZE = this.CONFIG != null ? this.CONFIG.getInt("Slime.size") : 8;

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
