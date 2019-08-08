package me.Derpy.Bosses.utilities;


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
}
