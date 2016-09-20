package com.sapient.multithreading;

public class ThreadSleepState {

	public static void main(String[] args) {
		try {
			Thread.sleep(1000000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
