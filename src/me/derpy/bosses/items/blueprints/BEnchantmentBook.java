package me.derpy.bosses.items.blueprints;

import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.EnchantmentStorageMeta;

import me.derpy.bosses.Morebosses;
import me.derpy.bosses.enchantments.EnchantmentHandler;
import me.derpy.bosses.items.interfaces.IEnchantmentBook;

public class BEnchantmentBook implements IEnchantmentBook {

	@Override
	public ItemStack getItem() {
		// TODO Auto-generated method stub
		return new ItemStack(Material.ENCHANTED_BOOK);
	}

	@Override
	public NamespacedKey getNamespacedKey() {
		// TODO Auto-generated method stub
		return NamespacedKey.minecraft("enchantedbook-ember");
	}

	@Override
	public ItemStack getFinalizedItem() {
		// TODO Auto-generated method stub
		ItemStack item = this.getItem();
		EnchantmentStorageMeta meta = (EnchantmentStorageMeta) item.getItemMeta();
		meta.addStoredEnchant(this.getEnchantment(), this.getLevel(), true);
		item.setItemMeta(meta);
		return EnchantmentHandler.addEnchantCopy(item, this.getEnchantment(), this.getLevel(), true);
	}

	@Override
	public Enchantment getEnchantment() {
		// TODO Auto-generated method stub
		return Morebosses.getEnchantmentHandler().EMBER;
	}

	@Override
	public int getLevel() {
		// TODO Auto-generated method stub
		return 1;
	}

}
