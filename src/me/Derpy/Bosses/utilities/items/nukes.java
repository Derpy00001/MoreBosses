package me.Derpy.Bosses.utilities.items;

import org.bukkit.Material;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import net.md_5.bungee.api.ChatColor;

public class nukes {
	public static ItemStack nuke0() {
		ItemStack nuke = new ItemStack(Material.TNT);
		ItemMeta meta = nuke.getItemMeta();
		meta.setDisplayName(ChatColor.RED+"Nuke");
		meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
		nuke.setItemMeta(meta);
		return nuke;
	}
}
