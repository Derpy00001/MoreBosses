package me.Derpy.Bosses.utilities.items;

import java.util.ArrayList;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import net.md_5.bungee.api.ChatColor;

public class infused_diamond {
	public static ItemStack get() {
		ItemStack item2 = new ItemStack(Material.DIAMOND, 1);
		ItemMeta meta = (ItemMeta) item2.getItemMeta();
		meta.setUnbreakable(true);
		meta.addItemFlags(ItemFlag.HIDE_UNBREAKABLE);
		meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
		meta.addItemFlags(ItemFlag.HIDE_PLACED_ON);
		ArrayList<String> lore = new ArrayList<String>();
		lore.add(ChatColor.RED+"A diamond infused");
		lore.add(ChatColor.RED+"with a dark energy");
		meta.setLore(lore);
		meta.setDisplayName(ChatColor.RESET+""+ChatColor.GOLD+"Infused Diamond");
		item2.setItemMeta(meta);
		item2.addUnsafeEnchantment(Enchantment.RIPTIDE, 1);
		return item2;
	}
}
