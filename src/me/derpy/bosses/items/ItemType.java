package me.derpy.bosses.items;

import org.bukkit.Material;
import org.bukkit.NamespacedKey;

import me.derpy.bosses.items.enchantments.EmberBook;
import me.derpy.bosses.items.interfaces.ILootable;
import me.derpy.bosses.items.spoils.SpoilTier1;
import me.derpy.bosses.items.spoils.SpoilTier2;
import me.derpy.bosses.items.weapons.Trident;

public enum ItemType {
	SPOILS_TIER1(new SpoilTier1()), SPOILS_TIER2(new SpoilTier2()), TRIDENT(new Trident()), BOOK_EMBER(new EmberBook());

	public static ItemType getFromName(String name) {
		for (ItemType type : ItemType.values()) {
			if (type.name().toLowerCase().equals(name.toLowerCase())) {
				return type;
			}
		}
		return null;
	}

	public static ItemType getFromKey(NamespacedKey key) {
		for (ItemType type : ItemType.values()) {
			if (type.getNamespacedKey().equals(key)) {
				return type;
			}
		}
		return null;
	}

	public static ItemType getFromInterfaceClass(Class<? extends ILootable> clazz) {
		for (ItemType itemType : ItemType.values()) {
			if (itemType.getInterface().getClass() == clazz) {
				return itemType;
			}
		}
		return null;
	}

	private final ILootable ilootable;

	ItemType(ILootable ilootable) {
		this.ilootable = ilootable;
	}

	public ILootable getInterface() {
		return this.ilootable;
	}

	public Material getMaterial() {
		return this.ilootable.getItem().getType();
	}

	public NamespacedKey getNamespacedKey() {
		return this.ilootable.getNamespacedKey();
	}
}
