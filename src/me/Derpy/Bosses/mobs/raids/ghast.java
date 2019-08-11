package me.Derpy.Bosses.mobs.raids;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Ghast;
import org.bukkit.entity.LivingEntity;
import org.bukkit.inventory.EntityEquipment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;

import me.Derpy.Bosses.utilities.GetName;

public class ghast {
	@SuppressWarnings("deprecation")
	public static Ghast newghast(final Location location, Plugin plugin) {
		final Ghast entity = (Ghast) location.getWorld().spawnEntity(location, EntityType.GHAST);
		if(plugin.getConfig().getBoolean("bosses.has_glow")) {
			entity.setGlowing(true);
		}
		entity.setSilent(true);
		entity.setAI(true);
		((LivingEntity) entity).setCanPickupItems(false);
		entity.setMaxHealth(entity.getMaxHealth()*plugin.getConfig().getInt("health_scale.raids.ghast"));
		entity.setHealth(entity.getMaxHealth());
		
		EntityEquipment ee = ((LivingEntity) entity).getEquipment();
		ItemStack helm = new ItemStack(Material.DIAMOND_HELMET);
		helm.addUnsafeEnchantment(Enchantment.THORNS, 7);
		ee.setHelmet(helm);
		ee.setHelmetDropChance(0);
		ee.setBootsDropChance(0);
		ee.setChestplateDropChance(0);
		ee.setLeggingsDropChance(0);
		ee.setItemInMainHandDropChance(0);;
		GetName.getname(entity, entity, plugin, false);
		return entity;
	}
}
