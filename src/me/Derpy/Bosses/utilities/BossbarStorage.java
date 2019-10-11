package me.Derpy.Bosses.utilities;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.NamespacedKey;

public class BossbarStorage {
	private static ArrayList<NamespacedKey> bars = new ArrayList<NamespacedKey>();
	public static ArrayList<NamespacedKey> getBars() {
		return bars;
	}
	public static void addBar(NamespacedKey key) {
		if(!(bars.contains(key))) {
			bars.add(key);
		}
	}
	public static void removeBar(NamespacedKey key) {
		if(bars.contains(key)) {
			bars.remove(key);
		}
	}
	public static void removeall() {
		try {
			for(NamespacedKey key : bars) {
				Bukkit.getBossBar(key).removeAll();
				Bukkit.removeBossBar(key);
				bars.remove(key);
			}
		}catch(Exception e) {
			
		}
	}
}
