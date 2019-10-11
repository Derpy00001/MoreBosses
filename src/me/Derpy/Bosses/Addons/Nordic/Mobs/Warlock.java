package me.Derpy.Bosses.Addons.Nordic.Mobs;

import org.bukkit.Location;
import org.bukkit.craftbukkit.v1_14_R1.CraftWorld;
import org.bukkit.craftbukkit.v1_14_R1.entity.CraftEntity;
import org.bukkit.entity.Illusioner;
import org.bukkit.event.entity.CreatureSpawnEvent.SpawnReason;

import me.Derpy.Bosses.MoreBosses;
import me.Derpy.Bosses.utilities.GetName;
import net.minecraft.server.v1_14_R1.EntityIllagerIllusioner;
import net.minecraft.server.v1_14_R1.EntityTypes;
import net.minecraft.server.v1_14_R1.GenericAttributes;
import net.minecraft.server.v1_14_R1.World;

public class Warlock extends EntityIllagerIllusioner{

	public Warlock(EntityTypes<EntityIllagerIllusioner> entitytypes, World world) {
		super(entitytypes, world);
		// TODO Auto-generated constructor stub
	}
	@Override
	public void initAttributes(){
		super.initAttributes();
		getAttributeInstance(GenericAttributes.KNOCKBACK_RESISTANCE).setValue(100);
		getAttributeInstance(GenericAttributes.MAX_HEALTH).setValue(getAttributeInstance(GenericAttributes.MAX_HEALTH).getValue()*MoreBosses.getPlugin(MoreBosses.class).getConfig().getInt("health_scale.addon.nordic.warlock"));
		getAttributeInstance(GenericAttributes.ATTACK_DAMAGE).setValue(getAttributeInstance(GenericAttributes.ATTACK_DAMAGE).getValue()*MoreBosses.getPlugin(MoreBosses.class).getConfig().getInt("damage_scale.addon.nordic.warlock"));
	}
	public static Illusioner spawn(Location location) {
		World world = (World) ((CraftWorld) location.getWorld()).getHandle();
		Warlock warlock = new Warlock(EntityTypes.ILLUSIONER, world);
		warlock.setLocation(location.getX(), location.getY(), location.getZ(), location.getYaw(), location.getPitch());
		world.addEntity(warlock, SpawnReason.NATURAL);
		warlock.setCanPickupLoot(false);
		Illusioner warlock0 = (Illusioner) ((CraftEntity)warlock.getBukkitEntity());
		warlock0.setAI(true);
		warlock0.setRemoveWhenFarAway(true);
		GetName.getname(warlock0, warlock0, MoreBosses.getPlugin(MoreBosses.class), false);
		return warlock0;
		  
	  }
}
