package org.learning.algorithms;

import java.util.Arrays;
import java.util.Scanner;

public class InsertionSortRecursion {

	// 10 8 9 1 2 3 6 7 5 
	// 1 2 3 8 9 10 5 
	// 3 2 1
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		int c = scanner.nextInt();
		int [] arr = new int[c];
		for (int i = 0; i < c; i++) {
			arr[i]=scanner.nextInt();
		}
		
		scanner.close();
		
		insertionSort(arr, 1);
		System.out.println(Arrays.toString(arr));
	}
	
	
	public static int [] insertionSort(int [] a, int pos) {
		if(pos == a.length) {
			return a;
		} else {
			int key = a[pos];
			int j = pos - 1;
			while(j >= 0 && a[j] > key) {
				a[j+1] = a[j];
				j--;
			}
			a[j+1] = key;
			return insertionSort(a, pos+1);
		}
	}
	
	
}
