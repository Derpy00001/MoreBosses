package me.derpy.bosses.events;

import org.bukkit.entity.Entity;
import org.bukkit.event.HandlerList;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

import me.derpy.bosses.mobs.interfaces.IBoss;

public class BossDamageByEntityEvent extends EntityDamageByEntityEvent {
	private IBoss boss;
	private int bossId;
	private static final HandlerList handlers = new HandlerList();

	public BossDamageByEntityEvent(Entity damager, Entity damagee, IBoss iboss, int id, DamageCause cause,
			double damage) {
		super(damager, damagee, cause, damage);
		// TODO Auto-generated constructor stub
		this.boss = iboss;
		this.bossId = id;
	}

	@Override
	public HandlerList getHandlers() {
		// TODO Auto-generated method stub
		return handlers;
	}

	public static HandlerList getHandlerList() {
		return handlers;
	}

	public IBoss getBoss() {
		return this.boss;
	}

	public int getBossId() {
		return this.bossId;
	}
}
