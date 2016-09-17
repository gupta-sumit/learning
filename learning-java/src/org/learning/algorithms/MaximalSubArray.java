package org.learning.algorithms;

import java.util.Scanner;

public class MaximalSubArray {

	// -1 -2 -3 4 -1 7 -3 1 2 3 8 -2
	// 
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		int c = scanner.nextInt();
		int [] arr = new int[c];
		for (int i = 0; i < c; i++) {
			arr[i]=scanner.nextInt();
		}
		scanner.close();
		int s = 0;
		int e = 0;
		int maxsum = arr[0];
		int prevSum = arr[0];
		int csum=0;
		for(int i=1; i < arr.length; i++) {
			if(prevSum > 0) {
				csum = prevSum + arr[i]; 
			} else {
				//System.out.println(arr[i] + " == -----" + prevSum);
				if(arr[i] > maxsum) {
					csum = arr[i];
					s=i;
					e=i;
					//System.out.println("  -----" + e);
				} else {
					csum = prevSum + arr[i];
				}
				
			}
			if(csum > maxsum) {
				e=i;
				maxsum=csum;
			//	System.out.println("=====  " + e);
			}
			prevSum = csum;
			
		}
		for(int i=s; i <=e; i++) {
			System.out.print(arr[i] + " ");
		}
	}
}
