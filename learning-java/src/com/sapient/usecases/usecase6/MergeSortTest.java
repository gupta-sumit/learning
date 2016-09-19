package com.sapient.usecases.usecase6;

import java.util.Arrays;
import java.util.Scanner;

public class MergeSortTest {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		int c = scanner.nextInt();
		int [] arr = new int[c];
		for (int i = 0; i < c; i++) {
			arr[i]=scanner.nextInt();
		}
		scanner.close();
		MergeSort.sort(arr, 0, arr.length -1);
		System.out.println(Arrays.toString(arr));
	}
}
