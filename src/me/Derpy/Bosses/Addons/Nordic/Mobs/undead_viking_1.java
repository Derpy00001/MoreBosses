package me.Derpy.Bosses.Addons.Nordic.Mobs;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.craftbukkit.v1_14_R1.CraftWorld;
import org.bukkit.craftbukkit.v1_14_R1.entity.CraftEntity;
import org.bukkit.entity.Skeleton;
import org.bukkit.event.entity.CreatureSpawnEvent.SpawnReason;
import org.bukkit.inventory.ItemStack;

import me.Derpy.Bosses.utilities.Random;
import net.minecraft.server.v1_14_R1.EntityGiantZombie;
import net.minecraft.server.v1_14_R1.EntityHuman;
import net.minecraft.server.v1_14_R1.EntitySkeleton;
import net.minecraft.server.v1_14_R1.EntityTypes;
import net.minecraft.server.v1_14_R1.PathfinderGoalFloat;
import net.minecraft.server.v1_14_R1.PathfinderGoalHurtByTarget;
import net.minecraft.server.v1_14_R1.PathfinderGoalMeleeAttack;
import net.minecraft.server.v1_14_R1.PathfinderGoalNearestAttackableTarget;
import net.minecraft.server.v1_14_R1.PathfinderGoalRandomLookaround;
import net.minecraft.server.v1_14_R1.PathfinderGoalRandomStrollLand;
import net.minecraft.server.v1_14_R1.World;


public class undead_viking_1 extends EntitySkeleton{
  
  public undead_viking_1(EntityTypes<? extends EntitySkeleton> var0, World var1) {
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
    this.targetSelector.a(2, new PathfinderGoalNearestAttackableTarget(this, EntityGiantZombie.class, true));
    this.targetSelector.a(2, new PathfinderGoalNearestAttackableTarget(this, boar.class, true));
  }
  public static Skeleton spawn(Location location) {
	  World world = (World) ((CraftWorld) location.getWorld()).getHandle();
	  undead_viking_1 entity = new undead_viking_1(EntityTypes.SKELETON, world);
	  entity.setLocation(location.getX(), location.getY(), location.getZ(), location.getYaw(), location.getPitch());
	  world.addEntity(entity, SpawnReason.NATURAL);
	  entity.setCanPickupLoot(false);
	  Skeleton entity0 = (Skeleton) ((CraftEntity)entity.getBukkitEntity());
	  entity0.setAI(true);
	  entity0.setCustomName("an Undead Viking");
	  entity0.setCustomNameVisible(false);
	  entity0.setRemoveWhenFarAway(true);
	  if(Random.random(0.5)) {
		  entity0.getEquipment().setBoots(new ItemStack(Material.IRON_BOOTS));
		  entity0.getEquipment().setLeggings(new ItemStack(Material.IRON_LEGGINGS));
		  entity0.getEquipment().setChestplate(new ItemStack(Material.IRON_CHESTPLATE));
		  entity0.getEquipment().setHelmet(new ItemStack(Material.IRON_HELMET));
	  }else {
		  entity0.getEquipment().setBoots(new ItemStack(Material.CHAINMAIL_BOOTS));
		  entity0.getEquipment().setLeggings(new ItemStack(Material.CHAINMAIL_LEGGINGS));
		  entity0.getEquipment().setChestplate(new ItemStack(Material.CHAINMAIL_CHESTPLATE));
		  entity0.getEquipment().setHelmet(new ItemStack(Material.CHAINMAIL_HELMET));
	  }
	  if(Random.random(0.5)) {
		  entity0.getEquipment().setItemInMainHand(new ItemStack(Material.IRON_AXE));
	  }else {
		  entity0.getEquipment().setItemInMainHand(new ItemStack(Material.IRON_SWORD));
	  }
	  if(Random.random(0.5)) {
		  entity0.getEquipment().setItemInOffHand(new ItemStack(Material.SHIELD));
	  }else {
		  entity0.getEquipment().setItemInOffHand(entity0.getEquipment().getItemInOffHand());
	  }
	return entity0;
	  
  }




}
