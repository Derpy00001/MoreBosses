package me.derpy.bosses.enchantments;

import java.util.Arrays;
import java.util.List;

import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.enchantments.EnchantmentTarget;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.EnchantmentStorageMeta;
import org.jetbrains.annotations.NotNull;

import me.derpy.bosses.Morebosses;

public class BEmber extends Enchantment implements Listener {

	public BEmber(NamespacedKey key) {
		super(key);
		// TODO Auto-generated constructor stub
	}

	@EventHandler
	public void onDamaged(EntityDamageByEntityEvent event) {
		Entity entity = event.getEntity();
		if (entity instanceof LivingEntity) {
			if (!entity.isDead()) {
				for (ItemStack item : ((LivingEntity) entity).getEquipment().getArmorContents()) {
					if (item.getItemMeta().hasEnchant(this)) {
						for (Entity entityNear : entity.getNearbyEntities(10, 10, 10)) {
							entityNear.setFireTicks(20 * (5 * item.getItemMeta().getEnchantLevel(this)));
						}
					}
				}
			}
		}
	}

	@Override
	public boolean canEnchantItem(@NotNull ItemStack arg0) {
		// TODO Auto-generated method stub
		List<Material> materials = Arrays.asList(Material.CHAINMAIL_HELMET, Material.IRON_HELMET,
				Material.GOLDEN_HELMET, Material.LEATHER_HELMET, Material.DIAMOND_HELMET, Material.CHAINMAIL_CHESTPLATE,
				Material.IRON_CHESTPLATE, Material.GOLDEN_CHESTPLATE, Material.LEATHER_CHESTPLATE,
				Material.DIAMOND_CHESTPLATE, Material.CHAINMAIL_LEGGINGS, Material.IRON_LEGGINGS,
				Material.GOLDEN_LEGGINGS, Material.LEATHER_LEGGINGS, Material.DIAMOND_LEGGINGS,
				Material.CHAINMAIL_BOOTS, Material.IRON_BOOTS, Material.GOLDEN_BOOTS, Material.LEATHER_BOOTS,
				Material.DIAMOND_BOOTS);
		return materials.contains(arg0.getType());
	}

	@Override
	public boolean conflictsWith(@NotNull Enchantment arg0) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public @NotNull EnchantmentTarget getItemTarget() {
		// TODO Auto-generated method stub
		return EnchantmentTarget.WEARABLE;
	}

	@Override
	public int getMaxLevel() {
		// TODO Auto-generated method stub
		return 4;
	}

	@Override
	public @NotNull String getName() {
		// TODO Auto-generated method stub
		return "Ember";
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
		return false;
	}

	public static ItemStack getBook() {
		ItemStack item = new ItemStack(Material.ENCHANTED_BOOK);
		EnchantmentStorageMeta meta = (EnchantmentStorageMeta) item.getItemMeta();
		meta.addStoredEnchant(Morebosses.getEnchantmentHandler().EMBER, 1, true);
		item.setItemMeta(meta);
		return item;
	}

}
