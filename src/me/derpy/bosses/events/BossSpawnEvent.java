package me.derpy.bosses.events;

import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

import me.derpy.bosses.mobs.interfaces.IBoss;

public class BossSpawnEvent extends Event implements Cancellable {
	private Entity entity;
	private int bossId;
	private Location location;
	private IBoss boss;
	private boolean canceled;
	private static final HandlerList handlers = new HandlerList();

	public BossSpawnEvent(boolean isAsync, Entity entity, int bossId, IBoss boss, Location location) {
		super(isAsync);
		this.entity = entity;
		this.bossId = bossId;
		this.location = location;
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

	public Location getLocation() {
		return this.location;
	}

	public int getBossId() {
		return this.bossId;
	}

	public IBoss getBossSettings() {
		return this.boss;
	}

	@Override
	public boolean isCancelled() {
		// TODO Auto-generated method stub
		return this.canceled;
	}

	@Override
	public void setCancelled(boolean arg0) {
		// TODO Auto-generated method stub
		this.canceled = arg0;
	}
}
