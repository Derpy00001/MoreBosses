package me.derpy.bosses.mobs.tier2.abilities;

import java.util.Arrays;
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
	private final double YIELD = this.CONFIG != null ? this.CONFIG.getDouble("Creeper.ability.explosion_yield") : 0;
	private final int MOB_COUNT = this.CONFIG != null ? this.CONFIG.getInt("Creeper.ability.mob_count") : 7;
	private final boolean ENABLED = this.CONFIG != null ? this.CONFIG.getBoolean("Creeper.ability.enabled") : true;
	private final boolean DESTROY = this.CONFIG != null ? this.CONFIG.getBoolean("Creeper.ability.destroy_blocks")
			: false;
	private final List<?> MOBS = this.CONFIG != null ? this.CONFIG.getList("Creeper.ability.mobs")
			: Arrays.asList("ZOMBIE", "SKELETON", "CREEPER", "DROWNED", "STRAY", "PILLAGER", "WITCH", "SILVERFISH");

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
							Object obj = this.MOBS.get(Random.random(0, this.MOBS.size() - 1));
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
