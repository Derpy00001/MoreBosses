package me.derpy.bosses.raids;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.World;
import org.bukkit.boss.BarColor;
import org.bukkit.boss.BarFlag;
import org.bukkit.boss.BarStyle;
import org.bukkit.boss.KeyedBossBar;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.AreaEffectCloud;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Illager;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Mob;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.raid.RaidFinishEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionData;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.potion.PotionType;

import me.derpy.bosses.Morebosses;
import me.derpy.bosses.items.ItemType;
import me.derpy.bosses.utilities.Random;
import net.md_5.bungee.api.ChatColor;

public class GladiatorRaid extends BArena {
	private static volatile GladiatorRaid instance;

	private Location powerUpLocation;
	private Location bossLocation;
	private PotionType powerUpTypes[] = { PotionType.REGEN, PotionType.POISON, PotionType.STRENGTH,
			PotionType.TURTLE_MASTER, PotionType.WEAKNESS };
	private List<Location> minionLocations;

	private int minionSpawnAmount = 1;
	private int waveInt = 0;
	private boolean waveActive = false;
	private KeyedBossBar bossBar;

	private LivingEntity bossEntity;
	private List<Entity> activeMinions;
	private List<EntityType> availableMinionTypes;

	public GladiatorRaid() {
		this.minionLocations = new ArrayList<Location>();
		YamlConfiguration configuration = Morebosses.getConfigurationHandler()
				.openRaidConfiguration("GladiatorRaid.yml");
		this.bossLocation = this.getLocation("locations.king", configuration);
		this.powerUpLocation = this.getLocation("locations.powerup", configuration);
		for (Object key : Arrays
				.asList(configuration.getConfigurationSection("locations.minions").getKeys(false).toArray())) {
			if (key instanceof String) {
				this.minionLocations.add(this.getLocation("locations.minions." + key, configuration));
			}
		}
	}

	@Override
	public World getBossArenaWorld() {
		return Morebosses.getWorldHandler().COLOSSEUM_ARENA;
	}

