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

import me.Derpy.Bosses.MoreBosses;
import me.Derpy.Bosses.Addons.Nordic.Items.runes;
import me.Derpy.Bosses.Addons.Nordic.Items.shards;
import me.Derpy.Bosses.Addons.Nordic.Items.spirit;
import me.Derpy.Bosses.Addons.Nordic.Items.wolfram;
import me.Derpy.Bosses.Addons.Nordic.Mobs.Human;
import me.Derpy.Bosses.Addons.Nordic.Mobs.boar;
import me.Derpy.Bosses.Addons.Nordic.Mobs.fenrir;
import me.Derpy.Bosses.Addons.Nordic.Mobs.giant;
import me.Derpy.Bosses.mobs.custommobs.custom_endermite;
import me.Derpy.Bosses.mobs.merchants.createNomad;
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
import me.Derpy.Bosses.utilities.Refs;
import me.Derpy.Bosses.utilities.items.Enchants;
import me.Derpy.Bosses.utilities.items.gems;
import me.Derpy.Bosses.utilities.items.ghast;
import me.Derpy.Bosses.utilities.items.ghast_totem;
import me.Derpy.Bosses.utilities.items.ichor;
import me.Derpy.Bosses.utilities.items.nukes;
import me.Derpy.Bosses.utilities.items.village;
import net.md_5.bungee.api.ChatColor;

public class bspawn implements CommandExecutor, TabCompleter{

	private MoreBosses plugin;
	
