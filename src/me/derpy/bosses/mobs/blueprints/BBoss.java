package me.derpy.bosses.mobs.blueprints;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.bukkit.NamespacedKey;
import org.bukkit.entity.EntityType;
import org.bukkit.inventory.ItemStack;

import me.derpy.bosses.items.interfaces.ILootable;
import me.derpy.bosses.mobs.interfaces.IBoss;

public class BBoss implements IBoss {
	double healthMultiplier = 1;
	double speedMultiplier = 1;
	double armorMultiplier = 1;
	double armorStrengthMultiplier = 1;
	double knockbackMultiplier = 1;
	EntityType type;
	List<Object> spoilsPool = new ArrayList<Object>();
	int experience = 0;
	int minions = 0;

	public BBoss() {

	}

	@Override
	public EntityType getEntityType() {
		// TODO Auto-generated method stub
		return this.type;
	}

	@Override
	public void setEntityType(EntityType type) {
		this.type = type;
	}

	@Override
	public double getHealthMultiplier() {
		// TODO Auto-generated method stub
		return this.healthMultiplier;
	}

	@Override
	public void setHealthMultiplier(double d) {
		// TODO Auto-generated method stub
		this.healthMultiplier = d;
	}

	@Override
	public double getSpeedMultiplier() {
		// TODO Auto-generated method stub
		return this.speedMultiplier;
	}

	@Override
	public void setSpeedMultiplier(double d) {
		// TODO Auto-generated method stub
		this.speedMultiplier = d;
	}

	@Override
	public double getArmorMultiplier() {
		// TODO Auto-generated method stub
		return this.armorMultiplier;
	}

	@Override
	public void setArmorMultiplier(double d) {
		// TODO Auto-generated method stub
		this.armorMultiplier = d;
	}

	@Override
	public double getArmorStrengthMultiplier() {
		// TODO Auto-generated method stub
		return this.armorStrengthMultiplier;
	}

	@Override
	public void setArmorStrengthMutliplier(double d) {
		// TODO Auto-generated method stub
		this.armorStrengthMultiplier = d;
	}

	@Override
	public double getKnockbackResistance() {
		// TODO Auto-generated method stub
		return this.knockbackMultiplier;
	}

	@Override
	public void setKnockbackResistance(double d) {
		// TODO Auto-generated method stub
		this.knockbackMultiplier = d;
	}

	@Override
	public int getMinions() {
		// TODO Auto-generated method stub
		return this.minions;
	}

	@Override
	public void setMinions(int i) {
		// TODO Auto-generated method stub
		this.minions = i;
	}

	@Override
	public List<Object> getSpoilsPool() {
		// TODO Auto-generated method stub
		return this.spoilsPool;
	}

	@Override
	public void setSpoilsPool(List<Object> list) {
		// TODO Auto-generated method stub
		this.spoilsPool = list;
	}

	@Override
	public void addSpoil(ItemStack... item) {
		// TODO Auto-generated method stub
		for (ItemStack item2 : item) {
			this.spoilsPool.add(item2);
		}
	}

	@Override
	public void addSpoil(ILootable... item) {
		for (ILootable item2 : item) {
			this.spoilsPool.add(item2);
		}

	}

	@Override
	public void removeSpoil(Object... item) {
		// TODO Auto-generated method stub
		this.spoilsPool.remove(item);
	}

	@Override
	public int getExperience() {
		// TODO Auto-generated method stub
		return this.experience;
	}

	@Override
	public void setExperience(int i) {
		// TODO Auto-generated method stub
		this.experience = i;
	}

	@Override
	public Map<Integer, Object> getMappedDrops() {
		// TODO Auto-generated method stub
		Map<Integer, Object> map = new HashMap<Integer, Object>();
		map.put(0, this.spoilsPool);
		map.put(1, this.experience);
		return map;
	}

	@Override
	public int getBossId() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean hasBossbar() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public NamespacedKey generateRandomKey(String name, String titleName) {
		// TODO Auto-generated method stub
		name = name.replaceAll("[^a-zA-Z0-9]", "_");
		name = name.replace(' ', '-');
		titleName = titleName.replace(' ', '-');
		NamespacedKey key = NamespacedKey.minecraft(name.toLowerCase() + "." + titleName.toLowerCase()
				+ Integer.toString(this.getBossId()) + Integer.toString(this.getMinions())
				+ Double.toString(this.getArmorMultiplier()) + Double.toString(this.getArmorStrengthMultiplier())
				+ Double.toString(this.getHealthMultiplier()) + Double.toString(this.getKnockbackResistance())
				+ Double.toString(this.getSpeedMultiplier()) + UUID.randomUUID().toString());
		return key;
	}

	@Override
	public IBoss cloneBoss() throws CloneNotSupportedException {
		return (IBoss) this.clone();
	}

	@Override
	public double getSpawnChance() {
		// TODO Auto-generated method stub
		return 0;
	}
}
