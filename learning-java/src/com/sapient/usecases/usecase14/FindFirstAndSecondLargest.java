package com.sapient.usecases.usecase14;

import java.util.Scanner;

public class FindFirstAndSecondLargest {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		int c = scanner.nextInt();
		int [] arr = new int[c];
		for (int i = 0; i < c; i++) {
			arr[i]=scanner.nextInt();
		}
		scanner.close();
		
		int firstLargest = arr[0];
		int secondLargest = arr[1];
		if(firstLargest < secondLargest) {
			firstLargest = arr[1];
			secondLargest = arr[0];
		} 
		
		for(int i=2; i < arr.length; i++) {
			if(arr[i] > firstLargest) {
				secondLargest = firstLargest;
				firstLargest = arr[i];
			} else if(arr[i] > secondLargest){
				secondLargest = arr[i];			
			}
		}
		
		System.out.println("First " + firstLargest + " Second " + secondLargest);
	}
}
