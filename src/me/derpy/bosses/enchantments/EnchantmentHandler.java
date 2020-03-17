package me.derpy.bosses.enchantments;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.bukkit.Bukkit;
import org.bukkit.NamespacedKey;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.EnchantmentStorageMeta;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;

import me.derpy.bosses.Morebosses;
import me.derpy.bosses.utilities.Console;
import net.md_5.bungee.api.ChatColor;

public class EnchantmentHandler {
	final public Enchantment EMBER;
	final public Enchantment FLEET;
	final private Map<String, Enchantment> ENCHANTMENTS = new HashMap<String, Enchantment>();
	private Map<String, Enchantment> enchantments_custom = new HashMap<String, Enchantment>();
	final private static String[] NUMERALS = { "I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX", "X" };
	private static final NamespacedKey ENCHANTMENT_KEY_IDENTIFIER = NamespacedKey
			.minecraft("morebosses-enchantment-indentifier");

	public EnchantmentHandler()
			throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {
		this.setAcceptingNew(true);
		this.EMBER = this.registerEnchantment(new BEmber(NamespacedKey.minecraft("enchant-ember")), true);
		this.FLEET = this.registerEnchantment(new BFleet(NamespacedKey.minecraft("enchant-fleet")), true);
		this.setAcceptingNew(false);
	}

	public static ItemStack addEnchantCopy(ItemStack item, Enchantment enchantment, int level, boolean book) {
		ItemStack newItem = item.clone();
		ItemMeta meta = newItem.getItemMeta();
		if (!book) {
			meta.addEnchant(enchantment, level, true);
		} else {
			((EnchantmentStorageMeta) meta).addStoredEnchant(enchantment, level, true);
		}
		List<String> lore = meta.getLore();
		if (lore == null) {
			lore = new ArrayList<String>();
		}
		lore.add(ChatColor.GRAY + getNumeral(enchantment, level));
		meta.setLore(lore);
		newItem.setItemMeta(meta);
		return newItem;
	}

	public static boolean addTag(ItemStack item) {
		ItemMeta meta = item.getItemMeta();
		if (!meta.getPersistentDataContainer().has(ENCHANTMENT_KEY_IDENTIFIER, PersistentDataType.INTEGER)) {
			meta.getPersistentDataContainer().set(ENCHANTMENT_KEY_IDENTIFIER, PersistentDataType.INTEGER, 1);
			item.setItemMeta(meta);
			return true;
		} else {
			return false;
		}
	}

	public static boolean removeTag(ItemStack item) {
		ItemMeta meta = item.getItemMeta();
		if (meta.getPersistentDataContainer().has(ENCHANTMENT_KEY_IDENTIFIER, PersistentDataType.INTEGER)) {
			meta.getPersistentDataContainer().remove(ENCHANTMENT_KEY_IDENTIFIER);
			item.setItemMeta(meta);
			return true;
		} else {
			return false;
		}
	}

	public static boolean hasTag(ItemStack item) {
		ItemMeta meta = item.getItemMeta();
		if (meta.getPersistentDataContainer().has(ENCHANTMENT_KEY_IDENTIFIER, PersistentDataType.INTEGER)) {
			return true;
		} else {
			return false;
		}
	}

	@SuppressWarnings("deprecation")
	public static String getNumeral(Enchantment enchantment, int level) {
		if (level == 1 && enchantment.getMaxLevel() == 1) {
			return enchantment.getName();
		}
		if (level > 10 || level <= 0) {
			return enchantment.getName() + " " + Integer.toString(level);
		}
		return enchantment.getName() + " " + NUMERALS[level - 1];
	}

	public Map<String, Enchantment> getEnchantments() {
		return this.ENCHANTMENTS;
	}

	public Map<String, Enchantment> getCustomEnchantments() {
		return Collections.unmodifiableMap(this.enchantments_custom);
	}

	public void registerEnchantment(Enchantment enchantment)
			throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {
		this.registerEnchantment(enchantment, false);
	}

	public void unregisterEnchantment(Enchantment enchantment) {
		this.unregisterEnchantment(enchantment, false);
	}

	private void setAcceptingNew(boolean b) {
		try {
			Field acceptingNew = Enchantment.class.getDeclaredField("acceptingNew");
			acceptingNew.setAccessible(b);
			acceptingNew.set(null, b);
		} catch (Exception e) {
		}
	}

	@SuppressWarnings("deprecation")
	private Enchantment registerEnchantment(Enchantment enchantment, boolean isBosses)
			throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {
		if (enchantment instanceof Listener) {
			Bukkit.getServer().getPluginManager().registerEvents((@NotNull Listener) enchantment,
					JavaPlugin.getPlugin(Morebosses.class));
		}
		if (Enchantment.isAcceptingRegistrations()) {
			Enchantment.registerEnchantment(enchantment);
			if (isBosses) {
				this.ENCHANTMENTS.put(enchantment.getName().toUpperCase(), enchantment);
			} else {
				this.enchantments_custom.put(enchantment.getName().toUpperCase(), enchantment);
			}
			Console.print("Registered Enchantment: " + enchantment.getName().toUpperCase());
			return enchantment;
		} else {
			Field acceptingNew = Enchantment.class.getDeclaredField("acceptingNew");
			acceptingNew.setAccessible(true);
			acceptingNew.set(null, true);
			Enchantment.registerEnchantment(enchantment);
			if (isBosses) {
				this.ENCHANTMENTS.put(enchantment.getName().toUpperCase(), enchantment);
			} else {
				this.enchantments_custom.put(enchantment.getName().toUpperCase(), enchantment);
			}
			Console.print("Registered Enchantment: " + enchantment.getName().toUpperCase());
			return enchantment;
		}

	}

	@SuppressWarnings({ "unchecked", "deprecation" })
	private void unregisterEnchantment(Enchantment enchantment, boolean isBosses) {
		try {
			Field keyField = Enchantment.class.getDeclaredField("byKey");
			keyField.setAccessible(true);
			HashMap<NamespacedKey, Enchantment> byKey = (HashMap<NamespacedKey, Enchantment>) keyField.get(null);
			if (byKey.containsKey(enchantment.getKey())) {
				byKey.remove(enchantment.getKey());
			}
			Field nameField = Enchantment.class.getDeclaredField("byName");
			nameField.setAccessible(true);
			HashMap<String, Enchantment> byName = (HashMap<String, Enchantment>) nameField.get(null);
			if (byName.containsKey(enchantment.getName())) {
				byName.remove(enchantment.getName());
			}
			keyField.setAccessible(false);
			nameField.setAccessible(false);
			if (this.ENCHANTMENTS.containsKey(enchantment.getName().toUpperCase())) {
				this.ENCHANTMENTS.remove(enchantment.getName().toUpperCase(), enchantment);
			}
			if (this.enchantments_custom.containsKey(enchantment.getName().toUpperCase())) {
				this.enchantments_custom.remove(enchantment.getName().toUpperCase(), enchantment);
			}
		} catch (Exception e) {
			Console.print("Failed to unregister: " + enchantment.getName().toUpperCase());
		}
	}
}
