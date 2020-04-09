package me.derpy.bosses.items;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.Recipe;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.ShapelessRecipe;

import me.derpy.bosses.items.blueprints.armor.GhastBoots;
import me.derpy.bosses.items.blueprints.armor.GhastChestplate;
import me.derpy.bosses.items.blueprints.armor.GhastHelmet;
import me.derpy.bosses.items.blueprints.armor.GhastLeggings;
import me.derpy.bosses.items.blueprints.misc.BannerGlobe;
import me.derpy.bosses.items.blueprints.misc.BannerSkull;
import me.derpy.bosses.items.blueprints.misc.GhastTissue;
import me.derpy.bosses.items.blueprints.misc.GhastTotem;
import me.derpy.bosses.items.interfaces.ICraftable;
import me.derpy.bosses.items.interfaces.ILootable;
import me.derpy.bosses.items.spoils.SpoilTier1;
import me.derpy.bosses.items.spoils.SpoilTier2;
import me.derpy.bosses.items.spoils.SpoilTier3;
import me.derpy.bosses.items.spoils.SpoilTier4;

public enum ItemType {
	SPOILS_TIER1(new SpoilTier1()), SPOILS_TIER2(new SpoilTier2()), SPOILS_TIER3(new SpoilTier3()),
	SPOILS_TIER4(new SpoilTier4()), BANNER_DEATH(new BannerSkull()), BANNER_OVERLORD(new BannerGlobe()),
	GHAST_TISSUE(new GhastTissue()), GHAST_TOTEM(new GhastTotem()), GHAST_HELMET(new GhastHelmet()),
	GHAST_CHESTPLATE(new GhastChestplate()), GHAST_LEGGINGS(new GhastLeggings()), GHAST_BOOTS(new GhastBoots());

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

	public static ItemType[] getFromInterfaceClass(Class<? extends ILootable> clazz) {
		List<ItemType> type = new ArrayList<ItemType>();
		for (ItemType itemType : ItemType.values()) {
			if (itemType.getInterface().getClass() == clazz) {
				type.add(itemType);
			}
		}
		ItemType[] array = new ItemType[type.size()];
		type.toArray(array);
		return array;
	}

	static Map<NamespacedKey, Recipe> recipes = new HashMap<NamespacedKey, Recipe>();

	public static void addRecipe(NamespacedKey key, Recipe recipe) {
		recipes.put(key, recipe);
	}

	public static Map<NamespacedKey, Recipe> getRecipes() {
		return Collections.unmodifiableMap(recipes);
	}

	private final ILootable ilootable;

	ItemType(ILootable ilootable) {
		this.ilootable = ilootable;
		if (ilootable instanceof ICraftable) {
			if(((ICraftable) ilootable).getRecipes().length>0) {
				for (Recipe recipe : Arrays.asList(((ICraftable) ilootable).getRecipes())) {
					if(recipe!=null) {
						Bukkit.addRecipe(recipe);
						if(recipe instanceof ShapelessRecipe) {
							addRecipe(((ShapelessRecipe) recipe).getKey(), recipe);
						}else if(recipe instanceof ShapedRecipe) {
							addRecipe(((ShapedRecipe) recipe).getKey(), recipe);
						}
						
					}
				}
			}
		}
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
