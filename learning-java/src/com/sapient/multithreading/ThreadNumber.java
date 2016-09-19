package com.sapient.multithreading;

public class ThreadNumber {

	 public static void main(String[] args) {
	
		 ThreadOrderingLock lock = new ThreadOrderingLock(3);
		 System.out.println();
		 for(int i=0; i < 3; i++) {
			 PrinterThread t = new PrinterThread(i, i, lock);
			 t.start();
		 }
	 }
	
}


/**
 * 
 * T1  	T2 	T3
 * 1	2	3
 * 4	5	6	
 * 7	8 	9
 * 
 * 
 * 
 */


