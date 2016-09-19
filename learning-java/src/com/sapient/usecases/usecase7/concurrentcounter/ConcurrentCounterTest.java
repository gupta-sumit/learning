package com.sapient.usecases.usecase7.concurrentcounter;


public class ConcurrentCounterTest {

	public static void main(String[] args) {
	
		final ConcurrentCounter counter = new ConcurrentCounter(10);
		for(int i=0; i < 3; i++) {
			new Thread(new Runnable() {
				
				@Override
				public void run() {
					while(counter.getValue() < 100) {
						try {
							int incrementAndGet = counter.incrementAndGet();
							System.out.println( Thread.currentThread().getName()  + " dddd  " + incrementAndGet);
							Thread.currentThread().sleep((int)Math.random()*1000);
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
