package me.derpy.bosses.raids;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.attribute.Attribute;
import org.bukkit.configuration.file.YamlConfiguration;
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
import org.bukkit.event.EventPriority;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import me.derpy.bosses.Morebosses;
import me.derpy.bosses.items.ItemType;
import me.derpy.bosses.mobs.BossType;
import me.derpy.bosses.mobs.MobHandler;
import me.derpy.bosses.utilities.Random;
import me.derpy.bosses.utilities.Tagger;
import net.md_5.bungee.api.ChatColor;

public class GhastRaid extends BArena {
	private static volatile GhastRaid instance;

	private List<Location> bossLocations;
	private List<Location> minionLocations;

	private final GhastRaid RAID;
	private final int MINION_MAX = 25;

	private Ghast bossEntity;
	private boolean secondPhase = false;

	public GhastRaid() {
		this.RAID = this;
		this.bossLocations = new ArrayList<Location>();
		this.minionLocations = new ArrayList<Location>();
		YamlConfiguration configuration = Morebosses.getConfigurationHandler().openRaidConfiguration("GhastRaid.yml");
		for (Object key : Arrays
				.asList(configuration.getConfigurationSection("locations.ghast.teleports").getKeys(false).toArray())) {
			if (key instanceof String) {
				this.bossLocations.add(this.getLocation("locations.ghast.teleports." + key, configuration));
			}
		}
		for (Object key : Arrays
				.asList(configuration.getConfigurationSection("locations.ghast.minions").getKeys(false).toArray())) {
			if (key instanceof String) {
				this.minionLocations.add(this.getLocation("locations.ghast.minions." + key, configuration));
			}
		}
	}

	@Override
	public World getBossArenaWorld() {
		return Morebosses.getWorldHandler().GHAST_ARENA;
	}

	@Override
	protected void initalize() {
		this.createMinions();
		this.createBoss();
	}

	@Override
	protected void deinitalize(boolean completed) {
		this.secondPhase = false;
		this.bossEntity = null;
	}

