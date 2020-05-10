package me.derpy.bosses.enchantments;

import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.enchantments.EnchantmentTarget;
import org.bukkit.entity.LivingEntity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import me.derpy.bosses.Morebosses;

public class BFleet extends Enchantment implements Listener {
	final YamlConfiguration CONFIG = Morebosses.getConfigurationHandler().openEnchantmentConfiguration("fleet.yml");
	final int DURATION = this.CONFIG != null ? this.CONFIG.getInt("fleet.duration") : 5;
	final int LEVEL_CAP = this.CONFIG != null ? this.CONFIG.getInt("fleet.level_cap") : 5;

	public BFleet(NamespacedKey key) {
		super(key);
	}

	@EventHandler
	public void onDamaged(EntityDamageByEntityEvent event) {
		if (event.getEntity() instanceof LivingEntity) {
			if (!event.getEntity().isDead()) {
				for (ItemStack item : ((LivingEntity) event.getEntity()).getEquipment().getArmorContents()) {
					if (!(item == null)) {
						if (item.hasItemMeta()) {
							if (item.getItemMeta().hasEnchant(this)) {
								((LivingEntity) event.getEntity())
										.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 20 * this.DURATION,
												item.getItemMeta().getEnchantLevel(this) - 1));
							}
						}
					}
				}
			}
		}
	}

	@Override
	public boolean canEnchantItem(ItemStack arg0) {
		// TODO Auto-generated method stub
		if (arg0.getType() == Material.ENCHANTED_BOOK) {
			return true;
		} else {
			if (arg0.getType().name().toLowerCase().contains("boot")) {
				return true;
			}
		}
		return false;
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
		return this.LEVEL_CAP;
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