	public bspawn(MoreBosses plugin) {
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
				String arg = (String) args[1].toLowerCase();
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
				if(arg.equals("ghast_totem")) {
					p.getInventory().addItem(ghast_totem.get(plugin));
				}
				if(arg.equals("tissue")) {
					p.getInventory().addItem(ghast.tissue(1));
				}
				if(arg.equals("phantom")) {
					Entity entity = (Entity) p.getWorld().spawnEntity(p.getLocation(), EntityType.PHANTOM);
					me.Derpy.Bosses.mobs.tier0.Phantom.newphantom((LivingEntity) entity, plugin);
				}
				if(arg.equals("vex")) {
					me.Derpy.Bosses.mobs.vex.newvex(p.getLocation(), plugin);
				}
				if(arg.equals("bosses.trader_merchant")){
					Entity entity = (Entity) p.getWorld().spawnEntity(p.getLocation(), EntityType.WANDERING_TRADER);
					me.Derpy.Bosses.mobs.merchants.merchant.newmerchant((LivingEntity) entity);
				}
				if(arg.equals("challengers_token")) {
					p.getInventory().addItem(village.token());
				}
				if(arg.equals("fleet")) {
					
					p.getInventory().addItem(Enchants.fleet(1));
				}
				if(arg.equals("ember")) {
					p.getInventory().addItem(Enchants.ember());
				}
				if(arg.equals("lifesteal")) {
					p.getInventory().addItem(Enchants.lifesteal());
				}
				if(arg.equals("undying")) {
					p.getInventory().addItem(Enchants.undying());
				}
				if(arg.equals("mites")) {
					p.getInventory().addItem(Enchants.mites());
				}
				if(arg.equals("gemstone_levitation")) {
					p.getInventory().addItem(gems.levitiationget());
				}
				if(arg.equals("gemstone_breath")) {
					p.getInventory().addItem(gems.water_breathingget());
				}
				if(arg.equals("gemstone_speed")) {
					p.getInventory().addItem(gems.speedget());
				}
				if(arg.equals("gemstone_jump")) {
					p.getInventory().addItem(gems.jumpget());
				}
				if(arg.equals("nomad")) {
					LivingEntity entity = (LivingEntity) p.getWorld().spawnEntity(p.getLocation(), EntityType.WANDERING_TRADER);
					createNomad.start(entity);
				}
				if(arg.equals("mite")) {
					custom_endermite.spawn(p.getLocation());
				}
				if(arg.equals("smelt")) {
					p.getInventory().addItem(Enchants.smelt());
				}
				if(arg.equals("nuke")) {
					p.getInventory().addItem(nukes.nuke0());
				}
				if(arg.equals("addon-nordic-giant")) {
					if(Refs.getReload()) {
						giant.spawn(p.getLocation());
					}else {
						p.sendMessage(ChatColor.GOLD+"[MoreBosses]: "+ChatColor.RED+"Error: Dev-locked mob");
						p.sendMessage(ChatColor.GOLD+"[MoreBosses]: "+ChatColor.RED+"Information: AI not Finished (WIP)");
					}
				}
				if(arg.equals("addon-nordic-boar")) {
					boar.spawn(p.getLocation());
				}
				if(arg.equals("addon-nordic-fenrir")) {
					fenrir.spawn(p.getLocation());
				}
				if(arg.equals("shard_0")) {
					p.getInventory().addItem(shards.Midgardshard());
				}
				if(arg.equals("shard_1")) {
					p.getInventory().addItem(shards.Alfheimrshard());
				}
				if(arg.equals("wolfram_ore")) {
					p.getInventory().addItem(wolfram.ore());
				}
				if(arg.equals("runes")) {
					p.getInventory().addItem(runes.damage());
					p.getInventory().addItem(runes.health());
					p.getInventory().addItem(runes.speed());
					p.getInventory().addItem(runes.knockback());
				}
				if(arg.equals("human")) {
					Human.spawn(p.getLocation(), p);
				}
				if(arg.equals("spoils_books")) {
					p.sendMessage(ChatColor.RED+"[Error]: Not yet available, expected release: Morebosses Entire recode");
				}
				if(arg.equals("bleed")) {
					p.getInventory().addItem(Enchants.bleed(1));
				}
				if(arg.equals("spirit_armor")) {
					p.getInventory().addItem(spirit.boots(), spirit.chestplate(),spirit.helmet(),spirit.pants());
				}
				if(arg.equals("cores")) {
					p.getInventory().addItem(spirit.core(), spirit.core_charged_wolf(), spirit.blood_fenrir(), spirit.spirit_item());
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
			if (args.length == 2) {
				ArrayList<String> available = new ArrayList<String>();
				if(args[0].toLowerCase().equals("mob")) {
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
					available.add("phantom");
					available.add("vex");
					available.add("bosses.trader_merchant");
					available.add("nomad");
					available.add("mite");
					available.add("addon-nordic-giant");
					available.add("addon-nordic-boar");
					available.add("addon-nordic-fenrir");
					available.add("human");
				}else if(args[0].toLowerCase().equals("item")){
					available.add("ichor");
					available.add("ghast_totem");
					available.add("tissue");
					available.add("nuke");
					available.add("challengers_token");
					available.add("fleet");
					available.add("ember");
					available.add("lifesteal");
					available.add("undying");
					available.add("mites");
					available.add("smelt");
					available.add("bleed");
					available.add("gemstone_levitation");
					available.add("gemstone_breath");
					available.add("gemstone_speed");
					available.add("gemstone_jump");
					available.add("shard_0");
					available.add("shard_1");
					available.add("wolfram_ore");
					available.add("runes");
					available.add("spoils_books");
					available.add("spirit_armor");
					available.add("lore0");
					available.add("cores");
				}
				
				ArrayList<String> mobs = new ArrayList<String>();
				if (!args[1].equals("")) {
					for(String type: available) {
						if (type.toLowerCase().contains(args[1].toLowerCase())) {
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
			}else if(args.length==1) {
				ArrayList<String> available = new ArrayList<String>();
				available.add("item");
				available.add("mob");
				ArrayList<String> mobs = new ArrayList<String>();
				if(!args[0].toLowerCase().equals("")) {
					for(String type:available) {
						if(type.toLowerCase().startsWith(args[0].toLowerCase())) {
							mobs.add(type.toLowerCase());
						}
					}
				}else {
					for(String type:available) {
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
