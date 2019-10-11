package me.Derpy.Bosses.enchants;

import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.enchantments.EnchantmentTarget;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;


public class fleet extends Enchantment implements Listener{
	public fleet(NamespacedKey key) {
		super(key);
	}
	
	@EventHandler
	private static void ondamage(EntityDamageEvent event) {
		if(event.getEntity() instanceof Player) {
			Player p = (Player) event.getEntity();
			if(p.getInventory().getBoots()!=null) {
				if(p.getInventory().getBoots().getItemMeta().hasEnchant(EnchantmentStorage.getfleet())) {
					if(!(p.getInventory().getBoots().getItemMeta().getEnchantLevel(EnchantmentStorage.getfleet())<=0)) {
						p.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 100, p.getInventory().getBoots().getItemMeta().getEnchantLevel(EnchantmentStorage.getfleet())-1, true));
					}
				}
			}
		}
	}

	@Override
	public boolean canEnchantItem(ItemStack arg0) {
		// TODO Auto-generated method stub
		if(arg0.getType()==Material.IRON_BOOTS) {
			return true;
		}else if(arg0.getType()==Material.LEATHER_BOOTS) {
			return true;
		}else if(arg0.getType()==Material.GOLDEN_BOOTS) {
			return true;
		}else if(arg0.getType()==Material.DIAMOND_BOOTS) {
			return true;
		}else if(arg0.getType()==Material.CHAINMAIL_BOOTS) {
			return true;
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
