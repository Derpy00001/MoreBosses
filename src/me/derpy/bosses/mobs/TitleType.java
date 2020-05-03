package me.derpy.bosses.mobs;

import java.util.Arrays;

import me.derpy.bosses.mobs.interfaces.ITitle;
import me.derpy.bosses.mobs.titles.TiAgile;
import me.derpy.bosses.mobs.titles.TiAngra;
import me.derpy.bosses.mobs.titles.TiInvincible;
import me.derpy.bosses.mobs.titles.TiOverlord;
import me.derpy.bosses.mobs.titles.TiStrong;
import me.derpy.bosses.mobs.titles.TiWarlord;
import me.derpy.bosses.mobs.titles.TiWeak;
import me.derpy.bosses.utilities.Random;

public enum TitleType {
	WARLORD(new TiWarlord()), AGILE(new TiAgile()), ANGRA(new TiAngra()), INVINCIBLE(new TiInvincible()),
	STRONG(new TiStrong()), WEAK(new TiWeak()), OVERLORD(new TiOverlord());

	private ITitle ititle;

	TitleType(ITitle title) {
		this.ititle = title;
	}

	public ITitle getTitle() {
		return this.ititle;
	}

	public static TitleType getRandomTitle() {
		return Arrays.asList(TitleType.values()).get(Random.random(0, TitleType.values().length - 1));
	}
}
