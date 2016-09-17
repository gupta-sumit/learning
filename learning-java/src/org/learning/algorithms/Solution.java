package org.learning.algorithms;

import java.io.*;
import java.io.ObjectInputStream.GetField;
import java.util.*;
import java.util.Map.Entry;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {

    public static void main(String[] args) {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
        Scanner scanner = new Scanner(System.in);
        int gc = scanner.nextInt();
        Set<String> passionSet = new HashSet<String>();
        for(int i=0; i < gc; i++) {
            int passions = scanner.nextInt();
            for(int j=0; j < passions; j++) {
            	passionSet.add(scanner.next());
            }
        }
        int city = scanner.nextInt();
        Map<String,CityDetails> cityLatLongMap = new HashMap<String, Solution.CityDetails>();
        Map<String,Set<String>> cityPassions = new HashMap<String, Set<String>>();
        Set<String> citySet = new HashSet<String>();
        for(int i=0; i < city; i++) {
            String cityName = scanner.next();
            float lat = scanner.nextFloat();
            float lng = scanner.nextFloat();
            cityLatLongMap.put(cityName, new CityDetails(lat, lng));
            int cityPassion = scanner.nextInt(); 
            Set<String> cityPassionSet = new HashSet<String>();
            for(int j=0; j <  cityPassion; j++) {
            	cityPassionSet.add(scanner.next());
            }
            cityPassions.put(cityName, cityPassionSet);
            citySet.add(cityName);
        }
        scanner.close();
        List<CityPassionCount>  list = new ArrayList<Solution.CityPassionCount>();
        for (Entry<String, Set<String>> cityPEntry : cityPassions.entrySet()) {
        	int count = 0;
        	for (String p : cityPEntry.getValue()) {
				if(passionSet.contains(p)) {
					count++;
				}
			}
        	list.add(new CityPassionCount(cityPEntry.getKey(), count));
		}
        Collections.sort(list);
        Collections.reverse(list);
    //    System.out.println(list);
        int passionCountSum = Integer.MIN_VALUE;
        String fcombination = null;
        double minDistance = Double.MAX_VALUE;
        String c1 = null;
        String c2 = null;
        for(int i=0; i <list.size() - 1; i++) {
        	for(int j=i+1; j < list.size(); j++) {
        		int sum = getDistinctPassionCountSum(cityPassions, list.get(i).cityName, list.get(j).cityName,passionSet);
        		double distance = getDistance(cityLatLongMap, list.get(i).cityName, list.get(j).cityName);
        	//	System.out.println(getGroup(list.get(i).cityName, list.get(j).cityName) + "   " + distance);
        		if(sum > passionCountSum) {
        			minDistance = distance;
        			passionCountSum = sum;
        			c1 = list.get(i).cityName;
        			c2 = list.get(j).cityName;
        		} else if(sum == passionCountSum) {
        			if(distance < minDistance ) {
        				minDistance = distance;
            			//passionCountSum = sum;
            			c1 = list.get(i).cityName;
            			c2 = list.get(j).cityName;
        			}
        		} else {
        			break;
        		}
        	}
        }
        fcombination = getGroup(c1, c2);
        System.out.println(fcombination);
        
    }
    
    private static int getDistinctPassionCountSum(Map<String,Set<String>> cityPassionSet, String c1, String c2, Set<String> guestPassionSet) {
    	Set<String> passionSet = new HashSet<String>();
    	if(cityPassionSet.containsKey(c1)) {
    		passionSet.addAll(cityPassionSet.get(c1));
    	}
    	if(cityPassionSet.containsKey(c2)) {
    		passionSet.addAll(cityPassionSet.get(c2));
    	}
    	int count = 0;
    	for (String passion : passionSet) {
    		if(guestPassionSet.contains(passion)) {
    			count++;
    		}
		}
    	return count;
    }
    
    private static String getGroup(String c1, String c2) {
    	return c1.compareTo(c2) <= 0 ? c1 + " " + c2 : c2 + " " + c1;
    }
    
    private static double getDistance(Map<String, CityDetails> cityDetailsMap, String sourceCity, String destCity) {
    	CityDetails sCityDetails = cityDetailsMap.get(sourceCity);
    	CityDetails dCityDetails = cityDetailsMap.get(destCity);
    	double EARTH_RADIUS = 6371;
        double point1_lat_in_radians  = Math.toRadians( sCityDetails.lat );
        double point2_lat_in_radians  = Math.toRadians( sCityDetails.lng );
        double point1_long_in_radians  = Math.toRadians( dCityDetails.lat );
        double point2_long_in_radians  = Math.toRadians( dCityDetails.lng );

        return Math.acos( Math.sin(point1_lat_in_radians ) * Math.sin( point2_lat_in_radians ) +
                     Math.cos( point1_lat_in_radians ) * Math.cos( point2_lat_in_radians ) *
                     Math.cos( point2_long_in_radians - point1_long_in_radians) ) * EARTH_RADIUS;
    	
    }
    
    private static class CityDetails {
    	
    	private final float lat;
    	private final float lng;
    	
    	public CityDetails (float lat, float lng) {
    		this.lat = lat;
    		this.lng = lng;
    	}
    }
    
    private static class CityPassionCount implements Comparable<CityPassionCount>{
    	
    	private final String cityName;
    	private final int passionCount;
    
    	public CityPassionCount(String city, int passionCount) {
			this.cityName = city;
			this.passionCount = passionCount;
		}

		@Override
		public String toString() {
			return "CityPassionCount [cityName=" + cityName + ", passionCount="
					+ passionCount + "]";
		}

		@Override
		public int compareTo(CityPassionCount o) {
			if(this.passionCount < o.passionCount) {
				return -1;
			}
			if(this.passionCount == o.passionCount) {
				return 0;
			}
			return 1 ;
		}
    	
    	
    }
    
   
}