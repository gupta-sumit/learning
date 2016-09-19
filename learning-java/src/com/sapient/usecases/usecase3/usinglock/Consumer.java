package com.sapient.usecases.usecase3.usinglock;

import java.util.List;

public class Consumer implements Runnable, Stoppable {

	private List<String> queue;
	private volatile boolean stop;
	private FairLock lock;
	private MyCondition queueFull;
	private MyCondition notEmpty;
	
	public Consumer(List<String> queue,FairLock lock, MyCondition waitForSpace, MyCondition notEmpty) {
		this.queue = queue;
		this.lock = lock;
		this.queueFull = waitForSpace;
		this.notEmpty = notEmpty;
	}
	
	@Override
	public void run() {
		while(!stop) {
			try {
				lock.lock();
				while(queue.isEmpty()) {
					try {
						System.out.println("Waiting for message as queue is empty " + Thread.currentThread().getName());
						notEmpty.await();
						System.out.println("Consumer Signalled");
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
				consume();
				queueFull.signal();
			} catch (InterruptedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} finally {
				lock.unlock();
			}
				
				
		}
		
	}
	
	

	private void consume() {
		System.out.println(queue.remove(0));
	}

	@Override
	public void abort() {
		this.stop = true;
	}

}
