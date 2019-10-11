package me.Derpy.Bosses.Addons.Nordic.Items;

import java.util.UUID;

import org.bukkit.Material;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.attribute.AttributeModifier.Operation;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import net.md_5.bungee.api.ChatColor;

public class runes {
	public static ItemStack speed() {
		ItemStack item = new ItemStack(Material.CYAN_DYE);
		ItemMeta meta = item.getItemMeta();
		meta.setDisplayName(ChatColor.RED+"Speed Rune");
		meta.addAttributeModifier(Attribute.GENERIC_MOVEMENT_SPEED, new AttributeModifier(UUID.randomUUID(), "speed_rune", 0.02D, Operation.ADD_NUMBER, EquipmentSlot.OFF_HAND));
		item.setItemMeta(meta);
		return item;
	}
	public static ItemStack health() {
		ItemStack item = new ItemStack(Material.PURPLE_DYE);
		ItemMeta meta =item.getItemMeta();
		meta.setDisplayName(ChatColor.RED+"Health Rune");
		meta.addAttributeModifier(Attribute.GENERIC_MAX_HEALTH, new AttributeModifier(UUID.randomUUID(), "health_rune", 4D, Operation.ADD_NUMBER, EquipmentSlot.OFF_HAND));
		item.setItemMeta(meta);
		return item;
	}
	public static ItemStack damage() {
		ItemStack item = new ItemStack(Material.RED_DYE);
		ItemMeta meta = item.getItemMeta();
		meta.setDisplayName(ChatColor.RED+"Damage Rune");
		meta.addAttributeModifier(Attribute.GENERIC_ATTACK_DAMAGE, new AttributeModifier(UUID.randomUUID(), "damage_rune", 3.5D, Operation.ADD_NUMBER, EquipmentSlot.OFF_HAND));
		item.setItemMeta(meta);
		return item;
	}
	public static ItemStack knockback() {
		ItemStack item = new ItemStack(Material.GREEN_DYE);
		ItemMeta meta = item.getItemMeta();
		meta.setDisplayName(ChatColor.RED+"Knockback Rune");
		meta.addAttributeModifier(Attribute.GENERIC_KNOCKBACK_RESISTANCE, new AttributeModifier(UUID.randomUUID(), "noknockback_rune", 3.5D, Operation.ADD_NUMBER, EquipmentSlot.OFF_HAND));
		item.setItemMeta(meta);
		return item;
	}
}