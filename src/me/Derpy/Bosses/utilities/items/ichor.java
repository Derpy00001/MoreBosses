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

public class ichor {
	private Plugin plugin = me.Derpy.Bosses.Main.getPlugin(me.Derpy.Bosses.Main.class);
	public static ItemStack get(int num0, Plugin plugin) {
		ItemStack item = new ItemStack(Material.YELLOW_DYE, num0);
		ItemMeta meta = item.getItemMeta();
		ArrayList<String> lore = new ArrayList<String>();
		lore.add(ChatColor.RED+translate.get("ichorlore", plugin));
		meta.setLore(lore);
		meta.setDisplayName(ChatColor.GOLD+"Ichor");
		meta.setUnbreakable(true);
		meta.addItemFlags(ItemFlag.HIDE_PLACED_ON);
		item.setItemMeta(meta);
		return item;
	}
	public static ItemStack getraw(Plugin plugin) {
		ItemStack item = new ItemStack(Material.YELLOW_DYE, 1);
		ItemMeta meta = item.getItemMeta();
		ArrayList<String> lore = new ArrayList<String>();
		lore.add(ChatColor.RED+translate.get("ichorlore", plugin));
		meta.setLore(lore);
		meta.setDisplayName(ChatColor.GOLD+"Ichor");
		meta.setUnbreakable(true);
		meta.addItemFlags(ItemFlag.HIDE_PLACED_ON);
		item.setItemMeta(meta);
		return item;
	}
	public static ItemStack stick(Plugin plugin) {
		ItemStack item = new ItemStack(Material.BLAZE_ROD, 1);
		ItemMeta meta = item.getItemMeta();
		ArrayList<String> lore = new ArrayList<String>();
		lore.add(ChatColor.RED+translate.get("ichorsticklore", plugin));
		meta.setLore(lore);
		meta.setDisplayName(ChatColor.GOLD+"Ichor Stick");
		meta.setUnbreakable(true);
		meta.addItemFlags(ItemFlag.HIDE_PLACED_ON);
		item.setItemMeta(meta);
		return item;
	}
	public static ItemStack sword(Plugin plugin) {
		ItemStack item = new ItemStack(Material.GOLDEN_SWORD, 1);
		ItemMeta meta = item.getItemMeta();
		ArrayList<String> lore = new ArrayList<String>();
		lore.add(ChatColor.RED+translate.get("ichorswordlore", plugin));
		meta.setLore(lore);
		meta.setDisplayName(ChatColor.GOLD+"Ichor Sword");
		meta.setUnbreakable(true);
		meta.addItemFlags(ItemFlag.HIDE_PLACED_ON);
		item.setItemMeta(meta);
		return item;
	}
	public static ItemStack chest(Plugin plugin) {
		ItemStack item = new ItemStack(Material.GOLDEN_CHESTPLATE);
		ItemMeta meta = item.getItemMeta();
		ArrayList<String> lore = new ArrayList<String>();
		lore.add(ChatColor.RED+translate.get("ichorchestlore", plugin));
		meta.setLore(lore);
		meta.setDisplayName(ChatColor.GOLD+"Ichor Chestplate");
		meta.setUnbreakable(true);
		meta.addItemFlags(ItemFlag.HIDE_PLACED_ON);
		item.setItemMeta(meta);
		return item;
	}
	public static ItemStack leggings(Plugin plugin) {
		ItemStack item = new ItemStack(Material.GOLDEN_LEGGINGS);
		ItemMeta meta = item.getItemMeta();
		ArrayList<String> lore = new ArrayList<String>();
		lore.add(ChatColor.RED+translate.get("ichorleglore", plugin));
		meta.setLore(lore);
		meta.setDisplayName(ChatColor.GOLD+"Ichor Leggings");
		meta.setUnbreakable(true);
		meta.addItemFlags(ItemFlag.HIDE_PLACED_ON);
		item.setItemMeta(meta);
		return item;
	}
	public static ItemStack boots(Plugin plugin) {
		ItemStack item = new ItemStack(Material.GOLDEN_BOOTS);
		ItemMeta meta = item.getItemMeta();
		ArrayList<String> lore = new ArrayList<String>();
		lore.add(ChatColor.RED+translate.get("ichorbootlore", plugin));
		meta.setLore(lore);
		meta.setDisplayName(ChatColor.GOLD+"Ichor Boots");
		meta.setUnbreakable(true);
		meta.addItemFlags(ItemFlag.HIDE_PLACED_ON);
		item.setItemMeta(meta);
		return item;
	}
	public static ItemStack helmet(Plugin plugin) {
		ItemStack item = new ItemStack(Material.GOLDEN_HELMET);
		ItemMeta meta = item.getItemMeta();
		ArrayList<String> lore = new ArrayList<String>();
		lore.add(ChatColor.RED+translate.get("ichorhelmlore", plugin));
		meta.setLore(lore);
		meta.setDisplayName(ChatColor.GOLD+"Ichor Helmet");
		meta.setUnbreakable(true);
		meta.addItemFlags(ItemFlag.HIDE_PLACED_ON);
		item.setItemMeta(meta);
		return item;
	}
	@SuppressWarnings("deprecation")
	public void customRecipeStick() {
		NamespacedKey key = new NamespacedKey(plugin, "ichor_stick");
		RecipeChoice ichori = new RecipeChoice.ExactChoice(ichor.getraw(plugin));
		ItemStack sticki = ichor.stick(plugin);
		ShapedRecipe stickre = new ShapedRecipe(key, sticki);
		stickre.shape("   "," * "," * ");
		stickre.setIngredient('*', ichori);
		Bukkit.addRecipe(stickre);
	}
	@SuppressWarnings("deprecation")
	public void customRecipeSword() {
		NamespacedKey key = new NamespacedKey(plugin, "ichor_sword");
		RecipeChoice ichori = new RecipeChoice.ExactChoice(ichor.getraw(plugin));
		RecipeChoice ichorsticki = new RecipeChoice.ExactChoice(ichor.stick(plugin));
		ItemStack isword = me.Derpy.Bosses.utilities.items.ichor.sword(plugin);
		ShapedRecipe iswordre=new ShapedRecipe(key, isword);
		iswordre.shape("%*%", "%*%", "%$%");
		iswordre.setIngredient('*', ichori);
		iswordre.setIngredient('$', ichorsticki);
		Bukkit.addRecipe(iswordre);
	}
	@SuppressWarnings("deprecation")
	public void customRecipeChest() {
		NamespacedKey key = new NamespacedKey(plugin, "ichor_chest");
		RecipeChoice ichori = new RecipeChoice.ExactChoice(ichor.getraw(plugin));
		ItemStack result = me.Derpy.Bosses.utilities.items.ichor.chest(plugin);
		ShapedRecipe recipe=new ShapedRecipe(key, result);
		recipe.shape("* *", "***", "***");
		recipe.setIngredient('*', ichori);
		Bukkit.addRecipe(recipe);
	}
	@SuppressWarnings("deprecation")
	public void customRecipeLeggings() {
		NamespacedKey key = new NamespacedKey(plugin, "ichor_leggings");
		RecipeChoice ichori = new RecipeChoice.ExactChoice(ichor.getraw(plugin));
		ItemStack result = me.Derpy.Bosses.utilities.items.ichor.leggings(plugin);
		ShapedRecipe recipe=new ShapedRecipe(key, result);
		recipe.shape("***", "* *", "* *");
		recipe.setIngredient('*', ichori);
		Bukkit.addRecipe(recipe);
	}
	@SuppressWarnings("deprecation")
	public void customRecipeBoots() {
		NamespacedKey key = new NamespacedKey(plugin, "ichor_boots");
		RecipeChoice ichori = new RecipeChoice.ExactChoice(ichor.getraw(plugin));
		ItemStack result = me.Derpy.Bosses.utilities.items.ichor.boots(plugin);
		ShapedRecipe recipe=new ShapedRecipe(key, result);
		recipe.shape("   ", "* *", "* *");
		recipe.setIngredient('*', ichori);
		Bukkit.addRecipe(recipe);
	}
	@SuppressWarnings("deprecation")
	public void customRecipeHelmet() {
		NamespacedKey key = new NamespacedKey(plugin, "ichor_helmet");
		RecipeChoice ichori = new RecipeChoice.ExactChoice(ichor.getraw(plugin));
		ItemStack result = me.Derpy.Bosses.utilities.items.ichor.helmet(plugin);
		ShapedRecipe recipe=new ShapedRecipe(key, result);
		recipe.shape("***", "* *", "   ");
		recipe.setIngredient('*', ichori);
		Bukkit.addRecipe(recipe);
	}
}
