package org.learning.algorithms;

import java.util.Arrays;
import java.util.Scanner;

public class InsertionSort {

	// 10 8 9 1 2 3 6 7 5 
	// 1 2 3 8 9 10 5 
	// 2 1
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		int c = scanner.nextInt();
		int [] arr = new int[c];
		for (int i = 0; i < c; i++) {
			arr[i]=scanner.nextInt();
		}
		scanner.close();
		for(int i=1; i < c; i++) {
			int key = arr[i];
			int j = i-1;
			while(j >= 0 && arr[j] > key) {
				arr[j+1] = arr[j];
				j--;
			}
			arr[j+1] = key;
		}
		System.out.println(Arrays.toString(arr));
	}
	
	
	
}
