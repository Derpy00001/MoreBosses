package me.derpy.bosses.items.blueprints;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import me.derpy.bosses.items.interfaces.ILootable;
import net.md_5.bungee.api.ChatColor;

public class BLootable implements ILootable {
	@Override
	public ItemStack getItem() {
		ItemStack item = new ItemStack(Material.IRON_AXE);
		ItemMeta meta = item.getItemMeta();
		meta.setDisplayName("Hide");
		meta.setLore(Arrays.asList("A material that should not", "exist."));
		item.setItemMeta(meta);
		return item;
	}

	@Override
	public NamespacedKey getNamespacedKey() {
		return NamespacedKey.minecraft("hidden-hide");
	}

	@Override
	public ItemStack getFinalizedItem() {
		ItemStack item = this.getItem();
		ItemMeta meta = item.getItemMeta();
		List<String> lore = meta.getLore();
		List<String> strings = new ArrayList<String>();
		for (String string : lore) {
			strings.add(ChatColor.RESET + "" + ChatColor.GRAY + string);
		}
		meta.setLore(strings);
		meta.setDisplayName(ChatColor.RESET + "" + ChatColor.WHITE + meta.getDisplayName());
		item.setItemMeta(meta);
		return item;
	}
}
