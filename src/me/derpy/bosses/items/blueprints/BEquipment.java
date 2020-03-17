package me.derpy.bosses.items.blueprints;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import org.bukkit.ChatColor;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.attribute.AttributeModifier.Operation;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.Damageable;
import org.bukkit.inventory.meta.ItemMeta;

import me.derpy.bosses.items.PrefixType;
import me.derpy.bosses.items.interfaces.IEquipment;
import me.derpy.bosses.utilities.Random;

public class BEquipment extends BLootable implements IEquipment {
	final List<PrefixType> INCOMPATABLE = Arrays.asList(PrefixType.DEADLY, PrefixType.SHARP, PrefixType.SLOW,
			PrefixType.LARGE, PrefixType.SMALL);
	final PrefixType PREFIX = PrefixType.NORMAL;
	final boolean IS_WEAPON = true;
	final boolean HAS_RANDOM_TITLE = true;

	@Override
	public EquipmentSlot getEquipmentSlot() {
		return EquipmentSlot.HAND;
	}

	@Override
	public boolean isWeapon() {
		return this.IS_WEAPON;
	}

	@Override
	public boolean hasRandomTitle() {
		// TODO Auto-generated method stub
		return this.HAS_RANDOM_TITLE;
	}

	@Override
	public PrefixType getPrefix() {
		// TODO Auto-generated method stub
		if (this.hasRandomTitle()) {
			PrefixType prefix = PrefixType.getPrefix();
			if (this.isWeapon()) {
				if (this.INCOMPATABLE.contains(prefix)) {
					return this.getPrefix();
				} else {
					return prefix;
				}
			} else {
				return prefix;
			}
		} else {
			return this.PREFIX;
		}
	}

