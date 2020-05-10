package me.derpy.bosses.utilities;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;

public class Console {
	private static ChatColor name = ChatColor.GOLD;
	private static ChatColor text = ChatColor.GREEN;

	public static void print(String string) {
		Bukkit.getConsoleSender().sendMessage(name + "[Morebosses] " + text + string);
	}
}
