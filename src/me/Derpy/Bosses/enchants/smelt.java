package me.Derpy.Bosses.enchants;

import java.util.ArrayList;

import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.enchantments.EnchantmentTarget;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;

import me.Derpy.Bosses.utilities.Random;


public class smelt extends Enchantment implements Listener{
	public smelt(NamespacedKey key) {
		super(key);
	}
	//Ab2 reg 2
	@EventHandler
	private static void onbreak(BlockBreakEvent event) {
		if(event.getPlayer().getInventory().getItemInMainHand()!=null) {
			ItemStack item = event.getPlayer().getInventory().getItemInMainHand();
			if(item.getType()==Material.AIR) {
				return;
			}
			if(item.getItemMeta().hasEnchant(EnchantmentStorage.getsmelt())) {
				if(event.isDropItems()) {
					Material type = event.getBlock().getType();
					ArrayList<Material> logs = new ArrayList<Material>();
					logs.add(Material.ACACIA_LOG);
					logs.add(Material.BIRCH_LOG);
					logs.add(Material.DARK_OAK_LOG);
					logs.add(Material.JUNGLE_LOG);
					logs.add(Material.OAK_LOG);
					logs.add(Material.SPRUCE_LOG);
					logs.add(Material.STRIPPED_ACACIA_LOG);
					logs.add(Material.STRIPPED_BIRCH_LOG);
					logs.add(Material.STRIPPED_DARK_OAK_LOG);
					logs.add(Material.STRIPPED_JUNGLE_WOOD);
					logs.add(Material.STRIPPED_OAK_LOG);
					logs.add(Material.STRIPPED_SPRUCE_LOG);
					Double chance = 0.07*item.getItemMeta().getEnchantLevel(EnchantmentStorage.getsmelt());
					Integer amt = 1;
					if(item.getItemMeta().getEnchantLevel(EnchantmentStorage.getsmelt())>1) {
						if(Random.random(chance)) {
							amt=Random.random(2, item.getItemMeta().getEnchantLevel(EnchantmentStorage.getsmelt()));
						}
					}
					
					if(type==Material.GOLD_ORE) {
						event.setDropItems(false);
						event.getBlock().getWorld().dropItemNaturally(event.getBlock().getLocation(), new ItemStack(Material.GOLD_INGOT, amt));
					}else if(type==Material.IRON_ORE) {
						event.setDropItems(false);
						event.getBlock().getWorld().dropItemNaturally(event.getBlock().getLocation(), new ItemStack(Material.IRON_INGOT, amt));
					}else if(type==Material.COBBLESTONE) {
						event.setDropItems(false);
						event.getBlock().getWorld().dropItemNaturally(event.getBlock().getLocation(), new ItemStack(Material.STONE, amt));
					}else if(type==Material.SAND) {
						event.setDropItems(false);
						event.getBlock().getWorld().dropItemNaturally(event.getBlock().getLocation(), new ItemStack(Material.GLASS, amt));
					}else if(logs.contains(type)) {
						event.setDropItems(false);
						event.getBlock().getWorld().dropItemNaturally(event.getBlock().getLocation(), new ItemStack(Material.CHARCOAL, amt));
					}
				}
			}
		}
	}

	@Override
	public boolean canEnchantItem(ItemStack arg0) {
		// TODO Auto-generated method stub
		ArrayList<Material> items = new ArrayList<Material>();
		items.add(Material.IRON_PICKAXE);
		items.add(Material.WOODEN_PICKAXE);
		items.add(Material.GOLDEN_PICKAXE);
		items.add(Material.DIAMOND_PICKAXE);
		items.add(Material.IRON_AXE);
		items.add(Material.WOODEN_AXE);
		items.add(Material.GOLDEN_AXE);
		items.add(Material.DIAMOND_AXE);
		items.add(Material.IRON_SHOVEL);
		items.add(Material.WOODEN_SHOVEL);
		items.add(Material.GOLDEN_SHOVEL);
		items.add(Material.DIAMOND_SHOVEL);
		if(items.contains(arg0.getType())) {
			return true;
		}else {
			return false;
		}
	}

	@Override
	public boolean conflictsWith(Enchantment arg0) {
		// TODO Auto-generated method stub
		ArrayList<Enchantment> enchants = new ArrayList<Enchantment>();
		enchants.add(Enchantment.SILK_TOUCH);
		enchants.add(Enchantment.LOOT_BONUS_BLOCKS);
		if(enchants.contains(arg0)) {
			return true;
		}else {
			return false;
		}
	}

	@Override
	public EnchantmentTarget getItemTarget() {
		// TODO Auto-generated method stub
		return EnchantmentTarget.TOOL;
	}

	@Override
	public int getMaxLevel() {
		// TODO Auto-generated method stub
		return 4;
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return "Smelt";
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
