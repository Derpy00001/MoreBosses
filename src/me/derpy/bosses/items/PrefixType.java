package me.derpy.bosses.items;

import java.util.ArrayList;
import java.util.List;

import me.derpy.bosses.utilities.Random;

public enum PrefixType {
	BROKEN, SLOW, DAMAGED, SHARP, DEADLY, LEGENDARY, GODLY, MISFORGED, SMALL, LARGE, NORMAL;

	public static PrefixType getPrefix() {
		List<PrefixType> types = new ArrayList<PrefixType>();
		for (PrefixType type : PrefixType.values()) {
			types.add(type);
		}
		return types.get(Random.random(0, types.size() - 1));
	}
}
