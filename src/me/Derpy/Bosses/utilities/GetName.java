package me.Derpy.Bosses.utilities;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.NamespacedKey;
import org.bukkit.attribute.Attributable;
import org.bukkit.attribute.Attribute;
import org.bukkit.boss.BarColor;
import org.bukkit.boss.BarFlag;
import org.bukkit.boss.BarStyle;
import org.bukkit.boss.BossBar;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;

import net.md_5.bungee.api.ChatColor;

public class GetName {
	@SuppressWarnings("deprecation")
	public static void getname(final LivingEntity entity, final LivingEntity entity2, final Plugin plugin, boolean b) {
		entity.setRemoveWhenFarAway(true);
		Boolean music = false;
		ArrayList<String> names = new ArrayList<String>();
		names.add("Amun");
		names.add("Amon");
		names.add("Andraste");
		names.add("Adrastos");
		names.add("Agni");
		names.add("Aiolos");
		names.add("Alvíss");
		names.add("AN");
		names.add("Ananta");
		names.add("Aniruddha");
		names.add("Áed");
		names.add("Asar");
		names.add("Belial");
		names.add("Bhima");
		names.add("Charon");
		names.add("Deimos");
		names.add("Fachtna");
		names.add("Lycus");
		names.add("Magni");
		names.add("Metis");
		names.add("Mot");
		names.add("Perun");
		names.add("Raijin");
		names.add("Ragini");
		names.add("Sosruko");
		names.add("Urd");
		names.add("Valli");
		names.add("Živa");
		ArrayList<String> titles = new ArrayList<String>();
		if(entity.getType()==EntityType.TROPICAL_FISH || entity.getType()==EntityType.MAGMA_CUBE||entity.getType()==EntityType.VEX||entity.getType()==EntityType.GHAST||entity.getType()==EntityType.WANDERING_TRADER||entity.getType()==EntityType.WOLF) {
			if(entity.getType()==EntityType.TROPICAL_FISH) {
				titles.add("Fish");
			}
			if(entity.getType()==EntityType.MAGMA_CUBE) {
				titles.add("Titan");
			}
			if(b) {
				titles.add("Mutated");
			}
			if(entity.getType()==EntityType.VEX) {
				titles.add("Vex");
			}
			if(entity.getType()==EntityType.GHAST) {
				titles.add("Overlord");
			}
			if(entity.getType()==EntityType.WANDERING_TRADER||entity.getType()==EntityType.WOLF) {
				titles.add("");
			}
		}else {
			if(b) {
				titles.add("Mutated");
			}else {
				titles.add("Destroyer");
				titles.add("Invincible");
				titles.add("Strong");
				titles.add("Weak");
				titles.add("Nimble");
				titles.add("Angra Mainyu");
				titles.add("Warlord");
			}
		}
		Integer min = 0;
		Integer max = names.size()-1;
		Integer range = max-min+1;
		int num = (int) ((int)(Math.random()*range)+min);
		final String thename= names.get(num);
		Integer min2 = 0;
		Integer max2 = titles.size()-1;
		Integer range2 = max2-min2+1;
		int num2 = (int) ((int)(Math.random()*range2)+min2);
		String thetitle= titles.get(num2);
		if(!(entity.getType()==EntityType.WANDERING_TRADER)) {
			entity.setCustomName(thename+" the "+thetitle);
			if(entity.getType()==EntityType.WOLF) {
				entity.setCustomName(ChatColor.RED+"Fragment of Fenrir");
			}
			entity.setCustomNameVisible(false);
		}
		Attributable bossAttributable = (Attributable) entity2;
		if(!(entity.getType()==EntityType.SLIME)) {
			if(thetitle.equals("Destroyer")) {
				entity.setMaxHealth(entity.getHealth()*2);
				entity.setHealth(entity.getMaxHealth());
				
				bossAttributable.getAttribute(Attribute.GENERIC_ATTACK_DAMAGE).setBaseValue(bossAttributable.getAttribute(Attribute.GENERIC_ATTACK_DAMAGE).getBaseValue()*2);
			}else if(thetitle.equals("Invincible")) {
				entity.setMaxHealth(entity.getHealth()*3);
				entity.setHealth(entity.getMaxHealth());
			}else if(thetitle.equals("Strong")) {
				bossAttributable.getAttribute(Attribute.GENERIC_ATTACK_DAMAGE).setBaseValue(bossAttributable.getAttribute(Attribute.GENERIC_ATTACK_DAMAGE).getBaseValue()*3);
			}else if(thetitle.equals("Weak")) {
				bossAttributable.getAttribute(Attribute.GENERIC_ATTACK_DAMAGE).setBaseValue(bossAttributable.getAttribute(Attribute.GENERIC_ATTACK_DAMAGE).getBaseValue()/3);
			}else if(thetitle.equals("Nimble")) {
				bossAttributable.getAttribute(Attribute.GENERIC_MOVEMENT_SPEED).setBaseValue(bossAttributable.getAttribute(Attribute.GENERIC_MOVEMENT_SPEED).getBaseValue()*2);
			}else if(thetitle.equals("Angra Mainyu")) {
				entity.setMaxHealth(entity.getHealth()*2);
				entity.setHealth(entity.getMaxHealth());
				bossAttributable.getAttribute(Attribute.GENERIC_ATTACK_DAMAGE).setBaseValue(bossAttributable.getAttribute(Attribute.GENERIC_ATTACK_DAMAGE).getBaseValue()*2);
				bossAttributable.getAttribute(Attribute.GENERIC_MOVEMENT_SPEED).setBaseValue(bossAttributable.getAttribute(Attribute.GENERIC_MOVEMENT_SPEED).getBaseValue()*2);
			}else if(thetitle.equals("Warlord")) {
				for(int i=0;i<num; i++) { // 1-28
					entity.getWorld().spawnEntity(entity.getLocation(), entity.getType());
				}
			}else if(thetitle.equals("Mutated")) {
				bossAttributable.getAttribute(Attribute.GENERIC_ATTACK_DAMAGE).setBaseValue(bossAttributable.getAttribute(Attribute.GENERIC_ATTACK_DAMAGE).getBaseValue()*2);
			}else if(thetitle.equals("Overlord")) {
				music = true;
			}
		}
		if(plugin.getConfig().getBoolean("bosses.show_tier_in_bar")) {
			EntityType type = entity.getType();
			if(type==EntityType.COW||type==EntityType.PIG||type==EntityType.SHEEP||type==EntityType.TROPICAL_FISH) {
				thetitle = thetitle.concat(" Tier 0");
			}else if(type==EntityType.DROWNED||type==EntityType.SKELETON||type==EntityType.ZOMBIE) {
				thetitle =thetitle.concat(" Tier 1");
			}else if(type==EntityType.BLAZE||type==EntityType.CREEPER||type==EntityType.GUARDIAN||type==EntityType.STRAY) {
				thetitle= thetitle.concat(" Tier 2");
			}else if(type==EntityType.ELDER_GUARDIAN||type==EntityType.SLIME||type==EntityType.WITHER_SKELETON) {
				thetitle= thetitle.concat(" Tier 3");
			}else if(type==EntityType.ENDER_DRAGON||type==EntityType.WITHER) {
				thetitle= thetitle.concat(" Tier 4");
			}else if(type==EntityType.MAGMA_CUBE) {
				thetitle= thetitle.concat(" Tier 5");
			}
		}
		if(plugin.getConfig().getBoolean("bosses.bar_enabled")) {
			Integer min3 = 0;
			Integer max3 = 99999;
			Integer range3 = max3-min3+1;
			int num3 = (int) ((int)(Math.random()*range3)+min3);
			final NamespacedKey key = new NamespacedKey(plugin, thename.replace(" ", "").replace("Ž", "Z").replace("Á", "A").replace("í", "i")+thetitle.replace(" ", "")+"-"+Integer.toString(num3));
			BossBar bar2;
			if(!(entity.getType()==EntityType.WANDERING_TRADER||entity.getType()==EntityType.WOLF)) {
				bar2 = Bukkit.getServer().createBossBar( key, thename+" the "+thetitle, BarColor.BLUE, BarStyle.SEGMENTED_20, BarFlag.DARKEN_SKY);
			}else {
				if(entity.getType()==EntityType.WANDERING_TRADER) {
					bar2 = Bukkit.getServer().createBossBar(key, ChatColor.GOLD+"Nomad", BarColor.RED, BarStyle.SOLID);
				}else{
					bar2 = Bukkit.getServer().createBossBar(key, ChatColor.RED+"Fragment of Fenrir", BarColor.RED, BarStyle.SOLID);
				}
			}
			BossbarStorage.addBar(key);
			if(music) {
				bar2.addFlag(BarFlag.PLAY_BOSS_MUSIC);
			}
			bar2.setVisible(true);
			new BukkitRunnable(){
				@Override
				public void run(){
					try {
						if(!(this.isCancelled())) {
							if(Bukkit.getServer().getBossBar(key)==null) {
								this.cancel();
							}
							ArrayList<Player> players = new ArrayList<Player>();
							for(Player p : players) {
								Bukkit.getServer().getBossBar(key).removePlayer(p);
							}
							if(entity.isValid()) {
								if(!(entity.isDead())){
									if(!(entity.getHealth()<=0)) {
										Bukkit.getServer().getBossBar(key).setProgress(entity.getHealth()/entity.getMaxHealth());
										Integer amt = plugin.getConfig().getInt("bosses.show_bar");
										for(Entity entity2 : entity.getNearbyEntities(amt, amt, amt)) {
											if(entity2 instanceof Player) {
												players.add((Player) entity2);
												Bukkit.getServer().getBossBar(key).addPlayer((Player) entity2);
											}
										}
									}else {
		//								Bukkit.getServer().getBossBar(key).setVisible(false);
										Bukkit.getServer().getBossBar(key).removeAll();
										Bukkit.getServer().removeBossBar(key);
										BossbarStorage.removeBar(key);
										this.cancel();
									}
								}else {
		//							Bukkit.getServer().getBossBar(key).setVisible(false);
									Bukkit.getServer().getBossBar(key).removeAll();
									Bukkit.getServer().removeBossBar(key);
									BossbarStorage.removeBar(key);
									this.cancel();
								}
							}else {
								Bukkit.getServer().getBossBar(key).removeAll();
								Bukkit.getServer().removeBossBar(key);
								BossbarStorage.removeBar(key);
								this.cancel();
							}
						}
					}catch(Exception ignored) {
						this.cancel();
					}
				}
			}.runTaskTimer(plugin, 10L, 10L);
		}
	}
}
