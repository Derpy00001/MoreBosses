package me.Derpy.Bosses.utilities.raid_inventories;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.EnchantmentStorageMeta;

import me.Derpy.Bosses.MoreBosses;
import me.Derpy.Bosses.utilities.ConfigManager;
import me.Derpy.Bosses.utilities.Random;
import me.Derpy.Bosses.utilities.items.Enchants;
import me.Derpy.Bosses.utilities.items.RecipeStorage;
import me.Derpy.Bosses.utilities.items.ghast;
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
		if(!MoreBosses.getPlugin(MoreBosses.class).getConfig().getBoolean("bosses.custom_loot_pools.ghast_raid")) {
			prize.addItem((ItemStack) ghast.tissue(num));
		}else {
			@SuppressWarnings("unchecked")
			ArrayList<ItemStack> a = (ArrayList<ItemStack>) ConfigManager.pools.getPools().get("MoreBosses.Pool.ghast_raid");
			while(a.remove(null)) {};
			prize.addItem(a.get(Random.random(a)));
		}
		return prize;
	}
	public static Inventory gladiator_get() {
		Inventory prize = Bukkit.createInventory(null, 9, ChatColor.RED+"Spoils");
		
		// Give rewards
		if(!MoreBosses.getPlugin(MoreBosses.class).getConfig().getBoolean("bosses.custom_loot_pools.ghast_raid")) {
			Integer min2 = 1;
			Integer max2 = 6-1;
			Integer range2 = max2-min2+1;
			int num2 = (int) ((int)(Math.random()*range2)+min2);
			prize.addItem(new ItemStack(Material.DIAMOND, num2));
			prize.addItem(soul.get(21));
			ArrayList<ItemStack> recipesbooks = RecipeStorage.getbooks();
			prize.addItem(recipesbooks.get(Random.random(recipesbooks)));
			ArrayList<ItemStack> enchants = new ArrayList<ItemStack>();
			enchants.add(Enchants.fleet(1));
			enchants.add(Enchants.ember());
			enchants.add(Enchants.lifesteal());
			enchants.add(Enchants.undying());
			enchants.add(Enchants.replenish());
			ItemStack item8 = new ItemStack(Material.ENCHANTED_BOOK);
			EnchantmentStorageMeta meta8 =(EnchantmentStorageMeta) item8.getItemMeta();
			meta8.addStoredEnchant(Enchantment.BINDING_CURSE, 1, true);
			item8.setItemMeta(meta8);
			enchants.add(item8);
			prize.addItem(enchants.get(Random.random(enchants)));
		}else {
			@SuppressWarnings("unchecked")
			ArrayList<ItemStack> a = (ArrayList<ItemStack>) ConfigManager.pools.getPools().get("MoreBosses.Pool.gladiator");
			while(a.remove(null)) {};
			prize.addItem(a.get(Random.random(a)));
			prize.addItem(a.get(Random.random(a)));
			prize.addItem(a.get(Random.random(a)));
			prize.addItem(a.get(Random.random(a)));
		}
		return prize;
	}
}
