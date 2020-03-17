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

import javax.annotation.Nonnull;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.craftbukkit.libs.org.apache.commons.io.IOUtils;

import me.derpy.bosses.Morebosses;

public class ConfigurationHandler {
	private Morebosses plugin = Morebosses.getPlugin(Morebosses.class);
	private File nameFile;
	private String jarPath = "src/me/derpy/bosses/resources/";
	private final List<String> WORLDS = Arrays.asList("Colosseum", "Ghast");
	private final List<String> BOSS_CONFIGS = Arrays.asList("Tier0/Bee.yml", "Tier2/Blaze.yml", "Tier2/Creeper.yml",
			"Tier2/Stray.yml", "Tier2/Guardian.yml", "Tier3/Slime.yml", "Tier3/WitherSkeleton.yml", "Tier4/Pigman.yml",
			"Tier4/MagmaCube.yml");

	public ConfigurationHandler() {
		this.checkFiles();
	}

	public void checkFile(String fileName, String pathOutput) {
		try {
			File file = new File(pathOutput + "\\" + fileName);
			copyFile(file, false);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void checkFiles() {
		if (!(plugin.getDataFolder().exists())) {
			plugin.getDataFolder().mkdir();
		}
		this.nameFile = new File(plugin.getDataFolder().getAbsolutePath() + "\\names.txt");
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
		for (String configs : this.BOSS_CONFIGS) {
			File configFile = new File(plugin.getDataFolder().getAbsolutePath() + "\\Bosses\\" + configs);
			if (!configFile.exists()) {
				try {
					String splitName = configs.split("/")[1];
					configFile = new File(plugin.getDataFolder().getAbsolutePath() + "\\Bosses\\" + splitName);
					this.copyFile(configFile, false);
				} catch (IOException | URISyntaxException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		for (String worldName : this.WORLDS) {
			File worldFile = new File(
					Bukkit.getServer().getWorldContainer().getAbsolutePath() + "\\Morebosses-" + worldName);
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
				File dataFile = new File(Bukkit.getServer().getWorldContainer().getAbsolutePath() + "\\Morebosses-"
						+ worldName + "\\data.yml");
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
	}

	public @Nonnull YamlConfiguration openConfiguration(String name, String path) {
		File configFile = new File(path + "\\" + name);
		if (!configFile.exists()) {
			try {
				this.copyFile(configFile, false);
			} catch (IOException | URISyntaxException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return YamlConfiguration.loadConfiguration(configFile);
	}

	public YamlConfiguration openBossConfiguration(String name) {
		File configFile = new File(plugin.getDataFolder().getAbsolutePath() + "\\Bosses\\" + name);
		if (!configFile.exists()) {
			try {
				this.copyFile(configFile, false);
			} catch (IOException | URISyntaxException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return YamlConfiguration.loadConfiguration(configFile);
	}

	private void copyFile(File file, boolean isArena) throws IOException, URISyntaxException {
		Console.print("-------Copying Files to Directory-------");
		if (isArena) {
			Console.print("-------Boss Arena: " + file.getName() + "-------");
		} else {
			Console.print("-------File: " + file.getName() + "-------");
		}
		JarFile jar = new JarFile(
				Morebosses.class.getProtectionDomain().getCodeSource().getLocation().toURI().getPath());
		Enumeration<JarEntry> entries = jar.entries();
		while (entries.hasMoreElements()) {
			JarEntry entry = entries.nextElement();
			if (entry.getName().contains("resources/") && entry.getName().contains(file.getName())) {
				String newName = entry.getName().substring(this.jarPath.length());
				String[] splitName = newName.split("/");
				String newFileString = "";
				for (String string : splitName) {
					if (!string.contains(".")) {
						newFileString += "\\" + string;
					}
				}
				File newFile = new File(file.getParentFile().getAbsolutePath() + "\\" + newFileString);
				if (!newFile.exists()) {
					Console.print("New Directory:" + newFile.getAbsolutePath());
					if (!newFile.exists()) {
						newFile.mkdirs();
					}
					if (!newFile.isDirectory()) {
						newFile.delete();
					}
				}
				try {
					File checkFile = new File(file.getParentFile().getAbsolutePath() + "\\" + newName);
					if (!checkFile.exists()) {
						String[] display = entry.getName().split("/");
						Console.print("File:" + display[display.length - 1]);
						InputStream inStream = plugin.getResource(entry.getName());
						File tempFile = new File(file.getParentFile().getAbsolutePath() + "\\" + newName);
						File.createTempFile(file.getParentFile().getAbsolutePath() + "\\" + newName, null);
						FileOutputStream outStream = new FileOutputStream(tempFile);
						IOUtils.copy(inStream, outStream);
						inStream.close();
						outStream.close();
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		jar.close();
		Console.print("Copied: " + file.getName() + "!");
	}
}
