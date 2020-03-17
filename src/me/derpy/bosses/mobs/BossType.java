package me.derpy.bosses.mobs;

import org.bukkit.entity.EntityType;

import me.derpy.bosses.mobs.interfaces.IBoss;
import me.derpy.bosses.mobs.tier0.BBee;
import me.derpy.bosses.mobs.tier1.BDrowned;
import me.derpy.bosses.mobs.tier1.BSkeleton;
import me.derpy.bosses.mobs.tier1.BZombie;
import me.derpy.bosses.mobs.tier2.BBlaze;
import me.derpy.bosses.mobs.tier2.BCreeper;
import me.derpy.bosses.mobs.tier2.BStray;
import me.derpy.bosses.mobs.tier3.BSlime;
import me.derpy.bosses.mobs.tier3.BWitherSkeleton;
import me.derpy.bosses.mobs.tier4.BMagma;
import me.derpy.bosses.mobs.tier4.BPigman;
import me.derpy.bosses.mobs.tier4.BRavager;
import me.derpy.bosses.mobs.tierraids.BGhast;

public enum BossType {
	BEE(new BBee()), DROWNED(new BDrowned()), ZOMBIE(new BZombie()), SKELETON(new BSkeleton()), BLAZE(new BBlaze()),
	CREEPER(new BCreeper()), STRAY(new BStray()), SLIME(new BSlime()), WITHER_SKELETON(new BWitherSkeleton()),
	PIG_ZOMBIE(new BPigman()), MAGMA_CUBE(new BMagma()), RAVAGER(new BRavager()), GHAST(new BGhast());

	public static BossType getFromName(String name) {
		for (BossType type : BossType.values()) {
			if (type.name().toLowerCase().equals(name.toLowerCase())) {
				return type;
			}
		}
		return null;
	}

	public static BossType getFromId(int id) {
		for (BossType type : BossType.values()) {
			if (type.getId() == id) {
				return type;
			}
		}
		return null;
	}

	public static BossType getFromInterfaceClass(Class<? extends IBoss> clazz) {
		for (BossType type : BossType.values()) {
			if (type.getInterface().getClass() == clazz) {
				return type;
			}
		}
		return null;
	}

	public static BossType getFromEntityType(EntityType entityType) {
		for (BossType type : BossType.values()) {
			if (type.getInterface().getEntityType() == entityType) {
				return type;
			}
		}
		return null;
	}

	private final IBoss iBoss;

	BossType(IBoss iBoss) {
		this.iBoss = iBoss;
	}

	public IBoss getInterface() {
		return this.iBoss;
	}

	public EntityType getEntityType() {
		return this.iBoss.getEntityType();
	}

	public int getId() {
		return this.iBoss.getBossId();
	}
}
