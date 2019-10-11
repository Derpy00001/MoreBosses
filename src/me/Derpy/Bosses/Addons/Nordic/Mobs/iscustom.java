package me.Derpy.Bosses.Addons.Nordic.Mobs;

import net.minecraft.server.v1_14_R1.Entity;;
public class iscustom {
	public static Boolean check(Entity entity) {
		if(entity instanceof boar) {
			return true;
		}
		if(entity instanceof giant) {
			return true;
		}
		if(entity instanceof undead_viking_0||entity instanceof undead_viking_1) {
			return true;
		}
		if(entity instanceof bomber) {
			return true;
		}
		return false;
	}
}
