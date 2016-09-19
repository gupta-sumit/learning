package com.sapient.usecases.usecase13;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

public class MapUtilTest {

	public static void main(String[] args) {
		Map<String, Integer> map = new HashMap<String, Integer>();
		map.put("k1", 48);
		map.put("k2", 30);
		map.put("k3", 49);
		map.put("k4", 30);
		map.put("k5", 20);
		map.put("k9", 20);
		Map<String, Integer> sortMapByValues = MapUtils.sortMapByValues(map, new Comparator<Integer>() {
			@Override
			public int compare(Integer o1, Integer o2) {
				return o1.compareTo(o2);
			}
		});
		System.out.println(sortMapByValues);
	}
}
