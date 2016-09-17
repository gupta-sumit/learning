package org.learning.algorithms;

import java.util.Arrays;
import java.util.Scanner;

public class Quicksort {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		int c = scanner.nextInt();
		int [] arr = new int[c];
		for (int i = 0; i < c; i++) {
			arr[i]=scanner.nextInt();
		}
		scanner.close();
		quickSort(arr, 0, c-1);
		System.out.println(Arrays.toString(arr));
	}
	
	private static void quickSort(int [] a, int start, int end) {
		if(start < end) {
			int pivot = partition(a, start, end);
			System.out.println("-== " + pivot);
			System.out.println(Arrays.toString(a));
			quickSort(a, start, pivot - 1 );
			quickSort(a, pivot + 1, end);
		}
		
		
	}
	
	// 10 8 9 1 2 3 6 7 5 
		// 1 2 3 8 9 10 5 
		// 2 1
	private static int partition(int [] a, int start, int end) {
		int pivotIndex = end;
		int i=-1;
		for(int j=0; j < end; j++) {
			if(a[j] <= a[pivotIndex]) {
				i=i+1;
				swap(a,j,i);
			}
		}
		swap(a,i+1,pivotIndex);
		return i+1;
	}
	
	private static void swap(int [] a, int i, int j) {
		int temp = a[j];
		a[j] = a[i];
		a[i] = temp;
	}
}
