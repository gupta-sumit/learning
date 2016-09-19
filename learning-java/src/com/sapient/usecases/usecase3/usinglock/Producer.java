package com.sapient.usecases.usecase3.usinglock;

import java.util.List;
import java.util.UUID;

public class Producer implements Runnable, Stoppable {

	private List<String> queue;
	private volatile boolean stop;
	private int queueMaxLimit;
	private FairLock lock;
	private MyCondition queueFull;
	private MyCondition notEmpty;
	
	public Producer(List<String> queue, int queueMaxLimit, FairLock lock, MyCondition waitForSpace, MyCondition notEmpty) {
		this.queue = queue;
		this.queueMaxLimit = queueMaxLimit;
		this.lock = lock;
		this.queueFull = waitForSpace;
		this.notEmpty = notEmpty;
	}
	
	@Override
	public void run() {
		while(!stop) {
			try {
				try {
					lock.lock();
					System.out.println("Got lock producer");
					while(queue.size() == queueMaxLimit) {
						try {
							System.out.println("Waiting for queue to be empty " + Thread.currentThread().getName());
							queueFull.await();
							System.out.println("Producer wait end");
						} catch (InterruptedException e) {
							System.out.println("Looks like interuppted. Stopping here.." + Thread.currentThread().getName());
							e.printStackTrace();
							stop = true;
						}
					}
					produce();
					notEmpty.list();
					notEmpty.signal();
					System.out.println("Producer signalled");
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} finally {
				lock.unlock();
			}
			
				
			
		}
	}

	@Override
	public void abort() {
		this.stop = true;
	}

	private void produce() {
		String str = UUID.randomUUID().toString();
		System.out.println("Producing  " + str);
		queue.add(str);
	}
}
