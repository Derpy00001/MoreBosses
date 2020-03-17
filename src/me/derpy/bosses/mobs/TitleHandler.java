package me.derpy.bosses.mobs;

import java.util.ArrayList;
import java.util.List;

import me.derpy.bosses.mobs.interfaces.ITitle;
import me.derpy.bosses.mobs.titles.TiAgile;
import me.derpy.bosses.mobs.titles.TiAngra;
import me.derpy.bosses.mobs.titles.TiInvincible;
import me.derpy.bosses.mobs.titles.TiStrong;
import me.derpy.bosses.mobs.titles.TiWarlord;
import me.derpy.bosses.mobs.titles.TiWeak;
import me.derpy.bosses.utilities.Random;

public class TitleHandler {
	private List<ITitle> titles = new ArrayList<ITitle>();

	public TitleHandler() {
		this.addTitle(new TiWarlord());
		this.addTitle(new TiAgile());
		this.addTitle(new TiAngra());
		this.addTitle(new TiInvincible());
		this.addTitle(new TiStrong());
		this.addTitle(new TiWeak());
	}

	public void addTitle(ITitle title) {
		this.titles.add(title);
	}

	public void removeTitle(ITitle title) {
		this.titles.remove(title);
	}

	public List<ITitle> getTitles() {
		return this.titles;
	}

	public ITitle getRandomTitle() {
		return this.titles.get(Random.random(0, this.getTitles().size() - 1));
	}
}
