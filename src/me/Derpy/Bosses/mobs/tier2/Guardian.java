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

public class Guardian {
	@SuppressWarnings("deprecation")
	public static void newguardian(LivingEntity entity, Plugin plugin) {
		if(plugin.getConfig().getBoolean("bosses.has_glow")) {
			entity.setGlowing(true);
		}
		entity.setSilent(true);
		((LivingEntity) entity).setCanPickupItems(false);
		((LivingEntity) entity).addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 1000000, 2, true), true);
		entity.setMaxHealth(entity.getMaxHealth()*plugin.getConfig().getInt("health_scale.tier2.guardian"));
		entity.setHealth(entity.getMaxHealth());
		
		Attributable bossAttributable = (Attributable) entity;
		AttributeInstance ai2 = bossAttributable.getAttribute(Attribute.GENERIC_ATTACK_DAMAGE);
		ai2.setBaseValue(ai2.getBaseValue()*plugin.getConfig().getInt("damage_scale.tier2.guardian"));
		
		EntityEquipment ee = ((LivingEntity) entity).getEquipment();
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
