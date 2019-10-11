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


public class lifesteal extends Enchantment implements Listener{
	public lifesteal(NamespacedKey key) {
		super(key);
	}

	@SuppressWarnings("deprecation")
	@EventHandler
	private static void ondamage(EntityDamageByEntityEvent event) {
		if(event.getDamager() instanceof Player) {
			Player p = (Player) event.getDamager();
			if(p.getInventory().getItemInMainHand().getType()!=Material.AIR) {
				if(p.getInventory().getItemInMainHand().getItemMeta().hasEnchant(EnchantmentStorage.getlifesteal())) {
					if(p.getHealth()+(event.getDamage()*(me.Derpy.Bosses.MoreBosses.getPlugin(me.Derpy.Bosses.MoreBosses.class).getConfig().getDouble("enchants.lifesteal")*p.getInventory().getItemInMainHand().getItemMeta().getEnchantLevel(EnchantmentStorage.getlifesteal())))>p.getMaxHealth()) {
						p.setHealth(p.getMaxHealth());
					}else {
						p.setHealth(p.getHealth()+(event.getDamage()*(me.Derpy.Bosses.MoreBosses.getPlugin(me.Derpy.Bosses.MoreBosses.class).getConfig().getDouble("enchants.lifesteal")*p.getInventory().getItemInMainHand().getItemMeta().getEnchantLevel(EnchantmentStorage.getlifesteal()))));
					}
				}
			}
		}
	}

	@Override
	public boolean canEnchantItem(ItemStack arg0) {
		// TODO Auto-generated method stub
		ArrayList<Material> items = new ArrayList<Material>();
		items.add(Material.IRON_SWORD);
		items.add(Material.WOODEN_SWORD);
		items.add(Material.GOLDEN_SWORD);
		items.add(Material.DIAMOND_SWORD);
		items.add(Material.IRON_AXE);
		items.add(Material.WOODEN_AXE);
		items.add(Material.GOLDEN_AXE);
		items.add(Material.DIAMOND_AXE);
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
