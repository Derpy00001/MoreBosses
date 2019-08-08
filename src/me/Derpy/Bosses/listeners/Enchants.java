package me.Derpy.Bosses.listeners;

import java.util.Arrays;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.event.player.PlayerSwapHandItemsEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;

import me.Derpy.Bosses.Main;

public class Enchants implements Listener{


	private Main plugin;
	public Enchants(Main main) {
		// TODO Auto-generated constructor stub
		this.plugin = main;
	}
	// Gem of flight?
	
	
	@EventHandler
	public void Swap(final PlayerSwapHandItemsEvent event) {
		if(plugin.getConfig().getInt("experimental.Gems")==1) {
			if(event.getPlayer() instanceof Player) {
				final Player p = (Player) event.getPlayer();
				if(event.getOffHandItem().getType()==Material.EMERALD) {
					final ItemStack item = (ItemStack) event.getOffHandItem();
					if(item.getItemMeta().isUnbreakable()) {
						if(item.getItemMeta().hasLore()) {
							if(item.getItemMeta().getLore().get(0).equals(ChatColor.GREEN+"Levitation")) {
								new BukkitRunnable(){
									@Override
									public void run(){
										if(p.getInventory().getItemInOffHand().toString().equals(item.toString())){
											event.getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.LEVITATION, 40, 0));
										}else {
											this.cancel();
										}
									}
								}.runTaskTimer(plugin, 10, 10);
							}
							if(item.getItemMeta().getLore().get(0).equals(ChatColor.AQUA+"Water Breathing")) {
								new BukkitRunnable(){
									@Override
									public void run(){
										if(p.getInventory().getItemInOffHand().toString().equals(item.toString())){
											event.getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.WATER_BREATHING, 40, 0));
										}else {
											this.cancel();
										}
									}
								}.runTaskTimer(plugin, 10, 10);
							}
							if(item.getItemMeta().getLore().get(0).equals(ChatColor.WHITE+"Speed")) {
								new BukkitRunnable(){
									@Override
									public void run(){
										if(p.getInventory().getItemInOffHand().toString().equals(item.toString())){
											event.getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 40, 2));
										}else {
											this.cancel();
										}
									}
								}.runTaskTimer(plugin, 10, 10);
							}
							if(item.getItemMeta().getLore().get(0).equals(ChatColor.YELLOW+"Jump Boost")) {
								new BukkitRunnable(){
									@Override
									public void run(){
										if(p.getInventory().getItemInOffHand().toString().equals(item.toString())){
											event.getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.JUMP, 40, 2));
										}else {
											this.cancel();
										}
									}
								}.runTaskTimer(plugin, 10, 10);
							}
						}
					}
				}
			}
		}
	}
	@EventHandler
	public void inventoryclose(InventoryCloseEvent event) {
		if(plugin.getConfig().getInt("experimental.Gems")==1) {
			final Player  p = (Player) event.getPlayer();
			if(event.getInventory().getType()==InventoryType.CRAFTING) {
				if(p.getInventory().getItemInOffHand().getType()==Material.EMERALD) {
					final ItemStack item = (ItemStack) p.getInventory().getItemInOffHand();
					if(item.getItemMeta().isUnbreakable()) {
						if(item.getItemMeta().hasLore()) {
							if(item.getItemMeta().getLore().get(0).equals(ChatColor.GREEN+"Levitation")) {
								new BukkitRunnable(){
									@Override
									public void run(){
										if(p.getInventory().getItemInOffHand().toString().equals(item.toString())){
											p.addPotionEffect(new PotionEffect(PotionEffectType.LEVITATION, 40, 0));
										}else {
											this.cancel();
										}
									}
								}.runTaskTimer(plugin, 10, 10);
							}
							if(item.getItemMeta().getLore().get(0).equals(ChatColor.AQUA+"Water Breathing")) {
								new BukkitRunnable(){
									@Override
									public void run(){
										if(p.getInventory().getItemInOffHand().toString().equals(item.toString())){
											p.addPotionEffect(new PotionEffect(PotionEffectType.WATER_BREATHING, 40, 0));
										}else {
											this.cancel();
										}
									}
								}.runTaskTimer(plugin, 10, 10);
							}
							if(item.getItemMeta().getLore().get(0).equals(ChatColor.WHITE+"Speed")) {
								new BukkitRunnable(){
									@Override
									public void run(){
										if(p.getInventory().getItemInOffHand().toString().equals(item.toString())){
											p.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 40, 2));
										}else {
											this.cancel();
										}
									}
								}.runTaskTimer(plugin, 10, 10);
							}
							if(item.getItemMeta().getLore().get(0).equals(ChatColor.YELLOW+"Jump Boost")) {
								new BukkitRunnable(){
									@Override
									public void run(){
										if(p.getInventory().getItemInOffHand().toString().equals(item.toString())){
											p.getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.JUMP, 40, 2));
										}else {
											this.cancel();
										}
									}
								}.runTaskTimer(plugin, 10, 10);
							}
						}
					}
				}
			}
		}
		if(event.getView().getTitle().equals(ChatColor.RED+"Spoils")) {
			if(event.getInventory().getSize()==9) {
				for(ItemStack item : event.getInventory().getContents()) {
					if(!(item==null)) {
						if(Arrays.asList(event.getPlayer().getInventory().getStorageContents()).contains(null)) {
							event.getPlayer().getInventory().addItem(item);
						}else {
							event.getPlayer().getWorld().dropItem(event.getPlayer().getLocation(), item);
						}
					}
				}
			}
		}
	}


}
