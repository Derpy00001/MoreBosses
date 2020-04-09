package me.derpy.bosses.enchantments;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.enchantments.EnchantmentTarget;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;

import me.derpy.bosses.Morebosses;

public class BBleed extends Enchantment implements Listener {
	final double DAMAGE = Morebosses.getConfigurationHandler().openEnchantmentConfiguration("bleed.yml")
			.getDouble("bleed.damage");
	final int DURATION = Morebosses.getConfigurationHandler().openEnchantmentConfiguration("bleed.yml")
			.getInt("bleed.bleed_ticks");
	final int FREQUENCY = Morebosses.getConfigurationHandler().openEnchantmentConfiguration("bleed.yml")
			.getInt("bleed.seconds_frequency");
	final int LEVEL_CAP = Morebosses.getConfigurationHandler().openEnchantmentConfiguration("bleed.yml")
			.getInt("bleed.level_cap");
	List<LivingEntity> bleedingEntites = new ArrayList<LivingEntity>();

	public BBleed(NamespacedKey key) {
		super(key);
	}

	@EventHandler
	public void onDamaged(EntityDamageByEntityEvent event) {
		if (event.getDamager() instanceof Player) {
			Player player = (Player) event.getDamager();
			if (!player.isDead()) {
				if (player.getInventory().getItemInMainHand() != null
						? player.getInventory().getItemInMainHand().getType() != Material.AIR ? true : false
						: false) {
					if (player.getInventory().getItemInMainHand().hasItemMeta()) {
						if (player.getInventory().getItemInMainHand().getItemMeta().hasEnchant(this)) {
							if (!event.getEntity().isDead()) {
								if (!this.getBleedingEntities().contains(event.getEntity())) {
									this.startBleed((LivingEntity) event.getEntity(), player.getInventory()
											.getItemInMainHand().getItemMeta().getEnchantLevel(this));
								}
							}
						}
					}
				}
			}
		}
	}

	public void startBleed(final LivingEntity entity, final int bleedLevel) {
		this.getBleedingEntities().add(entity);
		new BukkitRunnable() {
			int bleedTicks = 0;
			final double DAMAGE_CALC = bleedLevel * DAMAGE;

			@Override
			public void run() {
				this.bleedTicks++;
				if (entity.isDead()) {
					this.cancel();
				}
				if (!this.isCancelled()) {
					entity.damage(this.DAMAGE_CALC);
					if (bleedTicks >= DURATION) {
						this.cancel();
					}
				}
				if (this.isCancelled()) {
					if (getBleedingEntities().contains(entity)) {
						getBleedingEntities().remove(entity);
					}
				}
			}
		}.runTaskTimer(Morebosses.getPlugin(Morebosses.class), 0L, 20 * this.FREQUENCY);
	}

	private List<LivingEntity> getBleedingEntities() {
		return this.bleedingEntites;
	}

	@Override
	public boolean canEnchantItem(ItemStack arg0) {
		// TODO Auto-generated method stub
		if (arg0.getType().name().toLowerCase().contains("sword")) {
			return true;
		}
		return false;
	}

	@Override
	public boolean conflictsWith(Enchantment arg0) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public EnchantmentTarget getItemTarget() {
		// TODO Auto-generated method stub
		return EnchantmentTarget.WEAPON;
	}

	@Override
	public int getMaxLevel() {
		// TODO Auto-generated method stub
		return this.LEVEL_CAP;
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return "Bleed";
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
