package me.Derpy.Bosses.mobs.tier5;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.MagmaCube;
import org.bukkit.inventory.EntityEquipment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;

import me.Derpy.Bosses.utilities.GetName;

public class Magma {
	@SuppressWarnings("deprecation")
	public static void newmagma(final LivingEntity entity, Plugin plugin) {
		if(plugin.getConfig().getInt("bosses.has_glow")==1) {
			entity.setGlowing(true);
		}
		entity.setSilent(true);
		((LivingEntity) entity).setCanPickupItems(false);
		((LivingEntity) entity).addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 1000000, 2, true), true);
		entity.setMaxHealth(entity.getMaxHealth()*plugin.getConfig().getInt("health_scale.tier5"));
		entity.setHealth(entity.getMaxHealth());
		
		((org.bukkit.entity.MagmaCube) entity).setSize(10);
		
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
		GetName.getname(entity, entity, plugin, false);
		new BukkitRunnable(){
			@Override
			public void run(){
				if(!(entity.isDead())){
					((MagmaCube) entity.getWorld().spawnEntity(new Location(entity.getWorld(), entity.getLocation().getX(), entity.getLocation().getY()+30, entity.getLocation().getZ()), EntityType.MAGMA_CUBE)).setSize(3);
				}else {
					this.cancel();
				}
			}
		}.runTaskTimer(plugin, 100, 100);
	}
}
