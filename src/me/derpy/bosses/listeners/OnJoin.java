package me.derpy.bosses.listeners;

import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.Plugin;

import me.derpy.bosses.Morebosses;
import me.derpy.bosses.items.blueprints.misc.TutorialBook;
import net.md_5.bungee.api.chat.BaseComponent;

public class OnJoin implements Listener {
	final Plugin PLUGIN = Morebosses.getPlugin(Morebosses.class);
	final boolean JOIN_MESSAGE_ENABLED = PLUGIN.getConfig().getBoolean("plugin.JoinMessage.enabled");
	final boolean JOIN_MESSAGE_FIRST_ONLY = PLUGIN.getConfig().getBoolean("plugin.JoinMessage.onlyFirstJoin");
	final BaseComponent[] MESSAGE_COMPONENT = TutorialBook.createLink(
			ChatColor.GOLD + "This server runs Morebosses!\n" + ChatColor.GOLD + "To open the Wiki, click here.",
			"https://github.com/Derpy00001/MoreBosses/wiki", "Opens the Morebosses Wiki");

	@EventHandler
	private void onJoin(PlayerJoinEvent e) {
		if (this.JOIN_MESSAGE_ENABLED) {
			if (this.JOIN_MESSAGE_FIRST_ONLY) {
				if (!e.getPlayer().hasPlayedBefore()) {
					e.getPlayer().spigot().sendMessage(this.MESSAGE_COMPONENT);
				}
			} else {
				e.getPlayer().spigot().sendMessage(this.MESSAGE_COMPONENT);
			}
		}
	}
}
