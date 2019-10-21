package me.Derpy.Bosses.commands;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

import me.Derpy.Bosses.Main;
import me.Derpy.Bosses.discord.DiscordWebhook;
import net.md_5.bungee.api.ChatColor;

public class report implements CommandExecutor, TabCompleter{

	private Main plugin;
	private ArrayList<UUID> list;
	
	public report(Main plugin) {
		this.plugin = plugin;
		plugin.getCommand("breport").setExecutor(this);
		ArrayList<UUID> list = new ArrayList<UUID>();
		this.list = list;
		
	}
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (!(sender instanceof Player)) {
			sender.sendMessage("Inable to execute command as console");
			return true;
		}
		final Player p = (Player) sender;
		if(!(list.contains(p.getUniqueId()))) {
		    DiscordWebhook webhook = new DiscordWebhook("https://canary.discordapp.com/api/webhooks/602204194220408832/w373qRy7N7I_Kf6UvayM3cDG1jUmCD0Wxhefyg8CDRUsRVSUiorlrDflekX3OrLvLnj0");
		    webhook.setContent(Arrays.toString(args).trim().replace("[", "").replace("]", "").replace(",", ""));
		    webhook.setAvatarUrl("https://your.awesome/image.png");
		    webhook.setUsername(p.getUniqueId().toString());
		    webhook.setTts(false);
		    try {
				webhook.execute();
				list.add(p.getUniqueId());
				p.sendMessage(ChatColor.GREEN+"Thank you for filing a report!");
				plugin.getServer().getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() {
					  public void run() {
					      list.remove(p.getUniqueId());
					      p.sendMessage(ChatColor.GREEN+"You are now able to send another bug report!");
					  }
					}, 6000L);
				return true;
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return false;
			} //Handle exception
		}else {
			p.sendMessage(ChatColor.RED+"Please wait 5 minutes to file another report, this is to prevent floods of fake reports, thank you for your help in improving MoreBosses!");
			return true;
		}
	}
	@Override
	public List<String> onTabComplete(CommandSender sender, Command cmd, String label, String[] args) {
		if (cmd.getName().equalsIgnoreCase("breport")) {
			if (args.length == 1) {
				ArrayList<String> available = new ArrayList<String>();
				available.add("BUG-REPORT_HERE");
				Collections.sort(available);
				return available;
			}
		}
		
		return null;
	}
}