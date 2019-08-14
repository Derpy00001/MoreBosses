package me.Derpy.Bosses.events;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import net.md_5.bungee.api.ChatColor;

public class gladiator {
	private static Plugin plugin = me.Derpy.Bosses.Main.getPlugin(me.Derpy.Bosses.Main.class);
	private static Player player;
	private static Integer wave;
	private static Integer maxwaves = 5;
	private static ArrayList<LivingEntity> wavemobs;
	public static void start(Player p) throws InterruptedException {
		p.setGameMode(GameMode.ADVENTURE);
		p.teleport(Bukkit.getWorld("MoreBosses-Colosseum").getSpawnLocation());
		((LivingEntity) p.getWorld().spawnEntity((Location) plugin.getConfig().get("raids.gladiator.spawns.kind"), EntityType.WANDERING_TRADER)).setAI(false);
		p.sendMessage("<"+ChatColor.GOLD+"Emperor"+ChatColor.RESET+"> "+ChatColor.RED+"You must defeat every other gladiator to be declared the victor and recieve the spoils.");
		Thread.sleep(5000);
		p.sendMessage("<"+ChatColor.GOLD+"Emperor"+ChatColor.RESET+"> "+ChatColor.RED+"BEGIN!");
		p.getWorld().playSound(p.getLocation(), Sound.EVENT_RAID_HORN, 100F, 1F);
		setPlayer(p);
		setWave(1);
		startspawns();
	}
	public static void checkwave(LivingEntity e) {
		if(e instanceof Player) {
			if(e==getPlayer()) {
				end(false);
			}
		}
		if(!(getWavemobs().isEmpty())) {
			for(LivingEntity entity : getWavemobs()) {
				if(!(entity.isValid())) {
					getWavemobs().remove(entity);
				}
			}
			if(getWavemobs().isEmpty()) {
				endwave();
			}
		}else {
			endwave();
		}
	}
	private static void endwave() {
		getWavemobs().clear();
		setWave(getWave()+1);
		if(getWave()==gladiator.maxwaves+1) {
			end(true);
		}else {
			startspawns();
		}
	}
	private static void end(Boolean success) {
		if(success) {
			
		}else {
			
		}
	}
	private static void startspawns() {
		ArrayList<EntityType> types = new ArrayList<EntityType>();
		ArrayList<LivingEntity> mobs = new ArrayList<LivingEntity>();
		if(getWave()==1) {
			Integer min = 8;
			Integer max = 13-1;
			Integer range = max-min+1;
			int num = (int) ((int)(Math.random()*range)+min);
			types.add(EntityType.PILLAGER);
			types.add(EntityType.VINDICATOR);
			for(int i=0;i<num;i++) {
				Integer min1 = 1;
				Integer max1 = 3-1;
				Integer range1 = max1-min1+1;
				int num1 = (int) ((int)(Math.random()*range1)+min1);
				Integer min2 = 0;
				Integer max2 = types.size()-1;
				Integer range2 = max2-min2+1;
				int num2 = (int) ((int)(Math.random()*range2)+min2);
				LivingEntity ent = (LivingEntity) ((Location) plugin.getConfig().get("raids.gladiator.spawns.gate"+Integer.toString(num1))).getWorld().spawnEntity((Location) plugin.getConfig().get("raids.gladiator.spawns.gate"+Integer.toString(num1)), types.get(num2));
				mobs.add(ent);
			}
			setWavemobs(mobs);
		}
	}
	private static Player getPlayer() {
		return player;
	}
	private static void setPlayer(Player player) {
		gladiator.player = player;
	}
	private static Integer getWave() {
		return wave;
	}
	private static void setWave(Integer wave) {
		gladiator.wave = wave;
	}
	private static ArrayList<LivingEntity> getWavemobs() {
		return wavemobs;
	}
	private static void setWavemobs(ArrayList<LivingEntity> wavemobs) {
		gladiator.wavemobs = wavemobs;
	}
}
