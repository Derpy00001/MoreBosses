package me.derpy.bosses.items.interfaces;

import org.bukkit.inventory.EquipmentSlot;

public interface IEquipment extends ILootable {
	EquipmentSlot getEquipmentSlot();

	boolean isWeapon();
}
