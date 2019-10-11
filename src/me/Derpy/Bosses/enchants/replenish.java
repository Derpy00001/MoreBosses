package me.Derpy.Bosses.enchants;

import java.util.ArrayList;

import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.enchantments.EnchantmentTarget;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.inventory.ItemStack;


public class replenish extends Enchantment implements Listener{
	public replenish(NamespacedKey key) {
		super(key);
	}

	@SuppressWarnings("deprecation")
	@EventHandler
	private static void ondamage(EntityDamageByEntityEvent event) {
		if(event.getEntity() instanceof Player) {
			Player p = (Player) event.getEntity();
			if(p.isBlocking()) {
				ItemStack shield = null;
				if(p.getInventory().getItemInOffHand().getType()==Material.SHIELD) {
					shield = p.getInventory().getItemInOffHand();
				}else if(p.getInventory().getItemInMainHand().getType()==Material.SHIELD){
					shield = p.getInventory().getItemInMainHand();
				}
				if(shield!=null) {
					if(shield.getItemMeta().hasEnchant(EnchantmentStorage.getreplenish())) {
						if(p.getHealth()+(event.getDamage()*(me.Derpy.Bosses.MoreBosses.getPlugin(me.Derpy.Bosses.MoreBosses.class).getConfig().getDouble("enchants.lifesteal")*p.getInventory().getItemInMainHand().getItemMeta().getEnchantLevel(EnchantmentStorage.getreplenish())))>p.getMaxHealth()) {
							p.setHealth(p.getMaxHealth());
						}else {
							p.setHealth(p.getHealth()+(event.getDamage()*(0.05*p.getInventory().getItemInMainHand().getItemMeta().getEnchantLevel(EnchantmentStorage.getreplenish()))));
						}
					}
				}
			}
		}
//		if(event.getDamager() instanceof Player) {
//			Player p = (Player) event.getDamager();
//			if(p.getInventory().getItemInMainHand().getType()!=Material.AIR) {
//				if(p.getInventory().getItemInMainHand().getItemMeta().hasEnchant(EnchantmentStorage.getlifesteal())) {
//					if(p.getHealth()+(event.getDamage()*(me.Derpy.Bosses.MoreBosses.getPlugin(me.Derpy.Bosses.MoreBosses.class).getConfig().getDouble("enchants.lifesteal")*p.getInventory().getItemInMainHand().getItemMeta().getEnchantLevel(EnchantmentStorage.getlifesteal())))>p.getMaxHealth()) {
//						p.setHealth(p.getMaxHealth());
//					}else {
//						p.setHealth(p.getHealth()+(event.getDamage()*(0.05*p.getInventory().getItemInMainHand().getItemMeta().getEnchantLevel(EnchantmentStorage.getlifesteal()))));
//					}
//				}
//			}
//		}
	}

	@Override
	public boolean canEnchantItem(ItemStack arg0) {
		// TODO Auto-generated method stub
		ArrayList<Material> items = new ArrayList<Material>();
		items.add(Material.SHIELD);
		if(items.contains(arg0.getType())) {
			return true;
		}else {
			return false;
		}
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
		return 5;
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
