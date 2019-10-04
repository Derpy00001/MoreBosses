package me.Derpy.Bosses.utilities.items;

import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.attribute.AttributeModifier.Operation;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.RecipeChoice;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.LeatherArmorMeta;
import org.bukkit.plugin.Plugin;

import net.md_5.bungee.api.ChatColor;

public class ghast {
	private static Plugin plugin = me.Derpy.Bosses.MoreBosses.getPlugin(me.Derpy.Bosses.MoreBosses.class);
	public static ItemStack tissue(Integer amt) {
		ItemStack item = new ItemStack(Material.FERMENTED_SPIDER_EYE, amt);
		ItemMeta meta = item.getItemMeta();
		meta.setDisplayName(ChatColor.RED+"Ghast Tissue");
		meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
		item.setItemMeta(meta);
		return item;
	}
	public static class tissue_armor{
		public static ItemStack chestplate() {
			ItemStack item = new ItemStack(Material.LEATHER_CHESTPLATE);
			LeatherArmorMeta meta = (LeatherArmorMeta) item.getItemMeta();
			meta.setColor(Color.RED);
			meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES, ItemFlag.HIDE_UNBREAKABLE);
			meta.setDisplayName(ChatColor.RED+"Ghast Tunic");
			meta.addAttributeModifier(Attribute.GENERIC_MAX_HEALTH, new AttributeModifier(UUID.randomUUID(), "Extra_Health", 4D, Operation.ADD_NUMBER, EquipmentSlot.CHEST));
			meta.setUnbreakable(true);
			item.setItemMeta(meta);
			return item;
		}
		public static ItemStack pants() {
			ItemStack item = new ItemStack(Material.LEATHER_LEGGINGS);
			LeatherArmorMeta meta = (LeatherArmorMeta) item.getItemMeta();
			meta.setColor(Color.RED);
			meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES, ItemFlag.HIDE_UNBREAKABLE);
			meta.setDisplayName(ChatColor.RED+"Ghast Leggings");
			meta.addAttributeModifier(Attribute.GENERIC_MAX_HEALTH, new AttributeModifier(UUID.randomUUID(), "Extra_Health", 2D, Operation.ADD_NUMBER, EquipmentSlot.LEGS));
			meta.setUnbreakable(true);
			item.setItemMeta(meta);
			return item;
		}
		public static ItemStack helmet() {
			ItemStack item = new ItemStack(Material.LEATHER_HELMET);
			LeatherArmorMeta meta = (LeatherArmorMeta) item.getItemMeta();
			meta.setColor(Color.RED);
			meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES, ItemFlag.HIDE_UNBREAKABLE);
			meta.setDisplayName(ChatColor.RED+"Ghast Helmet");
			meta.addAttributeModifier(Attribute.GENERIC_MAX_HEALTH, new AttributeModifier(UUID.randomUUID(), "Extra_Health", 1D, Operation.ADD_NUMBER, EquipmentSlot.HEAD));
			meta.setUnbreakable(true);
			item.setItemMeta(meta);
			return item;
		}
		public static ItemStack boots() {
			ItemStack item = new ItemStack(Material.LEATHER_BOOTS);
			LeatherArmorMeta meta = (LeatherArmorMeta) item.getItemMeta();
			meta.setColor(Color.RED);
			meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES, ItemFlag.HIDE_UNBREAKABLE);
			meta.setDisplayName(ChatColor.RED+"Ghast Boots");
			meta.addAttributeModifier(Attribute.GENERIC_MAX_HEALTH, new AttributeModifier(UUID.randomUUID(), "Extra_Health", 1D, Operation.ADD_NUMBER, EquipmentSlot.FEET));
			meta.setUnbreakable(true);
			item.setItemMeta(meta);
			return item;
		}
		@SuppressWarnings("deprecation")
		public void customRecipe_chestplate() {
			// TODO Auto-generated method stub
			NamespacedKey key = new NamespacedKey(plugin, "ghast_tunic");
			RecipeChoice Custom2 = new RecipeChoice.ExactChoice(ghast.tissue(1));
			ItemStack result = ghast.tissue_armor.chestplate();
			ShapedRecipe shaped = new ShapedRecipe(key, result);
			shaped.shape("*%*","***","***");
			shaped.setIngredient('*', Custom2);
			Bukkit.addRecipe(shaped);
			RecipeStorage.addrecipe(shaped, key);
		}
		@SuppressWarnings("deprecation")
		public void customRecipe_leggings() {
			// TODO Auto-generated method stub
			NamespacedKey key = new NamespacedKey(plugin, "ghast_leggings");
			RecipeChoice Custom2 = new RecipeChoice.ExactChoice(ghast.tissue(1));
			ItemStack result = ghast.tissue_armor.pants();
			ShapedRecipe shaped = new ShapedRecipe(key, result);
			shaped.shape("***","*%*","*%*");
			shaped.setIngredient('*', Custom2);
			Bukkit.addRecipe(shaped);
			RecipeStorage.addrecipe(shaped, key);
		}
		@SuppressWarnings("deprecation")
		public void customRecipe_helmet() {
			// TODO Auto-generated method stub
			NamespacedKey key = new NamespacedKey(plugin, "ghast_helmet");
			RecipeChoice Custom2 = new RecipeChoice.ExactChoice(ghast.tissue(1));
			ItemStack result = ghast.tissue_armor.helmet();
			ShapedRecipe shaped = new ShapedRecipe(key, result);
			shaped.shape("***","*%*","%%%");
			shaped.setIngredient('*', Custom2);
			Bukkit.addRecipe(shaped);
			RecipeStorage.addrecipe(shaped, key);
		}
		@SuppressWarnings("deprecation")
		public void customRecipe_boots() {
			// TODO Auto-generated method stub
			NamespacedKey key = new NamespacedKey(plugin, "ghast_boots");
			RecipeChoice Custom2 = new RecipeChoice.ExactChoice(ghast.tissue(1));
			ItemStack result = ghast.tissue_armor.boots();
			ShapedRecipe shaped = new ShapedRecipe(key, result);
			shaped.shape("%%%","*%*","*%*");
			shaped.setIngredient('*', Custom2);
			Bukkit.addRecipe(shaped);
			RecipeStorage.addrecipe(shaped, key);
		}
	}
}
