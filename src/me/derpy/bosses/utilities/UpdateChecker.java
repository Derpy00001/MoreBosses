package me.derpy.bosses.utilities;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

import me.derpy.bosses.Morebosses;

public class UpdateChecker {
	private static int project = 69355;

	public static boolean isUpdateAvailable() throws IOException {
		URL url = new URL("https://api.spigotmc.org/legacy/update.php?resource=" + project);
		URLConnection connection = url.openConnection();
		String currentVersion = new BufferedReader(new InputStreamReader(connection.getInputStream())).readLine();
		return !Morebosses.getPlugin(Morebosses.class).getDescription().getVersion().equals(currentVersion);
	}

	public static String getCurrentSpigotVersion() throws IOException {
		URL url = new URL("https://api.spigotmc.org/legacy/update.php?resource=" + project);
		URLConnection connection = url.openConnection();
		String currentVersion = new BufferedReader(new InputStreamReader(connection.getInputStream())).readLine();
		return currentVersion;
	}

	public static String getSpigotUrl() {
		return "https://spigotmc.org/resources/" + project;
	}
}