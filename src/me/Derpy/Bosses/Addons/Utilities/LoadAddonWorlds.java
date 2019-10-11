package me.Derpy.Bosses.Addons.Utilities;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameRule;
import org.bukkit.World;
import org.bukkit.World.Environment;
import org.bukkit.WorldCreator;
import org.bukkit.WorldType;

import me.Derpy.Bosses.MoreBosses;
import me.Derpy.Bosses.Addons.Nordic.Listeners.registernorse;
import me.Derpy.Bosses.Addons.Nordic.Listeners.worldevents;
import me.Derpy.Bosses.utilities.Refs;

public class LoadAddonWorlds {
	public static World Alfheimr = null;
	public static void load_nordic() {
		if(Refs.getNordicEnabled()) {
			registernorse.listeners(MoreBosses.getPlugin(MoreBosses.class));
			registernorse.enchants();
			registernorse.recipes();
			World nordicworld = WorldCreator.name("MoreBosses-Addon-Nordic-Alfheimr").generator(Refs.getNordic()).type(WorldType.NORMAL).environment(Environment.NETHER).createWorld();
			nordicworld.setGameRule(GameRule.NATURAL_REGENERATION, false);
			Bukkit.getConsoleSender().sendMessage(ChatColor.GOLD+"[MoreBosses-Addon]: "+ChatColor.AQUA+"Loaded Nordic World");
			LoadAddonWorlds.Alfheimr=nordicworld;
			worldevents.startrunnable();
		}
	}
}
