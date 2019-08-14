package me.Derpy.Bosses.events;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.Color;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.boss.BarColor;
import org.bukkit.boss.BarFlag;
import org.bukkit.boss.BarStyle;
import org.bukkit.boss.BossBar;
import org.bukkit.entity.AreaEffectCloud;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Monster;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.potion.PotionData;
import org.bukkit.potion.PotionType;
import org.bukkit.scheduler.BukkitRunnable;

import me.Derpy.Bosses.utilities.Random;
import net.md_5.bungee.api.ChatColor;

public class gladiator {
	private static Plugin plugin = me.Derpy.Bosses.Main.getPlugin(me.Derpy.Bosses.Main.class);
	private static Player player;
	private static Integer wave;
	private static Integer maxwaves = 5;
	private static ArrayList<Entity> wavemobs;
	private static BossBar bar;
	private static Integer maxmobsofround;
	private static Boolean active;
	private static LivingEntity king;
	public static void start(final Player p) throws InterruptedException {
		p.setGameMode(GameMode.ADVENTURE);
		p.teleport(Bukkit.getWorld("MoreBosses-Colosseum").getSpawnLocation());
		LivingEntity theking = (LivingEntity) p.getWorld().spawnEntity((Location) plugin.getConfig().get("raids.gladiator.spawns.king"), EntityType.WANDERING_TRADER);
		theking.setAI(false);
		setKing(theking);
		p.sendMessage("<"+ChatColor.GOLD+"Emperor"+ChatColor.RESET+"> "+ChatColor.RED+"You must defeat every other gladiator to be declared the victor and recieve the spoils.");
		plugin.getServer().getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() {
			  public void run() {
				  p.sendMessage("<"+ChatColor.GOLD+"Emperor"+ChatColor.RESET+"> "+ChatColor.RED+"BEGIN!");
					p.getWorld().playSound(p.getLocation(), Sound.EVENT_RAID_HORN, 100F, 1F);
					setPlayer(p);
					setWave(1);
					final NamespacedKey key = new NamespacedKey(plugin, "Colossuem-"+p.getName());
					BossBar bar = Bukkit.getServer().createBossBar( key, "Wave 1", BarColor.YELLOW, BarStyle.SOLID, BarFlag.values());
					bar.addPlayer(getPlayer());
					bar.setProgress(1.0);
					setBar(bar);
					setActive(true);
					startspawns();
					startpotions();
			 }
		}, 20*5);
	}
	private static void startpotions() {
		Location loc =  (Location) plugin.getConfig().get("raids.gladiator.specialblocks.beacon_glass");
		final Location partloc = new Location(loc.getWorld(), loc.getX(), loc.getY(), loc.getZ());
		partloc.setY(loc.getY()+1.5);
		new BukkitRunnable(){
			@SuppressWarnings("deprecation")
			@Override
			public void run(){
				if(getActive()) {
					if(Random.random(0.8)) {
						getPlayer().sendTitle(ChatColor.GOLD+"The Emperor", ChatColor.GOLD+"has sent a gift!");
						ArrayList<PotionType> potions = new ArrayList<PotionType>();
						potions.add(PotionType.REGEN);
						potions.add(PotionType.POISON);
						potions.add(PotionType.STRENGTH);
						potions.add(PotionType.TURTLE_MASTER);
						potions.add(PotionType.NIGHT_VISION);
						potions.add(PotionType.WEAKNESS);
						
						AreaEffectCloud cloud = (AreaEffectCloud) ((Location) plugin.getConfig().get("raids.gladiator.specialblocks.beacon_glass")).getWorld().spawnEntity(partloc, EntityType.AREA_EFFECT_CLOUD);
						cloud.setColor(Color.BLACK);
						cloud.setDuration(200);
						cloud.setParticle(Particle.TOTEM);
						cloud.setRadius(5);
						Integer min = 0;
						Integer max = potions.size();
						Integer range = max-min+1;
						int num = (int) ((int)(Math.random()*range)+min);
						cloud.setBasePotionData(new PotionData(potions.get(num)));
						((Location) plugin.getConfig().get("raids.gladiator.specialblocks.beacon_glass")).getBlock().setType(Material.RED_STAINED_GLASS);
						plugin.getServer().getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() {
							  public void run() {
								  ((Location) plugin.getConfig().get("raids.gladiator.specialblocks.beacon_glass")).getBlock().setType(Material.BLACK_CONCRETE);
							 }
	//					}, 6000L);
						}, 400);
					}
				}else {
					this.cancel();
				}
			}
		}.runTaskTimer(plugin, 800, 800);
	}
	public static void checkwave(Entity entity2) throws InterruptedException {
		if(getActive()) {
			if(entity2 instanceof Player) {
				if(entity2==getPlayer()) {
					end(false);
				}
			}
			if(!(getWavemobs().size()==0)) {
				try{
					for(Entity entity : getWavemobs()) {
						if(entity2==entity) {
							getWavemobs().remove(entity);
							String max = getMaxmobsofround().toString();
							Integer curr = getWavemobs().size();
							String currr = curr.toString();
							getBar().setProgress(Double.parseDouble(currr)/Double.parseDouble(max));
							if(getWavemobs().size()==0) {
								endwave();
							}
						}
					}
				}catch(Exception e){
					
				}
			}else {
				endwave();
			}
		}
	}
	private static void endwave() throws InterruptedException {
		setWave(getWave()+1);
		if(getWave()==gladiator.maxwaves+1) {
			end(true);
		}else {
			plugin.getServer().getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() {
				  public void run() {
					getBar().setTitle("Wave "+Integer.toString(getWave()));
					startspawns();
				  }
			}, 20*5);
		}
	}
	private static void end(Boolean success) throws InterruptedException {
		setActive(false);
		getBar().removeAll();
		for(Entity e : getKing().getNearbyEntities(100, 100, 100)) {
			if(!(e instanceof Player)) {
				e.remove();
			}
		}
		getKing().remove();
		getPlayer().setGameMode(GameMode.SURVIVAL);
		if(success) {
			getPlayer().playSound(getPlayer().getLocation(), Sound.UI_TOAST_CHALLENGE_COMPLETE, 100, 1);
			getPlayer().sendMessage(ChatColor.GOLD+"Returning in 2 minutes");
			plugin.getServer().getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() {
				  public void run() {
					  if(getPlayer().getBedSpawnLocation()==null) {
							getPlayer().teleport(Bukkit.getWorlds().get(0).getSpawnLocation());
						}else {
							getPlayer().teleport(getPlayer().getBedSpawnLocation());
						}
				  }
			}, 120000);
			
		}else {
			plugin.getServer().getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() {
				  public void run() {
					if(getPlayer().getBedSpawnLocation()==null) {
						getPlayer().teleport(Bukkit.getWorlds().get(0).getSpawnLocation());
					}else {
						getPlayer().teleport(getPlayer().getBedSpawnLocation());
					}
				  }
			}, 120000);
		}
	}
	private static void startspawns() {
		ArrayList<EntityType> types = new ArrayList<EntityType>();
		ArrayList<Entity> mobs = new ArrayList<Entity>();
		if(getWave()==1) {
			Integer min = 5;
			Integer max = 9-1;
			Integer range = max-min+1;
			int num = (int) ((int)(Math.random()*range)+min);
			setMaxmobsofround(num);
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
				Entity ent = (Entity) ((Location) plugin.getConfig().get("raids.gladiator.spawns.gate"+Integer.toString(num1))).getWorld().spawnEntity((Location) plugin.getConfig().get("raids.gladiator.spawns.gate"+Integer.toString(num1)), types.get(num2));
				Monster mob = (Monster) ent;
				mob.setTarget(getPlayer());
				mobs.add(ent);
			}
			setWavemobs(mobs);
		}else if(getWave()==2) {
			Integer min = 8;
			Integer max = 16-1;
			Integer range = max-min+1;
			int num = (int) ((int)(Math.random()*range)+min);
			setMaxmobsofround(num);
			types.add(EntityType.PILLAGER);
			types.add(EntityType.VINDICATOR);
			types.add(EntityType.EVOKER);
			for(int i=0;i<num;i++) {
				Integer min1 = 1;
				Integer max1 = 3-1;
				Integer range1 = max1-min1+1;
				int num1 = (int) ((int)(Math.random()*range1)+min1);
				Integer min2 = 0;
				Integer max2 = types.size()-1;
				Integer range2 = max2-min2+1;
				int num2 = (int) ((int)(Math.random()*range2)+min2);
				Entity ent = (Entity) ((Location) plugin.getConfig().get("raids.gladiator.spawns.gate"+Integer.toString(num1))).getWorld().spawnEntity((Location) plugin.getConfig().get("raids.gladiator.spawns.gate"+Integer.toString(num1)), types.get(num2));
				Monster mob = (Monster) ent;
				mob.setTarget(getPlayer());
				mobs.add(ent);
			}
			setWavemobs(mobs);
		}else if(getWave()==3) {
			Integer min = 16;
			Integer max = 20-1;
			Integer range = max-min+1;
			int num = (int) ((int)(Math.random()*range)+min);
			setMaxmobsofround(num);
			types.add(EntityType.PILLAGER);
			types.add(EntityType.VINDICATOR);
			types.add(EntityType.EVOKER);
			for(int i=0;i<num;i++) {
				Integer min1 = 1;
				Integer max1 = 3-1;
				Integer range1 = max1-min1+1;
				int num1 = (int) ((int)(Math.random()*range1)+min1);
				Integer min2 = 0;
				Integer max2 = types.size()-1;
				Integer range2 = max2-min2+1;
				int num2 = (int) ((int)(Math.random()*range2)+min2);
				Entity ent = (Entity) ((Location) plugin.getConfig().get("raids.gladiator.spawns.gate"+Integer.toString(num1))).getWorld().spawnEntity((Location) plugin.getConfig().get("raids.gladiator.spawns.gate"+Integer.toString(num1)), types.get(num2));
				Monster mob = (Monster) ent;
				mob.setTarget(getPlayer());
				mobs.add(ent);
			}
			setWavemobs(mobs);
		}else if(getWave()==4) {
			Integer min = 20;
			Integer max = 25-1;
			Integer range = max-min+1;
			int num = (int) ((int)(Math.random()*range)+min);
			setMaxmobsofround(num);
			types.add(EntityType.PILLAGER);
			types.add(EntityType.VINDICATOR);
			types.add(EntityType.EVOKER);
			types.add(EntityType.ILLUSIONER);
			for(int i=0;i<num;i++) {
				Integer min1 = 1;
				Integer max1 = 3-1;
				Integer range1 = max1-min1+1;
				int num1 = (int) ((int)(Math.random()*range1)+min1);
				Integer min2 = 0;
				Integer max2 = types.size()-1;
				Integer range2 = max2-min2+1;
				int num2 = (int) ((int)(Math.random()*range2)+min2);
				Entity ent = (Entity) ((Location) plugin.getConfig().get("raids.gladiator.spawns.gate"+Integer.toString(num1))).getWorld().spawnEntity((Location) plugin.getConfig().get("raids.gladiator.spawns.gate"+Integer.toString(num1)), types.get(num2));
				Monster mob = (Monster) ent;
				mob.setTarget(getPlayer());
				mobs.add(ent);
			}
			setWavemobs(mobs);
		}else if(getWave()==5) {
			setMaxmobsofround(25);
			types.add(EntityType.PILLAGER);
			types.add(EntityType.VINDICATOR);
			types.add(EntityType.EVOKER);
			types.add(EntityType.ILLUSIONER);
			types.add(EntityType.RAVAGER);
			for(int i=0;i<25;i++) {
				Integer min1 = 1;
				Integer max1 = 3-1;
				Integer range1 = max1-min1+1;
				int num1 = (int) ((int)(Math.random()*range1)+min1);
				Integer min2 = 0;
				Integer max2 = types.size()-1;
				Integer range2 = max2-min2+1;
				int num2 = (int) ((int)(Math.random()*range2)+min2);
				Entity ent = (Entity) ((Location) plugin.getConfig().get("raids.gladiator.spawns.gate"+Integer.toString(num1))).getWorld().spawnEntity((Location) plugin.getConfig().get("raids.gladiator.spawns.gate"+Integer.toString(num1)), types.get(num2));
				Monster mob = (Monster) ent;
				mob.setTarget(getPlayer());
				mobs.add(ent);
			}
			setWavemobs(mobs);
		}
		String max = getMaxmobsofround().toString();
		Integer curr = getWavemobs().size();
		String currr = curr.toString();
		getBar().setProgress(Double.parseDouble(currr)/Double.parseDouble(max));
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
	private static ArrayList<Entity> getWavemobs() {
		return wavemobs;
	}
	private static void setWavemobs(ArrayList<Entity> wavemobs) {
		gladiator.wavemobs = wavemobs;
	}
	public static BossBar getBar() {
		return bar;
	}
	public static void setBar(BossBar bar) {
		gladiator.bar = bar;
	}
	public static Integer getMaxmobsofround() {
		return maxmobsofround;
	}
	public static void setMaxmobsofround(Integer maxmobsofround) {
		gladiator.maxmobsofround = maxmobsofround;
	}
	public static Boolean getActive() {
		return active;
	}
	public static void setActive(Boolean active) {
		gladiator.active = active;
	}
	public static LivingEntity getKing() {
		return king;
	}
	public static void setKing(LivingEntity king) {
		gladiator.king = king;
	}
}
