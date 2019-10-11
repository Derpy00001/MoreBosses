package me.Derpy.Bosses.enchants;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.Sound;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.enchantments.EnchantmentTarget;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;


public class undying extends Enchantment implements Listener{
	public undying(NamespacedKey key) {
		super(key);
	}
	//Ab2 reg 2
	@EventHandler
	private static void ondamage(EntityDamageEvent event) {
		if(event.getEntity() instanceof Player) {
			Player p = (Player) event.getEntity();
			if(p.getHealth()-event.getDamage()<=0) {
				if(p.getInventory().getChestplate()!=null) {
					if(p.getInventory().getChestplate().getItemMeta().hasEnchant(EnchantmentStorage.getundying())) {
						event.setCancelled(true);
						p.setHealth(2);
						p.addPotionEffect(new PotionEffect(PotionEffectType.ABSORPTION, 20*6, p.getInventory().getChestplate().getItemMeta().getEnchantLevel(EnchantmentStorage.getundying())-1));
						p.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, 20*6, p.getInventory().getChestplate().getItemMeta().getEnchantLevel(EnchantmentStorage.getundying())-1));
						p.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, 20*7, 0));
						p.playSound(p.getLocation(), Sound.ENTITY_WITHER_BREAK_BLOCK, 1, 1);
						if(p.getInventory().getChestplate().getItemMeta().getEnchantLevel(EnchantmentStorage.getundying())-1==0) {
							ItemMeta storage = p.getInventory().getChestplate().getItemMeta();
							storage.removeEnchant(EnchantmentStorage.getundying());
							if(storage.hasLore()) {
								List<String> lore = storage.getLore();
								lore.remove(0);
								storage.setLore(lore);
							}
							p.getInventory().getChestplate().setItemMeta(storage);
						}else {
							Integer level = p.getInventory().getChestplate().getItemMeta().getEnchantLevel(EnchantmentStorage.getundying())-1;
							ItemMeta beforestorage = p.getInventory().getChestplate().getItemMeta();
							beforestorage.removeEnchant(EnchantmentStorage.getundying());
							p.getInventory().getChestplate().setItemMeta(beforestorage);
							ItemMeta storage = p.getInventory().getChestplate().getItemMeta();
							storage.addEnchant(EnchantmentStorage.getundying(), level, false);
							if(storage.hasLore()) {
								List<String> lore = storage.getLore();
								lore.set(0, EnchantLevel.returnEnchantmentName(EnchantmentStorage.getundying(), level, ChatColor.GRAY));
								storage.setLore(lore);
							}else {
								storage.setLore(Arrays.asList(EnchantLevel.returnEnchantmentName(EnchantmentStorage.getundying(), level, ChatColor.GRAY)));
							}
							p.getInventory().getChestplate().setItemMeta(storage);
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
		items.add(Material.IRON_CHESTPLATE);
		items.add(Material.GOLDEN_CHESTPLATE);
		items.add(Material.DIAMOND_CHESTPLATE);
		items.add(Material.CHAINMAIL_CHESTPLATE);
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
		return EnchantmentTarget.ARMOR_TORSO;
	}

	@Override
	public int getMaxLevel() {
		// TODO Auto-generated method stub
		return 4;
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return "Undying";
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