	@Override
	protected void initalize() {
		this.activeMinions = new ArrayList<Entity>();
		this.availableMinionTypes = new ArrayList<EntityType>();
		this.waveActive = false;
		this.waveInt = 1;
		this.createBoss();
		for (Player player : this.getPlayers()) {
			player.sendMessage(ChatColor.GOLD
					+ "You must defeat every other gladiator to be declared the victor and recieve the spoils.");
		}
		this.addTimer(Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(this.PLUGIN, new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				for (Player player : getPlayers()) {
					player.sendMessage(ChatColor.GOLD + "BEGIN!");
				}
				createBossbar();
				beginWave();
				createPotions();
			}

		}, 20 * 10));
	}

	@Override
	protected void deinitalize(boolean completed) {
		this.activeMinions = null;
		this.bossEntity = null;
		if (this.bossBar != null) {
			this.bossBar.removeAll();
			Bukkit.getServer().removeBossBar(this.bossBar.getKey());
			this.bossBar = null;
		}
	}

	private void createBoss() {
		this.bossEntity = (LivingEntity) this.getBossArenaWorld().spawnEntity(this.bossLocation,
				EntityType.WANDERING_TRADER);
		bossEntity.setAI(false);
		bossEntity.setInvulnerable(true);
	}

	protected final void beginWave() {
		if (!this.waveActive) {
			this.waveActive = true;
			this.getBossArenaWorld().playSound(this.bossLocation, Sound.EVENT_RAID_HORN, 100, 1);
			if (this.waveInt < 1 || this.waveInt > 6) {
				this.waveInt = 1;
			}
			this.bossBar.setTitle("Wave " + Integer.toString(this.waveInt));
			this.updateBar(1, 1);
			switch (this.waveInt) {
			case 1:
				Random.addWeight(EntityType.PILLAGER, this.availableMinionTypes, 3);
				this.availableMinionTypes.add(EntityType.VINDICATOR);
				spawnMinions(5, 9);
				break;
			case 2:
				this.availableMinionTypes.add(EntityType.EVOKER);
				spawnMinions(8, 16);
				break;
			case 3:
				spawnMinions(16, 20);
				break;
			case 4:
				this.availableMinionTypes.add(EntityType.EVOKER);
				this.availableMinionTypes.add(EntityType.ILLUSIONER);
				spawnMinions(20, 25);
				break;
			case 5:
				this.availableMinionTypes.add(EntityType.RAVAGER);
				spawnMinions(25, 25);
				break;
			case 6:
				this.endArena(true);
			default:
				spawnMinions(8, 16);
			}
			this.bossBar.setProgress(1);
		}
	}

	protected final void endWave() {
		if (this.waveActive) {
			this.waveActive = false;

			this.waveInt++;
			this.addTimer(Bukkit.getScheduler().scheduleSyncDelayedTask(this.PLUGIN, new Runnable() {

				@Override
				public void run() {
					// TODO Auto-generated method stub
					beginWave();
				}

			}, 20 * 5));
		}
	}

	public final int getWave() {
		return this.waveInt;
	}

	private void createPotions() {
		this.addTimer(Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(this.PLUGIN, new Runnable() {

			@SuppressWarnings("deprecation")
			@Override
			public void run() {
				// TODO Auto-generated method stub
				if (Random.random(0.5)) {
					for (Player player : getPlayers()) {
						player.playSound(player.getLocation(), Sound.ENTITY_ELDER_GUARDIAN_CURSE, 1, 0.5F);
						player.sendTitle(ChatColor.GOLD + "The Emperor", ChatColor.GOLD + "has sent a gift");
						AreaEffectCloud powerUp = (AreaEffectCloud) getBossArenaWorld().spawnEntity(powerUpLocation,
								EntityType.AREA_EFFECT_CLOUD);
						powerUp.setDuration(10 * 20);
						powerUp.setParticle(Particle.TOTEM);
						powerUp.setRadius(5);
						PotionType potionType = Arrays.asList(powerUpTypes)
								.get(Random.random(0, powerUpTypes.length - 1));
						powerUp.setBasePotionData(new PotionData(potionType, false, potionType.isUpgradeable()));
					}
				}
				List<Entity> minions = new ArrayList<Entity>();
				if (activeMinions.size() > 0) {
					minions.addAll(activeMinions);
					for (Entity entity : minions) {
						if (!entity.isValid()) {
							activeMinions.remove(entity);
							updateBar(activeMinions.size(), minionSpawnAmount);
						}
					}
					if (activeMinions.size() == 0) {
						endWave();
					}
					minions.clear();
				} else {
					endWave();
				}
			}

		}, 0, 40 * 20));
	}

	private void createBossbar() {
		this.bossBar = Bukkit
				.createBossBar(
						NamespacedKey.minecraft(this.getBossArenaWorld().getName().toLowerCase()
								.replaceAll("[^a-z0-9]", "_").replace(' ', '-') + UUID.randomUUID()),
						"Gladiator", BarColor.YELLOW, BarStyle.SOLID, BarFlag.PLAY_BOSS_MUSIC);
		for (Player player : this.getPlayers()) {
			this.bossBar.addPlayer(player);
		}
		this.bossBar.setVisible(true);
	}

	private void updateBar(double newAmount, double defaultAmount) {
		double calculatedAmount = newAmount / defaultAmount;
		this.bossBar.setProgress(calculatedAmount);
		if (calculatedAmount >= 0.7) {
			this.bossBar.setColor(BarColor.GREEN);
		} else {
			if (calculatedAmount >= 0.4 && calculatedAmount < 0.7) {
				this.bossBar.setColor(BarColor.YELLOW);
			} else {
				if (calculatedAmount < 0.4) {
					this.bossBar.setColor(BarColor.RED);
				}
			}
		}
	}

	private void spawnMinions(int min, int max) {
		int spawnAmount = Random.random(min, max);
		for (int i = 0; i < spawnAmount; i++) {
			LivingEntity entity = (LivingEntity) this.getBossArenaWorld().spawnEntity(this.getMinionSpawnLocation(),
					this.availableMinionTypes.get(Random.random(0, this.availableMinionTypes.size() - 1)));
			if (entity instanceof Mob) {
				((Mob) entity).setTarget(this.getPlayers().get(Random.random(0, this.getPlayers().size() - 1)));
				this.activeMinions.add(entity);
			}
		}
		this.minionSpawnAmount = spawnAmount;
	}

	private Location getMinionSpawnLocation() {
		return this.minionLocations.get(Random.random(0, this.minionLocations.size() - 1));
	}

	@EventHandler
	protected void onDeathGladiator(EntityDeathEvent e) {
		if (e.getEntity().getWorld() == this.getBossArenaWorld()) {
			if (this.waveActive) {
				if (this.activeMinions.size() > 0) {
					if (this.activeMinions.contains(e.getEntity())) {
						e.getDrops().clear();
						e.setDroppedExp(0);
						this.activeMinions.remove(e.getEntity());
						this.updateBar(this.activeMinions.size(), this.minionSpawnAmount);
						if (this.activeMinions.size() == 0) {
							this.endWave();
						}
					}
				}
			}
		}
	}

	@EventHandler
	protected void onInteractGladiator(PlayerInteractEvent e) {
		if (e.getPlayer().getWorld() == this.getBossArenaWorld()) {
			if (e.getAction() == Action.PHYSICAL) {
				if (e.getClickedBlock().getType() == Material.BIRCH_PRESSURE_PLATE) {
					e.getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, 20 * 10, 1));
					e.getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.NIGHT_VISION, 20 * 10, 1));
					e.getPlayer().playSound(e.getPlayer().getLocation(), Sound.BLOCK_BELL_RESONATE, 5, 1);
					for (Entity entity : this.getBossArenaWorld().getEntities()) {
						if (entity instanceof Illager) {
							((Illager) entity).addPotionEffect(new PotionEffect(PotionEffectType.GLOWING, 20 * 10, 0));
						}
					}
				}
			}
		}
	}

	@EventHandler
	protected void onInteractEntityGladiator(PlayerInteractEntityEvent e) {
		if (this.getHandItem(ItemType.CHALLENGER_TOKEN.getInterface().getFinalizedItem(), e.getPlayer()) != null) {
			if (e.getRightClicked() != null) {
				if (e.getRightClicked().getType() == EntityType.WANDERING_TRADER) {
					e.setCancelled(true);
					if (e.getPlayer().hasPotionEffect(PotionEffectType.HERO_OF_THE_VILLAGE)) {
						if (!this.isActive()) {
							ItemStack token = this.getHandItem(
									ItemType.CHALLENGER_TOKEN.getInterface().getFinalizedItem(), e.getPlayer());
							token.setAmount(token.getAmount() - 1);
							this.createTeleportCloud(e.getRightClicked().getLocation(), 10);
							this.startArena(e.getRightClicked().getLocation(), new int[] { 10, 10, 10 },
									e.getRightClicked().getWorld(), 10, 10);
						} else {
							e.getPlayer().sendMessage(ChatColor.RED + "Arena is already in progress!\nTime Remaining: "
									+ this.getTimerString());
						}
					} else {
						e.getPlayer()
								.sendMessage(ChatColor.RED + "Please come back when you are the hero of a village.");
					}
				}
			}
		}
	}

	@EventHandler
	protected void onRaidFinishedGladiator(RaidFinishEvent e) {
		if (e.getWinners().size() > 0) {
			for (Player player : e.getWinners()) {
				if (player.getInventory().firstEmpty() == -1) {
					player.getWorld().dropItemNaturally(player.getLocation(),
							ItemType.CHALLENGER_TOKEN.getInterface().getFinalizedItem());
				} else {
					player.getInventory().addItem(ItemType.CHALLENGER_TOKEN.getInterface().getFinalizedItem());
				}
			}
		}
	}

	public static GladiatorRaid getInstance() {
		if (instance == null) {
			instance = new GladiatorRaid();
		}
		return instance;
	}
}
