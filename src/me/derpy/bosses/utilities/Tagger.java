package me.derpy.bosses.utilities;

import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;

public class Tagger {
	private final static NamespacedKey SPOIL_KEY = NamespacedKey.minecraft("spoil-container");
	private final static NamespacedKey RAID_KEY = NamespacedKey.minecraft("raid-item");

	public static ItemStack tagItem(ItemStack item, NamespacedKey key) {
		ItemMeta meta = item.getItemMeta();
		meta.getPersistentDataContainer().set(key, PersistentDataType.INTEGER, 1);
		item.setItemMeta(meta);
		return item;
	}

	public static boolean hasTag(ItemStack item, NamespacedKey key) {
		return item.hasItemMeta() ? item.getItemMeta().getPersistentDataContainer().has(key, PersistentDataType.INTEGER)
				: false;
	}

	public static ItemStack tagRaidItem(ItemStack item) {
		return tagItem(item, RAID_KEY);
	}

	public static boolean hasRaidTag(ItemStack item) {
		return item.hasItemMeta()
				? item.getItemMeta().getPersistentDataContainer().has(RAID_KEY, PersistentDataType.INTEGER)
				: false;
	}

	public static ItemStack tagSpoil(ItemStack item, int lvl) {
		ItemMeta meta = item.getItemMeta();
		meta.getPersistentDataContainer().set(SPOIL_KEY, PersistentDataType.INTEGER, lvl);
		item.setItemMeta(meta);
		return item;
	}

	public static boolean hasSpoilTag(ItemStack item) {
		return item.hasItemMeta()
				? item.getItemMeta().getPersistentDataContainer().has(SPOIL_KEY, PersistentDataType.INTEGER)
				: false;
	}

	public static int getLevelFromSpoilTag(ItemStack item) {
		if (hasSpoilTag(item)) {
			ItemMeta meta = item.getItemMeta();
			return meta.getPersistentDataContainer().get(SPOIL_KEY, PersistentDataType.INTEGER);
		} else {
			return 0;
		}
	}
}
