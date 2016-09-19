package com.sapient.multithreading;

public class PrinterThread extends Thread {

	private int id;
	private int count;
	private ThreadOrderingLock lock;
	
	public PrinterThread(int id, int count, ThreadOrderingLock lock) {
		super("Printer " + id);
		this.id = id;
		this.count = count;
		this.lock = lock;
	}
	
	@Override
	public void run() {
		while(count < 100) {
			try {
				lock.lock(id);
				System.out.println("Thread " + Thread.currentThread() + "    "+  count);
				count = count+3;
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				lock.unlock();
			}
			
		}
	}
	
	
}
