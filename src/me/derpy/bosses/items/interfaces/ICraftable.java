package me.derpy.bosses.items.interfaces;

import org.bukkit.inventory.Recipe;

public interface ICraftable extends ILootable {
	Recipe[] getRecipes();
}
