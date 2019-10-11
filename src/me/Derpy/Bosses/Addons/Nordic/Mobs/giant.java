package me.Derpy.Bosses.Addons.Nordic.Mobs;

import org.bukkit.Location;
import org.bukkit.craftbukkit.v1_14_R1.CraftWorld;
import org.bukkit.craftbukkit.v1_14_R1.entity.CraftEntity;
import org.bukkit.entity.Giant;
import org.bukkit.event.entity.CreatureSpawnEvent.SpawnReason;

import net.minecraft.server.v1_14_R1.DamageSource;
import net.minecraft.server.v1_14_R1.EntityCow;
import net.minecraft.server.v1_14_R1.EntityEnderman;
import net.minecraft.server.v1_14_R1.EntityGiantZombie;
import net.minecraft.server.v1_14_R1.EntityHuman;
import net.minecraft.server.v1_14_R1.EntityPig;
import net.minecraft.server.v1_14_R1.EntityPillager;
import net.minecraft.server.v1_14_R1.EntitySheep;
import net.minecraft.server.v1_14_R1.EntitySkeleton;
import net.minecraft.server.v1_14_R1.EntitySpider;
import net.minecraft.server.v1_14_R1.EntityTypes;
import net.minecraft.server.v1_14_R1.EntityVindicator;
import net.minecraft.server.v1_14_R1.EntityZombie;
import net.minecraft.server.v1_14_R1.GenericAttributes;
import net.minecraft.server.v1_14_R1.PathfinderGoalFloat;
import net.minecraft.server.v1_14_R1.PathfinderGoalHurtByTarget;
import net.minecraft.server.v1_14_R1.PathfinderGoalMeleeAttack;
import net.minecraft.server.v1_14_R1.PathfinderGoalNearestAttackableTarget;
import net.minecraft.server.v1_14_R1.PathfinderGoalRandomLookaround;
import net.minecraft.server.v1_14_R1.PathfinderGoalRandomStrollLand;
import net.minecraft.server.v1_14_R1.SoundEffects;
import net.minecraft.server.v1_14_R1.World;


public class giant extends EntityGiantZombie{
  
  public giant(EntityTypes<EntityGiantZombie> giant, World var1) {
    super(giant, var1);
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
    this.targetSelector.a(2, new PathfinderGoalNearestAttackableTarget(this, EntityGiantZombie.class, true));
    this.targetSelector.a(2, new PathfinderGoalNearestAttackableTarget(this, EntityZombie.class, true));
    this.targetSelector.a(2, new PathfinderGoalNearestAttackableTarget(this, EntitySkeleton.class, true));
    this.targetSelector.a(2, new PathfinderGoalNearestAttackableTarget(this, EntityEnderman.class, true));
    this.targetSelector.a(2, new PathfinderGoalNearestAttackableTarget(this, EntityPig.class, true));
    this.targetSelector.a(2, new PathfinderGoalNearestAttackableTarget(this, EntityCow.class, true));
    this.targetSelector.a(2, new PathfinderGoalNearestAttackableTarget(this, EntitySheep.class, true));
    this.targetSelector.a(2, new PathfinderGoalNearestAttackableTarget(this, EntitySpider.class, true));
    this.targetSelector.a(2, new PathfinderGoalNearestAttackableTarget(this, EntityPillager.class, true));
    this.targetSelector.a(2, new PathfinderGoalNearestAttackableTarget(this, EntityVindicator.class, true));
  }
  @Override
  public void initAttributes(){
	  super.initAttributes();
	  getAttributeInstance(GenericAttributes.MOVEMENT_SPEED).setValue(0.25D);
	  getAttributeInstance(GenericAttributes.FOLLOW_RANGE).setValue(72);
	  getAttributeInstance(GenericAttributes.KNOCKBACK_RESISTANCE).setValue(100);
  }
  @Override
  protected void c(DamageSource source) {
      a(SoundEffects.ENTITY_ZOMBIE_HURT, 3F,0.2F);
  }
  @Override
  public void B() {
	  a(SoundEffects.ENTITY_HUSK_AMBIENT, 3F, 0.2F);
  }
  public static Giant spawn(Location location) {
	  World world = (World) ((CraftWorld) location.getWorld()).getHandle();
	  giant Giant = new giant(EntityTypes.GIANT, world);
	  Giant.setLocation(location.getX(), location.getY(), location.getZ(), location.getYaw(), location.getPitch());
	  world.addEntity(Giant, SpawnReason.NATURAL);
	  Giant.setCanPickupLoot(false);
	  Giant giant0 = (Giant) ((CraftEntity)Giant.getBukkitEntity());
	  giant0.setAI(true);
	  giant0.setRemoveWhenFarAway(true);
	  
	return giant0;
	  
  }




}
