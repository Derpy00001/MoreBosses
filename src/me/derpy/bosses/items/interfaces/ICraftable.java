package me.derpy.bosses.items.interfaces;

import java.util.Map;

import org.bukkit.NamespacedKey;
import org.bukkit.inventory.Recipe;

public interface ICraftable extends ILootable {
	Recipe[] getRecipes();
}
