package me.derpy.bosses.utilities;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;

public class Console {
	private static ChatColor name = ChatColor.GOLD;
	private static ChatColor text = ChatColor.GREEN;

	public static void print(String string) {
		Bukkit.getConsoleSender().sendMessage(name + "[Morebosses] " + text + string);
	}

	public static void error(String string) {
		String lines = "";
		for (int i = 0; i < string.length() + 1; i++) {
			lines = lines + "=";
		}
		Bukkit.getConsoleSender()
				.sendMessage(ChatColor.RED + "\n" + lines + "\n[Morebosses] Error:\n" + string + "\n" + lines);
	}
}
