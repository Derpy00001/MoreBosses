package me.Derpy.Bosses.mobs.tier1;

import org.bukkit.Material;
import org.bukkit.attribute.Attributable;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeInstance;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.inventory.EntityEquipment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import me.Derpy.Bosses.utilities.GetName;
import me.Derpy.Bosses.utilities.Random;

public class Skeleton {
	@SuppressWarnings("deprecation")
	public static void newskeleton(LivingEntity entity, Plugin plugin) {
		if(plugin.getConfig().getBoolean("bosses.has_glow")) {
			entity.setGlowing(true);
		}
		entity.setSilent(true);
		((LivingEntity) entity).setCanPickupItems(false);
		((LivingEntity) entity).addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 1000000, 1, true), true);
		entity.setMaxHealth(entity.getMaxHealth()*plugin.getConfig().getInt("health_scale.tier1.skeleton"));
		entity.setHealth(entity.getMaxHealth());
		
		Attributable bossAttributable = (Attributable) entity;
		AttributeInstance ai2 = bossAttributable.getAttribute(Attribute.GENERIC_ATTACK_DAMAGE);
		ai2.setBaseValue(ai2.getBaseValue()*plugin.getConfig().getInt("damage_scale.tier1.skeleton"));
		
		EntityEquipment ee = ((LivingEntity) entity).getEquipment();
		if(Random.random(0.50)) {
			ee.setItemInMainHand(new ItemStack(Material.DIAMOND_SWORD, 64));
			AttributeInstance ai3 = bossAttributable.getAttribute(Attribute.GENERIC_MOVEMENT_SPEED);
			ai3.setBaseValue(0.5);
		}else {
			ItemStack bow = new ItemStack(Material.BOW, 64);
			bow.addUnsafeEnchantment(Enchantment.ARROW_DAMAGE, 6);
			ee.setItemInMainHand(bow);
		}
		ee.setHelmet(new ItemStack(Material.IRON_HELMET, 64));
		ee.setBoots(new ItemStack(Material.IRON_BOOTS, 64));
		ee.setChestplate(new ItemStack(Material.IRON_CHESTPLATE, 64));
		ee.setLeggings(new ItemStack(Material.IRON_LEGGINGS, 64));
		ee.setHelmetDropChance(0);
		ee.setBootsDropChance(0);
		ee.setChestplateDropChance(0);
		ee.setLeggingsDropChance(0);
		ee.setItemInMainHandDropChance(0);;
		if(Random.random(0.25)) {
			Entity newentity = entity.getWorld().spawnEntity(entity.getLocation(), EntityType.SPIDER);
			newentity.setPassenger(entity);
			Attributable bossAttributable2 = (Attributable) newentity;
			AttributeInstance ai4 = bossAttributable2.getAttribute(Attribute.GENERIC_MOVEMENT_SPEED);
			ai4.setBaseValue(0.5);
			AttributeInstance ai5 = bossAttributable2.getAttribute(Attribute.GENERIC_ARMOR);
			ai5.setBaseValue(5.0);
			EntityEquipment eee = ((LivingEntity) newentity).getEquipment();
			eee.setHelmet(new ItemStack(Material.DIAMOND_HELMET, 64));
			eee.setBoots(new ItemStack(Material.DIAMOND_BOOTS, 64));
			eee.setChestplate(new ItemStack(Material.DIAMOND_CHESTPLATE, 64));
			eee.setLeggings(new ItemStack(Material.DIAMOND_LEGGINGS, 64));
			eee.setHelmetDropChance(0);
			eee.setBootsDropChance(0);
			eee.setChestplateDropChance(0);
			eee.setLeggingsDropChance(0);
		}
		GetName.getname(entity, entity, plugin, false);
	}
}
