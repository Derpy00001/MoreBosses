package me.derpy.bosses.listeners;

import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityPickupItemEvent;

import me.derpy.bosses.items.ItemType;

public class Pickup implements Listener {
	@EventHandler
	private void onPickup(EntityPickupItemEvent e) {
		if (e.getEntity() instanceof Player) {
			Player player = (Player) e.getEntity();
			if (e.getItem().getItemStack().isSimilar(ItemType.GHAST_TISSUE.getInterface().getFinalizedItem())) {
				for (NamespacedKey key : ItemType.getRecipes().keySet()) {
					if (key.getKey().toLowerCase().contains("tissue")) {
						player.discoverRecipe(key);
					}
				}
			} else if (e.getItem().getItemStack().getType() == Material.GHAST_TEAR
					|| e.getItem().getItemStack().getType() == Material.TOTEM_OF_UNDYING) {
				player.discoverRecipe(ItemType.GHAST_TOTEM.getNamespacedKey());
			}
		}
	}
}
