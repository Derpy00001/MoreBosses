package me.derpy.bosses.enchantments;

import java.util.Arrays;
import java.util.List;

import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.enchantments.EnchantmentTarget;
import org.bukkit.entity.LivingEntity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class BFleet extends Enchantment implements Listener {
	public BFleet(NamespacedKey key) {
		super(key);
	}

	@EventHandler
	public void onDamaged(EntityDamageByEntityEvent event) {
		if (event.getEntity() instanceof LivingEntity) {
			if (!event.getEntity().isDead()) {
				for (ItemStack item : ((LivingEntity) event.getEntity()).getEquipment().getArmorContents()) {
					if (item.getItemMeta().hasEnchant(this)) {
						((LivingEntity) event.getEntity()).addPotionEffect(new PotionEffect(PotionEffectType.SPEED,
								20 * 5, item.getItemMeta().getEnchantLevel(this) - 1));
					}
				}
			}
		}
	}

	@Override
	public boolean canEnchantItem(ItemStack arg0) {
		// TODO Auto-generated method stub
		List<Material> materials = Arrays.asList(Material.LEATHER_BOOTS, Material.IRON_BOOTS, Material.GOLDEN_BOOTS,
				Material.DIAMOND_BOOTS, Material.CHAINMAIL_BOOTS);
		return materials.contains(arg0.getType());
	}

	@Override
	public boolean conflictsWith(Enchantment arg0) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public EnchantmentTarget getItemTarget() {
		// TODO Auto-generated method stub
		return EnchantmentTarget.ARMOR_FEET;
	}

	@Override
	public int getMaxLevel() {
		// TODO Auto-generated method stub
		return 5;
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return "Fleet";
	}

	@Override
	public int getStartLevel() {
		// TODO Auto-generated method stub
		return 1;
	}

	@Override
	public boolean isCursed() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isTreasure() {
		// TODO Auto-generated method stub
		return true;
	}

}
