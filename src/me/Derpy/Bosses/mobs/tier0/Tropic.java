package me.Derpy.Bosses.mobs.tier0;

import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Monster;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;

import me.Derpy.Bosses.utilities.GetName;


public class Tropic {
	@SuppressWarnings("deprecation")
	public static void newtropic(final LivingEntity entity, Plugin plugin) {
		if(!(entity.isGlowing())) {
			if(plugin.getConfig().getBoolean("bosses.has_glow")) {
				entity.setGlowing(true);
			}
			entity.setSilent(true);
			((LivingEntity) entity).setCanPickupItems(false);
			((LivingEntity) entity).addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 1000000, 1, true), true);
			entity.setMaxHealth(entity.getMaxHealth()*plugin.getConfig().getInt("health_scale.tier1.tropic"));
			entity.setHealth(entity.getMaxHealth());
			
			for(int i = 0; i < 30 ; i++) {
				Entity entity2 = (Entity) entity.getWorld().spawnEntity(entity.getLocation(), EntityType.TROPICAL_FISH);
				if(plugin.getConfig().getInt("bosses.has_glow")==1) {
					entity2.setGlowing(true);
				}
			}
			GetName.getname(entity, entity, plugin, false);
			
			new BukkitRunnable(){
				@Override
				public void run(){
					if(!(entity.isDead())){
						for(Entity entity2 : entity.getNearbyEntities(10, 10, 10)) {
							if(entity2 instanceof Player || entity2 instanceof Monster) {
								((LivingEntity) entity2).addPotionEffect(new PotionEffect(PotionEffectType.POISON, 70, 1));
							}
						}
					}else {
						this.cancel();
					}
				}
			}.runTaskTimer(plugin, 20, 20);
		}
	}
}
