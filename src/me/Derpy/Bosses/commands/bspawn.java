package me.Derpy.Bosses.commands;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;

import me.Derpy.Bosses.Main;
import me.Derpy.Bosses.mobs.tier0.Cow;
import me.Derpy.Bosses.mobs.tier0.Pig;
import me.Derpy.Bosses.mobs.tier0.Sheep;
import me.Derpy.Bosses.mobs.tier0.Tropic;
import me.Derpy.Bosses.mobs.tier1.Drowned;
import me.Derpy.Bosses.mobs.tier1.Skeleton;
import me.Derpy.Bosses.mobs.tier1.Zombie;
import me.Derpy.Bosses.mobs.tier2.Blaze;
import me.Derpy.Bosses.mobs.tier2.Creeper;
import me.Derpy.Bosses.mobs.tier2.Guardian;
import me.Derpy.Bosses.mobs.tier2.Stray;
import me.Derpy.Bosses.mobs.tier3.ElderGuardian;
import me.Derpy.Bosses.mobs.tier3.Slime;
import me.Derpy.Bosses.mobs.tier3.WitherSkeleton;
import me.Derpy.Bosses.mobs.tier4.EnderDragon;
import me.Derpy.Bosses.utilities.items.ichor;

public class bspawn implements CommandExecutor, TabCompleter{

	private Main plugin;
	
