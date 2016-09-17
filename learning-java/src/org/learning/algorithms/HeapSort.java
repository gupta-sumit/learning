package org.learning.algorithms;

import java.util.Arrays;
import java.util.Scanner;

public class HeapSort {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		int c = scanner.nextInt();
		int [] arr = new int[c];
		for (int i = 0; i < c; i++) {
			arr[i]=scanner.nextInt();
		}
		scanner.close();
		heapSort(arr);
		System.out.println(Arrays.toString(arr));
	}
	
	private static void heapSort(int [] a) {
		for (int i = a.length/2; i >=0 ; i--) {
			heapify(a, i,a.length);
		}
		System.out.println(Arrays.toString(a));
		int j=a.length - 1;
		while(j >= 0) {
			int temp = a[j];
			a[j] = a[0];
			a[0] = temp;
			//System.out.println(" j " + j);
			//System.out.println(Arrays.toString(a));
			heapify(a, 0, j);
			//System.out.println(Arrays.toString(a));
			j--;
		}
	}
	
	private static void heapify(int [] a, int pos, int lastPos) {
	//	System.out.println("---pos " + pos + "  0005 " + lastPos);
		
		int left = 2*pos + 1;
		int right = 2*(pos + 1);
		if(left > lastPos) {
			return;
		}
		int maxIndex = pos;
		if(left < lastPos && a[left] > a[maxIndex]) {
			maxIndex = left;
		}
		if(right < lastPos && a[right] > a[maxIndex]) {
			maxIndex = right;
		}
		if(maxIndex != pos) {
			int temp = a[maxIndex];
			a[maxIndex] = a[pos];
			a[pos] = temp;
			heapify(a, maxIndex,lastPos);
		}
		
	}
	
	
}
