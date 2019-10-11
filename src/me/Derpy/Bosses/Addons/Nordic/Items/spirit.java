package me.Derpy.Bosses.Addons.Nordic.Items;

import java.util.Arrays;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.Color;
import org.bukkit.FireworkEffect;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.attribute.AttributeModifier.Operation;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.RecipeChoice;
import org.bukkit.inventory.ShapelessRecipe;
import org.bukkit.inventory.meta.FireworkEffectMeta;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.LeatherArmorMeta;

import me.Derpy.Bosses.MoreBosses;
import me.Derpy.Bosses.utilities.items.RecipeStorage;
import net.md_5.bungee.api.ChatColor;

public class spirit {
	static UUID id = UUID.fromString("bf91325f-4050-4e8a-8e5f-094c439b98bc");
	static UUID id2 = UUID.fromString("b63891b8-3f6d-43d5-b8fe-dde2fb01bb97");
	public static ItemStack chestplate() {
		ItemStack item = new ItemStack(Material.LEATHER_CHESTPLATE);
		LeatherArmorMeta meta = (LeatherArmorMeta) item.getItemMeta();
		meta.setColor(Color.BLACK);
		meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES, ItemFlag.HIDE_UNBREAKABLE, ItemFlag.HIDE_ENCHANTS);
		meta.setDisplayName(ChatColor.RED+"Spirit Tunic");
		meta.addAttributeModifier(Attribute.GENERIC_MAX_HEALTH, new AttributeModifier(id, "Extra_Health", 6D, Operation.ADD_NUMBER, EquipmentSlot.CHEST));
		meta.addEnchant(Enchantment.ARROW_DAMAGE, 0, true);
		meta.setUnbreakable(true);
		item.setItemMeta(meta);
		return item;
	}
	public static ItemStack pants() {
		ItemStack item = new ItemStack(Material.LEATHER_LEGGINGS);
		LeatherArmorMeta meta = (LeatherArmorMeta) item.getItemMeta();
		meta.setColor(Color.BLACK);
		meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES, ItemFlag.HIDE_UNBREAKABLE, ItemFlag.HIDE_ENCHANTS);
		meta.setDisplayName(ChatColor.RED+"Spirit Leggings");
		meta.addAttributeModifier(Attribute.GENERIC_MAX_HEALTH, new AttributeModifier(id, "Extra_Health", 4D, Operation.ADD_NUMBER, EquipmentSlot.LEGS));
		meta.addEnchant(Enchantment.ARROW_DAMAGE, 0, true);
		meta.setUnbreakable(true);
		item.setItemMeta(meta);
		return item;
	}
	public static ItemStack helmet() {
		ItemStack item = new ItemStack(Material.LEATHER_HELMET);
		LeatherArmorMeta meta = (LeatherArmorMeta) item.getItemMeta();
		meta.setColor(Color.BLACK);
		meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES, ItemFlag.HIDE_UNBREAKABLE, ItemFlag.HIDE_ENCHANTS);
		meta.setDisplayName(ChatColor.RED+"Spirit Helmet");
		meta.addAttributeModifier(Attribute.GENERIC_MAX_HEALTH, new AttributeModifier(id, "Extra_Health", 2D, Operation.ADD_NUMBER, EquipmentSlot.HEAD));
		meta.addEnchant(Enchantment.ARROW_DAMAGE, 0, true);
		meta.setUnbreakable(true);
		item.setItemMeta(meta);
		return item;
	}
	public static ItemStack boots() {
		ItemStack item = new ItemStack(Material.LEATHER_BOOTS);
		LeatherArmorMeta meta = (LeatherArmorMeta) item.getItemMeta();
		meta.setColor(Color.BLACK);
		meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES, ItemFlag.HIDE_UNBREAKABLE, ItemFlag.HIDE_ENCHANTS);
		meta.setDisplayName(ChatColor.RED+"Spirit Boots");
		meta.addAttributeModifier(Attribute.GENERIC_MAX_HEALTH, new AttributeModifier(id, "Extra_Health", 2D, Operation.ADD_NUMBER, EquipmentSlot.FEET));
		meta.addAttributeModifier(Attribute.GENERIC_MOVEMENT_SPEED, new AttributeModifier(id2, "Extra_Speed", 0.05D, Operation.ADD_NUMBER, EquipmentSlot.FEET));
		meta.addEnchant(Enchantment.ARROW_DAMAGE, 0, true);
		meta.setUnbreakable(true);
		item.setItemMeta(meta);
		return item;
	}
	public static ItemStack spirit_item() {
		ItemStack item = new ItemStack(Material.BLACK_DYE);
		ItemMeta meta = item.getItemMeta();
		meta.setDisplayName(ChatColor.RED+"Spirit");
		meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
		item.setItemMeta(meta);
		return item;
	}
	public static ItemStack blood_fenrir() {
		ItemStack item = new ItemStack(Material.BLACK_DYE);
		ItemMeta meta = item.getItemMeta();
		meta.setDisplayName(ChatColor.RED+"Blood of Fenrir");
		meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
		item.setItemMeta(meta);
		return item;
	}
	public static ItemStack core() {
		ItemStack item = new ItemStack(Material.FIREWORK_STAR);
		FireworkEffectMeta meta = (FireworkEffectMeta) item.getItemMeta();
		meta.setEffect(FireworkEffect.builder().withColor(Color.GRAY).build());
		meta.setDisplayName(ChatColor.RESET+""+ChatColor.DARK_PURPLE+"Magical Core");
		meta.setLore(Arrays.asList(ChatColor.RESET+""+ChatColor.GRAY+"[Uncharged]"));
		item.setItemMeta(meta);
		return item;
	}
	public static ItemStack core_charged_wolf() {
		ItemStack item = new ItemStack(Material.FIREWORK_STAR);
		FireworkEffectMeta meta = (FireworkEffectMeta) item.getItemMeta();
		meta.setEffect(FireworkEffect.builder().withColor(Color.RED).build());
		meta.setDisplayName(ChatColor.RESET+""+ChatColor.DARK_PURPLE+"Magical Core");
		meta.setLore(Arrays.asList(ChatColor.RESET+""+ChatColor.RED+"[Charged]"));
		item.setItemMeta(meta);
		return item;
	}
	public static class recipes {
		@SuppressWarnings("deprecation")
		public static void core_charged_wolf_recipe() {
			NamespacedKey key = new NamespacedKey(MoreBosses.getPlugin(MoreBosses.class), "core_charged_wolf_recipe");
			RecipeChoice blood = new RecipeChoice.ExactChoice(spirit.blood_fenrir());
			RecipeChoice core_uncharged = new RecipeChoice.ExactChoice(spirit.core());
			RecipeChoice spirititem = new RecipeChoice.ExactChoice(spirit.spirit_item());
			ItemStack core = spirit.core_charged_wolf();
			ShapelessRecipe corere = new ShapelessRecipe(key, core);
			corere.addIngredient(blood);
			corere.addIngredient(core_uncharged);
			corere.addIngredient(spirititem);
			Bukkit.addRecipe(corere);
			RecipeStorage.addrecipe(corere, key);
		}
	}
}
