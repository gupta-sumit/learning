package com.sapient.usecases.usecase13;

import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeSet;


public class MapUtils {

	public static <K,V> Map<K, V> sortMapByValues(Map<K, V> map, Comparator<V> valueComparator) {
		TreeSet<Entry<K, V>> treeSet = new TreeSet<Entry<K, V>>(new EntryComparator<K, V>(valueComparator));
		treeSet.addAll(map.entrySet());
		LinkedHashMap<K, V> linkedHashMap = new LinkedHashMap<K, V>();
		for (Entry<K, V> entry : treeSet) {
			linkedHashMap.put(entry.getKey(), entry.getValue());
		}
		return linkedHashMap;
	}
	
	private static class EntryComparator<K,V> implements Comparator<Entry<K, V>>  {

		private Comparator<V> valueComparator;
		
		public EntryComparator(Comparator<V> valueComparator) {
			this.valueComparator = valueComparator;
		}
		
		@Override
		public int compare(Entry<K, V> o1, Entry<K, V> o2) {
			int compare = valueComparator.compare(o1.getValue(), o2.getValue());
			if(compare == 0) {
				if(o1.getKey().equals(o2.getKey())) {
					return 0;
				}
				return -1;
			}
			return compare; 
		}
		
	}
	
}
