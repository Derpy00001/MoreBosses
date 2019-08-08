package me.Derpy.Bosses.mobs.tier2;

import org.bukkit.Material;
import org.bukkit.attribute.Attributable;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeInstance;
import org.bukkit.entity.LivingEntity;
import org.bukkit.inventory.EntityEquipment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import me.Derpy.Bosses.utilities.GetName;
import me.Derpy.Bosses.utilities.Random;

public class Stray {
	@SuppressWarnings("deprecation")
	public static void newstray(LivingEntity entity, Plugin plugin) {
		if(plugin.getConfig().getBoolean("bosses.has_glow")) {
			entity.setGlowing(true);
		}
		entity.setSilent(true);
		((LivingEntity) entity).setCanPickupItems(false);
		((LivingEntity) entity).addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 1000000, 2, true), true);
		entity.setMaxHealth(entity.getMaxHealth()*plugin.getConfig().getInt("health_scale.tier2"));
		entity.setHealth(entity.getMaxHealth());
		
		Attributable bossAttributable = (Attributable) entity;
		AttributeInstance ai2 = bossAttributable.getAttribute(Attribute.GENERIC_ATTACK_DAMAGE);
		ai2.setBaseValue(ai2.getBaseValue()*5*plugin.getConfig().getInt("damage_scale.tier2"));
		
		EntityEquipment ee = ((LivingEntity) entity).getEquipment();
		if(Random.random(0.50)) {
			ee.setItemInMainHand(new ItemStack(Material.DIAMOND_SWORD, 64));
			AttributeInstance ai3 = bossAttributable.getAttribute(Attribute.GENERIC_MOVEMENT_SPEED);
			ai3.setBaseValue(0.5);
		}
		ee.setHelmet(new ItemStack(Material.DIAMOND_HELMET, 64));
		ee.setBoots(new ItemStack(Material.DIAMOND_HELMET, 64));
		ee.setChestplate(new ItemStack(Material.DIAMOND_HELMET, 64));
		ee.setLeggings(new ItemStack(Material.DIAMOND_HELMET, 64));
		ee.setHelmetDropChance(0);
		ee.setBootsDropChance(0);
		ee.setChestplateDropChance(0);
		ee.setLeggingsDropChance(0);
		ee.setItemInMainHandDropChance(0);;
		GetName.getname(entity, entity, plugin, false);
	}
}
