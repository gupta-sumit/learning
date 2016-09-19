package com.sapient.usecases.usecase7.concurrentcounter;

import java.util.LinkedList;

public class ReentrantFairLock {

	private WaitNode currentNode;
	private volatile int lockedCount = 0;
	private LinkedList<WaitNode> waitNodeList;
	
	public ReentrantFairLock() {
		waitNodeList = new LinkedList<ReentrantFairLock.WaitNode>();
	}
	
	private class WaitNode {
		private Thread thread;
		private long time;
		public WaitNode() {
			thread = Thread.currentThread();
			time = System.currentTimeMillis();
		}
		@Override
		public String toString() {
			return "WaitNode [thread=" + thread + ", time=" + time + "]";
		}
	}
	
	public synchronized void lock() throws InterruptedException {
		System.out.println(Thread.currentThread() + " Trying to lock");
		WaitNode node = new WaitNode();
		if(currentNode == null || currentNode.thread != Thread.currentThread()) {
			waitNodeList.add(node);
		}
		synchronized (node) {
			while(lockedCount > 0 && currentNode != null && currentNode.thread != Thread.currentThread()) {
				node.wait();
			}
		}
		WaitNode waitingNode = null;
		if(lockedCount > 0 && currentNode.thread == Thread.currentThread()) {
			waitingNode = waitNodeList.peek();
		} else {
			waitingNode = waitNodeList.remove();
		}
		lockedCount++;
		if(null == currentNode) {
			currentNode = waitingNode;
		}
		System.out.println("Node got lock : " + waitingNode + " LockedCount " + lockedCount + "  Waiting List : " + waitNodeList);
	}
	
	public synchronized void unlock() {
			if(currentNode.thread != Thread.currentThread()) {
				throw new IllegalMonitorStateException();
			} else {
				lockedCount--;
				if(lockedCount == 0) {
					System.out.println("Node got unlock : " + currentNode + " LockedCount " + lockedCount + "  Waiting List : " + waitNodeList);
					currentNode = null;
					WaitNode node = waitNodeList.peek();
					if(null != node) {
						node.notify();
					}
					
				}
			}
	}
}
