package me.derpy.bosses.utilities;

import net.md_5.bungee.api.chat.BaseComponent;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.ClickEvent.Action;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.chat.HoverEvent;

public class ExtraUtilities {
	public static BaseComponent[] createLink(String text, String link, String hoverText) {
		ComponentBuilder builder = new ComponentBuilder(text);
		if(link!=null) {
			builder.event(new ClickEvent(Action.OPEN_URL, link));
		}
		if(hoverText!=null) {
			builder.event(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder(hoverText).create()));
		}
		return builder.create();
	}
	
	public static int getDynamicSize(int size) {
		if (size == 0) {
			size = 1;
		}
		double calc = (double) size / 9;
		size = (int) Math.ceil(calc);
		if (size == 0) {
			size = 1;
		}
		return size * 9;
	}
}
