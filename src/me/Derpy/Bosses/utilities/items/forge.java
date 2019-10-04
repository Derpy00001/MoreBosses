package me.Derpy.Bosses.utilities.items;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.RecipeChoice;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.Plugin;

import me.Derpy.Bosses.utilities.translate;

public class forge {
	private Plugin plugin = me.Derpy.Bosses.MoreBosses.getPlugin(me.Derpy.Bosses.MoreBosses.class);
	public static ItemStack get(Plugin plugin) {
		ItemStack item2 = new ItemStack(Material.FURNACE, 1);
		ItemMeta meta = (ItemMeta) item2.getItemMeta();
		meta.setUnbreakable(true);
		meta.addItemFlags(ItemFlag.HIDE_PLACED_ON);
		meta.addItemFlags(ItemFlag.HIDE_UNBREAKABLE);
		meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
		ArrayList<String> lore = new ArrayList<String>();
		lore.add(ChatColor.RED+translate.get("forgelore", plugin));
		meta.setLore(lore);
		meta.setDisplayName(ChatColor.RESET+""+ChatColor.DARK_RED+"Forge");
		item2.setItemMeta(meta);
		return item2;
	}
	@SuppressWarnings("deprecation")
	public void customRecipe() {
		// TODO Auto-generated method stub
		NamespacedKey key = new NamespacedKey(plugin, "forge_block");
		RecipeChoice Custom = new RecipeChoice.ExactChoice(infused_diamond.get());
		RecipeChoice Custom2 = new RecipeChoice.MaterialChoice(Material.STONE_BRICKS);
		ItemStack result = forge.get(plugin);
		ShapedRecipe shaped = new ShapedRecipe(key, result);
		shaped.shape("***","*%*","***");
		shaped.setIngredient('*', Custom2);
		shaped.setIngredient('%', Custom);
		Bukkit.addRecipe(shaped);
	}
}
