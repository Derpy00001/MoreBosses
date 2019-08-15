package me.Derpy.Bosses.utilities.raid_inventories;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

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
		item.addEnchantment(Enchantment.ARROW_DAMAGE, 3);
		item.addEnchantment(Enchantment.KNOCKBACK, 3);
		item.addEnchantment(Enchantment.CHANNELING, 3);
		item.addEnchantment(Enchantment.ARROW_KNOCKBACK, 3);
		enchants.add(item);
		ItemStack item2 = new ItemStack(Material.ENCHANTED_BOOK);
		item2.addEnchantment(Enchantment.MENDING, 1);
		enchants.add(item);
		ItemStack item3 = new ItemStack(Material.ENCHANTED_BOOK);
		item3.addEnchantment(Enchantment.DAMAGE_ALL, 4);
		item3.addEnchantment(Enchantment.ARROW_FIRE, 3);
		item.addEnchantment(Enchantment.ARROW_INFINITE, 1);
		item3.addEnchantment(Enchantment.DURABILITY, 3);
		item3.addEnchantment(Enchantment.SWEEPING_EDGE, 2);
		enchants.add(item3);
		prize.addItem(enchants.get(Random.random(enchants)));
		return prize;
	}
}
