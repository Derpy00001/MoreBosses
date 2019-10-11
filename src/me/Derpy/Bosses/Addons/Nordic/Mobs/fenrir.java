package me.Derpy.Bosses.Addons.Nordic.Mobs;

import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.craftbukkit.v1_14_R1.CraftWorld;
import org.bukkit.craftbukkit.v1_14_R1.entity.CraftEntity;
import org.bukkit.entity.AreaEffectCloud;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Wolf;
import org.bukkit.event.entity.CreatureSpawnEvent.SpawnReason;
import org.bukkit.potion.PotionData;
import org.bukkit.potion.PotionType;
import org.bukkit.scheduler.BukkitRunnable;

import me.Derpy.Bosses.MoreBosses;
import me.Derpy.Bosses.utilities.GetName;
import net.minecraft.server.v1_14_R1.DamageSource;
import net.minecraft.server.v1_14_R1.EntityHuman;
import net.minecraft.server.v1_14_R1.EntityTypes;
import net.minecraft.server.v1_14_R1.EntityWolf;
import net.minecraft.server.v1_14_R1.GenericAttributes;
import net.minecraft.server.v1_14_R1.PathfinderGoalBeg;
import net.minecraft.server.v1_14_R1.PathfinderGoalFloat;
import net.minecraft.server.v1_14_R1.PathfinderGoalFollowOwner;
import net.minecraft.server.v1_14_R1.PathfinderGoalHurtByTarget;
import net.minecraft.server.v1_14_R1.PathfinderGoalLeapAtTarget;
import net.minecraft.server.v1_14_R1.PathfinderGoalLookAtPlayer;
import net.minecraft.server.v1_14_R1.PathfinderGoalMeleeAttack;
import net.minecraft.server.v1_14_R1.PathfinderGoalNearestAttackableTarget;
import net.minecraft.server.v1_14_R1.PathfinderGoalOwnerHurtByTarget;
import net.minecraft.server.v1_14_R1.PathfinderGoalOwnerHurtTarget;
import net.minecraft.server.v1_14_R1.PathfinderGoalRandomLookaround;
import net.minecraft.server.v1_14_R1.PathfinderGoalRandomStrollLand;
import net.minecraft.server.v1_14_R1.PathfinderGoalSit;
import net.minecraft.server.v1_14_R1.SoundEffect;
import net.minecraft.server.v1_14_R1.SoundEffects;
import net.minecraft.server.v1_14_R1.World;


public class fenrir extends EntityWolf{
  
  public fenrir(EntityTypes<EntityWolf> wolf, World var1) {
    super(wolf, var1);
  }
  @SuppressWarnings({"rawtypes", "unchecked" })
  @Override
  public void initPathfinder() {
	    this.goalSit = new PathfinderGoalSit(this);
	    this.goalSelector.a(1, new PathfinderGoalFloat(this));
	    this.goalSelector.a(2, this.goalSit);
	    this.goalSelector.a(4, new PathfinderGoalLeapAtTarget(this, 0.4F));
	    this.goalSelector.a(5, new PathfinderGoalMeleeAttack(this, 1.0D, true));
	    this.goalSelector.a(6, new PathfinderGoalFollowOwner(this, 1.0D, 10.0F, 2.0F));
	    this.goalSelector.a(8, new PathfinderGoalRandomStrollLand(this, 1.0D));
	    this.goalSelector.a(9, new PathfinderGoalBeg(this, 8.0F));
	    this.goalSelector.a(10, new PathfinderGoalLookAtPlayer(this, EntityHuman.class, 8.0F));
	    this.goalSelector.a(10, new PathfinderGoalRandomLookaround(this));
	    this.targetSelector.a(1, new PathfinderGoalOwnerHurtByTarget(this));
	    this.targetSelector.a(2, new PathfinderGoalOwnerHurtTarget(this));
	    this.targetSelector.a(3, (new PathfinderGoalHurtByTarget(this, new Class[0])).a(new Class[0]));
	    this.targetSelector.a(4, new PathfinderGoalNearestAttackableTarget(this, EntityHuman.class, false));
  }
  @Override
  public void initAttributes(){
	  super.initAttributes();
	  getAttributeInstance(GenericAttributes.MOVEMENT_SPEED).setValue(getAttributeInstance(GenericAttributes.MOVEMENT_SPEED).getValue()*2.3);
	  getAttributeInstance(GenericAttributes.ATTACK_DAMAGE).setValue(getAttributeInstance(GenericAttributes.ATTACK_DAMAGE).getValue()*4.3);
	  getAttributeInstance(GenericAttributes.MAX_HEALTH).setValue(76D*12);
  }
  @Override
  public void tame(EntityHuman var0) {
	  return;
  }
  @Override
  protected SoundEffect getSoundHurt(DamageSource source) {
      a(SoundEffects.ENTITY_WOLF_GROWL, 3F,0.2F);
      return null;
  }
  @Override
  public SoundEffect getSoundAmbient() {
	  a(SoundEffects.ENTITY_WOLF_HOWL, 3F, 0.2F);
	  return null;
  }
  @SuppressWarnings("deprecation")
public static Wolf spawn(Location location) {
	  World world = (World) ((CraftWorld) location.getWorld()).getHandle();
	  fenrir wolf = new fenrir(EntityTypes.WOLF, world);
	  wolf.setLocation(location.getX(), location.getY(), location.getZ(), location.getYaw(), location.getPitch());
	  world.addEntity(wolf, SpawnReason.NATURAL);
	  wolf.setAngry(true);
	  final Wolf wolf0= (Wolf) ((CraftEntity)wolf.getBukkitEntity());
	  wolf0.setMaxHealth(76*6);
	  wolf0.setHealth(wolf0.getMaxHealth());
	  wolf0.setAI(true);
	  wolf0.setRemoveWhenFarAway(true);
	  wolf0.setBreed(false);
//	  if(Random.random(0.5)) {
//		  wolf0.getEquipment().setItemInMainHand(Enchants.bleed(1));
//	  }else {
//		  wolf0.getEquipment().setItemInMainHand(Enchants.bleed(2));
//	  }
//	  wolf0.getEquipment().setItemInMainHandDropChance(100F);
	  new BukkitRunnable() {

		@Override
		public void run() {
			// TODO Auto-generated method stub
			if(wolf0.isValid()) {
				Location loc = wolf0.getLocation();
				AreaEffectCloud cloud = (AreaEffectCloud) wolf0.getWorld().spawnEntity(loc, EntityType.AREA_EFFECT_CLOUD);
				cloud.setDuration(20*1);
				cloud.setParticle(Particle.SQUID_INK);
				cloud.setBasePotionData(new PotionData(PotionType.POISON, false, false));
				cloud.setRadius(3);
			}else {
				this.cancel();
			}
		}
		  
	  }.runTaskTimer(MoreBosses.getPlugin(MoreBosses.class), 20*1, 20*1);
	  GetName.getname(wolf0, wolf0, MoreBosses.getPlugin(MoreBosses.class), false);
	return wolf0;
	  
  }




}
