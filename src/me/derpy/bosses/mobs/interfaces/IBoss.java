package me.derpy.bosses.mobs.interfaces;

import java.util.List;
import java.util.Map;

import org.bukkit.NamespacedKey;
import org.bukkit.entity.EntityType;
import org.bukkit.inventory.ItemStack;

import me.derpy.bosses.items.interfaces.ILootable;

public interface IBoss extends Cloneable {
	double getHealthMultiplier();

	void setHealthMultiplier(double d);

	double getSpeedMultiplier();

	void setSpeedMultiplier(double d);

	double getArmorMultiplier();

	void setArmorMultiplier(double d);

	double getArmorStrengthMultiplier();

	void setArmorStrengthMutliplier(double d);

	double getKnockbackResistance();

	void setKnockbackResistance(double d);

	int getMinions();

	void setMinions(int i);

	EntityType getEntityType();

	void setEntityType(EntityType type);

	List<Object> getSpoilsPool();

	void setSpoilsPool(List<Object> list);

	void addSpoil(ItemStack... item);

	void addSpoil(ILootable... item);

	void removeSpoil(Object... item);

	int getExperience();

	void setExperience(int i);

	int getBossId();

	Map<Integer, Object> getMappedDrops();

	boolean hasBossbar();

	NamespacedKey generateRandomKey(String name, String titleName);

	IBoss cloneBoss() throws CloneNotSupportedException;
}
