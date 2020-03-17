package me.derpy.bosses.listeners;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.event.inventory.PrepareAnvilEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.EnchantmentStorageMeta;

import me.derpy.bosses.Morebosses;
import me.derpy.bosses.enchantments.EnchantmentHandler;

public class Anvil implements Listener {
	// EnchantItemEvent - For enchantment table
	@EventHandler
	public void onPrepare(PrepareAnvilEvent e) {
		if (e.getInventory().getItem(0) != null && e.getInventory().getItem(1) != null) {
			if (e.getInventory().getItem(1).getType() == Material.ENCHANTED_BOOK) {
				ItemStack book = e.getInventory().getItem(1);
				EnchantmentStorageMeta meta = (EnchantmentStorageMeta) book.getItemMeta();
				for (Enchantment enchantment : meta.getStoredEnchants().keySet()) {
					if (Morebosses.getEnchantmentHandler().getEnchantments().containsValue(enchantment)) {
						if (enchantment.canEnchantItem(e.getInventory().getItem(0))) {
							if (!e.getInventory().getItem(0).getItemMeta().hasEnchant(enchantment)) {
								ItemStack copyItem = EnchantmentHandler.addEnchantCopy(e.getInventory().getItem(0),
										enchantment, meta.getStoredEnchantLevel(enchantment), false);
								EnchantmentHandler.addTag(copyItem);
								e.setResult(copyItem);
							}
						}
					}
				}
			}
		}
	}

	@EventHandler
	public void onClick(InventoryClickEvent e) {
		if (e.getInventory().getType() == InventoryType.ANVIL) {
			if (e.getInventory().getItem(0) != null && e.getInventory().getItem(1) != null
					&& e.getInventory().getItem(2) != null) {
				if (EnchantmentHandler.hasTag(e.getInventory().getItem(2))) {
					if (e.getSlot() == 2) {
						if (!e.isShiftClick()) {
							if (e.getCursor().getType() == Material.AIR) {
								e.setCancelled(true);
								EnchantmentHandler.removeTag(e.getInventory().getItem(2));
								e.getWhoClicked().setItemOnCursor(e.getInventory().getItem(2));
								e.getInventory().clear();
							}
						} else {
							if (e.getWhoClicked().getInventory().firstEmpty() != -1) {
								e.setCancelled(true);
								EnchantmentHandler.removeTag(e.getInventory().getItem(2));
								e.getWhoClicked().getInventory().addItem(e.getInventory().getItem(2));
								e.getInventory().clear();
							}
						}
					}
				}
			}
		}
	}
}
