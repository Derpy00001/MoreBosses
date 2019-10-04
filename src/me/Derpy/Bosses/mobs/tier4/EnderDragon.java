package me.Derpy.Bosses.mobs.tier4;

import org.bukkit.Material;
import org.bukkit.boss.BarColor;
import org.bukkit.boss.BarFlag;
import org.bukkit.boss.BarStyle;
import org.bukkit.entity.LivingEntity;
import org.bukkit.inventory.EntityEquipment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class EnderDragon {
	@SuppressWarnings("deprecation")
	public static void newenderdragon(LivingEntity entity, Plugin plugin) {
		if(plugin.getConfig().getBoolean("bosses.has_glow")) {
			entity.setGlowing(true);
		}
		entity.setCustomName("Undead Dragon");
		entity.setCustomNameVisible(true);
		entity.setSilent(true);
		((LivingEntity) entity).setCanPickupItems(false);
		((LivingEntity) entity).addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 1000000, 2, true), true);
		((LivingEntity) entity).addPotionEffect(new PotionEffect(PotionEffectType.HEALTH_BOOST, 1000000, 4, true), true);
		if(!(((org.bukkit.entity.EnderDragon) entity).getBossBar()==null)) {
			((org.bukkit.entity.EnderDragon) entity).getBossBar().setColor(BarColor.RED);
			((org.bukkit.entity.EnderDragon) entity).getBossBar().setStyle(BarStyle.SEGMENTED_20);;
			((org.bukkit.entity.EnderDragon) entity).getBossBar().setTitle("Undead Dragon");
			((org.bukkit.entity.EnderDragon) entity).getBossBar().addFlag(BarFlag.CREATE_FOG);
		}
		entity.setMaxHealth(entity.getMaxHealth()*plugin.getConfig().getInt("health_scale.tier4.dragon"));
		entity.setHealth(entity.getMaxHealth());
		
			//		Attributable bossAttributable = (Attributable) entity;
//		AttributeInstance ai2 = bossAttributable.getAttribute(Attribute.GENERIC_ATTACK_DAMAGE);
//		ai2.setBaseValue(ai2.getBaseValue()*5);
		
		EntityEquipment ee = ((LivingEntity) entity).getEquipment();
		ee.setItemInMainHand(new ItemStack(Material.STONE_AXE, 64));
		ee.setHelmet(new ItemStack(Material.DIAMOND_HELMET, 64));
		ee.setBoots(new ItemStack(Material.DIAMOND_HELMET, 64));
		ee.setChestplate(new ItemStack(Material.DIAMOND_HELMET, 64));
		ee.setLeggings(new ItemStack(Material.DIAMOND_HELMET, 64));
		ee.setHelmetDropChance(0);
		ee.setBootsDropChance(0);
		ee.setChestplateDropChance(0);
		ee.setLeggingsDropChance(0);
		ee.setItemInMainHandDropChance(0);;
	}
}
