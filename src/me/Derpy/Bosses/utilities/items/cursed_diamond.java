package me.Derpy.Bosses.utilities.items;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.RecipeChoice;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.Plugin;

import me.Derpy.Bosses.utilities.translate;
import net.md_5.bungee.api.ChatColor;

public class cursed_diamond {
	private Plugin plugin = me.Derpy.Bosses.MoreBosses.getPlugin(me.Derpy.Bosses.MoreBosses.class);
	public static ItemStack get(Plugin plugin) {
		ItemStack item2 = new ItemStack(Material.DIAMOND, 1);
		ItemMeta meta = (ItemMeta) item2.getItemMeta();
		meta.setUnbreakable(true);
		meta.addItemFlags(ItemFlag.HIDE_PLACED_ON);
		meta.addItemFlags(ItemFlag.HIDE_UNBREAKABLE);
		meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
		ArrayList<String> lore = new ArrayList<String>();
		lore.add(ChatColor.RED+translate.get("cursedlore", plugin));
		meta.setLore(lore);
		meta.setDisplayName(ChatColor.RESET+""+ChatColor.GOLD+"Cursed Diamond");
		item2.setItemMeta(meta);
		item2.addUnsafeEnchantment(Enchantment.CHANNELING, 1);
		return item2;
	}
	@SuppressWarnings("deprecation")
	public void customRecipe() {
		// TODO Auto-generated method stub
		NamespacedKey key = new NamespacedKey(plugin, "cursed_diamond");
		RecipeChoice Custom = new RecipeChoice.ExactChoice(infused_diamond.get());
		ItemStack result = cursed_diamond.get(plugin);
		ShapedRecipe shaped = new ShapedRecipe(key, result);
		shaped.shape("%*%","***","%*%");
		shaped.setIngredient('*', Custom);
		Bukkit.addRecipe(shaped);
		RecipeStorage.addrecipe(shaped, key);
	}
}
