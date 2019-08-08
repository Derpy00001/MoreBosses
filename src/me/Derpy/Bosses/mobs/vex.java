package me.Derpy.Bosses.mobs;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.attribute.Attributable;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeInstance;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Vex;
import org.bukkit.inventory.EntityEquipment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;

import me.Derpy.Bosses.utilities.GetName;

public class vex {
	@SuppressWarnings("deprecation")
	public static void newvex(Location loc, Plugin plugin) {
		World world = loc.getWorld();
		Vex entity = (Vex) world.spawnEntity(loc, EntityType.VEX);
		if(plugin.getConfig().getBoolean("bosses.has_glow")) {
			entity.setGlowing(true);
		}
		entity.setSilent(true);
		
		entity.setMaxHealth(entity.getMaxHealth()*2*plugin.getConfig().getInt("health_scale.vex"));
		entity.setHealth(entity.getMaxHealth());
		
		Attributable bossAttributable = (Attributable) entity;
		AttributeInstance ai2 = bossAttributable.getAttribute(Attribute.GENERIC_ATTACK_DAMAGE);
		ai2.setBaseValue(ai2.getBaseValue()*3*plugin.getConfig().getInt("damage_scale.vex"));

		EntityEquipment ee = ((LivingEntity) entity).getEquipment();
		ee.setHelmet(new ItemStack(Material.DIAMOND_HELMET, 64));
		ee.setBoots(new ItemStack(Material.DIAMOND_BOOTS, 64));
		ee.setChestplate(new ItemStack(Material.DIAMOND_CHESTPLATE, 64));
		ee.setLeggings(new ItemStack(Material.DIAMOND_LEGGINGS, 64));
		ee.setHelmetDropChance(0);
		ee.setBootsDropChance(0);
		ee.setChestplateDropChance(0);
		ee.setLeggingsDropChance(0);
		ee.setItemInMainHandDropChance(0);;
		GetName.getname(entity, entity, plugin, false);
	}
}
