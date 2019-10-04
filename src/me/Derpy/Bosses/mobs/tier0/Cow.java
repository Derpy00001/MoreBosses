package me.Derpy.Bosses.mobs.tier0;

import org.bukkit.Material;
import org.bukkit.attribute.Attributable;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeInstance;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Husk;
import org.bukkit.entity.LivingEntity;
import org.bukkit.inventory.EntityEquipment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import me.Derpy.Bosses.utilities.GetName;


public class Cow{


	@SuppressWarnings("deprecation")
	public static void newcow(LivingEntity entity, Plugin plugin) {
		if(plugin.getConfig().getBoolean("bosses.has_glow")) {
			entity.setGlowing(true);
		}
		entity.setSilent(true);
		
		// Make cow hostile
		Husk rider = (Husk) entity.getWorld().spawnEntity(entity.getLocation(), EntityType.HUSK);
		entity.setPassenger(rider);
		rider.setSilent(true);
		rider.setInvulnerable(true);
		rider.setCollidable(false);
		rider.setCustomName("Cow");
		rider.setCustomNameVisible(false);
		rider.setCanPickupItems(false);
		rider.addPotionEffect(new PotionEffect(PotionEffectType.INVISIBILITY, 99999, 1, true), true);
		rider.setRemoveWhenFarAway(false);
		entity.setMaxHealth(entity.getMaxHealth()*plugin.getConfig().getInt("health_scale.tier0.cow"));
		entity.setHealth(entity.getMaxHealth());
		
		Attributable bossAttributable = (Attributable) rider;
		AttributeInstance ai2 = bossAttributable.getAttribute(Attribute.GENERIC_ATTACK_DAMAGE);
		ai2.setBaseValue(ai2.getBaseValue()*plugin.getConfig().getInt("damage_scale.tier0.cow"));
		AttributeInstance ai3 = bossAttributable.getAttribute(Attribute.GENERIC_MOVEMENT_SPEED);
		ai3.setBaseValue(ai3.getBaseValue()*20);
		// https://hub.spigotmc.org/javadocs/spigot/org/spigotmc/event/entity/EntityDismountEvent.html
		// cow modification
		AttributeInstance ai1 = bossAttributable.getAttribute(Attribute.GENERIC_MOVEMENT_SPEED);
		ai1.setBaseValue(ai1.getBaseValue()*14);
		EntityEquipment ee = ((LivingEntity) entity).getEquipment();
		ee.setItemInMainHand(new ItemStack(Material.BARRIER, 1));
		ee.setHelmet(new ItemStack(Material.DIAMOND_HELMET, 64));
		ee.setBoots(new ItemStack(Material.DIAMOND_BOOTS, 64));
		ee.setChestplate(new ItemStack(Material.DIAMOND_CHESTPLATE, 64));
		ee.setLeggings(new ItemStack(Material.DIAMOND_LEGGINGS, 64));
		ee.setHelmetDropChance(0);
		ee.setBootsDropChance(0);
		ee.setChestplateDropChance(0);
		ee.setLeggingsDropChance(0);
		ee.setItemInMainHandDropChance(0);;
		GetName.getname(entity, rider, plugin, false);
	}
}
