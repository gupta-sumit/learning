package com.sapient.usecases.usecase3;

import java.util.List;
import java.util.UUID;

public class Producer implements Runnable, Stoppable {

	private List<String> queue;
	private volatile boolean stop;
	private int queueMaxLimit;
	
	public Producer(List<String> queue, int queueMaxLimit) {
		this.queue = queue;
		this.queueMaxLimit = queueMaxLimit;
	}
	
	@Override
	public void run() {
		while(!stop) {
			synchronized (queue) {
				while(queue.size() == queueMaxLimit) {
					try {
						System.out.println("Waiting for queue to be empty " + Thread.currentThread().getName());
						queue.wait();
					} catch (InterruptedException e) {
						System.out.println("Looks like interuppted. Stopping here.." + Thread.currentThread().getName());
						e.printStackTrace();
						stop = true;
					}
				}
				produce();
				queue.notifyAll();
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
