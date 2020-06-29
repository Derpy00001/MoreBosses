package me.derpy.bosses.mobs.interfaces;

import java.util.List;

import org.bukkit.entity.EntityType;

public interface IMount extends IBoss {
	List<EntityType> getMounts();

	void addMount(EntityType... type);
}
