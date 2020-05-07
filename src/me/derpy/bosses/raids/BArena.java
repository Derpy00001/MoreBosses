package me.derpy.bosses.raids;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Color;
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
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;
import org.bukkit.potion.PotionData;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.potion.PotionType;

import me.derpy.bosses.Morebosses;
import me.derpy.bosses.items.ItemType;
import me.derpy.bosses.utilities.Tagger;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;

public class BArena implements Listener {
	// SETTINGS
	private final int TIMER_MINUTES = 30;
	private final String INVENTORY_FULL = ChatColor.RED + "Inventory full!\nRewards dropped on the floor.";
	private final String INVENTORY_ADDED = ChatColor.GREEN + "Rewards added to inventory!";

	protected final Plugin PLUGIN = Morebosses.getPlugin(Morebosses.class);
	private final Material DEATH_BARRIER = Material.BARRIER;

	private List<Player> playerList;
	private List<Integer> timerTaskIds = new ArrayList<Integer>();
	private World startWorld;

	private boolean arenaActive = false;
	private int timerDisplaySeconds = 0;

	public final boolean startArena(Location location, int[] xyzRange, World world, int confusionSeconds,
			int teleportDelay) {
		// TODO Auto-generated method stub
		// startArena(players, e.getClickedBlock().getWorld(), 5);
		if (!this.arenaActive) {
			this.arenaActive = true;
			Bukkit.getScheduler().scheduleSyncDelayedTask(this.PLUGIN, new Runnable() {

				@Override
				public void run() {
					// TODO Auto-generated method stub
					playerList = new ArrayList<Player>();
					for (Entity entityPlayer : world.getNearbyEntities(location, xyzRange[0], xyzRange[1], xyzRange[2],
							(Entity e) -> e.getType() == EntityType.PLAYER)) {
						if (!entityPlayer.isDead()) {
							playerList.add((Player) entityPlayer);
						}
					}
					if (playerList.size() > 0) {
						startWorld = world;
						for (Player player : playerList) {
							player.setGameMode(GameMode.ADVENTURE);
							Location spawnLocation = getBossArenaWorld().getSpawnLocation();
							spawnLocation.setY(spawnLocation.getY() + 1);
							player.teleport(spawnLocation);
							player.addPotionEffect(
									new PotionEffect(PotionEffectType.CONFUSION, 20 * confusionSeconds, 255));
						}
						initalize();
						startTimer();
					} else {
						arenaActive = false;
						playerList = null;
					}
				}

			}, 20 * teleportDelay);
			return true;
		} else {
			return false;
		}

	}

	protected void initalize() {
		// TODO Auto-generated method stub

	}

	protected void deinitalize(boolean completed) {

	}

	public final void addTimer(int i) {
		this.timerTaskIds.add(i);
	}

