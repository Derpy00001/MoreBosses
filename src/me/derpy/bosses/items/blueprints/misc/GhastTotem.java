package me.derpy.bosses.items.blueprints.misc;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.Recipe;
import org.bukkit.inventory.RecipeChoice;
import org.bukkit.inventory.ShapelessRecipe;
import org.bukkit.inventory.meta.ItemMeta;

import me.derpy.bosses.items.blueprints.BAmount;
import me.derpy.bosses.items.interfaces.ICraftable;
import net.md_5.bungee.api.ChatColor;

public class GhastTotem extends BAmount implements ICraftable {
	@Override
	public ItemStack getItem() {
		ItemStack item = new ItemStack(Material.TOTEM_OF_UNDYING);
		ItemMeta meta = item.getItemMeta();
		meta.setDisplayName("Ghast Totem");
		meta.setLore(Arrays.asList(ChatColor.DARK_RED + "Consumable on End Gateway"));
		item.setItemMeta(meta);
		return item;
	}

	@Override
	public NamespacedKey getNamespacedKey() {
		return NamespacedKey.minecraft("ghast-totem");
	}

	@Override
	public Recipe[] getRecipes() {
		// TODO Auto-generated method stub
		List<Recipe> recipes = new ArrayList<Recipe>();
		ShapelessRecipe recipe = new ShapelessRecipe(this.getNamespacedKey(), this.getFinalizedItem());
		recipe.addIngredient(new RecipeChoice.MaterialChoice(Material.TOTEM_OF_UNDYING));
		recipe.addIngredient(new RecipeChoice.MaterialChoice(Material.DRAGON_BREATH));
		recipe.addIngredient(new RecipeChoice.MaterialChoice(Material.GHAST_TEAR));
		recipes.add(recipe);
		return recipes.toArray(new Recipe[recipes.size()]);
	}
}
