package me.derpy.bosses.listeners;

import java.util.Map;

import org.bukkit.Bukkit;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.inventory.ItemStack;

import me.derpy.bosses.events.BossDeathEvent;
import me.derpy.bosses.items.interfaces.ILootable;
import me.derpy.bosses.utilities.Console;
import me.derpy.bosses.utilities.Random;

public class Death implements Listener {
	@SuppressWarnings("unchecked")
	@EventHandler
	public void onDeath(EntityDeathEvent e) {
		if (e.getEntity().hasMetadata("Morebosses-BossId")) {
			int id = e.getEntity().getMetadata("Morebosses-BossId").get(0).asInt();
			Map<Integer, Object> map;
			if (e.getEntity().hasMetadata("Morebosses-Spoils")) {
				map = (Map<Integer, Object>) e.getEntity().getMetadata("Morebosses-Spoils").get(0).value();
			} else {
				map = null;
			}
			BossDeathEvent event = new BossDeathEvent(false, e.getEntity(), map, id);
			Bukkit.getPluginManager().callEvent(event);
			e.setDroppedExp(event.getExperience());
			e.getDrops().clear();
			if (event.getLootpool().size() > 0) {
				Object obj = event.getLootpool().get(Random.random(0, event.getLootpool().size() - 1));
				Console.print(obj.toString());
				if (obj instanceof ItemStack) {
					e.getDrops().add((ItemStack) obj);
				} else if (obj instanceof ILootable) {
					e.getDrops().add(((ILootable) obj).getFinalizedItem());
				}
			}

			e.getEntity().getWorld().playSound(e.getEntity().getLocation(), Sound.UI_TOAST_CHALLENGE_COMPLETE, 1, 1);
			e.getEntity().getWorld().spawnParticle(Particle.TOTEM, e.getEntity().getLocation(), 100,
					e.getEntity().getBoundingBox().getWidthX(), e.getEntity().getBoundingBox().getHeight(),
					e.getEntity().getBoundingBox().getWidthZ());
		}
	}
}
