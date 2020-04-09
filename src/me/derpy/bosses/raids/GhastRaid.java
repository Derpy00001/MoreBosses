package me.derpy.bosses.raids;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.GameRule;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.World;
import org.bukkit.attribute.Attribute;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.AreaEffectCloud;
import org.bukkit.entity.Blaze;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.EvokerFangs;
import org.bukkit.entity.Fireball;
import org.bukkit.entity.Ghast;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.PigZombie;
import org.bukkit.entity.Player;
import org.bukkit.entity.WitherSkeleton;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.Plugin;
import org.bukkit.potion.PotionData;
import org.bukkit.potion.PotionType;

import me.derpy.bosses.Morebosses;
import me.derpy.bosses.items.ItemType;
import me.derpy.bosses.mobs.BossType;
import me.derpy.bosses.mobs.MobHandler;
import me.derpy.bosses.utilities.Random;
import me.derpy.bosses.utilities.Tagger;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;

public class GhastRaid implements Listener {
	private static Plugin plugin = Morebosses.getPlugin(Morebosses.class);
	private static final YamlConfiguration configuration = Morebosses.getConfigurationHandler()
			.openConfiguration("data.yml", Morebosses.getWorldHandler().GHAST_ARENA.getWorldFolder().getAbsolutePath());
	private static List<Player> playerList;
	private static Ghast ghastBoss;
	private static boolean arenaActive = false;
	private static boolean phaseSecond = false;
	private final static int timerMinutes = 30;
	private static int timerDisplaySeconds = 0;
	private static List<Integer> timerTaskIds = new ArrayList<Integer>();
	final static int minionLimit = 10;
	private static World startWorld = null;
	private static int multiplier;

	public static boolean isActive() {
		return arenaActive;
	}

