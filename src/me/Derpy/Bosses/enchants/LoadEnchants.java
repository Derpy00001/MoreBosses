package me.Derpy.Bosses.enchants;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;

import org.bukkit.NamespacedKey;
import org.bukkit.enchantments.Enchantment;

public class LoadEnchants {
	//https://www.spigotmc.org/threads/custom-enchantments-1-13.346538/
	private static ArrayList<Enchantment> enchantments = new ArrayList<Enchantment>();
	public static Boolean isenchantment(Enchantment ench) {
		if(enchantments.contains(ench)) {
			return true;
		}else {
			return false;
		}
	}
	public static void registerEnchantment(Enchantment enchantment) {
	    boolean registered = true;
	    try {
	        Field f = Enchantment.class.getDeclaredField("acceptingNew");
	        f.setAccessible(true);
	        f.set(null, true);
	        Enchantment.registerEnchantment(enchantment);
	        enchantments.add(enchantment);
	    } catch (Exception e) {
	        registered = false;
	    }
	    if(registered){
	        // It's been registered!
	    }
	}
	@SuppressWarnings({ "unchecked", "deprecation"})
	public static void UnloadEnchantment(Enchantment enchant) {
		try {
		    Field keyField = Enchantment.class.getDeclaredField("byKey");
		 
		    keyField.setAccessible(true);
		    HashMap<NamespacedKey, Enchantment> byKey = (HashMap<NamespacedKey, Enchantment>) keyField.get(null);
		 
		    if(byKey.containsKey(enchant.getKey())) {
		        byKey.remove(enchant.getKey());
		    }
		    Field nameField = Enchantment.class.getDeclaredField("byName");

		    nameField.setAccessible(true);
		    HashMap<String, Enchantment> byName = (HashMap<String, Enchantment>) nameField.get(null);

		    if(byName.containsKey(enchant.getName())) {
		        byName.remove(enchant.getName());
		    }
		    keyField.setAccessible(false);
		    enchantments.remove(enchant);
		} catch (Exception ignored) { }
	}
}
