package me.Derpy.Bosses;


import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameRule;
import org.bukkit.World;
import org.bukkit.WorldCreator;
import org.bukkit.event.Listener;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import me.Derpy.Bosses.commands.bspawn;
import me.Derpy.Bosses.commands.debug;
import me.Derpy.Bosses.commands.event;
import me.Derpy.Bosses.commands.language;
import me.Derpy.Bosses.commands.saveworld;
import me.Derpy.Bosses.commands.setspawn;
import me.Derpy.Bosses.commands.tp;
import me.Derpy.Bosses.listeners.Enchants;
import me.Derpy.Bosses.listeners.Use;
import me.Derpy.Bosses.listeners.ondamage;
import me.Derpy.Bosses.listeners.ondeath;
import me.Derpy.Bosses.listeners.ondismount;
import me.Derpy.Bosses.listeners.onspawn;
import me.Derpy.Bosses.utilities.UpdateChecker;
import me.Derpy.Bosses.utilities.items.cursed_diamond;
import me.Derpy.Bosses.utilities.items.elytra;
import me.Derpy.Bosses.utilities.items.forge;
import me.Derpy.Bosses.utilities.items.ghast;
import me.Derpy.Bosses.utilities.items.ghast_totem;
import me.Derpy.Bosses.utilities.items.ichor;
import me.Derpy.Bosses.utilities.items.infused_tear;

public class Main extends JavaPlugin implements Listener {
	@Override
	public void onEnable() {
		World world = Bukkit.createWorld(new WorldCreator("MoreBosses-void"));
		world.setAutoSave(false);
		world.setGameRule(GameRule.DO_MOB_SPAWNING, false);
		world.setGameRule(GameRule.SPECTATORS_GENERATE_CHUNKS, false);
		world.setGameRule(GameRule.MOB_GRIEFING, false);
		world.setGameRule(GameRule.NATURAL_REGENERATION, false);
		this.saveDefaultConfig();
		// Listeners
		PluginManager gm = getServer().getPluginManager();
		onspawn listener = new onspawn(this);
		ondeath listener2 = new ondeath(this);
		ondamage listener4 = new ondamage(this);
		Enchants listener5 = new Enchants(this);
		ondismount listener6 = new ondismount(this);
		gm.registerEvents(listener, this);
		gm.registerEvents(listener2, this);
		gm.registerEvents(new Use(this), this);
		gm.registerEvents(listener4, this);
		gm.registerEvents(listener5, this);
		gm.registerEvents(listener6, this);
		
		// Ver checker
		UpdateChecker updater = new UpdateChecker(this, 69355);
		Bukkit.getConsoleSender().sendMessage(ChatColor.GOLD + "[More Bosses]:"+ChatColor.YELLOW+" Thank you for using More Bosses!");
		Bukkit.getConsoleSender().sendMessage(ChatColor.GOLD+"[More Bosses]:"+ChatColor.YELLOW+" Version "+getDescription().getVersion().toString());
		Bukkit.getConsoleSender().sendMessage(ChatColor.GOLD+"[More Bosses]:"+ChatColor.YELLOW+" Checking for updates...");
		try {
			if (updater.checkForUpdates()) {
				if(!(getDescription().getVersion().toString().equals("TEST_VERSION"))) {
					Bukkit.getConsoleSender().sendMessage(ChatColor.GOLD + "[More Bosses]: "+ChatColor.RED+"Plugin is not up to date!");
					Bukkit.getConsoleSender().sendMessage(ChatColor.GOLD+"[More Bosses]: "+ChatColor.RED+"Please install the lastest version at https://www.spigotmc.org/resources/more-bosses.69355/");
				}else {
					Bukkit.getConsoleSender().sendMessage(ChatColor.GOLD + "[More Bosses]: "+ChatColor.RED+"DEV VERSION LOADED");
					Bukkit.getConsoleSender().sendMessage(ChatColor.GOLD + "[More Bosses]: "+ChatColor.RED+"If you should not have access to this version, please immediately contact a developer");
				}
			}else {
				Bukkit.getConsoleSender().sendMessage(ChatColor.GOLD + "[More Bosses]: "+ChatColor.GREEN+"Plugin is up to date!");
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			Bukkit.getConsoleSender().sendMessage(ChatColor.GOLD + "[More Bosses]: "+ChatColor.RED+"Failed to connect [Version Checker]");
		}
		Bukkit.getConsoleSender().sendMessage(ChatColor.GOLD+"[More Bosses]: "+ChatColor.GREEN+"Have fun! :D ~Derpy");
		//Commands
		new bspawn(this);
		new debug(this);
		new event(this);
		new language(this);
		new tp(this);
		new setspawn(this);
		new saveworld(this);
		
		//CRAFTING
		cursed_diamond cursed_diamond = new cursed_diamond();
		cursed_diamond.customRecipe();
		ichor ichor = new ichor();
		ichor.customRecipeStick();
		ichor.customRecipeSword();
		ichor.customRecipeBoots();
		ichor.customRecipeChest();
		ichor.customRecipeHelmet();
		ichor.customRecipeLeggings();
		elytra elytra = new elytra();
		elytra.customRecipeElytra();
		forge forge = new forge();
		forge.customRecipe();
		ghast_totem totem = new ghast_totem();
		totem.customRecipe();
		infused_tear tear = new infused_tear();
		tear.customRecipe();
		ghast.tissue_armor ghast = new ghast.tissue_armor();
		ghast.customRecipe_boots();
		ghast.customRecipe_chestplate();
		ghast.customRecipe_helmet();
		ghast.customRecipe_leggings();
		
	}
	public void onDisable() {
		for(World world : Bukkit.getWorlds()) {
			if(world.getName().startsWith("MoreBosses")) {
				Bukkit.getConsoleSender().sendMessage(ChatColor.GOLD+"[MoreBosses]: Unloading "+world.getName());
				Bukkit.unloadWorld(world, false);
			}
		}
	}
}
