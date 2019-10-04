package me.Derpy.Bosses.utilities;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Location;
import org.bukkit.Material;

public class Random {
	public static boolean random(Double chance) {
		Integer min = 1;
		Integer max = 1000;
		Integer range = max-min+1;
		Double num = (double) ((int)(Math.random()*range)+min);
		if(chance>=(num/1000)) {
			return true;
		}else {
			return false;
		}
	}
	public static Integer random(Integer min, Integer max) {
		Integer range = max-min+1;
		Integer num = (Integer) ((int)(Math.random()*range)+min);
		return num;
	}
	public static Integer random(ArrayList<?> list) {
		Integer min = 0;
		Integer max = list.size()-1;
		Integer range = max-min+1;
		Integer num = (Integer) ((int)(Math.random()*range)+min);
		return num;
	}
	public static Integer random(List<?> list) {
		Integer min = 0;
		Integer max = list.size()-1;
		Integer range = max-min+1;
		Integer num = (Integer) ((int)(Math.random()*range)+min);
		return num;
	}
	public static Integer round(Double num, Double roundif) {
		String stringnum = Double.toString(num);
		String whole_string = stringnum.substring(0, stringnum.indexOf('.'));
		String decimal_string = stringnum.substring(stringnum.indexOf('.')+1);
		Integer whole = Integer.parseInt(whole_string);
		Double decimal = Double.parseDouble(decimal_string);
		if(decimal>=roundif) {
			whole++;
		}
		return whole;
	}
	public static Location randomlocation(Location origin, double radius, double minimum) {
		// NOT MY CODE
		// Thank you spigot forums
		// https://www.spigotmc.org/threads/getting-a-random-location-around-a-set-centre-location-within-a-radius.33677/
		java.util.Random r = new java.util.Random();
        Double randomRadius = r.nextDouble() * radius;
        Double theta =  Math.toRadians(r.nextDouble() * 360);
        Double phi = Math.toRadians(r.nextDouble() * 180 - 90);
 
        double x = randomRadius * Math.cos(theta) * Math.sin(phi);
        double y = origin.getY();
        double z = randomRadius * Math.cos(phi);
        Location newLoc = origin.add(x, origin.getY(), z);
        if(newLoc.distanceSquared(origin)<minimum) {
        	if(Random.random(0.5)) {
        		if(Random.random(0.5)) {
        			newLoc.setZ(origin.getZ()+minimum);
        		}else {
        			newLoc.setZ(origin.getZ()-minimum);
        		}
        	}else {
        		if(Random.random(0.5)) {
        			newLoc.setX(origin.getX()+minimum);
        		}else {
        			newLoc.setX(origin.getX()-minimum);
        		}
        	}
        }
        if(!(newLoc.getBlock().getType()==Material.VOID_AIR)) {
	        if(newLoc.getBlock().getType()==Material.AIR) {
		        while(newLoc.getBlock().getType()==Material.AIR) {
		        	newLoc.setY(newLoc.getY()+1);
		        }
		        if(newLoc.getBlock().getType()==Material.VOID_AIR) {
		        	newLoc.setY(y);
		        }
		        if(newLoc.getY()==y) {
			        if(newLoc.getBlock().getType()==Material.AIR) {
			        	while(newLoc.getBlock().getType()==Material.AIR) {
			        		newLoc.setY(newLoc.getY()-1);
			        	}
			        }
		        }
	        }
	        if(newLoc.getBlock().getType()!=Material.AIR) {
	        	while(newLoc.getBlock().getType()!=Material.AIR) {
	        		newLoc.setY(newLoc.getY()+1);
	        	}
	        	if(newLoc.getBlock().getType()==Material.VOID_AIR) {
	        		newLoc.setY(y);
	        	}
	        }
        }
        newLoc.setY(newLoc.getY()+1);
        return newLoc;
	}
	public static class fromlist{
		public static String randomstring(ArrayList<String> list) {
			Integer min = 0;
			Integer max = list.size()-1;
			Integer range = max-min+1;
			Integer num = (Integer) ((int)(Math.random()*range)+min);
			return list.get(num);
		}
	}
}
