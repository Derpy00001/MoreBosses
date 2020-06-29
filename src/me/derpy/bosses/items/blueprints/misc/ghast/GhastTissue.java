package me.derpy.bosses.items.blueprints.misc.ghast;

import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import me.derpy.bosses.items.blueprints.BAmount;

public class GhastTissue extends BAmount {
	@Override
	public ItemStack getItem() {
		ItemStack item = new ItemStack(Material.FERMENTED_SPIDER_EYE);
		ItemMeta meta = item.getItemMeta();
		meta.setDisplayName("Ghast Tissue");
		item.setItemMeta(meta);
		return item;
	}

	@Override
	public NamespacedKey getNamespacedKey() {
		return NamespacedKey.minecraft("ghast-tissue");
	}
}
