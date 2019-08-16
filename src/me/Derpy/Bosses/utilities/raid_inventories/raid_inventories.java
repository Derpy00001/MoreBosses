package me.Derpy.Bosses.utilities.raid_inventories;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.EnchantmentStorageMeta;

import me.Derpy.Bosses.utilities.Random;
import me.Derpy.Bosses.utilities.items.ghast;
import me.Derpy.Bosses.utilities.items.recipes;
import me.Derpy.Bosses.utilities.items.soul;
import net.md_5.bungee.api.ChatColor;

public class raid_inventories {
	public static Inventory ghast_get() {
		Integer min = 3;
		Integer max = 11-1;
		Integer range = max-min+1;
		int num = (int) ((int)(Math.random()*range)+min);
		Inventory prize = Bukkit.createInventory(null, 9, ChatColor.RED+"Spoils");
		
		// Give rewards
		prize.addItem((ItemStack) ghast.tissue(num));
		return prize;
	}
	public static Inventory gladiator_get() {
		Integer min = 1;
		Integer max = 3-1;
		Integer range = max-min+1;
		int num = (int) ((int)(Math.random()*range)+min);
		Inventory prize = Bukkit.createInventory(null, 9, ChatColor.RED+"Spoils");
		
		// Give rewards
		prize.addItem((ItemStack) ghast.tissue(num));
		Integer min2 = 1;
		Integer max2 = 6-1;
		Integer range2 = max2-min2+1;
		int num2 = (int) ((int)(Math.random()*range2)+min2);
		prize.addItem(new ItemStack(Material.DIAMOND, num2));
		prize.addItem(soul.get(21));
		ArrayList<ItemStack> recipesbooks = new ArrayList<ItemStack>();
		recipesbooks.add(recipes.cursed());
		recipesbooks.add(recipes.elytra());
		recipesbooks.add(recipes.ghastboots());
		recipesbooks.add(recipes.ghastchest());
		recipesbooks.add(recipes.ghasthelm());
		recipesbooks.add(recipes.ghastlegs());
		recipesbooks.add(recipes.ichorboots());
		recipesbooks.add(recipes.ichorchest());
		recipesbooks.add(recipes.ichorlegs());
		recipesbooks.add(recipes.ichorhelm());
		recipesbooks.add(recipes.ichorstick());
		recipesbooks.add(recipes.ichorsword());
		prize.addItem(recipesbooks.get(Random.random(recipesbooks)));
		ArrayList<ItemStack> enchants = new ArrayList<ItemStack>();
		ItemStack item = new ItemStack(Material.ENCHANTED_BOOK);
		EnchantmentStorageMeta meta =(EnchantmentStorageMeta) item.getItemMeta();
		meta.addStoredEnchant(Enchantment.KNOCKBACK, 2, true);
		meta.addStoredEnchant(Enchantment.CHANNELING, 2, true);
		meta.addStoredEnchant(Enchantment.ARROW_KNOCKBACK, 2, true);
		item.setItemMeta(meta);
		enchants.add(item);
		ItemStack item2 = new ItemStack(Material.ENCHANTED_BOOK);
		EnchantmentStorageMeta meta2 =(EnchantmentStorageMeta) item2.getItemMeta();
		meta2.addStoredEnchant(Enchantment.MENDING, 1, true);
		meta2.addStoredEnchant(Enchantment.DURABILITY, 2, true);
		item2.setItemMeta(meta2);
		enchants.add(item2);
		ItemStack item3 = new ItemStack(Material.ENCHANTED_BOOK);
		EnchantmentStorageMeta meta3 =(EnchantmentStorageMeta) item3.getItemMeta();
		meta3.addStoredEnchant(Enchantment.DAMAGE_ALL, 2, true);
		meta3.addStoredEnchant(Enchantment.ARROW_DAMAGE, 2, true);
		item3.setItemMeta(meta3);
		enchants.add(item3);
		ItemStack item4 = new ItemStack(Material.ENCHANTED_BOOK);
		EnchantmentStorageMeta meta4 =(EnchantmentStorageMeta) item4.getItemMeta();
		meta4.addStoredEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 2, true);
		meta4.addStoredEnchant(Enchantment.THORNS, 2, true);
		item4.setItemMeta(meta4);
		enchants.add(item4);
		ItemStack item5 = new ItemStack(Material.ENCHANTED_BOOK);
		EnchantmentStorageMeta meta5 =(EnchantmentStorageMeta) item5.getItemMeta();
		meta5.addStoredEnchant(Enchantment.FROST_WALKER, 2, true);
		meta5.addStoredEnchant(Enchantment.DEPTH_STRIDER, 2, true);
		meta5.addStoredEnchant(Enchantment.PROTECTION_FALL, 2, true);
		item5.setItemMeta(meta5);
		enchants.add(item5);
		ItemStack item6 = new ItemStack(Material.ENCHANTED_BOOK);
		EnchantmentStorageMeta meta6 =(EnchantmentStorageMeta) item6.getItemMeta();
		meta6.addStoredEnchant(Enchantment.OXYGEN, 2, true);
		item6.setItemMeta(meta6);
		enchants.add(item6);
		ItemStack item7 = new ItemStack(Material.ENCHANTED_BOOK);
		EnchantmentStorageMeta meta7 =(EnchantmentStorageMeta) item7.getItemMeta();
		meta7.addStoredEnchant(Enchantment.ARROW_KNOCKBACK, 2, true);
		meta7.addStoredEnchant(Enchantment.ARROW_FIRE, 2, true);
		meta7.addStoredEnchant(Enchantment.ARROW_INFINITE, 2, true);
		meta7.addStoredEnchant(Enchantment.KNOCKBACK, 2, true);
		meta7.addStoredEnchant(Enchantment.FIRE_ASPECT, 2, true);
		meta7.addStoredEnchant(Enchantment.DIG_SPEED, 2, true);
		meta7.addStoredEnchant(Enchantment.SILK_TOUCH, 2, true);
		meta7.addStoredEnchant(Enchantment.THORNS, 2, true);
		meta7.addStoredEnchant(Enchantment.LUCK, 2, true);
		meta7.addStoredEnchant(Enchantment.LOYALTY, 2, true);
		meta7.addStoredEnchant(Enchantment.QUICK_CHARGE, 2, true);
		meta7.addStoredEnchant(Enchantment.VANISHING_CURSE, 2, true);
		item7.setItemMeta(meta7);
		enchants.add(item7);
		ItemStack item8 = new ItemStack(Material.ENCHANTED_BOOK);
		EnchantmentStorageMeta meta8 =(EnchantmentStorageMeta) item8.getItemMeta();
		meta8.addStoredEnchant(Enchantment.BINDING_CURSE, 1, true);
		item8.setItemMeta(meta8);
		enchants.add(item8);
		prize.addItem(enchants.get(Random.random(enchants)));
		return prize;
	}
}
