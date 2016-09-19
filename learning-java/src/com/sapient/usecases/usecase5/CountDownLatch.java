package com.sapient.usecases.usecase5;

public class CountDownLatch {

	private int count;

	public CountDownLatch(int nThreads) {
		this.count = nThreads;
	}
	
	public synchronized void await() throws InterruptedException {
		while(count > 0) {
			wait();
		}
	}
	
	public synchronized void await(long timeInMilis) throws InterruptedException {
		while(count > 0) {
			wait(timeInMilis);
		}

	}
	
	public synchronized void countdown() {
		if(count > 0) {
			count--;
		}
		if(count == 0) {
			notifyAll();
		}
	}
}
