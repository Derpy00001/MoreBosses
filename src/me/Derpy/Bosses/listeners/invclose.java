package me.Derpy.Bosses.listeners;

import java.io.IOException;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryCloseEvent;

import me.Derpy.Bosses.utilities.ConfigMan;
import me.Derpy.Bosses.utilities.ConfigManager;
import net.md_5.bungee.api.ChatColor;

public class invclose implements Listener{
	private static ConfigMan config = ConfigManager.pools;
	@EventHandler
	public static void onclose(InventoryCloseEvent event) {
		if(!event.getPlayer().isOp()) {
			return;
		}
		if(event.getView().getTitle().contains(ChatColor.RED+"MoreBosses.Pool")) {
			String sub0 = event.getView().getTitle().substring(18);
			config.getPools().set("MoreBosses.Pool."+sub0, event.getInventory().getContents());
			try {
				config.getPools().save(config.getPoolsFile());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
