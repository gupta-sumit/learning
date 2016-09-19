package com.sapient.usecases.usecase15;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.SynchronousQueue;

public class CallCenter {

	private final int employees;
	private final int technicalLeads;
	private final int pms;
	
	private SynchronousQueue<Call> employeeCallsQueue = new SynchronousQueue<CallCenter.Call>();
	private SynchronousQueue<Call> tlCallsQueue = new SynchronousQueue<CallCenter.Call>();
	private SynchronousQueue<Call> pmCallsQueue = new SynchronousQueue<CallCenter.Call>();
	
	
	public CallCenter(int employees, int technicalLeads, int pms) {
		super();
		this.employees = employees;
		this.technicalLeads = technicalLeads;
		this.pms = pms;
		initialize();
	}

	private void initialize() {
		for (int i = 0; i < employees; i++) {
			CallWorker w = new CallWorker("employee-" + i, employeeCallsQueue);
			w.start();
		}
		for (int i = 0; i < technicalLeads; i++) {
			CallWorker w = new CallWorker("tech-lead", tlCallsQueue);
			w.start();
		}
		
		for (int i = 0; i < pms; i++) {
			CallWorker w = new CallWorker("manager", pmCallsQueue);
			w.start();
		}
		
		
		
	}
	
	
	public void submitCall(Call  call) {
		boolean offered = employeeCallsQueue.offer(call);
		if(!offered) {
			offered = tlCallsQueue.offer(call);
			if(!offered) {
				pmCallsQueue.offer(call);
			} else {
				throw  new RuntimeException("All busy");
			}
		}
	}
	
	
	public static class Call {
		private String fromNumber;
		
		public Call(String fromNumber) {
			this.fromNumber = fromNumber;
		}
		
		public String toString() {
			return fromNumber;
		}
		
		public void startConversation() throws InterruptedException {
			System.out.println(Thread.currentThread() + " doing converstation to this number " + fromNumber);
			Thread.sleep((long) (Math.random()*1000));
			System.out.println(Thread.currentThread() + " converstation finished " + fromNumber);
		}
		
	}
	
	private class CallWorker extends Thread {
		
		BlockingQueue<Call> queue;
		
		public CallWorker(String empId, BlockingQueue<Call> queue) {
			super(empId);
			this.queue = queue;
		}
		
		public void run() {
			while(true) {
				try {
					Call call = queue.take();
					call.startConversation();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
	
}
