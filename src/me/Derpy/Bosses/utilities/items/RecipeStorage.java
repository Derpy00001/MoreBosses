package me.Derpy.Bosses.utilities.items;

import java.util.ArrayList;

import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.Recipe;
import org.bukkit.inventory.meta.KnowledgeBookMeta;

import net.md_5.bungee.api.ChatColor;

public class RecipeStorage {
	private static ArrayList<Recipe> recipes = new ArrayList<Recipe>();
	private static ArrayList<ItemStack> books = new ArrayList<ItemStack>();
	public static void addrecipe(Recipe recipe, NamespacedKey key) {
		recipes.add(recipe);
		ItemStack item = new ItemStack(Material.KNOWLEDGE_BOOK);
		KnowledgeBookMeta meta = (KnowledgeBookMeta) item.getItemMeta();
		meta.addRecipe(key);
		if(recipe.getResult().getItemMeta().hasDisplayName()) {
			meta.setDisplayName(ChatColor.RESET+recipe.getResult().getItemMeta().getDisplayName());
		}else {
			String name = recipe.getResult().getType().name().toLowerCase().substring(0, 1).toUpperCase() + recipe.getResult().getType().name().toLowerCase().substring(1);
			meta.setDisplayName(ChatColor.RESET+name);
		}
		item.setItemMeta(meta);
		addbook(item);
	}
	public static void removerecipe(Recipe recipe) {
		if(recipes.contains(recipe)) {
			recipes.remove(recipe);
		}
	}
	public static ArrayList<Recipe> getrecipes(){
		return recipes;
	}
	public static boolean addbook(ItemStack item) {
		if(books.contains(item)) {
			return false;
		}else {
			books.add(item);
			return true;
		}
	}
	public static boolean removebook(ItemStack item) {
		if(books.contains(item)) {
			books.remove(item);
			return true;
		}
		return false;
	}
	public static ArrayList<ItemStack> getbooks(){
		return books;
	}
}
