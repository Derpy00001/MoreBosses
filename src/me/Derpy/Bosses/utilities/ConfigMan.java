package me.Derpy.Bosses.utilities;

import java.io.File;
import java.io.IOException;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.Plugin;

import me.Derpy.Bosses.MoreBosses;
// Configuration Manager for MoreBosses
//https://www.youtube.com/watch?v=pGkJwiJsMi0
public class ConfigMan {
	private Plugin plugin = MoreBosses.getPlugin(MoreBosses.class);
	private File poolsf;
	private FileConfiguration pools;
	public void setup() {
		//Check for config folder
		if(!(plugin.getDataFolder().exists())) {
			plugin.getDataFolder().mkdir();
		}
		//Create vars
		poolsf = new File(plugin.getDataFolder(), "pools.yml");
		//Check valid
		if(!(poolsf.exists())) {
			try {
				poolsf.createNewFile();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		//Document yml
		pools = YamlConfiguration.loadConfiguration(poolsf);
	}
	public FileConfiguration getPools() {
		return pools;
	}
	public File getPoolsFile() {
		return poolsf;
	}
}
