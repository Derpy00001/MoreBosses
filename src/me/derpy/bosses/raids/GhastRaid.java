package me.derpy.bosses.raids;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Ghast;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import me.derpy.bosses.Morebosses;
import me.derpy.bosses.mobs.BossType;
import me.derpy.bosses.mobs.MobHandler;

public class GhastRaid {
	private static Plugin plugin = Morebosses.getPlugin(Morebosses.class);
	private static final YamlConfiguration configuration = Morebosses.getConfigurationHandler()
			.openConfiguration("data.yml", Morebosses.getWorldHandler().GHAST_ARENA.getWorldFolder().getAbsolutePath());
	private static ArrayList<Player> playerList;
	private static Ghast ghastBoss;
	private static boolean arenaActive = false;
	private static boolean phaseSecond = false;
	private final static int timerMinutes = 30;
	private static int timerTaskId;
	final int minionLimit = 10;
	private static int multiplier;

	public static void startArena(ArrayList<Player> list) throws Exception {
		if (!arenaActive) {
			arenaActive = true;
			playerList = list;
			if (playerList.size() > 5) {
				multiplier = 5;
			} else {
				multiplier = playerList.size();
			}
			for (Player player : playerList) {
				player.setGameMode(GameMode.ADVENTURE);
				player.teleport(Morebosses.getWorldHandler().GHAST_ARENA.getSpawnLocation());
			}
			createBoss();

			startTimer();
		} else {
			throw new Exception("Arena already in progress!");
		}
	}

	private static void startTimer() {
		timerTaskId = Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() {
			public void run() {
				for (Player player : playerList) {
					player.setHealth(0);
				}
			}
		}, (60 * timerMinutes) * 20);
	}

	private static void createBoss() {
		try {
			MobHandler.spawnBossWithBar(getLocation("locations.ghast.spawn"), BossType.GHAST.getInterface());

		} catch (CloneNotSupportedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private static void startMinions() {

	}

	public static Location getLocation(String path) {
		return new Location(Morebosses.getWorldHandler().GHAST_ARENA, configuration.getDouble(path + ".x"),
				configuration.getDouble(path + ".y"), configuration.getDouble(path + ".z"),
				(float) configuration.getDouble(path + ".pitch"), (float) configuration.getDouble(path + ".yaw"));
	}
}
