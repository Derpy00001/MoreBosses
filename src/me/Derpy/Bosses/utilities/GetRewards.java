package me.Derpy.Bosses.utilities;

import java.io.IOException;
import java.util.ArrayList;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;
import org.bukkit.plugin.Plugin;

import me.Derpy.Bosses.utilities.items.ichor;
import me.Derpy.Bosses.utilities.items.recipes;


public class GetRewards {
	//Max tier rewards axe that spawns lightning
	public static ItemStack newgem(String name, ChatColor color) {
		ItemStack item = new ItemStack(Material.EMERALD, 1);
		ItemMeta meta = item.getItemMeta();
		ArrayList<String> lore = new ArrayList<String>();
		lore.add(color+name);
		meta.setLore(lore);
		meta.setUnbreakable(true);
		meta.setDisplayName(ChatColor.RESET+""+ChatColor.DARK_RED+"Gem of "+name);
		meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
		item.setItemMeta(meta);
		item.addUnsafeEnchantment(Enchantment.PIERCING, 0);
		return item;
	}
	public static ItemStack getreward_tier0() throws IOException {
		ArrayList<ItemStack> pool = new ArrayList<ItemStack>(50);
		pool.add(new ItemStack(Material.IRON_INGOT, 10));
		pool.add(new ItemStack(Material.MUSIC_DISC_11, 1));
		pool.add(new ItemStack(Material.MUSIC_DISC_13, 1));
		pool.add(new ItemStack(Material.MUSIC_DISC_BLOCKS, 1));
		pool.add(new ItemStack(Material.MUSIC_DISC_CAT, 1));
		pool.add(new ItemStack(Material.MUSIC_DISC_CHIRP, 1));
		pool.add(new ItemStack(Material.MUSIC_DISC_FAR, 1));
		pool.add(new ItemStack(Material.MUSIC_DISC_MALL, 1));
		pool.add(new ItemStack(Material.MUSIC_DISC_MELLOHI, 1));
		pool.add(new ItemStack(Material.MUSIC_DISC_STAL, 1));
		pool.add(new ItemStack(Material.MUSIC_DISC_STRAD, 1));
		pool.add(new ItemStack(Material.MUSIC_DISC_WAIT, 1));
		pool.add(new ItemStack(Material.MUSIC_DISC_WARD, 1));
		pool.add(new ItemStack(Material.CHAINMAIL_BOOTS, 1));
		pool.add(new ItemStack(Material.CHAINMAIL_CHESTPLATE, 1));
		pool.add(new ItemStack(Material.CHAINMAIL_HELMET, 1));
		pool.add(new ItemStack(Material.CHAINMAIL_LEGGINGS, 1));
		pool.add(new ItemStack(Material.COAL, 35));
		pool.add(new ItemStack(Material.CHARCOAL, 35));
		pool.add(new ItemStack(Material.NAUTILUS_SHELL, 2));
		pool.add(new ItemStack(Material.NAME_TAG, 2));
		pool.add(new ItemStack(Material.ENDER_PEARL, 9));
		pool.add(new ItemStack(Material.JUKEBOX, 1));
		pool.add(new ItemStack(Material.GOLDEN_CARROT, 6));
		pool.add(new ItemStack(Material.CAKE, 4));
		pool.add(new ItemStack(Material.COMPASS, 1));
		pool.add(new ItemStack(Material.CLOCK, 1));
		pool.add(new ItemStack(Material.SLIME_BALL, 3));
		pool.add(new ItemStack(Material.CHORUS_FRUIT, 7));
		pool.add(new ItemStack(Material.SCUTE, 2));
		pool.add(new ItemStack(Material.BOOK, 5));
		pool.add(new ItemStack(Material.DRIED_KELP, 23));
		
		pool.add(recipes.cursed());
		pool.add(recipes.ichorboots());
		pool.add(recipes.ichorlegs());
		pool.add(recipes.ichorchest());
		pool.add(recipes.ichorhelm());
		pool.add(recipes.ichorstick());
		pool.add(recipes.ichorsword());
		pool.add(recipes.elytra());
		
		Integer min = 0;
		Integer max = pool.size()-1;
		Integer range = max-min+1;
		int num = (int) ((int)(Math.random()*range)+min);
		return pool.get(num);
	}
	@SuppressWarnings("deprecation")
	public static ItemStack getreward_tier1(Player user) {
		ArrayList<ItemStack> pool = new ArrayList<ItemStack>(50);
		pool.add(new ItemStack(Material.DIAMOND, 3));
		pool.add(new ItemStack(Material.EXPERIENCE_BOTTLE, 4));
		pool.add(new ItemStack(Material.HEART_OF_THE_SEA, 1));
		pool.add(new ItemStack(Material.GOLDEN_APPLE, 3));
		pool.add(new ItemStack(Material.IRON_INGOT, 20));
		pool.add(new ItemStack(Material.EMERALD, 22));
		pool.add(new ItemStack(Material.REDSTONE_BLOCK, 4));
		pool.add(new ItemStack(Material.WITHER_SKELETON_SKULL, 1));
		pool.add(new ItemStack(Material.WITHER_ROSE, 5));
		pool.add(new ItemStack(Material.GOLD_INGOT, 3));
		//Player skull
		ItemStack skull = new ItemStack(Material.PLAYER_HEAD, 1);
		 
		SkullMeta meta = (SkullMeta) skull.getItemMeta();
		meta.setOwner(user.getName());
		meta.setDisplayName(ChatColor.AQUA + user.getName());
		skull.setItemMeta(meta);
		pool.add(skull);
		Integer min = 0;
		Integer max = pool.size()-1;
		Integer range = max-min+1;
		int num = (int) ((int)(Math.random()*range)+min);
		return pool.get(num);
	}
	@SuppressWarnings("deprecation")
	public static ItemStack getreward_tier2(Player user) {
		ArrayList<ItemStack> pool = new ArrayList<ItemStack>(50);
		pool.add(new ItemStack(Material.DIAMOND, 5));
		pool.add(new ItemStack(Material.EXPERIENCE_BOTTLE, 6));
		pool.add(new ItemStack(Material.HEART_OF_THE_SEA, 1));
		pool.add(new ItemStack(Material.GOLDEN_APPLE, 5));
		pool.add(new ItemStack(Material.IRON_INGOT, 25));
		pool.add(new ItemStack(Material.EMERALD, 27));
		pool.add(new ItemStack(Material.REDSTONE_BLOCK, 7));
		pool.add(new ItemStack(Material.WITHER_SKELETON_SKULL, 1));
		pool.add(new ItemStack(Material.WITHER_ROSE, 5));
		pool.add(new ItemStack(Material.TOTEM_OF_UNDYING, 1));
		pool.add(new ItemStack(Material.TRIDENT, 2));
		pool.add(new ItemStack(Material.GOLD_INGOT, 8));
		//Player skull
		ItemStack skull = new ItemStack(Material.PLAYER_HEAD, 1);
		 
		SkullMeta meta = (SkullMeta) skull.getItemMeta();
		meta.setOwner(user.getName());
		meta.setDisplayName(ChatColor.AQUA + user.getName());
		skull.setItemMeta(meta);
		pool.add(skull);
		Integer min = 0;
		Integer max = pool.size()-1;
		Integer range = max-min+1;
		int num = (int) ((int)(Math.random()*range)+min);
		return pool.get(num);
	}
	public static ItemStack getreward_tier3(Player user) {
		ArrayList<ItemStack> pool = new ArrayList<ItemStack>(50);
		pool.add(new ItemStack(Material.DIAMOND, 9));
		pool.add(new ItemStack(Material.EXPERIENCE_BOTTLE, 13));
		pool.add(new ItemStack(Material.HEART_OF_THE_SEA, 2));
		pool.add(new ItemStack(Material.GOLDEN_APPLE, 10));
		pool.add(new ItemStack(Material.IRON_INGOT, 25));
		pool.add(new ItemStack(Material.EMERALD, 34));
		pool.add(new ItemStack(Material.REDSTONE_BLOCK, 8));
		pool.add(new ItemStack(Material.WITHER_SKELETON_SKULL, 1));
		pool.add(new ItemStack(Material.TOTEM_OF_UNDYING, 1));
		pool.add(new ItemStack(Material.GOLD_INGOT, 18));
		pool.add(new ItemStack(Material.QUARTZ, 42));
		pool.add(new ItemStack(Material.ENCHANTED_GOLDEN_APPLE, 1));
		pool.add(new ItemStack(Material.GLOBE_BANNER_PATTERN, 1));
		pool.add(new ItemStack(Material.COAL_BLOCK, 19));
		ItemStack book = new ItemStack(Material.ENCHANTED_BOOK, 1);
		ItemMeta meta = (ItemMeta) book.getItemMeta();
		meta.addEnchant(Enchantment.MENDING, 1, true);
		book.setItemMeta(meta);
		pool.add(book);
		
		Integer min = 0;
		Integer max = pool.size()-1;
		Integer range = max-min+1;
		int num = (int) ((int)(Math.random()*range)+min);
		return pool.get(num);
		
	}
	public static ItemStack getreward_tier4(Player user) {
		ArrayList<ItemStack> pool = new ArrayList<ItemStack>();
		ItemStack item = new ItemStack(Material.EMERALD, 1);
		ItemMeta meta = item.getItemMeta();
		ArrayList<String> lore = new ArrayList<String>();
		lore.add(ChatColor.GREEN+"Levitation");
		meta.setLore(lore);
		meta.setDisplayName(ChatColor.RESET+""+ChatColor.DARK_RED+"Gem of Levitation");
		meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
		meta.setUnbreakable(true);
		item.setItemMeta(meta);
		item.addUnsafeEnchantment(Enchantment.PIERCING, 0);
		pool.add(item);
		ItemStack item2 = new ItemStack(Material.EMERALD, 1);
		ItemMeta meta2 = item2.getItemMeta();
		ArrayList<String> lore2 = new ArrayList<String>();
		lore2.add(ChatColor.AQUA+"Water Breathing");
		meta2.setLore(lore2);
		meta2.setDisplayName(ChatColor.RESET+""+ChatColor.DARK_RED+"Gem of Water Breathing");
		meta2.addItemFlags(ItemFlag.HIDE_ENCHANTS);
		meta2.setUnbreakable(true);
		item2.setItemMeta(meta2);
		item2.addUnsafeEnchantment(Enchantment.PIERCING, 0);
		pool.add(item2);
		ItemStack item3 = new ItemStack(Material.EMERALD, 1);
		ItemMeta meta3 = item3.getItemMeta();
		ArrayList<String> lore3 = new ArrayList<String>();
		lore3.add(ChatColor.WHITE+"Speed");
		meta3.setLore(lore3);
		meta3.setDisplayName(ChatColor.RESET+""+ChatColor.DARK_RED+"Gem of Speed");
		meta3.addItemFlags(ItemFlag.HIDE_ENCHANTS);
		meta3.setUnbreakable(true);
		item3.setItemMeta(meta3);
		item3.addUnsafeEnchantment(Enchantment.PIERCING, 0);
		pool.add(item3);
		ItemStack item4 = new ItemStack(Material.EMERALD, 1);
		ItemMeta meta4 = item4.getItemMeta();
		ArrayList<String> lore4 = new ArrayList<String>();
		lore4.add(ChatColor.WHITE+"Speed");
		meta4.setLore(lore4);
		meta4.setDisplayName(ChatColor.RESET+""+ChatColor.DARK_RED+"Gem of Speed");
		meta4.addItemFlags(ItemFlag.HIDE_ENCHANTS);
		meta4.setUnbreakable(true);
		item4.setItemMeta(meta4);
		item4.addUnsafeEnchantment(Enchantment.PIERCING, 0);
		pool.add(item4);
		pool.add(newgem("Jump Boost", ChatColor.YELLOW));
		
		Integer min = 0;
		Integer max = pool.size()-1;
		Integer range = max-min+1;
		int num = (int) ((int)(Math.random()*range)+min);
		return pool.get(num);
	}
	public static ItemStack getreward_tier5(Plugin plugin) {
		ArrayList<ItemStack> pool = new ArrayList<ItemStack>();
		Integer min0 = 1;
		Integer max0 = 8-1;
		Integer range0 = max0-min0+1;
		int num0 = (int) ((int)(Math.random()*range0)+min0);
		pool.add(ichor.get(num0, plugin));
		
		
		Integer min = 0;
		Integer max = pool.size()-1;
		Integer range = max-min+1;
		int num = (int) ((int)(Math.random()*range)+min);
		return pool.get(num);
	}
}