	public static void endArena(boolean completed) {
		endTimers();
		if (!completed) {
			for (Player player : playerList) {
				if (player.isOnline()) {
					player.setHealth(0);
				}
			}
			ghastBoss.remove();

		} else {
			if (!ghastBoss.isDead()) {
				ghastBoss.setHealth(0);
			}
			for (Player player : playerList) {
				if (player.isOnline()) {
					player.playSound(player.getLocation(), Sound.AMBIENT_UNDERWATER_LOOP_ADDITIONS_RARE, 1, 1);
					// REWARD
					player.giveExp(12000);
					player.sendMessage(ChatColor.GOLD + "Returning in 1 minute");
					plugin.getServer().getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() {

						@Override
						public void run() {
							// TODO Auto-generated method stub
							for (Player player : playerList) {
								if (player.isOnline()) {
									player.setGameMode(GameMode.SURVIVAL);
									player.setFireTicks(0);
									for (ItemStack item : player.getInventory().getContents()) {
										if (item != null) {
											if (Tagger.hasRaidTag(item)) {
												player.getInventory().removeItem(item);
											}
										}
									}
									if (player.getBedSpawnLocation() == null) {
										player.teleport(startWorld.getSpawnLocation());
									} else {
										player.teleport(player.getBedSpawnLocation());
									}
								}
							}
						}

					}, 60 * 20);
				}
			}
		}
		phaseSecond = false;
		startWorld = null;
		playerList.clear();
		ghastBoss = null;
		for (Entity entity : Morebosses.getWorldHandler().GHAST_ARENA.getEntities()) {
			entity.remove();
		}
		arenaActive = false;
	}

	private static void endTimers() {
		if (timerTaskIds.size() > 0) {
			for (int id : timerTaskIds) {
				Bukkit.getServer().getScheduler().cancelTask(id);
			}
		}
		timerTaskIds.clear();
	}

	public static void startArena(List<Player> list, World world) throws Exception {
		if (!arenaActive) {
			arenaActive = true;
			playerList = list;
			startWorld = world;
			if (playerList.size() > 5) {
				multiplier = 5;
			} else {
				multiplier = playerList.size();
			}
			for (Player player : playerList) {
				player.setGameMode(GameMode.ADVENTURE);
				player.teleport(Morebosses.getWorldHandler().GHAST_ARENA.getSpawnLocation());
			}
			createMinions();
			createBoss();

			startTimer();
		} else {
			throw new Exception("Arena already in progress!");
		}
	}

	private static void updateDisplay() {
		for (Player player : playerList) {
			int seconds = timerDisplaySeconds;
			int minutes = 0;
			while (seconds > 60) {
				minutes++;
				seconds -= 60;
			}
			player.spigot().sendMessage(ChatMessageType.ACTION_BAR,
					new TextComponent(Integer.toString(minutes) + ":" + Integer.toString(seconds)));
		}
	}

	private static void startTimer() {
		timerDisplaySeconds = 60 * timerMinutes;
		int timerTaskId = Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(plugin, new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				timerDisplaySeconds--;
				updateDisplay();
			}

		}, 0, 20 * 1);
		timerTaskIds.add(timerTaskId);
		timerTaskId = Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() {
			@Override
			public void run() {
				for (Player player : playerList) {
					player.setHealth(0);
				}
			}
		}, (60 * timerMinutes) * 20);
		timerTaskIds.add(timerTaskId);
	}

	private static void createBoss() {
		try {
			MobHandler.spawnBossWithBar(getLocation("locations.ghast.spawn"), BossType.GHAST.getInterface());
			int timerTaskId = Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(plugin, new Runnable() {

				@Override
				public void run() {
					// TODO Auto-generated method stub
					ghastBoss.teleport(getBossLocation());
				}

			}, 0, 60 * 20);
			timerTaskIds.add(timerTaskId);
		} catch (CloneNotSupportedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@SuppressWarnings("deprecation")
	private static void activatePhase() {
		phaseSecond = true;
		for (Player player : playerList) {
			player.sendTitle(ChatColor.GOLD + ghastBoss.getCustomName(), ChatColor.RED + "Has Enraged!");
			player.sendMessage(ChatColor.RED + "The spikes heal the overlord");
		}
		int timerTaskId = Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(plugin, new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				for (Player player : playerList) {
					if (player.getGameMode() == GameMode.ADVENTURE) {
						Morebosses.getWorldHandler().GHAST_ARENA.spawnEntity(player.getLocation(),
								EntityType.EVOKER_FANGS);
					}
				}
			}

		}, 0, 6 * 20);
		timerTaskIds.add(timerTaskId);
	}

	private static void createMinions() {
		int timerTaskId = Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(plugin, new Runnable() {
			@Override
			public void run() {
				// TODO Auto-generated method stub
				int minionCount = 0;
				for (Entity entity : Morebosses.getWorldHandler().GHAST_ARENA.getEntitiesByClass(PigZombie.class)) {
					if (entity instanceof LivingEntity) {
						((LivingEntity) entity).damage(1);
						if (!entity.isDead()) {
							minionCount++;
						}
					}
				}
				for (int i = 1; i < 5; i++) {
					if (minionCount < (multiplier * minionLimit)) {
						PigZombie minion = (PigZombie) Morebosses.getWorldHandler().GHAST_ARENA
								.spawnEntity(getMinionSpawnLocation(), EntityType.PIG_ZOMBIE);
						minion.setAnger(Integer.MAX_VALUE);
						minion.setAngry(true);
					}
				}
			}

		}, 0, 20 * 30);
		timerTaskIds.add(timerTaskId);
	}

	@EventHandler
	private void onDamage(EntityDamageEvent e) {
		if (e.getEntity().getWorld() == Morebosses.getWorldHandler().GHAST_ARENA) {
			if (isActive()) {
				if (e.getEntity() instanceof Player) {
					if (playerList.size() > 0) {
						if (playerList.contains(e.getEntity())) {
							Player player = (Player) e.getEntity();
							if (player.getHealth() - e.getFinalDamage() <= 0) {
								e.setCancelled(true);
								player.setHealth(player.getAttribute(Attribute.GENERIC_MAX_HEALTH).getValue());
								player.setFoodLevel(20);
								player.setExp(0);
								player.setGameMode(GameMode.SPECTATOR);
								player.getWorld().strikeLightningEffect(player.getLocation());
								if (!player.getWorld().getGameRuleValue(GameRule.KEEP_INVENTORY)) {
									for (ItemStack itemStack : player.getInventory().getContents()) {
										if (itemStack != null) {
											player.getWorld().dropItemNaturally(player.getLocation(), itemStack);
											player.getInventory().removeItem(itemStack);
										}
									}
								}
								if (e.getCause() == DamageCause.VOID) {
									player.teleport(Morebosses.getWorldHandler().GHAST_ARENA.getSpawnLocation());
								}
							}
						}
					}
				} else if (e.getEntity() instanceof Ghast) {
					if (ghastBoss != null) {
						if (e.getEntity() == ghastBoss) {
							if (!phaseSecond) {
								if (ghastBoss.getHealth() <= ghastBoss.getAttribute(Attribute.GENERIC_MAX_HEALTH)
										.getValue()) {
									activatePhase();
								}
							}
							ghastBoss.teleport(getBossLocation());
						}
					}
				}
			}
		}
	}

	@EventHandler
	private void onDamageByEntity(EntityDamageByEntityEvent e) {
		if (e.getEntity().getWorld() == Morebosses.getWorldHandler().GHAST_ARENA) {
			if (isActive()) {
				if (e.getEntity() instanceof Player) {
					Player player = (Player) e.getEntity();
					if (player.getGameMode() != GameMode.SPECTATOR) {
						if (e.getDamager() instanceof EvokerFangs) {
							if (ghastBoss != null) {
								if (ghastBoss.getHealth() + e.getFinalDamage() > ghastBoss
										.getAttribute(Attribute.GENERIC_MAX_HEALTH).getValue()) {
									ghastBoss
											.setHealth(ghastBoss.getAttribute(Attribute.GENERIC_MAX_HEALTH).getValue());
								} else {
									ghastBoss.setHealth(ghastBoss.getHealth() + e.getFinalDamage());
								}
							}
						} else if (e.getDamager() instanceof Fireball) {
							Fireball projectile = (Fireball) e.getDamager();
							if (projectile.getShooter() == e.getEntity()) {
								e.setDamage(0);
							}
						}
					}
				} else if (e.getEntity() instanceof Ghast) {
					if (ghastBoss != null) {
						if (e.getEntity() == ghastBoss) {
							if (e.getDamager() instanceof Fireball) {
								e.setDamage(e.getDamage() * 1.5);
							}
						}
					}
				}
			}
		}
	}

	@EventHandler
	private void onDeath(EntityDeathEvent e) {
		if (e.getEntity().getWorld() == Morebosses.getWorldHandler().GHAST_ARENA) {
			if (isActive()) {
				if (e.getEntity() instanceof Ghast) {
					if (ghastBoss != null) {
						if (e.getEntity() == ghastBoss) {
							endArena(true);
						}
					}
				} else if (e.getEntity() instanceof Blaze) {
					e.getDrops().add(getFireCharge());
				} else if (e.getEntity() instanceof WitherSkeleton) {
					createCloud(e.getEntity().getLocation(), 0L);
					createCloud(e.getEntity().getLocation(), 20L);
					createCloud(e.getEntity().getLocation(), 40L);
				}
			}
		}
	}

	@EventHandler
	private void onUse(PlayerInteractEvent e) {
		if (e.getItem().isSimilar(ItemType.GHAST_TOTEM.getInterface().getFinalizedItem())) {
			if (e.getClickedBlock().getType() == Material.END_GATEWAY) {
				if (!isActive()) {
					createTeleportCloud(e.getClickedBlock().getLocation(), 10);
					e.getItem().setAmount(e.getItem().getAmount() - 1);
					Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() {

						@Override
						public void run() {
							// TODO Auto-generated method stub

							List<Player> players = new ArrayList<Player>();
							for (Entity entity : e.getClickedBlock().getWorld()
									.getNearbyEntities(e.getClickedBlock().getLocation(), 10, 10, 10)) {
								if (entity instanceof Player) {
									if (!entity.isDead()) {
										players.add((Player) entity);
									}
								}
							}
							try {
								startArena(players, e.getClickedBlock().getWorld());
							} catch (Exception e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
						}

					}, 20 * 10);
				} else {
					e.getPlayer().sendMessage(ChatColor.RED + "Arena is already in progress!");
				}

			}
		}
	}

	private static void createTeleportCloud(Location location, int time) {
		AreaEffectCloud cloud = (AreaEffectCloud) location.getWorld().spawnEntity(location,
				EntityType.AREA_EFFECT_CLOUD);
		cloud.setRadius(10);
		cloud.setFallDistance(1);
		cloud.setParticle(Particle.ENCHANTMENT_TABLE);
		cloud.setDuration(20 * 10);
	}

	private static void createCloud(Location location, long time) {
		plugin.getServer().getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() {
			public void run() {
				AreaEffectCloud cloud = (AreaEffectCloud) location.getWorld().spawnEntity(location,
						EntityType.AREA_EFFECT_CLOUD);
				cloud.setDuration(2);
				cloud.setParticle(Particle.TOTEM);
				cloud.setRadius(10);
				cloud.setBasePotionData(new PotionData(PotionType.INSTANT_HEAL));
			}
		}, time);
	}

	private static ItemStack getFireCharge() {
		ItemStack item = new ItemStack(Material.FIRE_CHARGE);
		ItemMeta meta = item.getItemMeta();
		meta.setDisplayName(ChatColor.RESET + "Fireball");
		meta.setLore(Arrays.asList(ChatColor.RESET + "" + ChatColor.RED + "Raid Item"));
		item.setItemMeta(meta);
		return Tagger.tagRaidItem(item);
	}

	private static Location getMinionSpawnLocation() {
		Set<String> keys = configuration.getConfigurationSection("location.ghast.minions").getKeys(false);
		String selectionKey = (String) Arrays.asList(keys.toArray()).get(Random.random(0, keys.size() - 1));
		return getLocation("locations.ghast.minions." + selectionKey);
	}

	private static Location getBossLocation() {
		Set<String> keys = configuration.getConfigurationSection("location.ghast.teleports").getKeys(false);
		String selectionKey = (String) Arrays.asList(keys.toArray()).get(Random.random(0, keys.size() - 1));
		return getLocation("locations.ghast.teleports." + selectionKey);
	}

	private static Location getLocation(String path) {
		return new Location(Morebosses.getWorldHandler().GHAST_ARENA, configuration.getDouble(path + ".x"),
				configuration.getDouble(path + ".y"), configuration.getDouble(path + ".z"),
				(float) configuration.getDouble(path + ".pitch"), (float) configuration.getDouble(path + ".yaw"));
	}
}
