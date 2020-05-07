package me.derpy.bosses.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.ItemStack;

import me.derpy.bosses.inventory.objects.Holder;

public class OnInventoryClose implements Listener {
	@EventHandler
	private void onInventoryClose(InventoryCloseEvent e) {
		if (e.getInventory().getHolder() instanceof Holder) {
			for (ItemStack item : e.getInventory().getContents()) {
				if (item != null) {
					if (e.getPlayer().getInventory().firstEmpty() == -1) {
						e.getPlayer().getWorld().dropItemNaturally(e.getPlayer().getLocation(), item);
					} else {
						e.getPlayer().getInventory().addItem(item);
					}
				}
			}
		}
	}
}
