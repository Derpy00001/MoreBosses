package me.derpy.bosses.mobs.interfaces;

public interface ITitle {
	double getDamageMultiplier();

	double getHealthMultiplier();

	double getSpeedMultiplier();

	int getMinions();

	String getTitleName();

	boolean isBossTitle();
}
