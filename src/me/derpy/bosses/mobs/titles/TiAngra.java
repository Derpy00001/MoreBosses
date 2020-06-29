package me.derpy.bosses.mobs.titles;

import me.derpy.bosses.mobs.interfaces.ITitle;

public class TiAngra implements ITitle {

	@Override
	public double getDamageMultiplier() {
		// TODO Auto-generated method stub
		return 1.5;
	}

	@Override
	public double getHealthMultiplier() {
		// TODO Auto-generated method stub
		return 2;
	}

	@Override
	public double getSpeedMultiplier() {
		// TODO Auto-generated method stub
		return 1.2;
	}

	@Override
	public int getMinions() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public String getTitleName() {
		// TODO Auto-generated method stub
		return "Angra Mainyu";
	}

	@Override
	public boolean isBossTitle() {
		// TODO Auto-generated method stub
		return false;
	}
}
