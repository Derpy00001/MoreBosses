package me.derpy.bosses.items.blueprints.misc;

import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.BookMeta;

import me.derpy.bosses.items.blueprints.BLootable;
import net.md_5.bungee.api.chat.BaseComponent;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.chat.HoverEvent;

public class TutorialBook extends BLootable {
	@Override
	public ItemStack getItem() {
		ItemStack item = new ItemStack(Material.WRITTEN_BOOK);
		BookMeta meta = (BookMeta) item.getItemMeta();
		meta.setAuthor("Derpy");
		meta.setTitle("Morebosses Guide");
		meta.spigot().addPage(
				createLink("Wiki", "https://github.com/Derpy00001/MoreBosses/wiki", "Open the Morebosses Wiki"));
		item.setItemMeta(meta);
		return item;
	}

	@Override
	public NamespacedKey getNamespacedKey() {
		return NamespacedKey.minecraft("tutorial-book");
	}

	public static BaseComponent[] createLink(String text, String link, String hoverText) {
		ComponentBuilder component = new ComponentBuilder(text);
		if (link != null) {
			component.event(new ClickEvent(ClickEvent.Action.OPEN_URL, link));
		}
		if (hoverText != null) {
			component.event(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder(hoverText).create()));
		}
		return component.create();
	}

}
