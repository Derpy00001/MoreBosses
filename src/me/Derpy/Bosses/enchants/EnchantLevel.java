package me.Derpy.Bosses.enchants;

import org.bukkit.ChatColor;
import org.bukkit.enchantments.Enchantment;


public class EnchantLevel {
	private static final String[] NUMERALS = { "I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX", "X" };

	@SuppressWarnings("deprecation")
	public static String returnEnchantmentName(Enchantment ench, int enchLevel, ChatColor color){
	    if(enchLevel == 1 && ench.getMaxLevel() == 1){
	        return ChatColor.RESET+""+color+ench.getName();
	    }
	    if(enchLevel > 10 || enchLevel <= 0){
	        return ChatColor.RESET+""+color+ench.getName() + " enchantment.level." + enchLevel;
	    }
	 
	    return ChatColor.RESET+""+color+ench.getName() + " " + NUMERALS[enchLevel- 1];
	}
}
