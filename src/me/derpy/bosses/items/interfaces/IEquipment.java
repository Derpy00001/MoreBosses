package me.derpy.bosses.items.interfaces;

import org.bukkit.inventory.EquipmentSlot;

import me.derpy.bosses.items.PrefixType;

public interface IEquipment extends ILootable {
	EquipmentSlot getEquipmentSlot();

	boolean isWeapon();

	boolean hasRandomTitle();

	PrefixType getPrefix();
}
