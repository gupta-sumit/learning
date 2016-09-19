package com.sapient.usecases.usecase7;

public class ConcurrentCounter {

	private volatile int value;
	
	public ConcurrentCounter(int initialValue) {
		// TODO Auto-generated constructor stub
		this.value = initialValue;
	}
	
	public synchronized int incrementAndGet() {
			value = value + 1;
			return value;
	}
	
	public int decrementAndGet() throws InterruptedException {
			value = value - 1;
			return value;
	}
	
	public int getValue() {
		return value;
	}
	
}
