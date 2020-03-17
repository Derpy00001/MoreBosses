package me.derpy.bosses.mobs.tier2.abilities;

import java.util.List;

import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityExplodeEvent;

import me.derpy.bosses.Morebosses;
import me.derpy.bosses.utilities.Console;
import me.derpy.bosses.utilities.Random;

public class AbilityCreeper implements Listener {
	private final YamlConfiguration CONFIG = Morebosses.getConfigurationHandler()
			.openBossConfiguration("Tier2\\Creeper.yml");
	private final double YIELD = this.CONFIG.getDouble("Blaze.ability.explosion_yield");
	private final int MOB_COUNT = this.CONFIG.getInt("Blaze.ability.mob_count");
	private final boolean ENABLED = this.CONFIG.getBoolean("Blaze.ability.enabled");
	private final boolean DESTROY = this.CONFIG.getBoolean("Blaze.ability.destroy_blocks");
	private final List<?> MOBS = this.CONFIG.getList("Blaze.ability.mobs");

	@SuppressWarnings("deprecation")
	@EventHandler
	public void onExplode(EntityExplodeEvent e) {
		if (e.getEntity().hasMetadata("Morebosses-BossId")) {
			if (e.getEntity().getMetadata("Morebosses-BossId").get(0).asInt() == 4) {
				if (this.ENABLED) {
					e.setYield((float) this.YIELD);
					if (!this.DESTROY) {
						e.blockList().clear();
					}
					if (this.MOBS.size() > 0) {
						for (int i = 1; i <= this.MOB_COUNT; i++) {
							Console.print(Integer.toString(i));
							Object obj = this.MOBS.get(Random.random(0, this.MOBS.size()));
							if (obj instanceof String) {
								if (EntityType.fromName((String) obj) != null) {
									e.getLocation().getWorld().spawnEntity(e.getLocation(),
											EntityType.fromName((String) obj));
								}
							}
						}
					}
				}
			}
		}
	}
}
