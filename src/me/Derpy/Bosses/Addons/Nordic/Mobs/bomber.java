package me.Derpy.Bosses.Addons.Nordic.Mobs;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.craftbukkit.v1_14_R1.CraftWorld;
import org.bukkit.craftbukkit.v1_14_R1.entity.CraftEntity;
import org.bukkit.entity.Enderman;
import org.bukkit.event.entity.CreatureSpawnEvent.SpawnReason;
import org.bukkit.inventory.ItemStack;

import net.minecraft.server.v1_14_R1.EntityEnderman;
import net.minecraft.server.v1_14_R1.EntityHuman;
import net.minecraft.server.v1_14_R1.EntityTypes;
import net.minecraft.server.v1_14_R1.PathfinderGoalFloat;
import net.minecraft.server.v1_14_R1.PathfinderGoalHurtByTarget;
import net.minecraft.server.v1_14_R1.PathfinderGoalLookAtPlayer;
import net.minecraft.server.v1_14_R1.PathfinderGoalMeleeAttack;
import net.minecraft.server.v1_14_R1.PathfinderGoalRandomLookaround;
import net.minecraft.server.v1_14_R1.PathfinderGoalRandomStrollLand;
import net.minecraft.server.v1_14_R1.World;


public class bomber extends EntityEnderman{
  
  public bomber(EntityTypes<? extends EntityEnderman> var0, World var1) {
    super(var0, var1);
  }

  @Override
  public void initPathfinder() {
	this.goalSelector.a(0, new PathfinderGoalFloat(this));
	this.goalSelector.a(2, new PathfinderGoalMeleeAttack(this, 1.0D, false));
	this.goalSelector.a(7, new PathfinderGoalRandomStrollLand(this, 1.0D, 0.0F));
	this.goalSelector.a(8, new PathfinderGoalLookAtPlayer(this, EntityHuman.class, 8.0F));
	this.goalSelector.a(8, new PathfinderGoalRandomLookaround(this));
	this.targetSelector.a(2, new PathfinderGoalHurtByTarget(this, new Class[0]));
  }
  public static Enderman spawn(Location location) {
	  World world = (World) ((CraftWorld) location.getWorld()).getHandle();
	  bomber entity = new bomber(EntityTypes.ENDERMAN, world);
	  entity.setLocation(location.getX(), location.getY(), location.getZ(), location.getYaw(), location.getPitch());
	  world.addEntity(entity, SpawnReason.NATURAL);
	  entity.setCanPickupLoot(false);
	  Enderman entity0 = (Enderman) ((CraftEntity)entity.getBukkitEntity());
	  entity0.setAI(true);
	  entity0.setCarriedMaterial(new ItemStack(Material.TNT).getData());
	  entity0.setRemoveWhenFarAway(true);
	return entity0;
	  
  }




}
