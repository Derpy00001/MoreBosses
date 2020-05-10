package me.derpy.bosses.listeners;

import java.util.Arrays;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.CreatureSpawnEvent;
import org.bukkit.event.entity.CreatureSpawnEvent.SpawnReason;

import me.derpy.bosses.mobs.BossType;
import me.derpy.bosses.mobs.MobHandler;
import me.derpy.bosses.utilities.Random;

public class OnSpawn implements Listener {
	public final SpawnReason INVALID_REASON[] = { SpawnReason.BEEHIVE, SpawnReason.BREEDING, SpawnReason.CUSTOM,
			SpawnReason.BUILD_IRONGOLEM, SpawnReason.BUILD_SNOWMAN, SpawnReason.DEFAULT, SpawnReason.EXPLOSION,
			SpawnReason.SHEARED, SpawnReason.SHOULDER_ENTITY, SpawnReason.SLIME_SPLIT, SpawnReason.SPAWNER };
	public final BossType EXCEPTIONS[] = { BossType.BLAZE };
	public final BossType NO_RANDOM_SPAWNS[] = { BossType.GHAST };
//	public final World INVALID_WORLDS[] = { Morebosses.getWorldHandler().GHAST_ARENA,
//			Morebosses.getWorldHandler().COLOSSEUM_ARENA };

	@EventHandler
	public void onEntitySpawn(CreatureSpawnEvent e) {
//		if (!Arrays.asList(this.INVALID_WORLDS).contains(e.getLocation().getWorld())) {
		if (BossType.getFromEntityType(e.getEntityType()).length >= 1) {
			BossType type = Arrays.asList(BossType.getFromEntityType(e.getEntityType()))
					.get(Random.random(0, BossType.getFromEntityType(e.getEntityType()).length - 1));
			if (!Arrays.asList(this.INVALID_REASON).contains(e.getSpawnReason())) {

				if (!Arrays.asList(this.NO_RANDOM_SPAWNS).contains(type)) {
					if (Random.random(type.getInterface().getSpawnChance())) {
						e.setCancelled(true);
						try {
							MobHandler.spawnBossWithBar(e.getLocation(), type.getInterface());
						} catch (CloneNotSupportedException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}
				}
			} else {
				if (Arrays.asList(this.EXCEPTIONS).contains(type)) {
					if (Random.random(type.getInterface().getSpawnChance())) {
						e.setCancelled(true);
						try {
							MobHandler.spawnBossWithBar(e.getLocation(), type.getInterface());
						} catch (CloneNotSupportedException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}
				}
			}
		}
	}
//	}
}
