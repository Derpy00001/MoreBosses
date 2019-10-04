package me.Derpy.Bosses.utilities.items;

import java.util.ArrayList;

import org.bukkit.Material;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import me.Derpy.Bosses.MoreBosses;
import me.Derpy.Bosses.utilities.translate;
import net.md_5.bungee.api.ChatColor;

public class village {
	public static ItemStack token() {
		ItemStack item = new ItemStack(Material.GOLD_NUGGET);
		ItemMeta meta = item.getItemMeta();
		meta.setDisplayName(ChatColor.GOLD+"Challenger Token");
		ArrayList<String> lore = new ArrayList<String>();
		lore.add(ChatColor.GOLD+"["+translate.get("token_lore", MoreBosses.getPlugin(MoreBosses.class))+"]");
		meta.setLore(lore);
		meta.addItemFlags(ItemFlag.HIDE_UNBREAKABLE);
		item.setItemMeta(meta);
		return item;
	}
}