	public final void endArena(boolean completed) {
		// TODO Auto-generated method stub
		this.endTimers();
		this.deinitalize(completed);
		if (!completed) {
			for (Player player : this.playerList) {
				if (player.isOnline()) {
					if (player.getGameMode() != GameMode.SPECTATOR) {
						this.killPlayer(player, false, false, false);
					}
				}
			}
		} else {
			for (Player player : this.playerList) {
				if (player.isOnline()) {
					player.playSound(player.getLocation(), Sound.AMBIENT_UNDERWATER_LOOP_ADDITIONS_RARE, 1, 1);
					if (player.getInventory().firstEmpty() == -1) {
						player.getWorld().dropItem(player.getLocation(),
								ItemType.SPOILS_TIER5.getInterface().getFinalizedItem());
						player.sendMessage(this.INVENTORY_FULL);
					} else {
						player.getInventory().addItem(ItemType.SPOILS_TIER5.getInterface().getFinalizedItem());
						player.sendMessage(this.INVENTORY_ADDED);
					}
					player.giveExp(12000);

				}
			}
		}
		for (Entity entity : this.getBossArenaWorld().getEntities()) {
			if (entity instanceof LivingEntity) {
				if (!(entity instanceof Player)) {
					entity.remove();
				} else {
					entity.sendMessage(ChatColor.GOLD + "Returning in 30 seconds");
				}
			}
		}
		this.PLUGIN.getServer().getScheduler().scheduleSyncDelayedTask(this.PLUGIN, new Runnable() {

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
				arenaActive = false;
				playerList.clear();
				startWorld = null;
				for (Entity entity : getBossArenaWorld().getEntities()) {
					if (!(entity instanceof Player)) {
						entity.remove();
					}
				}
			}

		}, 30 * 20);
	}

	public World getBossArenaWorld() {
		return null;
	}

	private final void endTimers() {
		// TODO Auto-generated method stub
		if (this.timerTaskIds.size() > 0) {
			for (int id : this.timerTaskIds) {
				Bukkit.getServer().getScheduler().cancelTask(id);
			}
		}
		timerTaskIds.clear();
	}

	public final boolean isActive() {
		// TODO Auto-generated method stub
		return this.arenaActive;
	}

	public final String getTimerString() {
		// TODO Auto-generated method stub
		int seconds = this.timerDisplaySeconds;
		int minutes = 0;
		String secondsString = "";
		while (seconds > 60) {
			minutes++;
			seconds -= 60;
		}
		if (seconds < 10) {
			secondsString = "0" + Integer.toString(seconds);
		} else {
			secondsString = Integer.toString(seconds);
		}
		return minutes == 0 ? secondsString : Integer.toString(minutes) + ":" + secondsString;
	}

	private final void updateDisplay() {
		// TODO Auto-generated method stub
		for (Player player : this.playerList) {
			if (player.isOnline()) {
				player.spigot().sendMessage(ChatMessageType.ACTION_BAR, new TextComponent(this.getTimerString()));
			}
		}
	}

	private final void startTimer() {
		// TODO Auto-generated method stub
		this.timerDisplaySeconds = 60 * this.TIMER_MINUTES;
		int timerTaskId = Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(this.PLUGIN, new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				timerDisplaySeconds--;
				updateDisplay();
			}

		}, 0, 20 * 1);
		this.timerTaskIds.add(timerTaskId);
		timerTaskId = Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(this.PLUGIN, new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				endArena(false);
			}
		}, (60 * this.TIMER_MINUTES) * 20);
		timerTaskIds.add(timerTaskId);
	}

	protected final void createTeleportCloud(Location location, int time) {
		// TODO Auto-generated method stub
		AreaEffectCloud cloud = (AreaEffectCloud) location.getWorld().spawnEntity(location,
				EntityType.AREA_EFFECT_CLOUD);
		cloud.setRadius(10);
		cloud.setFallDistance(0);
		cloud.setColor(Color.RED);
		;
		cloud.setDuration(20 * 10);
	}

	protected final void createCloud(Location location, long delay, int Duration) {
		PLUGIN.getServer().getScheduler().scheduleSyncDelayedTask(PLUGIN, new Runnable() {
			public void run() {
				AreaEffectCloud cloud = (AreaEffectCloud) location.getWorld().spawnEntity(location,
						EntityType.AREA_EFFECT_CLOUD);
				cloud.setDuration(Duration);
				cloud.setParticle(Particle.TOTEM);
				cloud.setRadius(10);
				cloud.setBasePotionData(new PotionData(PotionType.INSTANT_HEAL));
			}
		}, delay);
	}

	protected final void killPlayer(Player player, boolean teleport, boolean logged, boolean check) {
		// TODO Auto-generated method stub
		player.getWorld().strikeLightningEffect(player.getLocation());
		if (!logged) {
			player.setHealth(player.getAttribute(Attribute.GENERIC_MAX_HEALTH).getValue());
			player.setFoodLevel(20);
			player.setExp(0);
			player.setGameMode(GameMode.SPECTATOR);

			if (!player.getWorld().getGameRuleValue(GameRule.KEEP_INVENTORY)) {
				for (ItemStack itemStack : player.getInventory().getContents()) {
					if (itemStack != null) {
						player.getWorld().dropItemNaturally(player.getLocation(), itemStack);
						player.getInventory().remove(itemStack);
					}
				}
			}
			if (teleport) {
				player.teleport(player.getWorld().getSpawnLocation());
			}
			if (check) {
				boolean allDead = true;
				if (this.playerList.size() > 0) {
					for (Player plr : this.playerList) {
						if (plr.getGameMode() == GameMode.ADVENTURE) {
							allDead = false;
						}
					}
				}
				if (allDead) {
					this.endArena(false);
				}
			}
		} else {
			player.setGameMode(GameMode.SURVIVAL);
			player.setHealth(0);
		}
	}

	protected Material getDeathBarrier() {
		return DEATH_BARRIER;
	}

	protected final ItemStack getHandItem(ItemStack item, Player player) {
		if (player.getInventory().getItemInMainHand() != null) {
			if (player.getInventory().getItemInMainHand().isSimilar(item)) {
				return player.getInventory().getItemInMainHand();
			}
		}
		if (player.getInventory().getItemInOffHand() != null) {
			if (player.getInventory().getItemInOffHand().isSimilar(item)) {
				return player.getInventory().getItemInOffHand();
			}
		}
		return null;
	}

	protected final ItemStack getHandItem(Material material, Player player) {
		if (player.getInventory().getItemInMainHand() != null) {
			if (player.getInventory().getItemInMainHand().getType() == material) {
				return player.getInventory().getItemInMainHand();
			}
		}
		if (player.getInventory().getItemInOffHand() != null) {
			if (player.getInventory().getItemInOffHand().getType() == material) {
				return player.getInventory().getItemInOffHand();
			}
		}
		return null;
	}

	protected final List<Player> getPlayers() {
		return this.playerList;
	}

	protected final Location getLocation(String path, YamlConfiguration config) {
		return new Location(this.getBossArenaWorld(), config.getDouble(path + ".x"), config.getDouble(path + ".y"),
				config.getDouble(path + ".z"), (float) config.getDouble(path + ".pitch"),
				(float) config.getDouble(path + ".yaw"));
	}

	@EventHandler
	public final void onPlayerLeaveArenaDefault(PlayerQuitEvent e) {
		try {
			if (this.playerList.size() > 0) {
				if (this.playerList.contains(e.getPlayer())) {
					this.killPlayer(e.getPlayer(), false, true, true);
					this.playerList.remove(e.getPlayer());
				}
			}
		} catch (Exception ee) {
		}
	}

	@EventHandler
	public final void onDamageArenaDefault(EntityDamageEvent e) {
		if (e.getEntity().getWorld() == this.getBossArenaWorld()) {
			if (isActive()) {
				if (e.getEntity() instanceof Player) {
					if (this.playerList.size() > 0) {
						if (this.getPlayers().contains(e.getEntity())) {
							Player player = (Player) e.getEntity();
							Location location = new Location(player.getWorld(), player.getLocation().getX(),
									player.getLocation().getY() - 1, player.getLocation().getZ());
							if (location.getBlock().getType() == this.getDeathBarrier()) {
								e.setCancelled(true);
								killPlayer(player, true, false, true);
							} else {
								if (player.getHealth() - e.getFinalDamage() <= 0) {
									if (this.getHandItem(Material.TOTEM_OF_UNDYING, player) == null) {
										e.setCancelled(true);
										if (e.getCause() == DamageCause.VOID) {
											killPlayer(player, true, false, true);
										} else {
											killPlayer(player, false, false, true);
										}
									}
								}
							}
						}
					}
				}
			}
		}
	}
}
