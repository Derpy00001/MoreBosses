package me.Derpy.Bosses.Addons.Nordic.Listeners;

import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Illusioner;
import org.bukkit.entity.Player;
import org.bukkit.entity.Vindicator;
import org.bukkit.entity.Wolf;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.scheduler.BukkitRunnable;

import me.Derpy.Bosses.MoreBosses;
import me.Derpy.Bosses.Addons.Nordic.Mobs.Warlock;
import me.Derpy.Bosses.Addons.Nordic.Mobs.fenrir;
import me.Derpy.Bosses.utilities.Random;
import net.md_5.bungee.api.ChatColor;

public class worldevents implements Listener{
	public static Boolean powered=false;
	public static void startrunnable() {
		new BukkitRunnable() {

			@Override
			public void run() {
				if(!(powered)) {
					if(Random.random(0.5)) {
						powered=true;
						for(Player p : Bukkit.getWorld("MoreBosses-Addon-Nordic-Alfheimr").getPlayers()) {
							p.sendMessage(ChatColor.RED+""+ChatColor.ITALIC+"A dark power begins flowing through this realm.");
						}
						World world = Bukkit.getWorld("MoreBosses-Addon-Nordic-Alfheimr");
						if(!(world.getPlayers().size()==0)) {
							if(Random.random(0.2)) {
								Player p = world.getPlayers().get(Random.random(world.getPlayers()));
								Illusioner ill = Warlock.spawn(Random.randomlocation(p.getLocation(), 8, 6));
								ill.setTarget(p);
								for(int i =0;i>=4;i++) {
									((Vindicator) ill.getWorld().spawnEntity(Random.randomlocation(ill.getLocation(), 5, 1), EntityType.VINDICATOR)).setTarget(p);
								}
							}
						}
						new BukkitRunnable() {

							@Override
							public void run() {
								// TODO Auto-generated method stub
								powered=false;
								for(Player p : Bukkit.getWorld("MoreBosses-Addon-Nordic-Alfheimr").getPlayers()) {
									p.sendMessage(ChatColor.RED+""+ChatColor.ITALIC+"A dark power has left this realm.");
								}
							}
							
						}.runTaskLater(MoreBosses.getPlugin(MoreBosses.class), 20*425);
					}
				}
				if(Random.random(0.2)) {
					World world = Bukkit.getWorld("MoreBosses-Addon-Nordic-Alfheimr");
					if(!(world.getPlayers().size()==0)) {
						if(MoreBosses.getPlugin(MoreBosses.class).getConfig().getBoolean("bosses.enabled_bosses.fenrir")) {
							Player p= world.getPlayers().get(Random.random(world.getPlayers()));
							Wolf wolf = fenrir.spawn(Random.randomlocation(p.getLocation(), 8, 6));
							wolf.setTarget(p);
							for(int i = 0;i>=12;i++) {
								((Wolf) wolf.getWorld().spawnEntity(Random.randomlocation(wolf.getLocation(), 5, 1), EntityType.WOLF)).setTarget(p);
							}
							p.sendMessage(ChatColor.RED+"You are being hunted, be wary of your surroundings traveler.");
							p.sendMessage(ChatColor.RED+"You have displeased an ancient god.");
						}
					}
				}
			}
			
		}.runTaskTimer(MoreBosses.getPlugin(MoreBosses.class), 20*600, 20*60);
	}
	@EventHandler
	public static void ondamageby(EntityDamageByEntityEvent event) {
		if(powered) {
			if(event.getEntity().getWorld().getName().equals("MoreBosses-Addon-Nordic-Alfheimr")) {
				if(event.getEntity() instanceof Player) {
					event.setDamage(event.getDamage()*2);
				}
			}
		}
	}
}
