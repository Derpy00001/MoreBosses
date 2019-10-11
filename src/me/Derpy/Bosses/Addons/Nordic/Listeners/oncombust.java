package me.Derpy.Bosses.Addons.Nordic.Listeners;

import org.bukkit.entity.EntityType;
import org.bukkit.entity.Item;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityCombustEvent;

import me.Derpy.Bosses.utilities.items.Enchants;

public class oncombust implements Listener{
	@EventHandler
	public static void combust(EntityCombustEvent event) {
		if(event.getEntity().getType()==EntityType.DROPPED_ITEM) {
			Item item = (Item) event.getEntity();
			if(item.getItemStack().isSimilar(Enchants.bleed(1))||item.getItemStack().isSimilar(Enchants.bleed(2))) {
				event.setCancelled(true);
			}
		}
	}
}