	public bspawn(Main plugin) {
		this.plugin = plugin;
		plugin.getCommand("bspawn").setExecutor(this);
		
	}
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (!(sender instanceof Player)) {
			sender.sendMessage("Inable to execute command as console");
			return true;
		}
		Player p = (Player) sender;
		if (p.hasPermission("bosses.spawn")) {
			if(!(args.length == 0)) {
				String arg = (String) args[0].toLowerCase();
				if(arg.equals("tropic")) {
					Entity entity = (Entity) p.getWorld().spawnEntity(p.getLocation(), EntityType.TROPICAL_FISH);
					Tropic.newtropic((LivingEntity) entity, plugin);
				}
				if(arg.equals("pig")) {
					Entity entity = (Entity) p.getWorld().spawnEntity((Location) p.getLocation(), EntityType.PIG);
					Pig.newpig((LivingEntity) entity, plugin);
				}
				if(arg.equals("cow")) {
					Entity entity = (Entity) p.getWorld().spawnEntity(p.getLocation(), EntityType.COW);
					Cow.newcow((LivingEntity) entity, plugin);
				}
				if(arg.equals("sheep")) {
					Entity entity = (Entity) p.getWorld().spawnEntity(p.getLocation(), EntityType.SHEEP);
					Sheep.newsheep((LivingEntity) entity, plugin);
				}
				if(arg.equals("drowned")) {
					Entity entity = (Entity) p.getWorld().spawnEntity(p.getLocation(), EntityType.DROWNED);
					Drowned.newdrowned((LivingEntity) entity, plugin, false);
				}
				if(arg.equals("skeleton")) {
					Entity entity = (Entity) p.getWorld().spawnEntity(p.getLocation(), EntityType.SKELETON);
					Skeleton.newskeleton((LivingEntity) entity, plugin);
				}
				if(arg.equals("zombie")) {
					Entity entity = (Entity) p.getWorld().spawnEntity(p.getLocation(), EntityType.ZOMBIE);
					Zombie.newzombie((LivingEntity) entity, plugin);
				}
				if(arg.equals("blaze")) {
					Entity entity = (Entity) p.getWorld().spawnEntity(p.getLocation(), EntityType.BLAZE);
					Blaze.newblaze((LivingEntity) entity, plugin);
				}
				if(arg.equals("creeper")) {
					Entity entity = (Entity) p.getWorld().spawnEntity(p.getLocation(), EntityType.CREEPER);
					Creeper.newcreeper((LivingEntity) entity, plugin);
				}
				if(arg.equals("guardian")) {
					Entity entity = (Entity) p.getWorld().spawnEntity(p.getLocation(), EntityType.GUARDIAN);
					Guardian.newguardian((LivingEntity) entity, plugin);
				}
				if(arg.equals("elder_guardian")) {
					Entity entity = (Entity) p.getWorld().spawnEntity(p.getLocation(), EntityType.ELDER_GUARDIAN);
					ElderGuardian.newelderguardian((LivingEntity) entity, plugin);
				}
				if(arg.equals("slime")) {
					Entity entity = (Entity) p.getWorld().spawnEntity(p.getLocation(), EntityType.SLIME);
					Slime.newslime((LivingEntity) entity, plugin);
				}
				if(arg.equals("wither_skeleton")) {
					Entity entity = (Entity) p.getWorld().spawnEntity(p.getLocation(), EntityType.WITHER_SKELETON);
					WitherSkeleton.newwitherskeleton((LivingEntity) entity, plugin);
				}
				if(arg.equals("stray")) {
					Entity entity = (Entity) p.getWorld().spawnEntity(p.getLocation(), EntityType.STRAY);
					Stray.newstray((LivingEntity) entity, plugin);
				}
				if(arg.equals("ender_dragon")) {
					Entity entity = (Entity) p.getWorld().spawnEntity(p.getLocation(), EntityType.ENDER_DRAGON);
					EnderDragon.newenderdragon((LivingEntity) entity, plugin);
				}
				if(arg.equals("wither")) {
					Entity entity = (Entity) p.getWorld().spawnEntity(p.getLocation(), EntityType.WITHER);
					me.Derpy.Bosses.mobs.tier4.Wither.newwither((LivingEntity) entity, plugin);
				}
				if(arg.equals("magma")) {
					Entity entity = (Entity) p.getWorld().spawnEntity(p.getLocation(), EntityType.MAGMA_CUBE);
					me.Derpy.Bosses.mobs.tier5.Magma.newmagma((LivingEntity) entity, plugin);
				}
				if(arg.equals("ichor")) {
					p.getInventory().addItem(ichor.getraw(plugin));
				}
				if(arg.equals("phantom")) {
					Entity entity = (Entity) p.getWorld().spawnEntity(p.getLocation(), EntityType.PHANTOM);
					me.Derpy.Bosses.mobs.tier0.Phantom.newphantom((LivingEntity) entity, plugin);
				}
				if(arg.equals("vex")) {
					me.Derpy.Bosses.mobs.vex.newvex(p.getLocation(), plugin);
				}
				return true;
			}
		} else {
			p.sendMessage("You do not have the required permissions to execute this command");
			return true;
		}
		return false;
	}
	@Override
	public List<String> onTabComplete(CommandSender sender, Command cmd, String label, String[] args) {
		if (cmd.getName().equalsIgnoreCase("bspawn")) {
			if (args.length == 1) {
				ArrayList<String> available = new ArrayList<String>();
				available.add("pig");
				available.add("cow");
				available.add("sheep");
				available.add("drowned");
				available.add("skeleton");
				available.add("zombie");
				available.add("blaze");
				available.add("creeper");
				available.add("guardian");
				available.add("elder_guardian");
				available.add("slime");
				available.add("wither_skeleton");
				available.add("stray");
				available.add("ender_dragon");
				available.add("wither");
				available.add("tropic");
				available.add("magma");
				available.add("ichor");
				available.add("phantom");
				available.add("vex");
				ArrayList<String> mobs = new ArrayList<String>();
				if (!args[0].equals("")) {
					for(String type: available) {
						if (type.toLowerCase().contains(args[0].toLowerCase())) {
							mobs.add(type.toLowerCase());
						}
					}
				}else {
					for(String type: available) {
							mobs.add(type.toLowerCase());
					}
				}
				Collections.sort(mobs);
				return mobs;
			}
		}
		
		return null;
	}
}
