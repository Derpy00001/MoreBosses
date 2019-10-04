package me.Derpy.Bosses.utilities.items;


import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.RecipeChoice;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.Plugin;

import me.Derpy.Bosses.MoreBosses;
import me.Derpy.Bosses.utilities.translate;


public class ghast_totem {
	private Plugin plugin = me.Derpy.Bosses.MoreBosses.getPlugin(me.Derpy.Bosses.MoreBosses.class);
	public static ItemStack get(Plugin plugin) {
		ItemStack item2 = new ItemStack(Material.TOTEM_OF_UNDYING, 1);
		ItemMeta meta = (ItemMeta) item2.getItemMeta();
		meta.setUnbreakable(true);
		meta.addItemFlags(ItemFlag.HIDE_PLACED_ON);
		meta.addItemFlags(ItemFlag.HIDE_UNBREAKABLE);
		meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
		meta.setDisplayName(ChatColor.RESET+""+ChatColor.DARK_RED+"Totem of the Ghasts");
		ArrayList<String> lore = new ArrayList<String>();
		lore.add(ChatColor.GOLD+"["+translate.get("totem_lore", MoreBosses.getPlugin(MoreBosses.class))+"]");
		meta.setLore(lore);
		item2.setItemMeta(meta);
		item2.addUnsafeEnchantment(Enchantment.RIPTIDE, 1);
		return item2;
	}
	@SuppressWarnings("deprecation")
	public void customRecipe() {
		// TODO Auto-generated method stub
		NamespacedKey key = new NamespacedKey(plugin, "ghast_totem");
		RecipeChoice Custom = new RecipeChoice.MaterialChoice(Material.TOTEM_OF_UNDYING);
		RecipeChoice Custom2 = new RecipeChoice.ExactChoice(infused_tear.get(plugin));
		ItemStack result = ghast_totem.get(plugin);
		ShapedRecipe shaped = new ShapedRecipe(key, result);
		shaped.shape("***","*%*","***");
		shaped.setIngredient('*', Custom2);
		shaped.setIngredient('%', Custom);
		Bukkit.addRecipe(shaped);
		RecipeStorage.addrecipe(shaped, key);
	}
}
