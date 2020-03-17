package me.derpy.bosses.commands;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import me.derpy.bosses.Morebosses;
import net.md_5.bungee.api.ChatColor;

public class CommandArena implements CommandExecutor, TabCompleter {
	final String PERMISSION_ARENA = "morebosses.arena";
	final String PERMISSION_TELEPORT = "morebosses.arena.teleport";
	final String PERMISSION_SAVE = "morebosses.arena.save";
	final String MESSAGE_TELEPORT = ChatColor.GREEN + "Teleported to Arena!";
	final String MESSAGE_SAVE = ChatColor.GREEN + "Saved Arena";
	final String MESSAGE_ERROR = ChatColor.RED + "There was an issue trying to perform this.";
	final String MESSAGE_PERMISSION = ChatColor.RED + "You do not have the permissions required to run this command!";
	List<String> choices = new ArrayList<String>();

	public CommandArena() {
		JavaPlugin.getPlugin(Morebosses.class).getCommand("barena").setExecutor(this);
		JavaPlugin.getPlugin(Morebosses.class).getCommand("barena").setPermission(PERMISSION_ARENA);
		if (Morebosses.getWorldHandler().getArenas().size() > 0) {
			for (World world : Morebosses.getWorldHandler().getArenas()) {
				choices.add(world.getName());
			}
		}
	}

	@Override
	public List<String> onTabComplete(CommandSender arg0, Command arg1, String arg2, String[] arg3) {
		// TODO Auto-generated method stub
		List<String> culled = new ArrayList<String>();
		if (arg3.length == 1) {
			if (arg0.hasPermission(PERMISSION_TELEPORT)) {
				culled.add("teleport");
			}
			if (arg0.hasPermission(PERMISSION_SAVE)) {
				culled.add("save");
			}

		} else if (arg3.length == 2) {
			for (String key : choices) {
				if (key.toLowerCase().contains(arg3[1].toLowerCase())) {
					culled.add(key);
				}
			}
		}
		Collections.sort(culled);
		return culled;
	}

	@Override
	public boolean onCommand(CommandSender arg0, Command arg1, String arg2, String[] arg3) {
		// TODO Auto-generated method stub
		if (!(arg0 instanceof Player)) {
			arg0.sendMessage("Inable to execute command as console");
			return true;
		} else {
			Player player = (Player) arg0;
			if (player.hasPermission(this.PERMISSION_ARENA)) {
				if (!(arg3.length == 0)) {
					if (arg3[0].toLowerCase().equals("teleport")) {
						if (player.hasPermission(this.PERMISSION_TELEPORT)) {
							for (World world : Morebosses.getWorldHandler().getArenas()) {
								if (world.getName().toLowerCase().equals(arg3[1].toLowerCase())) {
									player.teleport(world.getSpawnLocation());
									player.sendMessage(MESSAGE_TELEPORT);
									return true;
								}
							}
							player.sendMessage(MESSAGE_ERROR);
							return true;
						} else {
							player.sendMessage(this.MESSAGE_PERMISSION);
							return true;
						}
					} else if (arg3[0].toLowerCase().equals("save")) {
						if (player.hasPermission(this.PERMISSION_SAVE)) {
							for (World world : Morebosses.getWorldHandler().getArenas()) {
								if (world.getName().toLowerCase().equals(arg3[1].toLowerCase())) {
									world.save();
									player.sendMessage(MESSAGE_SAVE);
									return true;
								}
							}
							player.sendMessage(MESSAGE_ERROR);
							return true;
						} else {
							player.sendMessage(this.MESSAGE_PERMISSION);
							return true;
						}
					}
				} else
					return false;
			} else {
				player.sendMessage(this.MESSAGE_PERMISSION);
				return true;
			}
		}
		return false;
	}

}
