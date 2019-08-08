package me.Derpy.Bosses.utilities.items;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.RecipeChoice;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.plugin.Plugin;

public class elytra {
	private Plugin plugin = me.Derpy.Bosses.Main.getPlugin(me.Derpy.Bosses.Main.class);
	@SuppressWarnings("deprecation")
	public void customRecipeElytra() {
		NamespacedKey key = new NamespacedKey(plugin, "elytra_broken");
		RecipeChoice souli = new RecipeChoice.ExactChoice(soul.get());
		ItemStack result = new ItemStack(Material.ELYTRA);
		result.setDurability((short) 400);
		result.addEnchantment(Enchantment.VANISHING_CURSE, 1);
		ShapedRecipe recipe=new ShapedRecipe(key, result);
		recipe.shape("***", "***", "* *");
		recipe.setIngredient('*', souli);
		Bukkit.addRecipe(recipe);
	}
}
