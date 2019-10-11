package me.Derpy.Bosses.Addons.Nordic.enchants;

import java.util.ArrayList;
import java.util.HashMap;

import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.Particle;
import org.bukkit.craftbukkit.v1_14_R1.entity.CraftEntity;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.enchantments.EnchantmentTarget;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;

import me.Derpy.Bosses.MoreBosses;
import me.Derpy.Bosses.Addons.Nordic.Mobs.fenrir;
import me.Derpy.Bosses.Addons.Utilities.AddonEnchantStorage;
import me.Derpy.Bosses.utilities.Random;


public class bleed extends Enchantment implements Listener{
	public bleed(NamespacedKey key) {
		super(key);
	}

	@EventHandler
	private static void ondamage(EntityDamageByEntityEvent event) {
		if(event.getDamager() instanceof Player) {
			Player p = (Player) event.getDamager();
			if(p.getInventory().getItemInMainHand().getType()!=Material.AIR) {
				if(p.getInventory().getItemInMainHand().getItemMeta().hasEnchant(AddonEnchantStorage.nordic.bleed)) {
					startbleed((LivingEntity) event.getEntity(), p.getInventory().getItemInMainHand().getItemMeta().getEnchantLevel(AddonEnchantStorage.nordic.getbleed()), event.getDamage());
				}
			}
		}
		if(((CraftEntity)event.getDamager()).getHandle() instanceof fenrir) {
			startbleed((LivingEntity) event.getEntity(), 1, event.getDamage());
		}
	}
	@SuppressWarnings("deprecation")
	private static void startbleed(final LivingEntity ent, int itemlevel, final Double damage) {
		final HashMap<String, Double> vars = new HashMap<String, Double>();
		vars.put("bleedticks", 0D);
		vars.put("level", new Double((itemlevel*0.5)+3));
		if(!(vars.get("level")).toString().contains(".")) {
			vars.replace("level", vars.get("level")+2);
		}
		vars.put("scaleofdamage", new Double(MoreBosses.getPlugin(MoreBosses.class).getConfig().getDouble("enchants.bleed")));
		if(vars.get("level").toString().contains(".")) {
			vars.put("bleedtickmax", new Double(Random.round(vars.get("level"), 0.2)));
		}else {
			vars.put("bleedtickmax", new Double(vars.get("level")));
		}
		new BukkitRunnable() {

			@Override
			public void run() {
				vars.replace("bleedticks", vars.get("bleedticks")+1);
				ent.damage(damage*vars.get("scaleofdamage"));
//				Double height = (ent.getBoundingBox().getVolume()/ent.getBoundingBox().getWidthX())/ent.getBoundingBox().getWidthZ();
				ent.getWorld().spawnParticle(Particle.FALLING_DUST, ent.getLocation(), Random.round(ent.getBoundingBox().getVolume(), 0.5)*10, ent.getBoundingBox().getWidthX(), 0, ent.getBoundingBox().getWidthZ(), Material.REDSTONE_BLOCK.createBlockData());
				if(vars.get("bleedticks")>=vars.get("bleedtickmax")) {
					this.cancel();
				}
			}
			
		}.runTaskTimer(MoreBosses.getPlugin(MoreBosses.class), 20*4, 20*4);
	}

	@Override
	public boolean canEnchantItem(ItemStack arg0) {
		// TODO Auto-generated method stub
		ArrayList<Material> items = new ArrayList<Material>();
		items.add(Material.IRON_SWORD);
		items.add(Material.WOODEN_SWORD);
		items.add(Material.GOLDEN_SWORD);
		items.add(Material.DIAMOND_SWORD);
		items.add(Material.IRON_AXE);
		items.add(Material.WOODEN_AXE);
		items.add(Material.GOLDEN_AXE);
		items.add(Material.DIAMOND_AXE);
		if(items.contains(arg0.getType())) {
			return true;
		}else {
			return false;
		}
	}

	@Override
	public boolean conflictsWith(Enchantment arg0) {
		// TODO Auto-generated method stub]
		ArrayList<Enchantment> enchants = new ArrayList<Enchantment>();
		enchants.add(Enchantment.FIRE_ASPECT);
		enchants.add(Enchantment.IMPALING);
		enchants.add(Enchantment.LOOT_BONUS_MOBS);
		if(enchants.contains(arg0)) {
			return true;
		}
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
		return 2;
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
