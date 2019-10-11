package me.Derpy.Bosses.Addons.Nordic.Items;


import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.attribute.AttributeModifier.Operation;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.FurnaceRecipe;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.RecipeChoice;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.meta.ItemMeta;

import me.Derpy.Bosses.MoreBosses;
import me.Derpy.Bosses.utilities.items.RecipeStorage;
import net.md_5.bungee.api.ChatColor;

public class wolfram {
	public static ItemStack ore() {
		ItemStack item = new ItemStack(Material.IRON_ORE);
		ItemMeta meta = item.getItemMeta();
		meta.setDisplayName(ChatColor.WHITE+"Wolfram Ore");
		meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
		item.setItemMeta(meta);
		return item;
	}
	public static ItemStack refined() {
		ItemStack item = new ItemStack(Material.IRON_NUGGET);
		ItemMeta meta = item.getItemMeta();
		meta.setDisplayName(ChatColor.WHITE+"Refined Wolfram");
		meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
		item.setItemMeta(meta);
		return item;
	}
	public static ItemStack ingot() {
		ItemStack item = new ItemStack(Material.IRON_INGOT);
		ItemMeta meta = item.getItemMeta();
		meta.setDisplayName(ChatColor.WHITE+"Wolfram Ingot");
		meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
		item.setItemMeta(meta);
		return item;
	}
	public static ItemStack ingot_charged() {
		ItemStack item = new ItemStack(Material.IRON_INGOT);
		ItemMeta meta = item.getItemMeta();
		meta.setDisplayName(ChatColor.RED+"Charged Wolfram Ingot");
		meta.addEnchant(Enchantment.ARROW_DAMAGE, 1, true);
		meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
		item.setItemMeta(meta);
		return item;
	}
	public static ItemStack wolfram_boots() {
		ItemStack item = new ItemStack(Material.IRON_BOOTS);
		ItemMeta meta = item.getItemMeta();
		meta.setDisplayName(ChatColor.WHITE+"Wolfram Boots");
		meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
		meta.addAttributeModifier(Attribute.GENERIC_ARMOR_TOUGHNESS, new AttributeModifier(UUID.randomUUID(), "Extra_Armor", 4D, Operation.ADD_NUMBER, EquipmentSlot.FEET));
		meta.addAttributeModifier(Attribute.GENERIC_ARMOR, new AttributeModifier(UUID.randomUUID(), "Extra_Armor", 6D, Operation.ADD_NUMBER, EquipmentSlot.FEET));
		meta.addEnchant(Enchantment.DURABILITY, 4, true);
		item.setItemMeta(meta);
		return item;
	}
	public static ItemStack wolfram_leggings() {
		ItemStack item = new ItemStack(Material.IRON_LEGGINGS);
		ItemMeta meta = item.getItemMeta();
		meta.setDisplayName(ChatColor.WHITE+"Wolfram Leggings");
		meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
		meta.addAttributeModifier(Attribute.GENERIC_ARMOR_TOUGHNESS, new AttributeModifier(UUID.randomUUID(), "Extra_Armor", 4D, Operation.ADD_NUMBER, EquipmentSlot.LEGS));
		meta.addAttributeModifier(Attribute.GENERIC_ARMOR, new AttributeModifier(UUID.randomUUID(), "Extra_Armor", 12D, Operation.ADD_NUMBER, EquipmentSlot.LEGS));
		meta.addEnchant(Enchantment.DURABILITY, 4, true);
		item.setItemMeta(meta);
		return item;
	}
	public static ItemStack wolfram_chestplate() {
		ItemStack item = new ItemStack(Material.IRON_CHESTPLATE);
		ItemMeta meta = item.getItemMeta();
		meta.setDisplayName(ChatColor.WHITE+"Wolfram Chestplate");
		meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
		meta.addAttributeModifier(Attribute.GENERIC_ARMOR_TOUGHNESS, new AttributeModifier(UUID.randomUUID(), "Extra_Armor", 4D, Operation.ADD_NUMBER, EquipmentSlot.CHEST));
		meta.addAttributeModifier(Attribute.GENERIC_ARMOR, new AttributeModifier(UUID.randomUUID(), "Extra_Armor", 16D, Operation.ADD_NUMBER, EquipmentSlot.CHEST));
		meta.addEnchant(Enchantment.DURABILITY, 4, true);
		item.setItemMeta(meta);
		return item;
	}
	public static ItemStack wolfram_helmet() {
		ItemStack item = new ItemStack(Material.IRON_HELMET);
		ItemMeta meta = item.getItemMeta();
		meta.setDisplayName(ChatColor.WHITE+"Wolfram Helmet");
		meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
		meta.addAttributeModifier(Attribute.GENERIC_ARMOR_TOUGHNESS, new AttributeModifier(UUID.randomUUID(), "Extra_Armor", 4D, Operation.ADD_NUMBER, EquipmentSlot.LEGS));
		meta.addAttributeModifier(Attribute.GENERIC_ARMOR, new AttributeModifier(UUID.randomUUID(), "Extra_Armor", 6D, Operation.ADD_NUMBER, EquipmentSlot.LEGS));
		meta.addEnchant(Enchantment.DURABILITY, 4, true);
		item.setItemMeta(meta);
		return item;
	}
	public static ItemStack wolfram_pickmattock() {
		ItemStack item = new ItemStack(Material.IRON_PICKAXE);
		ItemMeta meta = item.getItemMeta();
		meta.setDisplayName(ChatColor.WHITE+"Wolfram Pick Mattock");
		meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
		item.setItemMeta(meta);
		return item;
	}
	@SuppressWarnings("deprecation")
	public static ItemStack wolfram_pickmattock_durability(Short num) {
		ItemStack item = new ItemStack(Material.IRON_PICKAXE);
		ItemMeta meta = item.getItemMeta();
		meta.setDisplayName(ChatColor.WHITE+"Wolfram Pick Mattock");
		meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
		item.setDurability(num);
		item.setItemMeta(meta);
		return item;
	}
	public static class recipes{
		@SuppressWarnings("deprecation")
		public static void refined() {
			NamespacedKey key = new NamespacedKey(MoreBosses.getPlugin(MoreBosses.class), "smelt_wolfram_ore");
			RecipeChoice input = new RecipeChoice.ExactChoice(wolfram.ore());
			FurnaceRecipe recipe = new FurnaceRecipe(key, wolfram.refined(), input, 0, 20*60);
			Bukkit.addRecipe(recipe);
			RecipeStorage.addrecipe(recipe, key);
		}
		@SuppressWarnings("deprecation")
		public static void ingot() {
			NamespacedKey key = new NamespacedKey(MoreBosses.getPlugin(MoreBosses.class), "wolfram_ingot");
			RecipeChoice choice = new RecipeChoice.ExactChoice(wolfram.refined());
			ItemStack result = wolfram.ingot();
			ShapedRecipe recipe=new ShapedRecipe(key, result);
			recipe.shape("***", "***", "***");
			recipe.setIngredient('*', choice);
			Bukkit.addRecipe(recipe);
			RecipeStorage.addrecipe(recipe, key);
		}
		@SuppressWarnings("deprecation")
		public static void wolfram_boots() {
			NamespacedKey key = new NamespacedKey(MoreBosses.getPlugin(MoreBosses.class), "wolfram_boots");
			RecipeChoice choice = new RecipeChoice.ExactChoice(wolfram.ingot());
			ItemStack result = wolfram.wolfram_boots();
			ShapedRecipe recipe=new ShapedRecipe(key, result);
			recipe.shape("   ", "* *", "* *");
			recipe.setIngredient('*', choice);
			recipe.setGroup("wolfram_boots");
			Bukkit.addRecipe(recipe);
			RecipeStorage.addrecipe(recipe, key);
		}
		@SuppressWarnings("deprecation")
		public static void wolfram_leggings() {
			NamespacedKey key = new NamespacedKey(MoreBosses.getPlugin(MoreBosses.class), "wolfram_leggings");
			RecipeChoice choice = new RecipeChoice.ExactChoice(wolfram.ingot());
			ItemStack result = wolfram.wolfram_leggings();
			ShapedRecipe recipe=new ShapedRecipe(key, result);
			recipe.shape("***", "* *", "* *");
			recipe.setIngredient('*', choice);
			recipe.setGroup("wolfram_leggings");
			Bukkit.addRecipe(recipe);
			RecipeStorage.addrecipe(recipe, key);
		}
		@SuppressWarnings("deprecation")
		public static void wolfram_chestplate() {
			NamespacedKey key = new NamespacedKey(MoreBosses.getPlugin(MoreBosses.class), "wolfram_chestplate");
			RecipeChoice choice = new RecipeChoice.ExactChoice(wolfram.ingot());
			ItemStack result = wolfram.wolfram_chestplate();
			ShapedRecipe recipe=new ShapedRecipe(key, result);
			recipe.shape("* *", "***", "***");
			recipe.setIngredient('*', choice);
			recipe.setGroup("wolfram_chestplate");
			Bukkit.addRecipe(recipe);
			RecipeStorage.addrecipe(recipe, key);
		}
		@SuppressWarnings("deprecation")
		public static void wolfram_helmet() {
			NamespacedKey key = new NamespacedKey(MoreBosses.getPlugin(MoreBosses.class), "wolfram_helmet");
			RecipeChoice choice = new RecipeChoice.ExactChoice(wolfram.ingot());
			ItemStack result = wolfram.wolfram_helmet();
			ShapedRecipe recipe=new ShapedRecipe(key, result);
			recipe.shape("***", "* *", "   ");
			recipe.setIngredient('*', choice);
			recipe.setGroup("wolfram_helmet");
			Bukkit.addRecipe(recipe);
			RecipeStorage.addrecipe(recipe, key);
		}
		@SuppressWarnings("deprecation")
		public static void wolfram_pick_mattock() {
			NamespacedKey key = new NamespacedKey(MoreBosses.getPlugin(MoreBosses.class), "wolfram_pick_mattock");
			RecipeChoice choice = new RecipeChoice.ExactChoice(wolfram.refined());
			ItemStack result = wolfram.wolfram_pickmattock();
			result.setDurability((short) 233);
			ShapedRecipe recipe=new ShapedRecipe(key, result);
			recipe.shape("   ", " **", " % ");
			recipe.setIngredient('*', choice);
			recipe.setIngredient('%', Material.STICK);
			recipe.setGroup("wolfram_pick_mattock");
			Bukkit.addRecipe(recipe);
			RecipeStorage.addrecipe(recipe, key);
		}
	}
}
