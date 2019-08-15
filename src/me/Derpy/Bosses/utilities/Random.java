package me.Derpy.Bosses.utilities;

import java.util.ArrayList;

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
}
