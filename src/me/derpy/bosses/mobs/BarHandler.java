package me.derpy.bosses.mobs;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.NamespacedKey;
import org.bukkit.attribute.Attribute;
import org.bukkit.boss.BarColor;
import org.bukkit.boss.BarFlag;
import org.bukkit.boss.BarStyle;
import org.bukkit.boss.BossBar;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.plugin.java.JavaPlugin;

import me.derpy.bosses.Morebosses;
import me.derpy.bosses.mobs.interfaces.IBoss;
import me.derpy.bosses.mobs.interfaces.IHostile;
import me.derpy.bosses.mobs.interfaces.IRaid;
import me.derpy.bosses.mobs.interfaces.ITitle;
import me.derpy.bosses.utilities.Console;
import me.derpy.bosses.utilities.Random;

public class BarHandler {
	private BarColor color = BarColor.WHITE;
	private Map<NamespacedKey, LivingEntity> bosses = new HashMap<NamespacedKey, LivingEntity>();

	public BarHandler() {
		if (JavaPlugin.getPlugin(Morebosses.class).getConfig().getBoolean("bosses.bossbar.enabled")) {
			Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(JavaPlugin.getPlugin(Morebosses.class),
					new Runnable() {
						@Override
						public void run() {
							// TODO Auto-generated method stub
							try {
								double distance = JavaPlugin.getPlugin(Morebosses.class).getConfig()
										.getDouble("bosses.bossbar.distance");
								try {
									List<NamespacedKey> barSet = new ArrayList<NamespacedKey>(
											Morebosses.getBarHandler().getBars().size());
									barSet.addAll(Morebosses.getBarHandler().getBars().keySet());
									for (NamespacedKey key : barSet) {
										BossBar bar = Bukkit.getServer().getBossBar(key);
										LivingEntity entity = Morebosses.getBarHandler().getBars().get(key);

										if (!entity.isValid() || bar == null) {
											Morebosses.getBarHandler().removeBar(key);
										} else {
											double trueDistance = bar.getTitle().toLowerCase().contains("the overlord")
													? 500.0
													: distance;
											bar.setProgress(entity.getHealth()
													/ entity.getAttribute(Attribute.GENERIC_MAX_HEALTH).getValue());
											for (Entity nearbyEntity : entity.getNearbyEntities(trueDistance,
													trueDistance, trueDistance)) {
												if (nearbyEntity instanceof Player) {
													bar.addPlayer((Player) nearbyEntity);
												}
											}
											for (Player player : bar.getPlayers()) {
												if (!entity.getNearbyEntities(trueDistance, trueDistance, trueDistance)
														.contains(player)) {
													bar.removePlayer(player);
												}
											}
										}
									}
								} catch (Exception e) {
									e.printStackTrace();
								}
								;
							} catch (Exception e) {
								e.printStackTrace();
							}
							;
						}
					}, 10L, 10L);
		}
	}

	public LivingEntity spawnBarBoss(Location location, IBoss boss) throws CloneNotSupportedException {
		boss = boss.cloneBoss();
		boolean bossbarEnabled = JavaPlugin.getPlugin(Morebosses.class).getConfig()
				.getBoolean("bosses.bossbar.enabled");
		List<String> names = new ArrayList<String>();
		File nameFile = new File(
				JavaPlugin.getPlugin(Morebosses.class).getDataFolder().getAbsolutePath() + "\\names.txt");
		try {
			Scanner reader = new Scanner(nameFile);
			while (reader.hasNextLine()) {
				names.add(reader.nextLine());
			}
			reader.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			names.add("Amun");
			Console.error("Failed to find names.txt, creating file");
			Morebosses.getConfigurationHandler().checkFiles();
		}
		ITitle title;
		if (boss instanceof IRaid) {
			title = TitleType.OVERLORD.getTitle();
		} else {
			title = TitleType.getRandomTitle().getTitle();
		}
		String name = names.get(Random.random(0, names.size() - 1));
		boss.setMinions(boss.getMinions() + title.getMinions());
		boss.setSpeedMultiplier(boss.getSpeedMultiplier() * title.getSpeedMultiplier());
		boss.setHealthMultiplier(boss.getHealthMultiplier() * title.getHealthMultiplier());
		if (boss instanceof IHostile) {
			((IHostile) boss)
					.setDamageMultiplier(((IHostile) boss).getDamageMultiplier() * title.getDamageMultiplier());
		}
		boolean override = false;
		LivingEntity mob = MobHandler.spawnBoss(location, boss);
		mob.setCustomNameVisible(false);
		mob.setCustomName(name);
		if (title == TitleType.OVERLORD.getTitle()) {
			mob.setRemoveWhenFarAway(false);
			override = true;
		}
		if (bossbarEnabled || override) {
			if (mob != null) {
				NamespacedKey key = boss.generateRandomKey(name, title.getTitleName());
				Bukkit.getServer().createBossBar(key, name + " the " + title.getTitleName(), this.color, BarStyle.SOLID,
						BarFlag.PLAY_BOSS_MUSIC).setVisible(true);
				;
				mob.setMetadata("MoreBosses-BossUniqueId",
						new FixedMetadataValue(JavaPlugin.getPlugin(Morebosses.class), key.getKey().toString()));
				this.getBars().put(key, mob);
			}
		}
		return mob;
	}

	public Map<NamespacedKey, LivingEntity> getBars() {
		return this.bosses;
	}

	public void removeBar(NamespacedKey key) {
		try {
			Bukkit.getServer().getBossBar(key).removeAll();
			Bukkit.getServer().removeBossBar(key);
		} catch (Exception e) {
			e.printStackTrace();
		}
		;
		try {
			if (this.getBars().get(key).isValid()) {
				this.getBars().get(key).remove();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		;
		if (this.getBars().containsKey(key)) {
			this.getBars().remove(key);
		}
	}

	public void removeAllBars() {
		if (this.getBars().size() > 0) {
			for (NamespacedKey key : this.getBars().keySet()) {
				this.removeBar(key);
			}
		}
	}
}
