package me.Derpy.Bosses.Addons.Nordic.Mobs;

import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.craftbukkit.v1_14_R1.CraftWorld;
import org.bukkit.craftbukkit.v1_14_R1.entity.CraftPlayer;
import org.bukkit.entity.Player;

import com.mojang.authlib.GameProfile;

import net.minecraft.server.v1_14_R1.DamageSource;
import net.minecraft.server.v1_14_R1.Entity;
import net.minecraft.server.v1_14_R1.EntityHuman;
import net.minecraft.server.v1_14_R1.PacketPlayOutNamedEntitySpawn;
import net.minecraft.server.v1_14_R1.PlayerConnection;
import net.minecraft.server.v1_14_R1.World;
import net.minecraft.server.v1_14_R1.WorldServer;

public class Human extends EntityHuman{

	public Human(World world, GameProfile gameprofile) {
		super(world, gameprofile);
		// TODO Auto-generated constructor stub
	}
	@Override
	public void collide(Entity entity) {
		super.collide(entity);
	}
	@Override
	public boolean damageEntity0(DamageSource source, float f) {
		super.damageEntity(source, f);
		return true;
	}
	@Override
	public boolean isCreative() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isSpectator() {
		// TODO Auto-generated method stub
		return false;
	}
	
	public static void spawn(final Location location, Player p) {
		  WorldServer world = ((CraftWorld) location.getWorld()).getHandle();
//		  MinecraftServer server = ((CraftServer) Bukkit.getServer()).getServer();
		  Bukkit.getConsoleSender().sendMessage(p.getUniqueId().toString());
		  Human human = new Human(world, new GameProfile(UUID.fromString("ce513964-a9d0-424b-a80b-9b1d5a292a7e"), "Derpy00001"));
		  human.setLocation(location.getX(), location.getY(), location.getZ(), location.getYaw(), location.getPitch());
		  world.addEntity(human);
		  for(Player plr : Bukkit.getOnlinePlayers()) {
			  PlayerConnection connection = ((CraftPlayer) plr).getHandle().playerConnection;
			  connection.sendPacket(new PacketPlayOutNamedEntitySpawn(human));
		  }
		  
//		  EntityPlayer plr = new EntityPlayer(server, world, new GameProfile(UUID.fromString("ce513964-a9d0-424b-a80b-9b1d5a292a7e"), "Derpy00001"), new PlayerInteractManager(world));
//		  final EntityPlayer plr = new EntityPlayer(server, world, new GameProfile(p.getUniqueId(), p.getName()), new PlayerInteractManager(world));
//		  plr.playerInteractManager.setGameMode(EnumGamemode.SURVIVAL);
//		  Refs.npcs.add(plr);
//		  plr.setLocation(location.getX(), location.getY(), location.getZ(), location.getYaw(), location.getPitch());
////		  plr.setPosition(location.getDirection().getX(), location.getDirection().getY(), location.getDirection().getZ());
////		  plr.setPositionRotation(location.getX(), location.getY(), location.getZ(), location.getYaw(), location.getPitch());
//		  
//		  final CraftHumanEntity plr0 = (CraftHumanEntity) ((CraftEntity)plr.getBukkitEntity());
//		  for(final Player pls : Bukkit.getOnlinePlayers())
//	        {
//	            final PlayerConnection connection = ((CraftPlayer) pls).getHandle().playerConnection;
//	            connection.sendPacket(new PacketPlayOutPlayerInfo(EnumPlayerInfoAction.ADD_PLAYER, ((EntityPlayer)plr)));
//	            connection.sendPacket(new PacketPlayOutNamedEntitySpawn(plr));
//	            connection.sendPacket(new PacketPlayOutPlayerInfo(EnumPlayerInfoAction.UPDATE_GAME_MODE, ((EntityPlayer)plr)));
//	            connection.sendPacket(new PacketPlayOutEntityHeadRotation(plr, (byte) 0));
//	            connection.sendPacket(new PacketPlayOutEntityHeadRotation(plr, (byte) location.getYaw()));
//	            new BukkitRunnable() {
//
//					@Override
//					public void run() {
//						// TODO Auto-generated method stub
//			            connection.sendPacket(new PacketPlayOutEntityHeadRotation(plr, (byte) -((pls.getLocation().getYaw()%360.)*256/360) ));
//		  			  connection.sendPacket(new PacketPlayOutAnimation(plr, 0));
//					}
//	            	
//	            }.runTaskTimer(MoreBosses.getPlugin(MoreBosses.class), 1, 1);
//	        }
//		return plr0;
		  
	  }
	
}