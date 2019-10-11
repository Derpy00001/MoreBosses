package me.Derpy.Bosses.enchants;

import java.util.ArrayList;

import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.enchantments.EnchantmentTarget;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Monster;
import org.bukkit.entity.Player;
import org.bukkit.entity.Projectile;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.inventory.ItemStack;

import me.Derpy.Bosses.mobs.custommobs.custom_endermite;


public class mites extends Enchantment implements Listener{
	public mites(NamespacedKey key) {
		super(key);
	}
	@EventHandler
	private static void ondamage(EntityDamageByEntityEvent event) {
		if(event.getEntity() instanceof Player) {
			if(!(event.getDamager() instanceof Projectile || event.getDamager() instanceof Player)) {
				Player p = (Player) event.getEntity();
				for(ItemStack item : p.getInventory().getArmorContents()){
					if(item!=null) {
						if(item.getItemMeta().hasEnchant(EnchantmentStorage.getmites())) {
							Integer level = item.getItemMeta().getEnchantLevel(EnchantmentStorage.getmites());
							Integer amt = 4*level;
							for(int i=0;i<amt;i++) {
								Monster mite = (Monster) custom_endermite.spawn(p.getLocation());
								mite.setTarget((LivingEntity) event.getDamager());
								if(mite.getTarget()==event.getEntity()) {
									mite.remove();
								}
							}
							return;
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
		return 3;
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return "Mites";
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
