package me.derpy.bosses.items.weapons;

import java.util.Arrays;

import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import me.derpy.bosses.items.blueprints.BEquipment;

public class Trident extends BEquipment {
	@Override
	public ItemStack getItem() {
		ItemStack item = new ItemStack(Material.TRIDENT);
		ItemMeta meta = item.getItemMeta();
		meta.setDisplayName("Old Trident");
		meta.setLore(Arrays.asList("A weapon used by the evils", "of the depths."));
		item.setItemMeta(meta);
		return item;
	}

	@Override
	public NamespacedKey getNamespacedKey() {
		return NamespacedKey.minecraft("weapon-oldtrident");
	}
}
