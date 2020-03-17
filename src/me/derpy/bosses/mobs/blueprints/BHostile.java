package me.derpy.bosses.mobs.blueprints;

import me.derpy.bosses.mobs.interfaces.IHostile;

public class BHostile extends BBoss implements IHostile {
	double damageMultiplier = 1;
	double attackMultiplier = 1;
	double followMultiplier = 1;

	public BHostile() {

	}

	@Override
	public double getDamageMultiplier() {
		// TODO Auto-generated method stub
		return this.damageMultiplier;
	}

	@Override
	public void setDamageMultiplier(double d) {
		// TODO Auto-generated method stub
		this.damageMultiplier = d;
	}

	@Override
	public double getAttackSpeedMultiplier() {
		// TODO Auto-generated method stub
		return this.attackMultiplier;
	}

	@Override
	public void setAttackSpeedMultiplier(double d) {
		// TODO Auto-generated method stub
		this.attackMultiplier = d;
	}

	@Override
	public double getFollowRange() {
		// TODO Auto-generated method stub
		return this.followMultiplier;
	}

	@Override
	public void setFollowRange(double d) {
		// TODO Auto-generated method stub
		this.followMultiplier = d;
	}

}
