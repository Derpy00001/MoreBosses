package me.Derpy.Bosses.utilities.raid_inventories;

import org.bukkit.Bukkit;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import me.Derpy.Bosses.utilities.items.ghast;
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
}
