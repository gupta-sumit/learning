package com.sapient.usecases.usecase3;

import java.util.List;

public class Consumer implements Runnable, Stoppable {

	private List<String> queue;
	private volatile boolean stop;
	
	public Consumer(List<String> queue) {
		this.queue = queue;
	}
	
	@Override
	public void run() {
		while(!stop) {
			synchronized (queue) {
				while(queue.isEmpty()) {
					try {
						System.out.println("Waiting for message as queue is empty " + Thread.currentThread().getName());
						queue.wait();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
				consume();
				queue.notifyAll();
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
