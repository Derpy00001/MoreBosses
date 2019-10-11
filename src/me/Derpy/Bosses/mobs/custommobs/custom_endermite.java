package me.Derpy.Bosses.mobs.custommobs;

import org.bukkit.Location;
import org.bukkit.craftbukkit.v1_14_R1.CraftWorld;
import org.bukkit.craftbukkit.v1_14_R1.entity.CraftEntity;
import org.bukkit.entity.Endermite;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Monster;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import net.minecraft.server.v1_14_R1.EntityEndermite;
import net.minecraft.server.v1_14_R1.EntityTypes;
import net.minecraft.server.v1_14_R1.PathfinderGoalFloat;
import net.minecraft.server.v1_14_R1.PathfinderGoalHurtByTarget;
import net.minecraft.server.v1_14_R1.PathfinderGoalMeleeAttack;
import net.minecraft.server.v1_14_R1.PathfinderGoalNearestAttackableTarget;
import net.minecraft.server.v1_14_R1.PathfinderGoalRandomLookaround;
import net.minecraft.server.v1_14_R1.PathfinderGoalRandomStrollLand;
import net.minecraft.server.v1_14_R1.World;


public class custom_endermite extends EntityEndermite{
  
  public custom_endermite(EntityTypes<EntityEndermite> endermite, World var1) {
    super(endermite, var1);
  }
  @SuppressWarnings({"rawtypes", "unchecked" })
  @Override
  public void initPathfinder() {
    this.goalSelector.a(1, new PathfinderGoalFloat(this));
    this.goalSelector.a(2, new PathfinderGoalMeleeAttack(this, 1.0D, false));
    this.goalSelector.a(3, new PathfinderGoalRandomStrollLand(this, 1.0D));
    this.goalSelector.a(8, new PathfinderGoalRandomLookaround(this));
    
    this.targetSelector.a(1, (new PathfinderGoalHurtByTarget(this, new Class[0])).a(new Class[0]));
    int i = 2;
    for(EntityType e : EntityType.values()) {
    	if(Monster.class.isAssignableFrom(e.getClass())) {
    		this.targetSelector.a(i, new PathfinderGoalNearestAttackableTarget(this, e.getClass(), true));
    		i++;
    	}
    }
  }
  
  public static Endermite spawn(Location location) {
	  World world = (World) ((CraftWorld) location.getWorld()).getHandle();
	  custom_endermite mite = new custom_endermite(EntityTypes.ENDERMITE, world);
	  mite.setLocation(location.getX(), location.getY(), location.getZ(), location.getYaw(), location.getPitch());
	  world.addEntity(mite);
	  mite.setCanPickupLoot(false);
	  Endermite mite0 = (Endermite) ((CraftEntity)mite.getBukkitEntity());
	  mite0.addPotionEffect(new PotionEffect(PotionEffectType.WITHER, 1000000, 1, true));
	  mite0.setRemoveWhenFarAway(true);
	  
	return mite0;
	  
  }




}
