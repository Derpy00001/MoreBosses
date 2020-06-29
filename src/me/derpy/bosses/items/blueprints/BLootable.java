package me.derpy.bosses.items.blueprints;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import me.derpy.bosses.items.interfaces.ILootable;
import me.derpy.bosses.utilities.Tagger;

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
		if (item.hasItemMeta()) {
			item.getItemMeta().setDisplayName(ChatColor.RESET + item.getItemMeta().getDisplayName());
			if (!this.hasCustomColor()) {
				ItemMeta meta = item.getItemMeta();
				if (meta.hasLore()) {
					List<String> lore = meta.getLore();
					List<String> strings = new ArrayList<String>();
					for (String string : lore) {
						strings.add(ChatColor.RESET + "" + this.getLoreColor() + string);
					}
					meta.setLore(strings);
				}
				meta.setDisplayName(ChatColor.RESET + "" + this.getNameColor() + meta.getDisplayName());
				item.setItemMeta(meta);
			}
		}
		return Tagger.tagItem(item, this.getNamespacedKey());
	}

	@Override
	public ChatColor getNameColor() {
		// TODO Auto-generated method stub
		return ChatColor.WHITE;
	}

	@Override
	public ChatColor getLoreColor() {
		// TODO Auto-generated method stub
		return ChatColor.GRAY;
	}

	@Override
	public boolean hasCustomColor() {
		// TODO Auto-generated method stub
		return false;
	}
}
