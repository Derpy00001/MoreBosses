package me.Derpy.Bosses.Addons.Nordic.Items;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.BookMeta;
import org.bukkit.inventory.meta.BookMeta.Generation;

import me.Derpy.Bosses.utilities.Random;
import net.md_5.bungee.api.ChatColor;
// https://lingojam.com/StandardGalacticAlphabet
public class Story {
	private static List<String> tattered = Arrays.asList("Ripped", "Unintelligable", "Stained");
	public static ItemStack book0() {
		ItemStack book = new ItemStack(Material.WRITTEN_BOOK);
		BookMeta meta = (BookMeta) book.getItemMeta();
		meta.setAuthor("↸ᒷ∷!¡||");
		meta.setTitle("⍑ᒷꖎ!¡");
		meta.setGeneration(Generation.TATTERED);
		ArrayList<String> texts = new ArrayList<String>();
		texts.add("リᒷᔑ∷ʖ|| ⍊╎ꖎꖎᔑ⊣ᒷᓭ ⍑ᔑ⍊ᒷ ʖᒷᒷリ ᓭᒷᒷ╎リ⊣ ᓭℸ ̣ ∷ᔑリ⊣ᒷ ᓵ∷ᒷᔑℸ ̣ ⚍∷ᒷᓭ ꖎᔑℸ ̣ ᒷꖎ||.\r\n" + 
				" ℸ ̣ ⍑ᒷ ᓭꖌ|| ⍑ᔑᓭ ʖᒷᓵ𝙹ᒲᒷ ∷ᒷ↸, ∷ᔑ⊣リᔑ∷𝙹ꖌ ᒲᔑ|| ⍑ᔑ⍊ᒷ ᔑ∷∷╎⍊ᒷ↸.");
		texts.add("text");
		texts.add("∷⚍ᒲ𝙹∷ᓭ ⍑ᔑ⍊ᒷ ᓭ!¡∷ᒷᔑ↸,\r\n" + 
				"ℸ ̣ ⍑ᒷ ⊣𝙹↸ᓭ ᔑ∷ᒷ ᔑℸ ̣  ∴ᔑ∷,\r\n" + 
				"ᓵ⍑ᔑ𝙹ᓭ ⍑ᔑᓭ ʖᒷ⊣⚍リ ᓵ𝙹リᓭ⚍ᒲ╎リ⊣ ℸ ̣ ⍑ᒷ ∴𝙹∷ꖎ↸.\r\n" + 
				" ||╎ꖎ ∴╎ꖎꖎ ⎓ᔑꖎꖎ, ℸ ̣ ⍑ᒷ ∷ᒷᔑꖎᒲᓭ ᓭ⍑ᔑꖎꖎ ↸╎ᒷ ∴╎ℸ ̣ ⍑.\r\n" + 
				"");
		texts.add("text");
		texts.add("ℸ ̣ ⍑ᒷ ∷ᒷᔑꖎᒲᓭ ⍑ᔑ⍊ᒷ ʖᒷ⊣⚍リ ℸ ̣ 𝙹 ᒲᒷ∷⊣ᒷ,\r\n" + 
				"⚍∷ ∴𝙹∷ꖎ↸ᓭ ᔑ∷ᒷ ╎リℸ ̣ ᒷ∷ℸ ̣ ∴╎リᒷ↸.\r\n" + 
				" ℸ ̣ ⍑ᒷ∷ᒷ ╎ᓭ ᔑリ ╎ᒲʖᔑꖎᔑリᓵᒷ;\r\n" + 
				"∷↸ᒷ∷ ⍑ᔑᓭ ꖎ𝙹ᓭℸ ̣  ℸ ̣ ⍑ᒷ ∴ᔑ∷ ℸ ̣ 𝙹 ᓵ⍑ᔑ𝙹ᓭ,\r\n" + 
				"ℸ ̣ ⍑ᒷ ∷ᒷᒲᔑ╎リ╎リ⊣ ⊣𝙹↸ᓭ ⎓ꖎ𝙹𝙹↸ ╎リℸ ̣ 𝙹 𝙹⚍∷ ∴𝙹∷ꖎ↸ᓭ");
		texts.add("text6");
		texts.add("text7");
		texts.add("ℸ ̣ ⍑ᒷ ⎓ꖎᔑᒲᒷᓭ 𝙹⎓ ⍑ᒷꖎ ⍑ᔑ⍊ᒷ ʖᒷ⊣⚍リ ℸ ̣ 𝙹 ⎓ꖎ𝙹∴ ╎リℸ ̣ 𝙹\r\n" + 
				"⚍∷ ∷ᒷᔑꖎᒲ,\r\n" + 
				"ℸ ̣ ⍑ᒷ ╎ᒲᒲ𝙹∷ℸ ̣ ᔑꖎᓭ ᔑリ↸ ℸ ̣ ⍑ᒷ╎∷ ᓭ𝙹ꖎ↸╎ᒷ∷ᓭ ᔑ∷ᒷ ᓭꖎᔑ⚍⊣⍑ℸ ̣ ᒷ∷╎リ⊣\r\n" + 
				"⚍ᓭ, ╎ ⎓ᒷᔑ∷ ╎ ∴𝙹リℸ ̣  ꖎ╎⍊ᒷ ᒲ⚍ᓵ⍑ ꖎ𝙹リ⊣ᒷ∷");
		texts.add("text9");
		for(String t : texts) {
			if(Random.random(0.15)) {
				if(!t.contains("text")) {
					meta.addPage(t);
				}else {
					meta.addPage(ChatColor.DARK_RED+""+ChatColor.DARK_RED+tattered.get(Random.random(tattered)));
				}
			}else {
				meta.addPage(ChatColor.DARK_RED+""+ChatColor.DARK_RED+tattered.get(Random.random(tattered)));
			}
		}
		book.setItemMeta(meta);
		return book;
	}
}
