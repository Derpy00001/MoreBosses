package me.derpy.bosses.items.blueprints;

import org.bukkit.inventory.ItemStack;

import me.derpy.bosses.items.interfaces.IAmount;

public class BAmount extends BLootable implements IAmount {

	@Override
	public ItemStack getAmount(int i) {
		// TODO Auto-generated method stub
		ItemStack item = this.getFinalizedItem();
		item.setAmount(i);
		return item;
	}
}
