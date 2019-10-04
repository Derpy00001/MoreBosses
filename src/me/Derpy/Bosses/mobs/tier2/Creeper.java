package me.Derpy.Bosses.mobs.tier2;


import org.bukkit.Material;
import org.bukkit.attribute.Attributable;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeInstance;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.inventory.EntityEquipment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import me.Derpy.Bosses.utilities.GetName;


public class Creeper {
	@SuppressWarnings("deprecation")
	public static void newcreeper(LivingEntity entity, Plugin plugin) {
		if(!(entity.isGlowing())) {
			if(plugin.getConfig().getBoolean("bosses.has_glow")) {
				entity.setGlowing(true);
			}
			entity.setSilent(true);
			((org.bukkit.entity.Creeper) entity).setPowered(true);
			((LivingEntity) entity).setCanPickupItems(false);
			((LivingEntity) entity).addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 1000000, 2, true), true);
			entity.setMaxHealth(entity.getMaxHealth()*plugin.getConfig().getInt("health_scale.tier2.creeper"));
			entity.setHealth(entity.getMaxHealth());
			
			Attributable bossAttributable = (Attributable) entity;
			AttributeInstance ai2 = bossAttributable.getAttribute(Attribute.GENERIC_ATTACK_DAMAGE);
			ai2.setBaseValue(ai2.getBaseValue()*plugin.getConfig().getInt("damage_scale.tier2.creeper"));
			
			EntityEquipment ee = ((LivingEntity) entity).getEquipment();
			ee.setHelmet(new ItemStack(Material.IRON_CHESTPLATE, 64));
			ee.setBoots(new ItemStack(Material.IRON_BOOTS, 64));
			ee.setChestplate(new ItemStack(Material.IRON_CHESTPLATE, 64));
			ee.setLeggings(new ItemStack(Material.IRON_LEGGINGS, 64));
			ee.setHelmetDropChance(0);
			ee.setBootsDropChance(0);
			ee.setChestplateDropChance(0);
			ee.setLeggingsDropChance(0);
			ee.setItemInMainHandDropChance(0);;
			for(int i = 0; i < 4 ; i++) {
				Entity entity2 = (Entity) entity.getWorld().spawnEntity(entity.getLocation(), EntityType.CREEPER);
				if(plugin.getConfig().getInt("bosses.has_glow")==1) {
					entity2.setGlowing(true);
				}
			}
			GetName.getname(entity, entity, plugin, false);
		}
	}
}