	private void createBoss() {
		try {
			this.bossEntity = (Ghast) MobHandler.spawnBossWithBar(
					this.getLocation("locations.ghast.spawn",
							Morebosses.getConfigurationHandler().openBossConfiguration("GhastRaid.yml")),
					BossType.GHAST.getInterface());
			this.addTimer(Bukkit.getScheduler().scheduleSyncRepeatingTask(this.PLUGIN, new Runnable() {

				@Override
				public void run() {
					// TODO Auto-generated method stub
					bossEntity.teleport(getBossLocation());
				}

			}, 0, 20 * 60));
		} catch (CloneNotSupportedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void createMinions() {
		this.addTimer(Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(this.PLUGIN, new Runnable() {
			@Override
			public void run() {
				// TODO Auto-generated method stub
				int minionCount = 0;
				for (Entity entity : RAID.getBossArenaWorld().getEntitiesByClasses(PigZombie.class)) {
					if (entity instanceof LivingEntity) {
						((LivingEntity) entity).damage(1);
						if (!entity.isDead()) {
							minionCount++;
						}
					}
				}
				for (int i = 1; i < 5; i++) {
					if (minionCount < MINION_MAX) {
						PigZombie minion = (PigZombie) RAID.getBossArenaWorld().spawnEntity(getMinionSpawnLocation(),
								EntityType.PIG_ZOMBIE);
						minion.setAngry(true);
						minion.setAnger(Integer.MAX_VALUE);
						if (Random.random(0.1)) {
							RAID.getBossArenaWorld().spawnEntity(getMinionSpawnLocation(), EntityType.BLAZE);
						}
						if (Random.random(0.05)) {
							RAID.getBossArenaWorld().spawnEntity(getMinionSpawnLocation(), EntityType.WITHER_SKELETON);
						}
					}
				}
			}

		}, 0, 20 * 30));
	}

	@SuppressWarnings("deprecation")
	private void activatePhase() {
		if (!this.secondPhase) {
			this.secondPhase = true;
			for (Player player : this.getPlayers()) {
				player.sendTitle(ChatColor.GOLD + this.bossEntity.getCustomName(), ChatColor.RED + "Has Enraged!");
				player.sendMessage(ChatColor.RED + "The spikes heal the overlord");
			}
			this.addTimer(Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(this.PLUGIN, new Runnable() {

				@Override
				public void run() {
					// TODO Auto-generated method stub
					for (Player player : getPlayers()) {
						if (player.getGameMode() == GameMode.ADVENTURE) {
							getBossArenaWorld().spawnEntity(player.getLocation(), EntityType.EVOKER_FANGS);
						}
					}
				}

			}, 0, 2 * 20));
		}
	}

	private boolean isEnraged() {
		return this.secondPhase;
	}

	@EventHandler(priority = EventPriority.HIGH)
	protected void onDamageByEntityGhast(EntityDamageByEntityEvent e) {
		if (e.getEntity().getWorld() == this.getBossArenaWorld()) {
			if (isActive()) {
				if (e.getEntity() instanceof Player) {
					Player player = (Player) e.getEntity();
					if (player.getGameMode() != GameMode.SPECTATOR) {
						if (e.getDamager() instanceof EvokerFangs) {
							if (this.bossEntity != null) {
								if (this.bossEntity.getHealth() + e.getFinalDamage() > this.bossEntity
										.getAttribute(Attribute.GENERIC_MAX_HEALTH).getValue()) {
									this.bossEntity.setHealth(
											this.bossEntity.getAttribute(Attribute.GENERIC_MAX_HEALTH).getValue());
								} else {
									this.bossEntity.setHealth(this.bossEntity.getHealth() + e.getFinalDamage());
								}
							}
						} else if (e.getDamager() instanceof Fireball) {
							Fireball projectile = (Fireball) e.getDamager();
							if (((Entity) projectile.getShooter()).getType() == EntityType.PLAYER) {
								e.setDamage(0);
							}
						}
					}
				} else if (e.getEntity() instanceof Ghast) {
					if (this.bossEntity != null) {
						if (e.getEntity() == this.bossEntity) {
							if (e.getDamager() instanceof Fireball) {
								e.setDamage(e.getDamage() * 1.5);
							}
							if (!this.isEnraged()) {
								if (this.bossEntity.getHealth() <= this.bossEntity
										.getAttribute(Attribute.GENERIC_MAX_HEALTH).getValue() / 2) {
									this.activatePhase();
								}
							}
							this.bossEntity.teleport(this.getBossLocation());
						}
					}
				}
			}
		}
	}

	@EventHandler
	protected void onDeathGhast(EntityDeathEvent e) {
		if (e.getEntity().getWorld() == Morebosses.getWorldHandler().GHAST_ARENA) {
			if (isActive()) {
				if (e.getEntity() instanceof Ghast) {
					if (this.bossEntity != null) {
						if (e.getEntity() == this.bossEntity) {
							endArena(true);
						}
					}
				} else if (e.getEntity() instanceof Blaze) {
					e.getDrops().add(getFireCharge());
				} else if (e.getEntity() instanceof WitherSkeleton) {
					createCloud(e.getEntity().getLocation(), 0L, 2);
					createCloud(e.getEntity().getLocation(), 20L, 2);
					createCloud(e.getEntity().getLocation(), 40L, 2);
				}
			}
		}
	}

	@EventHandler
	protected void onUseGhast(PlayerInteractEvent e) {
		if (e.getAction() == Action.RIGHT_CLICK_BLOCK || e.getAction() == Action.RIGHT_CLICK_AIR) {
			ItemStack item = this.getHandItem(ItemType.GHAST_TOTEM.getInterface().getFinalizedItem(), e.getPlayer());
			if (item != null) {
				if (e.getClickedBlock() != null) {
					if (e.getClickedBlock().getType() == Material.END_GATEWAY) {
						if (!isActive()) {
							this.createTeleportCloud(e.getClickedBlock().getLocation(), 10);
							item.setAmount(item.getAmount() - 1);
							this.startArena(e.getClickedBlock().getLocation(), new int[] { 10, 10, 10 },
									e.getClickedBlock().getWorld(), 5, 10);
						} else {
							e.getPlayer().sendMessage(ChatColor.RED + "Arena is already in progress!\nTime Remaining: "
									+ this.getTimerString());
						}

					}
				}
			} else {
				item = this.getHandItem(getFireCharge(), e.getPlayer());
				if (item != null) {
					item.setAmount(item.getAmount() - 1);
					Fireball fireball = e.getPlayer().launchProjectile(Fireball.class);
					fireball.setShooter(e.getPlayer());
					fireball.setYield(5);
					;
				}
			}
		}
	}

	private Location getMinionSpawnLocation() {
		return this.minionLocations.get(Random.random(0, this.minionLocations.size() - 1));
	}

	private Location getBossLocation() {
		return this.bossLocations.get(Random.random(0, this.bossLocations.size() - 1));
	}

	private static ItemStack getFireCharge() {
		ItemStack item = new ItemStack(Material.FIRE_CHARGE);
		ItemMeta meta = item.getItemMeta();
		meta.setDisplayName(ChatColor.RESET + "Fireball");
		meta.setLore(Arrays.asList(ChatColor.RESET + "" + ChatColor.RED + "Raid Item"));
		item.setItemMeta(meta);
		return Tagger.tagRaidItem(item);
	}

	public static GhastRaid getInstance() {
		if (instance == null) {
			instance = new GhastRaid();
		}
		return instance;
	}
}
