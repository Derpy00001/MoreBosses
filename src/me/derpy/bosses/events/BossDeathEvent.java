package me.derpy.bosses.events;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Nullable;

import org.bukkit.entity.Entity;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.bukkit.inventory.ItemStack;

import me.derpy.bosses.items.interfaces.ILootable;

public class BossDeathEvent extends Event {
	private Entity entity;
	private int experience;
	private List<Object> pool;
	private int bossId;
	private static final HandlerList handlers = new HandlerList();

	@SuppressWarnings("unchecked")
	public BossDeathEvent(boolean isAsync, Entity entity, @Nullable Map<Integer, Object> map, int bossId) {
		super(isAsync);
		this.entity = entity;
		if (map == null) {
			this.experience = 0;
			this.pool = new ArrayList<Object>();
		} else {
			if (map.get(0) == null) {
				this.pool = new ArrayList<Object>();
			} else {
				this.pool = (List<Object>) map.get(0);
			}
			if (map.get(1) == null) {
				this.experience = 0;
			} else {
				this.experience = (Integer) map.get(1);
			}
		}
		this.bossId = bossId;
	}

	@Override
	public HandlerList getHandlers() {
		// TODO Auto-generated method stub
		return handlers;
	}

	public static HandlerList getHandlerList() {
		return handlers;
	}

	public Entity getEntity() {
		return this.entity;
	}

	public int getExperience() {
		return this.experience;
	}

	public void setExperience(int i) {
		this.experience = i;
	}

	public List<Object> getLootpool() {
		return this.pool;
	}

	public void setLootpool(List<Object> lootPool) {
		this.pool = lootPool;
	}

	public void addToLootPool(ItemStack... item) {
		this.pool.add(item);
	}

	public void addToLootPool(ILootable... item) {
		this.pool.add(item);
	}

	public void removeFromLootPool(Object... item) {
		this.pool.remove(item);
	}

	public int getBossId() {
		return this.bossId;
	}
}
