package me.Derpy.Bosses.utilities;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;

import me.Derpy.Bosses.utilities.items.cursed_diamond;
import me.Derpy.Bosses.utilities.items.ichor;
import me.Derpy.Bosses.utilities.items.infused_diamond;
import me.Derpy.Bosses.utilities.items.soul;
import net.md_5.bungee.api.ChatColor;

public class inventories {
	public static Inventory curserecipe(Plugin plugin) {
		Inventory recipe = Bukkit.createInventory(null, InventoryType.WORKBENCH, ChatColor.DARK_RED+"Recipe");
		recipe.setItem(0, cursed_diamond.get(plugin));
		recipe.setItem(2, infused_diamond.get());
		recipe.setItem(4, infused_diamond.get());
		recipe.setItem(5, infused_diamond.get());
		recipe.setItem(6, infused_diamond.get());
		recipe.setItem(8, infused_diamond.get());
		return recipe;
	}
	public static Inventory ichorstick(Plugin plugin) {
		Inventory recipe = Bukkit.createInventory(null, InventoryType.WORKBENCH, ChatColor.DARK_RED+"Recipe");
		recipe.setItem(0, ichor.stick(plugin));
		recipe.setItem(5, ichor.getraw(plugin));
		recipe.setItem(8, ichor.getraw(plugin));
		return recipe;
	}
	public static Inventory ichorsword(Plugin plugin) {
		Inventory recipe = Bukkit.createInventory(null, InventoryType.WORKBENCH, ChatColor.DARK_RED+"Recipe");
		recipe.setItem(0, ichor.sword(plugin));
		recipe.setItem(5, ichor.getraw(plugin));
		recipe.setItem(2, ichor.getraw(plugin));
		recipe.setItem(8, ichor.stick(plugin));
		return recipe;
	}
	public static Inventory ichorboots(Plugin plugin) {
		Inventory recipe = Bukkit.createInventory(null, InventoryType.WORKBENCH, ChatColor.DARK_RED+"Recipe");
		recipe.setItem(0, ichor.boots(plugin));
		recipe.setItem(4, ichor.getraw(plugin));
		recipe.setItem(6, ichor.getraw(plugin));
		recipe.setItem(7, ichor.getraw(plugin));
		recipe.setItem(9, ichor.getraw(plugin));
		return recipe;
	}
	public static Inventory ichorlegs(Plugin plugin) {
		Inventory recipe = Bukkit.createInventory(null, InventoryType.WORKBENCH, ChatColor.DARK_RED+"Recipe");
		recipe.setItem(0, ichor.leggings(plugin));
		recipe.setItem(1, ichor.getraw(plugin));
		recipe.setItem(2, ichor.getraw(plugin));
		recipe.setItem(3, ichor.getraw(plugin));
		recipe.setItem(4, ichor.getraw(plugin));
		recipe.setItem(6, ichor.getraw(plugin));
		recipe.setItem(7, ichor.getraw(plugin));
		recipe.setItem(9, ichor.getraw(plugin));
		return recipe;
	}
	public static Inventory ichorchest(Plugin plugin) {
		Inventory recipe = Bukkit.createInventory(null, InventoryType.WORKBENCH, ChatColor.DARK_RED+"Recipe");
		recipe.setItem(0, ichor.chest(plugin));
		recipe.setItem(1, ichor.getraw(plugin));
		recipe.setItem(5, ichor.getraw(plugin));
		recipe.setItem(3, ichor.getraw(plugin));
		recipe.setItem(4, ichor.getraw(plugin));
		recipe.setItem(6, ichor.getraw(plugin));
		recipe.setItem(7, ichor.getraw(plugin));
		recipe.setItem(8, ichor.getraw(plugin));
		recipe.setItem(9, ichor.getraw(plugin));
		return recipe;
	}
	public static Inventory ichorhelm(Plugin plugin) {
		Inventory recipe = Bukkit.createInventory(null, InventoryType.WORKBENCH, ChatColor.DARK_RED+"Recipe");
		recipe.setItem(0, ichor.helmet(plugin));
		recipe.setItem(4, ichor.getraw(plugin));
		recipe.setItem(1, ichor.getraw(plugin));
		recipe.setItem(2, ichor.getraw(plugin));
		recipe.setItem(3, ichor.getraw(plugin));
		recipe.setItem(6, ichor.getraw(plugin));
		return recipe;
	}
	@SuppressWarnings("deprecation")
	public static Inventory elytra(Plugin plugin) {
		Inventory recipe = Bukkit.createInventory(null, InventoryType.WORKBENCH, ChatColor.DARK_RED+"Recipe");
		ItemStack result = new ItemStack(Material.ELYTRA);
		result.setDurability((short) 400);
		result.addEnchantment(Enchantment.VANISHING_CURSE, 1);
		recipe.setItem(0, result);
		recipe.setItem(1, soul.get());
		recipe.setItem(2, soul.get());
		recipe.setItem(3, soul.get());
		recipe.setItem(4, soul.get());
		recipe.setItem(5, soul.get());
		recipe.setItem(6, soul.get());
		recipe.setItem(7, soul.get());
		recipe.setItem(9, soul.get());
		return recipe;
		//https://wiki.vg/File:CraftingTable-slots.png
	}
}
