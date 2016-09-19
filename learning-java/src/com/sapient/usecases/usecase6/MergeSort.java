package com.sapient.usecases.usecase6;

import java.util.Arrays;


public class MergeSort {

	public static void sort(int [] arr, int start, int end) {
		if(start != end) {
			int m = (start + end) /2;
			sort(arr,0,m);
			sort(arr,m+1,end);
			//System.out.println(Arrays.toString(left) + "   " + Arrays.toString(right));
			merge(arr,start,m,end);
		}
	}
	
	private static void merge(int [] arr, int start, int middle, int end) {
		int [] a1 = new int[middle-start + 1];
		int [] a2 = new int[end - middle];
		int m=0, n=0;
		for(int i=start; i <= middle; i++) {
			a1[m++] = arr[i];
		}
		for(int i=middle+1; i <= end; i++) {
			a2[n++] = arr[i];
		}
		System.out.println(Arrays.toString(a1) + "   " + Arrays.toString(a2));
		int i=0,j=0,k=start;
		while(i < a1.length && j < a2.length) {
			if(a1[i] < a2[j]) {
				arr[k++] = a1[i++];
			} else {
				arr[k++] = a2[j++];
			}
		}
		while(i < a1.length) {
			arr[k++] = a1[i++];
		}
		while(j < a2.length) {
			arr[k++] = a2[j++];
		}
	}
	
}
