package me.derpy.bosses.items.blueprints;

import org.bukkit.NamespacedKey;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;

import me.derpy.bosses.items.interfaces.IEquipment;

public class BEquipment extends BLootable implements IEquipment {
	@Override
	public ItemStack getItem() {
		return null;
	}

	@Override
	public NamespacedKey getNamespacedKey() {
		return NamespacedKey.minecraft("hidden-hide");
	}

	@Override
	public EquipmentSlot getEquipmentSlot() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isWeapon() {
		// TODO Auto-generated method stub
		return false;
	}

}
