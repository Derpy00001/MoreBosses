package me.Derpy.Bosses.utilities;

import java.io.File;
import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import net.md_5.bungee.api.ChatColor;

public class ConfigManager {
	public static ConfigMan pools;
	public static Inventory tier0pool;
	public static Inventory tier1pool;
	public static Inventory tier2pool;
	public static Inventory tier3pool;
	public static Inventory tier4pool;
	public static Inventory tier5pool;
	public static Inventory ghast_raid_pool;
	public static Inventory gladiator_pool;
	public static Inventory fenrir_pool;
	public static Inventory warlock_pool;
	public static File poolsf;
	public static void setup() {
		ConfigMan config0 = new ConfigMan();
		config0.setup();
		ConfigManager.pools=config0;
		ConfigManager.poolsf=config0.getPoolsFile();
		//Loot Pools
		Inventory ghast_raid = Bukkit.createInventory(null, 9*6, ChatColor.RED+"MoreBosses.Pool.ghast_raid");
		if(config0.getPools().contains("MoreBosses.Pool.ghast_raid")) {
			if(config0.getPools().get("MoreBosses.Pool.ghast_raid")!=null){
				@SuppressWarnings("unchecked")
				ArrayList<ItemStack> items = (ArrayList<ItemStack>) config0.getPools().get("MoreBosses.Pool.ghast_raid");
				for(ItemStack item : items) {
					if(!(item==null)) {
						ghast_raid.addItem(item);
					}
				}
			}
		}
		Inventory gladiator_arena = Bukkit.createInventory(null, 9*6, ChatColor.RED+"MoreBosses.Pool.gladiator");
		if(config0.getPools().contains("MoreBosses.Pool.gladiator")) {
			if(config0.getPools().get("MoreBosses.Pool.gladiator")!=null){
				@SuppressWarnings("unchecked")
				ArrayList<ItemStack> items = (ArrayList<ItemStack>) config0.getPools().get("MoreBosses.Pool.gladiator");
				for(ItemStack item : items) {
					if(!(item==null)) {
						gladiator_arena.addItem(item);
					}
				}
			}
		}
		Inventory tier0 = Bukkit.createInventory(null, 9*6, ChatColor.RED+"MoreBosses.Pool.tier0");
		if(config0.getPools().contains("MoreBosses.Pool.tier0")) {
			if(config0.getPools().get("MoreBosses.Pool.tier0")!=null){
				@SuppressWarnings("unchecked")
				ArrayList<ItemStack> items = (ArrayList<ItemStack>) config0.getPools().get("MoreBosses.Pool.tier0");
				for(ItemStack item : items) {
					if(!(item==null)) {
						tier0.addItem(item);
					}
				}
			}
		}
		Inventory tier1 = Bukkit.createInventory(null, 9*6, ChatColor.RED+"MoreBosses.Pool.tier1");
		if(config0.getPools().contains("MoreBosses.Pool.tier1")) {
			if(config0.getPools().get("MoreBosses.Pool.tier1")!=null){
				@SuppressWarnings("unchecked")
				ArrayList<ItemStack> items = (ArrayList<ItemStack>) config0.getPools().get("MoreBosses.Pool.tier1");
				for(ItemStack item : items) {
					if(!(item==null)) {
						tier1.addItem(item);
					}
				}
			}
		}
		Inventory tier2 = Bukkit.createInventory(null, 9*6, ChatColor.RED+"MoreBosses.Pool.tier2");
		if(config0.getPools().contains("MoreBosses.Pool.tier2")) {
			if(config0.getPools().get("MoreBosses.Pool.tier2")!=null){
				@SuppressWarnings("unchecked")
				ArrayList<ItemStack> items = (ArrayList<ItemStack>) config0.getPools().get("MoreBosses.Pool.tier2");
				for(ItemStack item : items) {
					if(!(item==null)) {
						tier2.addItem(item);
					}
				}
			}
		}
		Inventory tier3 = Bukkit.createInventory(null, 9*6, ChatColor.RED+"MoreBosses.Pool.tier3");
		if(config0.getPools().contains("MoreBosses.Pool.tier3")) {
			if(config0.getPools().get("MoreBosses.Pool.tier3")!=null){
				@SuppressWarnings("unchecked")
				ArrayList<ItemStack> items = (ArrayList<ItemStack>) config0.getPools().get("MoreBosses.Pool.tier3");
				for(ItemStack item : items) {
					if(!(item==null)) {
						tier3.addItem(item);
					}
				}
			}
		}
		Inventory tier4 = Bukkit.createInventory(null, 9*6, ChatColor.RED+"MoreBosses.Pool.tier4");
		if(config0.getPools().contains("MoreBosses.Pool.tier4")) {
			if(config0.getPools().get("MoreBosses.Pool.tier4")!=null){
				@SuppressWarnings("unchecked")
				ArrayList<ItemStack> items = (ArrayList<ItemStack>) config0.getPools().get("MoreBosses.Pool.tier4");
				for(ItemStack item : items) {
					if(!(item==null)) {
						tier4.addItem(item);
					}
				}
			}
		}
		Inventory tier5 = Bukkit.createInventory(null, 9*6, ChatColor.RED+"MoreBosses.Pool.tier5");
		if(config0.getPools().contains("MoreBosses.Pool.tier5")) {
			if(config0.getPools().get("MoreBosses.Pool.tier5")!=null){
				@SuppressWarnings("unchecked")
				ArrayList<ItemStack> items = (ArrayList<ItemStack>) config0.getPools().get("MoreBosses.Pool.tier5");
				for(ItemStack item : items) {
					if(!(item==null)) {
						tier5.addItem(item);
					}
				}
			}
		}
		Inventory fenrir = Bukkit.createInventory(null, 9*6, ChatColor.RED+"MoreBosses.Pool.fenrir");
		if(config0.getPools().contains("MoreBosses.Pool.fenrir")) {
			if(config0.getPools().get("MoreBosses.Pool.fenrir")!=null){
				@SuppressWarnings("unchecked")
				ArrayList<ItemStack> items = (ArrayList<ItemStack>) config0.getPools().get("MoreBosses.Pool.fenrir");
				for(ItemStack item : items) {
					if(!(item==null)) {
						fenrir.addItem(item);
					}
				}
			}
		}
		Inventory warlock = Bukkit.createInventory(null, 9*6, ChatColor.RED+"MoreBosses.Pool.warlock");
		if(config0.getPools().contains("MoreBosses.Pool.warlock")) {
			if(config0.getPools().get("MoreBosses.Pool.warlock")!=null){
				@SuppressWarnings("unchecked")
				ArrayList<ItemStack> items = (ArrayList<ItemStack>) config0.getPools().get("MoreBosses.Pool.warlock");
				for(ItemStack item : items) {
					if(!(item==null)) {
						warlock.addItem(item);
					}
				}
			}
		}
		tier0pool = tier0;
		tier1pool = tier1;
		tier2pool = tier2;
		tier3pool = tier3;
		tier4pool = tier4;
		tier5pool = tier5;
		ghast_raid_pool=ghast_raid;
		gladiator_pool=gladiator_arena;
		fenrir_pool=fenrir;
		warlock_pool=warlock;
	}
}
