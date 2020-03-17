package me.derpy.bosses.items.blueprints;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.block.Chest;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.BlockStateMeta;
import org.bukkit.inventory.meta.ItemMeta;

import me.derpy.bosses.items.data.Spoil;
import me.derpy.bosses.items.interfaces.ISpoilbag;
import me.derpy.bosses.utilities.Random;

public class BSpoilbag implements ISpoilbag {
	private List<Object> itemPool = new ArrayList<Object>();
	private String name = "Spoils Container";

	public BSpoilbag() {
	}

	@Override
	public ItemStack getItem() {
		// TODO Auto-generated method stub
		ItemStack item = new ItemStack(Material.CHEST);
		BlockStateMeta meta = (BlockStateMeta) item.getItemMeta();
		Chest chest = (Chest) meta.getBlockState();
		if (this.getDrops().size() > 0) {
			for (int i = 1; i <= this.getDropCount(); i++) {
				Object obj = this.getDrops().get(Random.random(0, this.getDrops().size() - 1));
				if (obj instanceof ItemStack) {
					chest.getInventory().setItem(this.getChestSlot(chest), (ItemStack) obj);
				} else if (obj instanceof Spoil) {
					if(!((Spoil) obj).isEnchantable()) {
						chest.getInventory().setItem(this.getChestSlot(chest), new ItemStack(((Spoil) obj).getMaterial(),
								Random.random(((Spoil) obj).getMin(), ((Spoil) obj).getMax())));
					}else {
						ItemStack chestItem = new ItemStack(((Spoil) obj).getMaterial(), Random.random(((Spoil) obj).getMin(), ((Spoil) obj).getMax()));
						Enchantment enchant = ((Spoil) obj).getRandomEnchantment();
						ItemMeta chestItemMeta = chestItem.getItemMeta();
						chestItemMeta.addEnchant(enchant, Random.random(enchant.getStartLevel(), enchant.getMaxLevel()), false);
						chestItem.setItemMeta(chestItemMeta);
						chest.getInventory().setItem(this.getChestSlot(chest), chestItem);
					}
				}
			}
		}
		meta.setDisplayName(name);
		meta.setLore(Arrays.asList("A container filled of items"));
		meta.setBlockState(chest);
		item.setItemMeta(meta);
		return item;
	}

	public int getChestSlot(Chest chest) {
		int slot = Random.random(0, chest.getInventory().getSize() - 1);
		for (int attempts = 0;; attempts++, slot = Random.random(0, chest.getInventory().getSize() - 1)) {
			if (attempts >= chest.getInventory().getSize()) {
				break;
			}
			if (chest.getInventory().getItem(slot) == null) {
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
		ItemMeta meta = item.getItemMeta();
		List<String> lore = meta.getLore();
		List<String> strings = new ArrayList<String>();
		for (String string : lore) {
			strings.add(ChatColor.RESET + "" + ChatColor.GRAY + string);
		}
		meta.setLore(strings);
		meta.setDisplayName(ChatColor.RESET + "" + this.getColor() + meta.getDisplayName());
		item.setItemMeta(meta);
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
		this.itemPool.add(new Spoil(material, minAmount, maxAmount, false));
	}
	
	@Override
	public void addItemWithEnchants(Material material, Enchantment... enchantments) {
		this.itemPool.add(new Spoil(material, 1, 1, true, enchantments));
	}

	public Enchantment[] getEnchantmentsFor(Material material) {
		List<Enchantment> enchantments = new ArrayList<Enchantment>();	
		for(Enchantment enchantment : Enchantment.values()) {
			if(enchantment.canEnchantItem(new ItemStack(material))) {
				enchantments.add(enchantment);
			}
		}
		return (Enchantment[]) enchantments.toArray(new Enchantment[enchantments.size()]);
	}
	
	@Override
	public org.bukkit.ChatColor getColor() {
		// TODO Auto-generated method stub
		return ChatColor.WHITE;
	}

}
