package me.derpy.bosses.mobs.interfaces;

import java.util.List;

import org.bukkit.Material;

public interface IEquipable extends IHostile {
	List<Material> getWeaponChoices();

	void setWeaponChoices(List<Material> choices);

	void addWeaponChoice(Material material);

	void removeWeaponChoice(Material material);

	List<Material> getHelmetChoices();

	void setHelmetChoices(List<Material> choices);

	void addHelmetChoice(Material material);

	void removeHelmetChoice(Material material);

	List<Material> getChestplateChoices();

	void setChestplateChoices(List<Material> choices);

	void addChestplateChoice(Material material);

	void removeChestplateChoice(Material material);

	List<Material> getLeggingChoices();

	void setLeggingChoices(List<Material> choices);

	void addLeggingChoice(Material material);

	void removeLeggingChoice(Material material);

	List<Material> getBootChoices();

	void setBootChoices(List<Material> choices);

	void addBootChoice(Material material);

	void removeBootChoice(Material material);
}
