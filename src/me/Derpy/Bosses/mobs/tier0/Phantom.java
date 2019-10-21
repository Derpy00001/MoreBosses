package me.Derpy.Bosses.mobs.tier0;

import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Monster;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;

import me.Derpy.Bosses.utilities.GetName;


public class Phantom {
	@SuppressWarnings("deprecation")
	public static void newphantom(final LivingEntity entity, Plugin plugin) {
		if(!(entity.isGlowing())) {
			if(plugin.getConfig().getBoolean("bosses.has_glow")) {
				entity.setGlowing(true);
			}
			entity.setSilent(true);
			((LivingEntity) entity).setCanPickupItems(false);
			((LivingEntity) entity).addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 1000000, 1, true), true);
			entity.setMaxHealth(entity.getMaxHealth()*plugin.getConfig().getInt("health_scale.tier0.phantom"));
			entity.setHealth(entity.getMaxHealth());
			
			GetName.getname(entity, entity, plugin, false);
			if(plugin.getConfig().getBoolean("bosses.phantom.effect_enabled")) {
				new BukkitRunnable(){
					@Override
					public void run(){
						if(!(entity.isDead())){
							for(Entity entity2 : entity.getNearbyEntities(10, 10, 10)) {
								if(entity2 instanceof Player || entity2 instanceof Monster) {
									((LivingEntity) entity2).addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, 200, 1));
								}
							}
						}else {
							this.cancel();
						}
					}
				}.runTaskTimer(plugin, 10L, 10L);
			}
		}
	}
}
