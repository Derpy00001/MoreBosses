package me.derpy.bosses.mobs;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Slime;
import org.bukkit.inventory.ItemStack;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.plugin.java.JavaPlugin;

import me.derpy.bosses.Morebosses;
import me.derpy.bosses.events.BossSpawnEvent;
import me.derpy.bosses.mobs.interfaces.IAvian;
import me.derpy.bosses.mobs.interfaces.IBoss;
import me.derpy.bosses.mobs.interfaces.IEquipable;
import me.derpy.bosses.mobs.interfaces.IHostile;
import me.derpy.bosses.mobs.interfaces.IMount;
import me.derpy.bosses.mobs.interfaces.ISizeable;
import me.derpy.bosses.utilities.Random;

public class MobHandler {
	public MobHandler() {

	}

	public static LivingEntity spawnBossWithBar(Location location, IBoss boss) throws CloneNotSupportedException {
		return Morebosses.getBarHandler().spawnBarBoss(location, boss);
	}

	// Main
	public static LivingEntity spawnBoss(Location location, IBoss boss) {
		LivingEntity mob = (LivingEntity) location.getWorld().spawnEntity(location, boss.getEntityType());
		BossSpawnEvent event = new BossSpawnEvent(false, mob, boss.getBossId(), boss, location);
		Bukkit.getServer().getPluginManager().callEvent(event);
		if (!event.isCancelled()) {
			setAttribute(mob, Attribute.GENERIC_MAX_HEALTH, boss.getHealthMultiplier());
			setAttribute(mob, Attribute.GENERIC_MOVEMENT_SPEED, boss.getSpeedMultiplier() / 2);
			setAttribute(mob, Attribute.GENERIC_ARMOR, boss.getArmorMultiplier());
			setAttribute(mob, Attribute.GENERIC_ARMOR_TOUGHNESS, boss.getArmorStrengthMultiplier());
			setAttribute(mob, Attribute.GENERIC_KNOCKBACK_RESISTANCE, boss.getKnockbackResistance());
			if (boss instanceof IHostile) {
				setAttribute(mob, Attribute.GENERIC_ATTACK_DAMAGE, ((IHostile) boss).getDamageMultiplier());
				setAttribute(mob, Attribute.GENERIC_ATTACK_SPEED, ((IHostile) boss).getAttackSpeedMultiplier());
				setAttribute(mob, Attribute.GENERIC_FOLLOW_RANGE, ((IHostile) boss).getFollowRange());
			}
			if (boss instanceof IEquipable) {
				if (((IEquipable) boss).getHelmetChoices() != null) {
					if (((IEquipable) boss).getHelmetChoices().size() > 0) {
						if (Random.random(0.85)) {
							mob.getEquipment()
									.setHelmet(
											new ItemStack(
													((IEquipable) boss).getHelmetChoices()
															.get(Random.random(0,
																	((IEquipable) boss).getHelmetChoices().size() - 1)),
													1));
						}
					}
				}
				if (((IEquipable) boss).getChestplateChoices() != null) {
					if (((IEquipable) boss).getChestplateChoices().size() > 0) {
						if (Random.random(0.85)) {
							mob.getEquipment()
									.setChestplate(new ItemStack(
											((IEquipable) boss).getChestplateChoices()
													.get(Random.random(0,
															((IEquipable) boss).getChestplateChoices().size() - 1)),
											1));
						}
					}
				}
				if (((IEquipable) boss).getLeggingChoices() != null) {
					if (((IEquipable) boss).getLeggingChoices().size() > 0) {
						if (Random.random(0.85)) {
							mob.getEquipment().setLeggings(new ItemStack(((IEquipable) boss).getLeggingChoices()
									.get(Random.random(0, ((IEquipable) boss).getLeggingChoices().size() - 1)), 1));
						}
					}
				}
				if (((IEquipable) boss).getBootChoices() != null) {
					if (((IEquipable) boss).getBootChoices().size() > 0) {
						if (Random.random(0.85)) {
							mob.getEquipment()
									.setBoots(
											new ItemStack(
													((IEquipable) boss).getBootChoices()
															.get(Random.random(0,
																	((IEquipable) boss).getBootChoices().size() - 1)),
													1));
						}
					}
				}
				if (((IEquipable) boss).getWeaponChoices() != null) {
					if (((IEquipable) boss).getWeaponChoices().size() > 0) {
						if (Random.random(0.85)) {
							mob.getEquipment()
									.setItemInMainHand(
											new ItemStack(
													((IEquipable) boss).getWeaponChoices()
															.get(Random.random(0,
																	((IEquipable) boss).getWeaponChoices().size() - 1)),
													1));
						}
					}
				}
			}
			if (boss instanceof IAvian) {
				setAttribute(mob, Attribute.GENERIC_FLYING_SPEED, ((IAvian) boss).getFlightMultiplier());
			}
			if (boss.getMinions() > 0) {
				for (int i = 0; i <= boss.getMinions(); i++) {
					location.getWorld().spawnEntity(mob.getLocation(), mob.getType());
				}
			}
			if (boss instanceof ISizeable) {
				((Slime) mob).setSize(((ISizeable) boss).getSize());
			}
			if (boss instanceof IMount) {
				if (((IMount) boss).getMounts().size() > 0) {
					EntityType type = ((IMount) boss).getMounts()
							.get(Random.random(0, ((IMount) boss).getMounts().size() - 1));
					mob.addPassenger(mob.getWorld().spawnEntity(mob.getLocation(), type));
				}
			}
			mob.setHealth(mob.getAttribute(Attribute.GENERIC_MAX_HEALTH).getValue());
			mob.setRemoveWhenFarAway(true);
			mob.setMetadata("Morebosses-BossId",
					new FixedMetadataValue(JavaPlugin.getPlugin(Morebosses.class), boss.getBossId()));
			mob.setMetadata("Morebosses-Spoils",
					new FixedMetadataValue(JavaPlugin.getPlugin(Morebosses.class), boss.getMappedDrops()));
			if (JavaPlugin.getPlugin(Morebosses.class).getConfig().getBoolean("bosses.glowing")) {
				mob.setGlowing(true);
			}
			return mob;
		} else {
			mob.remove();
			return null;
		}

	}

	private static void setAttribute(LivingEntity e, Attribute a, double v) {
		try {
			e.getAttribute(a).setBaseValue(e.getAttribute(a).getDefaultValue() * v);
		} catch (NullPointerException exception) {
//			Bukkit.getServer().broadcastMessage("Failed to apply attribute: \""+a.name()+"\" to: \""+e.getType().name()+"\"");
		}
	}
}
