package me.derpy.bosses.commands;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import me.derpy.bosses.Morebosses;
import me.derpy.bosses.items.ItemType;
import me.derpy.bosses.mobs.BossType;
import me.derpy.bosses.mobs.MobHandler;
import net.md_5.bungee.api.ChatColor;

public class CommandSpawn implements CommandExecutor, TabCompleter {
	final String PERMISSION_SPAWN = "morebosses.spawn";
	final String PERMISSION_ITEM = "morebosses.spawn.item";
	final String PERMISSION_BOSS = "morebosses.spawn.boss";
	final String MESSAGE_BOSS = ChatColor.GREEN + "Spawned Boss!";
	final String MESSAGE_ITEM = ChatColor.GREEN + "Spawned Item!";
	final String MESSAGE_ERROR = ChatColor.RED + "There was an issue trying to spawn this.";
	final String MESSAGE_PERMISSION = ChatColor.RED + "You do not have the permissions required to run this command!";
	List<String> choices = new ArrayList<String>();

	public CommandSpawn() {
		JavaPlugin.getPlugin(Morebosses.class).getCommand("bspawn").setExecutor(this);
		JavaPlugin.getPlugin(Morebosses.class).getCommand("bspawn").setPermission(this.PERMISSION_SPAWN);
		choices.add("item");
		choices.add("boss");
	}

	@Override
	public List<String> onTabComplete(CommandSender arg0, Command arg1, String arg2, String[] arg3) {
		// TODO Auto-generated method stub
		List<String> culled = new ArrayList<String>();
		if (arg3.length == 1) {
			for (String choice : choices) {
				if (choice.toLowerCase().contains(arg3[0].toLowerCase())) {
					culled.add(choice);
				}
			}

		} else if (arg3.length == 2) {
			if (arg3[0].toLowerCase().equals("boss")) {
				for (BossType type : BossType.values()) {
					if (type.name().toLowerCase().contains(arg3[1].toLowerCase())) {
						culled.add(type.name().toLowerCase());
					}
				}
			} else if (arg3[0].toLowerCase().equals("item")) {
				for (ItemType type : ItemType.values()) {
					if (type.name().toLowerCase().contains(arg3[1].toLowerCase())) {
						culled.add(type.name().toLowerCase());
					}
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
			if (player.hasPermission(this.PERMISSION_SPAWN)) {
				if (!(arg3.length == 0)) {
					if (arg3[0].toLowerCase().equals("boss")) {
						if (player.hasPermission(this.PERMISSION_BOSS)) {
							try {
								MobHandler.spawnBossWithBar(player.getLocation(),
										BossType.getFromName(arg3[1].toLowerCase()).getInterface());
								player.sendMessage(this.MESSAGE_BOSS);
								return true;
							} catch (CloneNotSupportedException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
								player.sendMessage(this.MESSAGE_ERROR);
								return true;
							}
						} else {
							player.sendMessage(this.MESSAGE_PERMISSION);
							return true;
						}
					} else if (arg3[0].toLowerCase().equals("item")) {
						if (player.hasPermission(this.PERMISSION_ITEM)) {
							try {
								if (player.getInventory().firstEmpty() == -1) {
									player.getWorld().dropItemNaturally(player.getLocation(),
											ItemType.getFromName(arg3[1]).getInterface().getFinalizedItem());
									player.sendMessage(this.MESSAGE_ITEM);
									return true;
								} else {

									player.getInventory()
											.addItem(ItemType.getFromName(arg3[1]).getInterface().getFinalizedItem());
									player.sendMessage(this.MESSAGE_ITEM);
									return true;
								}
							} catch (Exception e) {
								e.printStackTrace();
								player.sendMessage(this.MESSAGE_ERROR);
								return true;
							}
						} else {
							player.sendMessage(this.MESSAGE_PERMISSION);
							return true;
						}
					} else {
						return false;
					}
				} else
					return false;
			} else {
				player.sendMessage(this.MESSAGE_PERMISSION);
				return true;
			}
		}
	}

}
