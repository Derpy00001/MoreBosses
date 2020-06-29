package me.derpy.bosses.enchantments;

import java.util.Arrays;

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
	final int DURATION = Morebosses.getConfigurationHandler().openEnchantmentConfiguration("ember.yml")
			.getInt("ember.duration");
	final int LEVEL_CAP = Morebosses.getConfigurationHandler().openEnchantmentConfiguration("ember.yml")
			.getInt("ember.level_cap");

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
					if (!(item == null)) {
						if (item.hasItemMeta()) {
							if (item.getItemMeta().hasEnchant(this)) {
								for (Entity entityNear : entity.getNearbyEntities(10, 10, 10)) {
									entityNear.setFireTicks(
											20 * (this.DURATION * item.getItemMeta().getEnchantLevel(this)));
								}
							}
						}
					}
				}
			}
		}
	}

	@Override
	public boolean canEnchantItem(@NotNull ItemStack arg0) {
		// TODO Auto-generated method stub
		if (arg0.getType().name().toLowerCase().contains("_")) {
			String[] allowedNames = { "helmet", "chestplate", "legging", "boot" };
			String name = arg0.getType().name().toLowerCase().split("_")[1];
			if (Arrays.asList(allowedNames).contains(name)) {
				return true;
			} else {
				if (arg0.getType() == Material.ENCHANTED_BOOK) {
					return true;
				}
			}
		}
		return false;

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
		return this.LEVEL_CAP;
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
