package me.derpy.bosses.utilities;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.List;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.craftbukkit.libs.org.apache.commons.io.IOUtils;

import me.derpy.bosses.Morebosses;

public class ConfigurationHandler {
	private Morebosses plugin = Morebosses.getPlugin(Morebosses.class);
	private File nameFile;
	private String jarPath = "src/me/derpy/bosses/resources/";

	public ConfigurationHandler() {
		this.checkFiles();
	}

	public void checkFiles() {
		final List<String> WORLDS = Arrays.asList("Colosseum", "Ghast");
		final List<String> BOSS_CONFIGS = Arrays.asList("Tier1/Bee.yml", "Tier1/Drowned.yml", "Tier1/Skeleton.yml",
				"Tier1/Zombie.yml", "Tier2/Blaze.yml", "Tier2/Creeper.yml", "Tier2/Stray.yml", "Tier2/Guardian.yml",
				"Tier2/Phantom.yml", "Tier3/Slime.yml", "Tier3/WitherSkeleton.yml", "Tier4/Pigman.yml",
				"Tier4/MagmaCube.yml", "Tier4/Ravager.yml");
		final List<String> ENCHANT_CONFIGS = Arrays.asList("bleed.yml", "ember.yml", "fleet.yml", "lifesteal.yml",
				"replenish.yml");
		final List<String> RAID_CONFIGS = Arrays.asList("GladiatorRaid.yml", "GhastRaid.yml");
		if (!(plugin.getDataFolder().exists())) {
			plugin.getDataFolder().mkdir();
		}
		this.nameFile = new File(plugin.getDataFolder().getAbsolutePath() + "/names.txt");
		if (!this.nameFile.exists()) {
			try {
				this.copyFile(this.nameFile, false);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (URISyntaxException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		for (String configs : BOSS_CONFIGS) {
			File configFile = new File(plugin.getDataFolder().getAbsolutePath() + "/Bosses/" + configs);
			if (!configFile.exists()) {
				try {
					String splitName = configs.split("/")[1];
//					configFile = new File(plugin.getDataFolder().getAbsolutePath() + "/Bosses/" + splitName);
					configFile = new File(plugin.getDataFolder().getAbsolutePath() + "/" + splitName);
					this.copyFile(configFile, false);
				} catch (IOException | URISyntaxException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		for (String enchantName : ENCHANT_CONFIGS) {
			enchantName = enchantName.toLowerCase();
			File configFile = new File(plugin.getDataFolder().getAbsolutePath() + "/Enchantments/" + enchantName);
			if (!configFile.exists()) {
				configFile = new File(plugin.getDataFolder().getAbsolutePath() + "/" + enchantName);
				try {
					this.copyFile(configFile, false);
				} catch (IOException | URISyntaxException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		for (String worldName : WORLDS) {
			File worldFile = new File(
					Bukkit.getServer().getWorldContainer().getAbsolutePath() + "/Morebosses-" + worldName);
			if (!worldFile.exists()) {
				try {
					this.copyFile(worldFile, true);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (URISyntaxException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} else {
				File dataFile = new File(Bukkit.getServer().getWorldContainer().getAbsolutePath() + "/Morebosses-"
						+ worldName + "/data.yml");
				if (!dataFile.exists()) {
					try {
						this.copyFile(dataFile, false);
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (URISyntaxException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		}
		for (String raidName : RAID_CONFIGS) {
			File raidFile = new File(plugin.getDataFolder().getAbsolutePath() + "/Raids/" + raidName);
			if (!raidFile.exists()) {
				raidFile = new File(plugin.getDataFolder().getAbsolutePath() + "/" + raidName);
				try {
					this.copyFile(raidFile, false);
				} catch (IOException | URISyntaxException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}

	public YamlConfiguration openConfiguration(String name, String path) {
		File configFile = new File(path + "/" + name);
		if (!configFile.exists()) {
			this.checkFiles();
		}
		return YamlConfiguration.loadConfiguration(configFile);
	}

	public YamlConfiguration openBossConfiguration(String name) {
		File configFile = new File(plugin.getDataFolder().getAbsolutePath() + "/Bosses/" + name);
		if (!configFile.exists()) {
			this.checkFiles();
//			return null;
		}
		return YamlConfiguration.loadConfiguration(configFile);
	}

	public YamlConfiguration openEnchantmentConfiguration(String name) {
		File configFile = new File(plugin.getDataFolder().getAbsolutePath() + "/Enchantments/" + name);
		if (!configFile.exists()) {
			this.checkFiles();
//			return null;
		}
		return YamlConfiguration.loadConfiguration(configFile);
	}

	public YamlConfiguration openRaidConfiguration(String name) {
		File raidFile = new File(plugin.getDataFolder().getAbsolutePath() + "/Raids/" + name);
		if (raidFile.exists()) {
			this.checkFiles();
		}
		return YamlConfiguration.loadConfiguration(raidFile);
	}

	private void copyFile(File file, boolean isWorldFile) throws IOException, URISyntaxException {
		JarFile jar = new JarFile(
				Morebosses.class.getProtectionDomain().getCodeSource().getLocation().toURI().getPath());
		Enumeration<JarEntry> entries = jar.entries();
		String fileName = file.getName().substring(file.getName().lastIndexOf("/") + 1);
		while (entries.hasMoreElements()) {
			JarEntry entry = entries.nextElement();
			if (entry.getName().contains("resources/") && entry.getName().contains(fileName)) {
				File newFile;
				if (!isWorldFile) {
					newFile = new File(plugin.getDataFolder().getAbsolutePath() + "/"
							+ entry.getName().substring(this.jarPath.length()).replace("/", "//"));
				} else {
					newFile = new File(plugin.getServer().getWorldContainer().getAbsolutePath() + "/"
							+ entry.getName().substring(this.jarPath.length()).replace("/", "//"));
				}
				if (!newFile.getParentFile().exists()) {
					newFile.getParentFile().mkdirs();
				}
				newFile.createNewFile();
				InputStream inputStream = plugin.getResource(entry.getName());
				FileOutputStream outputStream = new FileOutputStream(newFile);
				IOUtils.copy(inputStream, outputStream);
				inputStream.close();
				outputStream.close();
				Console.print("Created File: " + newFile.getName());
			}
		}
		jar.close();

	}
}
