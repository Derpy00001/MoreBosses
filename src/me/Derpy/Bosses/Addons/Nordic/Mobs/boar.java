package me.Derpy.Bosses.Addons.Nordic.Mobs;

import org.bukkit.Location;
import org.bukkit.craftbukkit.v1_14_R1.CraftWorld;
import org.bukkit.craftbukkit.v1_14_R1.entity.CraftEntity;
import org.bukkit.entity.Ravager;
import org.bukkit.event.entity.CreatureSpawnEvent.SpawnReason;

import net.minecraft.server.v1_14_R1.EntityHuman;
import net.minecraft.server.v1_14_R1.EntityLightning;
import net.minecraft.server.v1_14_R1.EntityRavager;
import net.minecraft.server.v1_14_R1.EntityTypes;
import net.minecraft.server.v1_14_R1.EnumHand;
import net.minecraft.server.v1_14_R1.PathfinderGoalFloat;
import net.minecraft.server.v1_14_R1.PathfinderGoalHurtByTarget;
import net.minecraft.server.v1_14_R1.PathfinderGoalMeleeAttack;
import net.minecraft.server.v1_14_R1.PathfinderGoalNearestAttackableTarget;
import net.minecraft.server.v1_14_R1.PathfinderGoalRandomLookaround;
import net.minecraft.server.v1_14_R1.PathfinderGoalRandomStrollLand;
import net.minecraft.server.v1_14_R1.World;


public class boar extends EntityRavager{
  
  public boar(EntityTypes<? extends EntityRavager> var0, World var1) {
    super(var0, var1);
  }
  @SuppressWarnings({"rawtypes", "unchecked" })
  @Override
  public void initPathfinder() {
    this.goalSelector.a(1, new PathfinderGoalFloat(this));
    this.goalSelector.a(2, new PathfinderGoalMeleeAttack(this, 1.0D, false));
    this.goalSelector.a(3, new PathfinderGoalRandomStrollLand(this, 1.0D));
    this.goalSelector.a(8, new PathfinderGoalRandomLookaround(this));
    
    this.targetSelector.a(1, (new PathfinderGoalHurtByTarget(this, new Class[0])).a(new Class[0]));
    this.targetSelector.a(2, new PathfinderGoalNearestAttackableTarget(this, EntityHuman.class, true));
    this.targetSelector.a(2, new PathfinderGoalNearestAttackableTarget(this, undead_viking_0.class, true));
    this.targetSelector.a(2, new PathfinderGoalNearestAttackableTarget(this, undead_viking_1.class, true));
  }
  @Override
  public int dD() {
	  return 0;
  }
  @Override
  public void onLightningStrike(EntityLightning entitylightning) {
  }
  @Override
  public boolean a(EntityHuman ent, EnumHand enumhand) {
	return false;
	  
  }
  
  public static Ravager spawn(Location location) {
	  World world = (World) ((CraftWorld) location.getWorld()).getHandle();
	  boar entity = new boar(EntityTypes.RAVAGER, world);
	  entity.setLocation(location.getX(), location.getY(), location.getZ(), location.getYaw(), location.getPitch());
	  world.addEntity(entity, SpawnReason.NATURAL);
	  entity.setCanPickupLoot(false);
	  Ravager entity0 = (Ravager) ((CraftEntity)entity.getBukkitEntity());
	  entity0.setAI(true);
	  entity0.setCustomName("a Boar");
	  entity0.setCustomNameVisible(false);
	  entity0.setRemoveWhenFarAway(true);
	return entity0;
	  
  }




}
