package me.Derpy.Bosses.mobs.merchants;

import java.util.ArrayList;

import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;

import me.Derpy.Bosses.MoreBosses;
import me.Derpy.Bosses.utilities.GetName;
import net.md_5.bungee.api.ChatColor;

public class nomad {
	private ArrayList<Entity> mobs = new ArrayList<Entity>();
	private ArrayList<Player> players = new ArrayList<Player>();
	private LivingEntity nomad = null;
	private LivingEntity llama = null;
	public nomad(LivingEntity entity) {
		entity.setCustomName(ChatColor.GOLD+"Nomad");
		for(Entity ent : entity.getNearbyEntities(20, 20, 20)) {
			if(ent instanceof Player) {
				Player p = (Player) ent;
				p.sendMessage("<"+ChatColor.GOLD+"Nomad"+ChatColor.RESET+"> Please help me!");
			}
		}
		GetName.getname(entity, entity, MoreBosses.getPlugin(MoreBosses.class), false);
		
	}
	public ArrayList<Entity> getMobs() {
		return mobs;
	}
	public void setMobs(ArrayList<Entity> mobs) {
		this.mobs=mobs;
	}
	public Boolean checkMob(Entity mob) {
		if(this.mobs.contains(mob)) {
			this.mobs.remove(mob);
		}
		if(this.mobs.size()==0){
			return true;
		}else {
			return false;
		}
	}
	public void setPlayers(ArrayList<Player> list) {
		this.players=list;
	}
	public ArrayList<Player> getPlayers() {
		return this.players;
	}
	public LivingEntity getNomad() {
		return nomad;
	}
	public void setNomad(LivingEntity nomad) {
		this.nomad = nomad;
	}
	public LivingEntity getLlama() {
		return llama;
	}
	public void setLlama(LivingEntity llama) {
		this.llama = llama;
	}
}
