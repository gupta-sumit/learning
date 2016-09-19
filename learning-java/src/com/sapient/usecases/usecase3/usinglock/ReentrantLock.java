package com.sapient.usecases.usecase3.usinglock;

public class ReentrantLock {

	private Thread lockedThread;
	private volatile boolean locked;
	private int lockedCount = 0;
	
	public synchronized void lock() throws InterruptedException {
		while(locked && lockedThread != Thread.currentThread()) {
			wait();
		}
		lockedThread = Thread.currentThread();
		lockedCount++;
		locked = true;
	}
	
	public synchronized void unlock() {
		if(locked && lockedThread == Thread.currentThread()) {
			lockedCount--;
			if(lockedCount == 0) {
				locked = false;
				lockedThread = null;
				notifyAll();
			}
		}
	}
}