	@Override
	public ItemStack getFinalizedItem() {
		PrefixType prefix = this.getPrefix();
		String prefixName = prefix.name().substring(0, 1).toUpperCase() + prefix.name().substring(1).toLowerCase();
		ItemStack item = this.getItem();
		ItemMeta meta = item.getItemMeta();
		List<String> lore = meta.getLore();
		List<String> strings = new ArrayList<String>();
		for (String string : lore) {
			strings.add(ChatColor.RESET + "" + ChatColor.GRAY + string);
		}
		switch (prefix) {
		case BROKEN:
			if (this.isWeapon()) {
				meta.addAttributeModifier(Attribute.GENERIC_ATTACK_SPEED,
						new AttributeModifier(UUID.randomUUID(), this.getNamespacedKey().getKey() + prefixName,
								-Random.randomNumber(0.1, 3.99), Operation.ADD_NUMBER, this.getEquipmentSlot()));
				meta.addAttributeModifier(Attribute.GENERIC_ATTACK_DAMAGE,
						new AttributeModifier(UUID.randomUUID(), this.getNamespacedKey().getKey() + prefixName,
								-Random.randomNumber(0.1, 6), Operation.ADD_NUMBER, this.getEquipmentSlot()));
			} else {
				meta.addAttributeModifier(Attribute.GENERIC_ARMOR,
						new AttributeModifier(UUID.randomUUID(), this.getNamespacedKey().getKey() + prefixName,
								-Random.randomNumber(0.1, 6), Operation.ADD_NUMBER, this.getEquipmentSlot()));
				meta.addAttributeModifier(Attribute.GENERIC_ARMOR_TOUGHNESS,
						new AttributeModifier(UUID.randomUUID(), this.getNamespacedKey().getKey() + prefixName,
								-Random.randomNumber(0.1, 6), Operation.ADD_NUMBER, this.getEquipmentSlot()));
			}
			((Damageable) meta).setDamage((int) (item.getType().getMaxDurability() * Random.randomNumber(0.56, 0.85)));
			break;
		case DAMAGED:
			if (this.isWeapon()) {
				meta.addAttributeModifier(Attribute.GENERIC_ATTACK_DAMAGE,
						new AttributeModifier(UUID.randomUUID(), this.getNamespacedKey().getKey() + prefixName,
								-Random.randomNumber(0.1, 6), Operation.ADD_NUMBER, this.getEquipmentSlot()));
			} else {
				meta.addAttributeModifier(Attribute.GENERIC_ARMOR_TOUGHNESS,
						new AttributeModifier(UUID.randomUUID(), this.getNamespacedKey().getKey() + prefixName,
								-Random.randomNumber(0.1, 6), Operation.ADD_NUMBER, this.getEquipmentSlot()));
			}
			((Damageable) meta).setDamage((int) (item.getType().getMaxDurability() * Random.randomNumber(0.56, 0.85)));
			break;
		case DEADLY:
			meta.addAttributeModifier(Attribute.GENERIC_ATTACK_DAMAGE,
					new AttributeModifier(UUID.randomUUID(), this.getNamespacedKey().getKey() + prefixName,
							Random.randomNumber(0.1, 3), Operation.ADD_NUMBER, this.getEquipmentSlot()));
			break;
		case GODLY:
			if (this.isWeapon()) {
				meta.addAttributeModifier(Attribute.GENERIC_ATTACK_SPEED,
						new AttributeModifier(UUID.randomUUID(), this.getNamespacedKey().getKey() + prefixName,
								Random.randomNumber(0.1, 3.99), Operation.ADD_NUMBER, this.getEquipmentSlot()));
				meta.addAttributeModifier(Attribute.GENERIC_ATTACK_DAMAGE,
						new AttributeModifier(UUID.randomUUID(), this.getNamespacedKey().getKey() + prefixName,
								Random.randomNumber(0.1, 6), Operation.ADD_NUMBER, this.getEquipmentSlot()));
			} else {
				meta.addAttributeModifier(Attribute.GENERIC_ARMOR,
						new AttributeModifier(UUID.randomUUID(), this.getNamespacedKey().getKey() + prefixName,
								Random.randomNumber(0.1, 6), Operation.ADD_NUMBER, this.getEquipmentSlot()));
				meta.addAttributeModifier(Attribute.GENERIC_ARMOR_TOUGHNESS,
						new AttributeModifier(UUID.randomUUID(), this.getNamespacedKey().getKey() + prefixName,
								Random.randomNumber(0.1, 6), Operation.ADD_NUMBER, this.getEquipmentSlot()));
				meta.addAttributeModifier(Attribute.GENERIC_MAX_HEALTH,
						new AttributeModifier(UUID.randomUUID(), this.getNamespacedKey().getKey() + prefixName,
								Random.randomNumber(0.1, 6), Operation.ADD_NUMBER, this.getEquipmentSlot()));
			}
			break;
		case LARGE:
			meta.addAttributeModifier(Attribute.GENERIC_MOVEMENT_SPEED,
					new AttributeModifier(UUID.randomUUID(), this.getNamespacedKey().getKey() + prefixName,
							-Random.randomNumber(0.1, 3), Operation.ADD_NUMBER, this.getEquipmentSlot()));
			meta.addAttributeModifier(Attribute.GENERIC_ATTACK_DAMAGE,
					new AttributeModifier(UUID.randomUUID(), this.getNamespacedKey().getKey() + prefixName,
							Random.randomNumber(0.1, 3), Operation.ADD_NUMBER, this.getEquipmentSlot()));
			meta.addAttributeModifier(Attribute.GENERIC_ATTACK_SPEED,
					new AttributeModifier(UUID.randomUUID(), this.getNamespacedKey().getKey() + prefixName,
							-Random.randomNumber(0.1, 3), Operation.ADD_NUMBER, this.getEquipmentSlot()));
			break;
		case LEGENDARY:
			if (this.isWeapon()) {
				meta.addAttributeModifier(Attribute.GENERIC_ATTACK_SPEED,
						new AttributeModifier(UUID.randomUUID(), this.getNamespacedKey().getKey() + prefixName,
								Random.randomNumber(0.1, 3), Operation.ADD_NUMBER, this.getEquipmentSlot()));
				meta.addAttributeModifier(Attribute.GENERIC_ATTACK_DAMAGE,
						new AttributeModifier(UUID.randomUUID(), this.getNamespacedKey().getKey() + prefixName,
								Random.randomNumber(0.1, 3), Operation.ADD_NUMBER, this.getEquipmentSlot()));
			} else {
				meta.addAttributeModifier(Attribute.GENERIC_ARMOR,
						new AttributeModifier(UUID.randomUUID(), this.getNamespacedKey().getKey() + prefixName,
								Random.randomNumber(0.1, 3), Operation.ADD_NUMBER, this.getEquipmentSlot()));
				meta.addAttributeModifier(Attribute.GENERIC_ARMOR_TOUGHNESS,
						new AttributeModifier(UUID.randomUUID(), this.getNamespacedKey().getKey() + prefixName,
								Random.randomNumber(0.1, 3), Operation.ADD_NUMBER, this.getEquipmentSlot()));
			}
			break;
		case MISFORGED:
			((Damageable) meta).setDamage((int) (item.getType().getMaxDurability() * Random.randomNumber(0.56, 0.85)));
			break;
		case NORMAL:
			break;
		case SHARP:
			meta.addAttributeModifier(Attribute.GENERIC_ATTACK_DAMAGE,
					new AttributeModifier(UUID.randomUUID(), this.getNamespacedKey().getKey() + prefixName,
							-Random.randomNumber(0.1, 2), Operation.ADD_NUMBER, this.getEquipmentSlot()));
			break;
		case SLOW:
			meta.addAttributeModifier(Attribute.GENERIC_ATTACK_SPEED,
					new AttributeModifier(UUID.randomUUID(), this.getNamespacedKey().getKey() + prefixName,
							-Random.randomNumber(0.1, 3), Operation.ADD_NUMBER, this.getEquipmentSlot()));
			break;
		case SMALL:
			meta.addAttributeModifier(Attribute.GENERIC_MOVEMENT_SPEED,
					new AttributeModifier(UUID.randomUUID(), this.getNamespacedKey().getKey() + prefixName,
							Random.randomNumber(0.1, 3), Operation.ADD_NUMBER, this.getEquipmentSlot()));
			meta.addAttributeModifier(Attribute.GENERIC_ATTACK_DAMAGE,
					new AttributeModifier(UUID.randomUUID(), this.getNamespacedKey().getKey() + prefixName,
							-Random.randomNumber(0.1, 3), Operation.ADD_NUMBER, this.getEquipmentSlot()));
			meta.addAttributeModifier(Attribute.GENERIC_ATTACK_SPEED,
					new AttributeModifier(UUID.randomUUID(), this.getNamespacedKey().getKey() + prefixName,
							-Random.randomNumber(0.1, 3), Operation.ADD_NUMBER, this.getEquipmentSlot()));
			break;
		default:
			break;

		}
		meta.setLore(strings);
		meta.setDisplayName(ChatColor.RESET + "" + ChatColor.WHITE + prefixName + " " + meta.getDisplayName());
		item.setItemMeta(meta);
		return item;
	}
}
