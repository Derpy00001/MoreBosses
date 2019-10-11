package me.Derpy.Bosses.utilities;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.generator.ChunkGenerator;
import org.bukkit.plugin.Plugin;

import net.md_5.bungee.api.ChatColor;
import net.minecraft.server.v1_14_R1.EntityPlayer;




public class Refs {
	private static Boolean reload = false;
	private static Boolean nordic=false;
	private static ChunkGenerator nordicgen=null;
	public static ArrayList<EntityPlayer> npcs = new ArrayList<EntityPlayer>();
	public static Boolean getReload() {
		return reload;
	}
	public static void setReload(Boolean _reload) {
		reload = _reload;
	}
	public static Boolean getNordicEnabled() {
		return nordic;
	}
	public static ChunkGenerator getNordic() {
		return nordicgen;
	}
	public static void setNordic(Boolean nordic) {
		Refs.nordic = nordic;
		if(nordic) {
			Plugin nordicplugin = Bukkit.getServer().getPluginManager().getPlugin("NordicReload");
			if(nordicplugin!=null) {
				try {
					String worldname = "world_nordic";
					String ID = "234";
					Refs.nordicgen=nordicplugin.getDefaultWorldGenerator(worldname, ID);
				}catch(Exception e) {
					Bukkit.getServer().getConsoleSender().sendMessage(ChatColor.RED+"[MoreBosses]: Failed to get nordic generator");
					Bukkit.getServer().getConsoleSender().sendMessage(ChatColor.RED+"[MoreBosses]: Disabling Nordic Addon");
					Refs.nordic=false;
				}
			}
		}
	}
	
}
