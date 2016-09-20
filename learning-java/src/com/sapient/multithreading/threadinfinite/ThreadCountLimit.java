package com.sapient.multithreading.threadinfinite;

public class ThreadCountLimit {

	public static void main(String[] args) {
		//List
		while(true) {
			new Thread(new Runnable() {
				
				@Override
				public void run() {
					while(true) {
						try {
							System.out.println(Thread.currentThread());
							Thread.sleep(10000);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
					
				}
			}).start();
		}
	}
	
	
}
