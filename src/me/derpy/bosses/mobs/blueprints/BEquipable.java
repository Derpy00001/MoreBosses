package me.derpy.bosses.mobs.blueprints;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Material;

import me.derpy.bosses.mobs.interfaces.IEquipable;

public class BEquipable extends BHostile implements IEquipable {
	List<Material> weaponChoices = new ArrayList<Material>();
	List<Material> helmetChoices = new ArrayList<Material>();
	List<Material> chestplateChoices = new ArrayList<Material>();
	List<Material> leggingChoices = new ArrayList<Material>();
	List<Material> bootChoices = new ArrayList<Material>();

	public BEquipable() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public List<Material> getWeaponChoices() {
		// TODO Auto-generated method stub
		return this.weaponChoices;
	}

	@Override
	public void setWeaponChoices(List<Material> choices) {
		// TODO Auto-generated method stub
		this.weaponChoices = choices;
	}

	@Override
	public void addWeaponChoice(Material material) {
		// TODO Auto-generated method stub
		this.weaponChoices.add(material);
	}

	@Override
	public void removeWeaponChoice(Material material) {
		// TODO Auto-generated method stub
		this.weaponChoices.remove(material);
	}

	@Override
	public List<Material> getHelmetChoices() {
		// TODO Auto-generated method stub
		return this.helmetChoices;
	}

	@Override
	public void setHelmetChoices(List<Material> choices) {
		// TODO Auto-generated method stub
		this.helmetChoices = choices;
	}

	@Override
	public void addHelmetChoice(Material material) {
		// TODO Auto-generated method stub
		this.helmetChoices.add(material);
	}

	@Override
	public void removeHelmetChoice(Material material) {
		// TODO Auto-generated method stub
		this.helmetChoices.remove(material);
	}

	@Override
	public List<Material> getChestplateChoices() {
		// TODO Auto-generated method stub
		return this.chestplateChoices;
	}

	@Override
	public void setChestplateChoices(List<Material> choices) {
		// TODO Auto-generated method stub
		this.chestplateChoices = choices;
	}

	@Override
	public void addChestplateChoice(Material material) {
		// TODO Auto-generated method stub
		this.chestplateChoices.remove(material);
	}

	@Override
	public void removeChestplateChoice(Material material) {
		// TODO Auto-generated method stub
		this.chestplateChoices.remove(material);
	}

	@Override
	public List<Material> getLeggingChoices() {
		// TODO Auto-generated method stub
		return this.leggingChoices;
	}

	@Override
	public void setLeggingChoices(List<Material> choices) {
		// TODO Auto-generated method stub
		this.leggingChoices = choices;
	}

	@Override
	public void addLeggingChoice(Material material) {
		// TODO Auto-generated method stub
		this.leggingChoices.add(material);
	}

	@Override
	public void removeLeggingChoice(Material material) {
		// TODO Auto-generated method stub
		this.leggingChoices.remove(material);
	}

	@Override
	public List<Material> getBootChoices() {
		// TODO Auto-generated method stub
		return this.bootChoices;
	}

	@Override
	public void setBootChoices(List<Material> choices) {
		// TODO Auto-generated method stub
		this.bootChoices = choices;
	}

	@Override
	public void addBootChoice(Material material) {
		// TODO Auto-generated method stub
		this.bootChoices.add(material);
	}

	@Override
	public void removeBootChoice(Material material) {
		// TODO Auto-generated method stub
		this.bootChoices.remove(material);
	}

}
