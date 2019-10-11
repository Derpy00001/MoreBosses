package me.Derpy.Bosses.Addons.Nordic.Items;

import java.util.ArrayList;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import net.md_5.bungee.api.ChatColor;

public class shards {
	public static ItemStack Alfheimrshard() {
		ItemStack item = new ItemStack(Material.STICK);
		ItemMeta meta = item.getItemMeta();
		meta.setDisplayName(ChatColor.GOLD+"Shard of Álfheimr");
		ArrayList<String> lore = new ArrayList<String>();
		lore.add(ChatColor.RED+"A shard that contains the");
		lore.add(ChatColor.RED+"power to open a rift between realms");
		meta.setLore(lore);
		meta.addEnchant(Enchantment.ARROW_DAMAGE, 1, false);
		meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
		item.setItemMeta(meta);
		return item;
	}
	public static ItemStack Midgardshard() {
		ItemStack item = new ItemStack(Material.STICK);
		ItemMeta meta = item.getItemMeta();
		meta.setDisplayName(ChatColor.GOLD+"Shard of Midgard");
		ArrayList<String> lore = new ArrayList<String>();
		lore.add(ChatColor.RED+"A shard that contains the");
		lore.add(ChatColor.RED+"to open a rift between realms");
		meta.setLore(lore);
		meta.addEnchant(Enchantment.ARROW_DAMAGE, 1, false);
		meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
		item.setItemMeta(meta);
		return item;
	}
}
