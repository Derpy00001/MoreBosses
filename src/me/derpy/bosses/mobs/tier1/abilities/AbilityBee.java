package me.derpy.bosses.mobs.tier1.abilities;

import java.util.Arrays;
import java.util.List;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Bee;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityEnterBlockEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import me.derpy.bosses.Morebosses;
import me.derpy.bosses.events.BossDamageByEntityEvent;
import me.derpy.bosses.events.EntityDamageByBossEvent;
import me.derpy.bosses.mobs.tier1.BBee;
import me.derpy.bosses.utilities.Console;

public class AbilityBee implements Listener {
	private final YamlConfiguration CONFIG = Morebosses.getConfigurationHandler()
			.openBossConfiguration("Tier1\\Bee.yml");
	private final boolean ENABLED = this.CONFIG != null ? this.CONFIG.getBoolean("Bee.ability.enabled") : true;
	private final int BEE_COUNT = this.CONFIG != null ? this.CONFIG.getInt("Bee.ability.bee_count") : 3;
	private final int EFFECT_SECONDS = this.CONFIG != null ? this.CONFIG.getInt("Bee.ability.effect_seconds") : 4;
	private final int EFFECT_LEVEL = this.CONFIG != null ? this.CONFIG.getInt("Bee.ability.effect_level") : 1;
	private final List<?> EFFECTS = this.CONFIG != null ? this.CONFIG.getList("Bee.ability.effects")
			: Arrays.asList("BLINDNESS", "SLOW", "WEAKNESS");

	@EventHandler
	public void onEnter(EntityEnterBlockEvent e) {
		if (e.getEntity().hasMetadata("Morebosses-BossId")) {
			if (e.getEntity().getMetadata("Morebosses-BossId").get(0).asInt() == 6) {
				if (e.getBlock().getType() == Material.BEEHIVE || e.getBlock().getType() == Material.BEE_NEST) {
					e.setCancelled(true);
				}
			}
		}
	}

	@EventHandler
	public void onDamaged(BossDamageByEntityEvent e) {
		if (e.getBoss() instanceof BBee) {
			if (this.ENABLED) {
				Location hive = ((Bee) e.getEntity()).getHive();
				if (hive == null) {
					hive = e.getEntity().getLocation();
				}
				for (int i = 1; i <= this.BEE_COUNT; i++) {
					hive.getWorld().spawnEntity(hive, EntityType.BEE);
				}
			}
		}
	}

	@EventHandler
	public void onDamage(EntityDamageByBossEvent e) {
		if (e.getBoss() instanceof BBee) {
			if (this.ENABLED) {
				Bee bee = (Bee) e.getDamager();
				bee.setHasStung(false);
				bee.setAnger(1);
				if (e.getDamager() instanceof LivingEntity) {
					for (Object obj : this.EFFECTS) {
						if (obj instanceof String) {
							if (PotionEffectType.getByName((String) obj) != null) {
								LivingEntity entity = (LivingEntity) e.getEntity();
								entity.addPotionEffect(new PotionEffect(PotionEffectType.getByName((String) obj),
										20 * this.EFFECT_SECONDS, this.EFFECT_LEVEL, false, false));
							} else {
								Console.print("Bee Ability: Could not find effect: " + obj);
							}
						}
					}
				}
			}
		}
	}
}
