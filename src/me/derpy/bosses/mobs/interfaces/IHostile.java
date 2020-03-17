package me.derpy.bosses.mobs.interfaces;

public interface IHostile extends IBoss {
	double getDamageMultiplier();

	void setDamageMultiplier(double d);

	double getAttackSpeedMultiplier();

	void setAttackSpeedMultiplier(double d);

	double getFollowRange();

	void setFollowRange(double d);
}
