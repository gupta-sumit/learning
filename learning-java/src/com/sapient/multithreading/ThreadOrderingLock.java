package com.sapient.multithreading;

public class ThreadOrderingLock {

	private final int threadCount;
	
	private Thread lockedThread;
	
	private int turn = 0;
	
	private Object [] locks;
	
	public ThreadOrderingLock(int threadCount) {
		if(threadCount <= 0) {
			throw new IllegalArgumentException("Invalid threadCount");
		}
		this.threadCount = threadCount;
		locks = new Object[threadCount];
	}
	
	class Node {
		
	}
	
	public void lock(int threadId) throws InterruptedException {
		synchronized (this) {
			while(turn != threadId) {
				wait();
			}
			lockedThread = Thread.currentThread();
		}
	}
	
	public void unlock() {
		synchronized (this) {
			if(lockedThread == Thread.currentThread()) {
				turn = (turn + 1)%threadCount;
				notifyAll();
			}
		}
	}
	
}
