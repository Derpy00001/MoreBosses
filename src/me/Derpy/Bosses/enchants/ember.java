package me.Derpy.Bosses.enchants;

import java.util.ArrayList;

import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.enchantments.EnchantmentTarget;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.inventory.ItemStack;


public class ember extends Enchantment implements Listener{
	public ember(NamespacedKey key) {
		super(key);
	}
	//Ab2 reg 2
	@EventHandler
	private static void ondamage(EntityDamageByEntityEvent event) {
		if(event.getEntity() instanceof Player) {
			Player p = (Player) event.getEntity();
			for(ItemStack item : p.getInventory().getArmorContents()){
				if(item!=null) {
					if(item.getItemMeta().hasEnchant(EnchantmentStorage.getember())) {
						if(!(event.getDamager() instanceof Arrow)) {
							event.getDamager().setFireTicks(100);
						}
					}
				}
			}
		}
	}

	@Override
	public boolean canEnchantItem(ItemStack arg0) {
		// TODO Auto-generated method stub
		ArrayList<Material> items = new ArrayList<Material>();
		items.add(Material.IRON_BOOTS);
		items.add(Material.GOLDEN_BOOTS);
		items.add(Material.DIAMOND_BOOTS);
		items.add(Material.CHAINMAIL_BOOTS);
		items.add(Material.IRON_LEGGINGS);
		items.add(Material.GOLDEN_LEGGINGS);
		items.add(Material.DIAMOND_LEGGINGS);
		items.add(Material.CHAINMAIL_LEGGINGS);
		items.add(Material.IRON_CHESTPLATE);
		items.add(Material.GOLDEN_CHESTPLATE);
		items.add(Material.DIAMOND_CHESTPLATE);
		items.add(Material.CHAINMAIL_CHESTPLATE);
		items.add(Material.IRON_HELMET);
		items.add(Material.GOLDEN_HELMET);
		items.add(Material.DIAMOND_HELMET);
		items.add(Material.CHAINMAIL_HELMET);
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
		return EnchantmentTarget.ARMOR;
	}

	@Override
	public int getMaxLevel() {
		// TODO Auto-generated method stub
		return 1;
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return "Ember";
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
