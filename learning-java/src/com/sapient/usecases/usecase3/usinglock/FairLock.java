package com.sapient.usecases.usecase3.usinglock;

import java.util.LinkedList;


public class FairLock {
	
	private LinkedList<Node> list = new LinkedList<FairLock.Node>();
	private volatile boolean locked = false;
	
	private class Node {
		Thread waitingThread;
		public String toString() {
			return waitingThread.getName();
		}
	}
	
	public void lock() throws InterruptedException {
		Node n = new Node();
		n.waitingThread = Thread.currentThread();
		synchronized (this) {
			if(locked) {
				list.add(n);
				System.out.println(list);
			}
		}
		synchronized (n) {
			while(locked) {
				n.wait();
			}
		}
		locked = true;
	}
	
	public synchronized void unlock() {
		if(list.size() > 0) {
			Node n = list.remove();
			if(n == null) {
				throw new IllegalMonitorStateException();
			}
			locked = false;
			System.out.println(list);
			synchronized (n) {
				n.notify();
			}
			
		}
		
	}
}
