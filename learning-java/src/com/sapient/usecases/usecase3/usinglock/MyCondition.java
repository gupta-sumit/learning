package com.sapient.usecases.usecase3.usinglock;

import java.util.LinkedList;

public class MyCondition {

	private LinkedList<Node> list = new LinkedList<MyCondition.Node>();
	private FairLock lock;
	
	public MyCondition(FairLock lock) {
		this.lock = lock;
	}
	
	private class Node {
		Thread waitingThread;
		
		public String toString() {
			return waitingThread.getName();
		}
	}
	
	public void await() throws InterruptedException {
		Node n = new Node();
		n.waitingThread = Thread.currentThread();
		synchronized (this) {
			list.add(n);
			lock.unlock();
		}
		synchronized (n) {
				n.wait();
		}
		System.out.println("Thread " + Thread.currentThread() + " trying to lock");
		lock.lock();
		System.out.println("Thread " + Thread.currentThread() + " trying to locked");
	}
	
	public synchronized void signal() {
		if(list.size() > 0) {
			Node n = list.remove();
			System.out.println("Signalling " + n.waitingThread);
			synchronized (n) {
				n.notify();
			}
			System.out.println("Still waiting " + list);
			
		}
		
		
		
	}
	
	public void list() {
		System.out.println(list);
	}
}
