package me.derpy.bosses.raids;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.GameRule;
import org.bukkit.World;
import org.bukkit.WorldCreator;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import me.derpy.bosses.utilities.Console;

public class WorldHandler {
	public final World GHAST_ARENA;
	public final World COLOSSEUM_ARENA;
	private List<World> worlds = new ArrayList<World>();

	public WorldHandler() {
		this.GHAST_ARENA = this.registerWorldFromType("GHAST_ARENA");
		this.COLOSSEUM_ARENA = this.registerWorldFromType("COLOSSEUM_ARENA");
		for (World world : worlds) {
			world.setAutoSave(false);
			world.setKeepSpawnInMemory(false);
			world.setGameRule(GameRule.DO_MOB_SPAWNING, false);
			world.setGameRule(GameRule.SPECTATORS_GENERATE_CHUNKS, false);
			world.setGameRule(GameRule.MOB_GRIEFING, false);
			world.setGameRule(GameRule.NATURAL_REGENERATION, false);
			world.setGameRule(GameRule.DO_DAYLIGHT_CYCLE, false);
			world.setGameRule(GameRule.DO_WEATHER_CYCLE, false);
			for (File file : world.getWorldFolder().listFiles()) {
				if (file.getName().equals("data.yml")) {
					YamlConfiguration configuration = YamlConfiguration.loadConfiguration(file);
					world.setTime(configuration.getLong("time"));
					if (configuration.getBoolean("border.enabled")) {
						world.getWorldBorder().setCenter(world.getSpawnLocation());
						world.getWorldBorder().setSize(configuration.getDouble("border.size"));
						world.getWorldBorder().setWarningDistance(configuration.getInt("border.warning-distance"));
					}
				}
			}
		}

	}

	public List<World> getArenas() {
		return Collections.unmodifiableList(this.worlds);
	}

	public void unregisterAllArenas() {
		if (this.worlds.size() > 0) {
			for (World world : this.worlds) {
				this.unregisterArena(world);
			}
		}
	}

	public void unregisterArena(World world) {
		if (world.getPlayers().size() > 0) {
			for (Player player : world.getPlayers()) {
				player.teleport(Bukkit.getServer().getWorlds().get(0).getSpawnLocation());
			}
		}
		Console.print("Unloading World: " + world.getName());
		if (worlds.contains(world)) {
			worlds.remove(world);
		}
		Bukkit.getServer().unloadWorld(world, false);
	}

	private World registerWorldFromType(String name) {
		for (File file : Bukkit.getServer().getWorldContainer().listFiles()) {
			if (file.isDirectory()) {
				for (File worldFile : file.listFiles()) {
					if (worldFile.getName().equals("data.yml")) {
						String typeString = YamlConfiguration.loadConfiguration(worldFile).getString("type");
						if (typeString.equals(name)) {
							World world = Bukkit.getServer().createWorld(new WorldCreator(file.getName()));
							if (!this.worlds.contains(world)) {
								this.worlds.add(world);
							}
							return world;
						}
					}
				}
			}
		}
		return null;
	}
}
