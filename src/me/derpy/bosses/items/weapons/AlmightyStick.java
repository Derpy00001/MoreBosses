package me.derpy.bosses.items.weapons;

import java.util.Arrays;

import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import me.derpy.bosses.items.blueprints.BLootable;

public class AlmightyStick extends BLootable {
	@Override
	public ItemStack getItem() {
		ItemStack item = new ItemStack(Material.STICK);
		ItemMeta meta = item.getItemMeta();
		meta.setDisplayName("Almighty Stick");
		meta.setLore(Arrays.asList("A weapon that should not exist."));
		item.setItemMeta(meta);
		return item;
	}

	@Override
	public NamespacedKey getNamespacedKey() {
		return NamespacedKey.minecraft("weapon-almighty");
	}
}
