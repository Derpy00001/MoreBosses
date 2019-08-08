package me.Derpy.Bosses.mobs.raids;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Fireball;
import org.bukkit.entity.Ghast;
import org.bukkit.entity.LivingEntity;
import org.bukkit.inventory.EntityEquipment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;

import me.Derpy.Bosses.utilities.GetName;

public class ghast {
	@SuppressWarnings("deprecation")
	public static Ghast newghast(final Location location, Plugin plugin) {
		final Ghast entity = (Ghast) location.getWorld().spawnEntity(location, EntityType.GHAST);
		if(plugin.getConfig().getBoolean("bosses.has_glow")) {
			entity.setGlowing(true);
		}
		entity.setSilent(true);
		entity.setAI(false);
		((LivingEntity) entity).setCanPickupItems(false);
		entity.setMaxHealth(entity.getMaxHealth()*plugin.getConfig().getInt("health_scale.raids.ghast"));
		entity.setHealth(entity.getMaxHealth());
		
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
		new BukkitRunnable(){
			@Override
			public void run(){
				if(!(entity.isDead())){
					for(Entity entity2 : entity.getNearbyEntities(100, 100, 100)) {
						if(entity2 instanceof LivingEntity) {
							Fireball fireball = entity.launchProjectile(Fireball.class);
							fireball.setFallDistance(0);
							fireball.setIsIncendiary(true);
							fireball.setDirection(entity2.getLocation().toVector().subtract(entity.getLocation().toVector()));
							fireball.setYield(fireball.getYield()*5);
							location.setDirection(entity2.getLocation().getDirection().multiply(-1));
							fireball.setVelocity(fireball.getVelocity().multiply(0.5));
							entity.teleport(location);
							try {
								Thread.sleep(1000);
							} catch (InterruptedException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
//							fireball.setVelocity(entity.getLocation().toVector().subtract(entity2.getLocation().toVector()).normalize());
//							fireball.setFallDistance(0);
						}
					}
				}else {
					this.cancel();
				}
			}
		}.runTaskTimer(plugin, 60, 60);
		return entity;
	}
}
