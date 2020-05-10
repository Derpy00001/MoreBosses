package me.derpy.bosses.enchantments;

import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.attribute.Attribute;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.enchantments.EnchantmentTarget;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.inventory.ItemStack;

import me.derpy.bosses.Morebosses;

public class BReplenish extends Enchantment implements Listener {
	final YamlConfiguration CONFIG = Morebosses.getConfigurationHandler().openEnchantmentConfiguration("replenish.yml");
	final double HEAL_PERCENT = this.CONFIG != null ? this.CONFIG.getDouble("replenish.heal_back") : 0.1;
	final int LEVEL_CAP = this.CONFIG != null ? this.CONFIG.getInt("replenish.level_cap") : 5;

	public BReplenish(NamespacedKey key) {
		super(key);
	}

	@EventHandler
	public void onDamaged(EntityDamageByEntityEvent event) {
		if (event.getDamager() instanceof Player) {
			Player player = (Player) event.getDamager();
			if (player.isBlocking()) {
				ItemStack shield = player.getInventory().getItemInMainHand().getType() == Material.SHIELD
						? player.getInventory().getItemInMainHand()
						: player.getInventory().getItemInOffHand();
				if (shield != null) {
					if (shield.hasItemMeta()) {
						if (shield.getItemMeta().hasEnchant(this)) {
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
		return arg0.getType() == Material.SHIELD;
	}

	@Override
	public boolean conflictsWith(Enchantment arg0) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public EnchantmentTarget getItemTarget() {
		// TODO Auto-generated method stub
		return EnchantmentTarget.TOOL;
	}

	@Override
	public int getMaxLevel() {
		// TODO Auto-generated method stub
		return this.LEVEL_CAP;
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return "Replenish";
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
