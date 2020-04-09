package me.derpy.bosses.items.blueprints;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import me.derpy.bosses.items.data.ItemSpoil;
import me.derpy.bosses.items.data.Spoil;
import me.derpy.bosses.items.interfaces.ISpoilbag;
import me.derpy.bosses.utilities.Random;
import me.derpy.bosses.utilities.Tagger;

public class BSpoilbag implements ISpoilbag {
	private List<Object> itemPool = new ArrayList<Object>();
	private String name = "Spoils Container";

	// YOU KNOW WHAT FUCK THIS - 3/20/20
	public BSpoilbag() {
	}

	@Override
	public ItemStack getItem() {
		// TODO Auto-generated method stub
		ItemStack item = new ItemStack(Material.BOOK);
		ItemMeta meta = item.getItemMeta();
		meta.setDisplayName(this.name);
		item.setItemMeta(meta);
		Tagger.tagSpoil(item, this.getTagId());
		return item;
	}

	public static int getSlot(Inventory inventory) {
		int slot = Random.random(0, inventory.getSize() - 1);
		for (int attempts = 0;; attempts++, slot = Random.random(0, inventory.getSize() - 1)) {
			if (attempts >= inventory.getSize()) {
				break;
			}
			if (inventory.getItem(slot) == null) {
				break;
			}
		}
		return slot;
	}

	@Override
	public int getDropCount() {
		return 3;
	}

	@Override
	public NamespacedKey getNamespacedKey() {
		// TODO Auto-generated method stub
		return NamespacedKey.minecraft("spoilbag-generic");
	}

	@Override
	public ItemStack getFinalizedItem() {
		// TODO Auto-generated method stub
		ItemStack item = this.getItem();
		if (!this.hasCustomColor()) {
			if (item.hasItemMeta()) {
				ItemMeta meta = item.getItemMeta();
				meta.setDisplayName(ChatColor.RESET + "" + this.getNameColor() + meta.getDisplayName());
				item.setItemMeta(meta);
			}
		}
		return item;
	}

	@Override
	public List<Object> getDrops() {
		// TODO Auto-generated method stub
		return this.itemPool;
	}

	@Override
	public void editSpoil(Material spoil, int newMax, int newMin) {
		for (Object obj : this.getDrops()) {
			if (obj instanceof Spoil) {
				if (((Spoil) obj).getMaterial() == spoil) {
					((Spoil) obj).setMax(newMax);
					((Spoil) obj).setMin(newMin);
				}
			}
		}
	}

	@Override
	public void incrementSpoil(Material spoil, int amountMax, int amountMin) {
		for (Object obj : this.getDrops()) {
			if (obj instanceof Spoil) {
				if (((Spoil) obj).getMaterial() == spoil) {
					((Spoil) obj).setMax(((Spoil) obj).getMax() + amountMax);
					((Spoil) obj).setMin(((Spoil) obj).getMin() + amountMin);
				}
			}
		}
	}

	@Override
	public void addItem(ItemStack item) {
		// TODO Auto-generated method stub
		this.itemPool.add(item);
	}

	@Override
	public void addItem(Material material, int count) {
		// TODO Auto-generated method stub
		this.itemPool.add(new ItemStack(material, count));
	}

	@Override
	public void addItem(Material material, int minAmount, int maxAmount) {
		// TODO Auto-generated method stub
		this.itemPool.add(new Spoil(material, minAmount, maxAmount, false, null, false));
	}

	@Override
	public void addItemWithEnchants(Material material, int limit, boolean forcedEnchant, Enchantment[] enchantments) {
		this.itemPool.add(new Spoil(material, 1, 1, true, limit, forcedEnchant, enchantments));
	}

	@Override
	public void addItem(ItemStack item, boolean guaranteed, int minAmount, int maxAmount) {
		// TODO Auto-generated method stub
		this.itemPool.add(new ItemSpoil(item, true, minAmount, maxAmount));
	}

	public Enchantment[] getEnchantmentsFor(Material material) {
		List<Enchantment> enchantments = new ArrayList<Enchantment>();
		for (Enchantment enchantment : Enchantment.values()) {
			if (enchantment.canEnchantItem(new ItemStack(material))) {
				enchantments.add(enchantment);
			}
		}
		return enchantments.toArray(new Enchantment[enchantments.size()]);
	}

	@Override
	public ChatColor getNameColor() {
		// TODO Auto-generated method stub
		return ChatColor.WHITE;
	}

	@Override
	public ChatColor getLoreColor() {
		// TODO Auto-generated method stub
		return ChatColor.GRAY;
	}

	@Override
	public boolean hasCustomColor() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public int getTagId() {
		// TODO Auto-generated method stub
		return 0;
	}

}
