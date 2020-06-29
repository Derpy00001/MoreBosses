package me.derpy.bosses.enchantments;

import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.attribute.Attribute;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.enchantments.EnchantmentTarget;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.inventory.ItemStack;

import me.derpy.bosses.Morebosses;

public class BLifesteal extends Enchantment implements Listener {
	final double HEAL_PERCENT = Morebosses.getConfigurationHandler().openEnchantmentConfiguration("lifesteal.yml")
			.getDouble("lifesteal.heal_back");
	final int LEVEL_CAP = Morebosses.getConfigurationHandler().openEnchantmentConfiguration("lifesteal.yml")
			.getInt("lifesteal.level_cap");

	public BLifesteal(NamespacedKey key) {
		super(key);
	}

	@EventHandler
	public void onDamaged(EntityDamageByEntityEvent event) {
		if (event.getDamager() instanceof Player) {
			Player player = (Player) event.getDamager();
			if (!player.isDead()) {
				if (player.getInventory().getItemInMainHand() != null
						? player.getInventory().getItemInMainHand().getType() != Material.AIR ? true : false
						: false) {
					if (player.getInventory().getItemInMainHand().hasItemMeta()) {
						if (player.getInventory().getItemInMainHand().getItemMeta().hasEnchant(this)) {
							if (player.getHealth() + (event.getDamage() * this.HEAL_PERCENT) > player
									.getAttribute(Attribute.GENERIC_MAX_HEALTH).getValue()) {
								player.setHealth(player.getAttribute(Attribute.GENERIC_MAX_HEALTH).getValue());
							} else {
								player.setHealth(player.getHealth() + (event.getDamage() * this.HEAL_PERCENT));
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
		if (arg0.getType().name().toLowerCase().contains("sword")) {
			return true;
		} else {
			if (arg0.getType().name().toLowerCase().contains("axe")) {
				if (!arg0.getType().name().toLowerCase().contains("pick")) {
					return true;
				}
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
		return EnchantmentTarget.WEAPON;
	}

	@Override
	public int getMaxLevel() {
		// TODO Auto-generated method stub
		return this.LEVEL_CAP;
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return "Lifesteal";
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
