package com.sapient.usecases.usecase7.concurrentcounter;

public class ConcurrentCounter {

	private ReentrantFairLock lock = new ReentrantFairLock();
	private volatile int value;
	
	public ConcurrentCounter(int initialValue) {
		// TODO Auto-generated constructor stub
		this.value = initialValue;
	}
	
	public int incrementAndGet() throws InterruptedException {
		try {
			lock.lock();
			value = value + 1;
			return value;
		} finally {
			lock.unlock();
		}
	}
	
	public int decrementAndGet() throws InterruptedException {
		try {
			lock.lock();
			value = value - 1;
			return value;
		} finally {
			lock.unlock();
		}
	}
	
	public int getValue() {
		return value;
	}
	
}
