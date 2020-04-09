package me.derpy.bosses.items.blueprints.armor;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.attribute.AttributeModifier.Operation;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.Recipe;
import org.bukkit.inventory.RecipeChoice;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.meta.LeatherArmorMeta;

import me.derpy.bosses.items.ItemType;
import me.derpy.bosses.items.blueprints.BEquipment;
import me.derpy.bosses.items.interfaces.ICraftable;

public class GhastChestplate extends BEquipment implements ICraftable {

	@Override
	public ItemStack getItem() {
		ItemStack item = new ItemStack(Material.LEATHER_CHESTPLATE);
		LeatherArmorMeta meta = (LeatherArmorMeta) item.getItemMeta();
		meta.setColor(Color.RED);
		meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
		meta.setDisplayName("Tissue Chestplate");
		meta.addAttributeModifier(Attribute.GENERIC_MAX_HEALTH, new AttributeModifier(UUID.randomUUID(), "Extra_Health",
				1D, Operation.ADD_NUMBER, this.getEquipmentSlot()));
		item.setItemMeta(meta);
		return item;
	}

	@Override
	public NamespacedKey getNamespacedKey() {
		return NamespacedKey.minecraft("ghast-chestplate");
	}

	@Override
	public EquipmentSlot getEquipmentSlot() {
		// TODO Auto-generated method stub
		return EquipmentSlot.CHEST;
	}

	@SuppressWarnings("deprecation")
	@Override
	public Recipe[] getRecipes() {
		// TODO Auto-generated method stub
		final String GROUP = "tissueChestplate";
		List<Recipe> recipes = new ArrayList<Recipe>();
		RecipeChoice choice = new RecipeChoice.ExactChoice(ItemType.GHAST_TISSUE.getInterface().getFinalizedItem());
		ShapedRecipe recipe = new ShapedRecipe(this.getNamespacedKey(), this.getFinalizedItem());
		recipe.shape("* *", "***", "***");
		recipe.setIngredient('*', choice);
		recipe.setGroup(GROUP);
		recipes.add(recipe);
		return recipes.toArray(new Recipe[recipes.size()]);
	}

}
