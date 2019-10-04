package me.Derpy.Bosses.utilities.items;

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

public class infused_tear {
	private Plugin plugin = me.Derpy.Bosses.MoreBosses.getPlugin(me.Derpy.Bosses.MoreBosses.class);
	public static ItemStack get(Plugin plugin) {
		ItemStack item2 = new ItemStack(Material.GHAST_TEAR, 1);
		ItemMeta meta = (ItemMeta) item2.getItemMeta();
		meta.setUnbreakable(true);
		meta.addItemFlags(ItemFlag.HIDE_PLACED_ON);
		meta.addItemFlags(ItemFlag.HIDE_UNBREAKABLE);
		meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
		meta.setDisplayName(ChatColor.RESET+""+ChatColor.DARK_RED+"Infused Tear");
		item2.setItemMeta(meta);
		item2.addUnsafeEnchantment(Enchantment.RIPTIDE, 1);
		return item2;
	}
	public void customRecipe() {
		// TODO Auto-generated method stub
		NamespacedKey key = new NamespacedKey(plugin, "infused_tear");
		RecipeChoice Custom2 = new RecipeChoice.MaterialChoice(Material.GHAST_TEAR);
		ItemStack result = infused_tear.get(plugin);
		ShapedRecipe shaped = new ShapedRecipe(key, result);
		shaped.shape(" * ","***"," * ");
		shaped.setIngredient('*', Custom2);
		Bukkit.addRecipe(shaped);
		RecipeStorage.addrecipe(shaped, key);
	}
}
