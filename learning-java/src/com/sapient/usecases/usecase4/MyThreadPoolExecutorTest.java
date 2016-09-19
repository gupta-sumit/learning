package com.sapient.usecases.usecase4;

import java.util.concurrent.ArrayBlockingQueue;

public class MyThreadPoolExecutorTest {

	public static void main(String[] args) {
		final MyThreadPoolExecutor executor = new MyThreadPoolExecutor(10,20,new ArrayBlockingQueue<Runnable>(100));
		for(int i=0; i < 10; i++) {
			new Thread(new Runnable() {
				
				@Override
				public void run() {
					while(true) {
						try {
							executor.execute(new Runnable() {
								
								@Override
								public void run() {
									int sleep = (int) (Math.random()*1000);
									System.out.println("Executing By " + Thread.currentThread() + " Going to sleep random time " + sleep);
									try {
										
										Thread.currentThread().sleep(sleep);
									} catch (InterruptedException e) {
										// TODO Auto-generated catch block
										e.printStackTrace();
									}
								}
							});
						} catch(Exception e) {
							e.printStackTrace();
							try {
								Thread.sleep(1000);
							} catch (InterruptedException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
						}
						
					}
					
				}
			}).start();
		}
	}
}
