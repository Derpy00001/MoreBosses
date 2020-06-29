package me.derpy.bosses.items.blueprints.misc.raid;

import java.util.Arrays;

import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import me.derpy.bosses.items.blueprints.BAmount;
import net.md_5.bungee.api.ChatColor;

public class ChallengerToken extends BAmount {
	@Override
	public ItemStack getItem() {
		ItemStack item = new ItemStack(Material.GOLD_NUGGET);
		ItemMeta meta = item.getItemMeta();
		meta.setDisplayName("Challenger Token");
		meta.setLore(Arrays.asList(ChatColor.DARK_RED + "Consumable on Wandering Merchant"));
		item.setItemMeta(meta);
		return item;
	}

	@Override
	public NamespacedKey getNamespacedKey() {
		return NamespacedKey.minecraft("challenger-token");
	}
}
