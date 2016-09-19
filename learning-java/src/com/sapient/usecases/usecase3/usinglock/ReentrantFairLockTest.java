package com.sapient.usecases.usecase3.usinglock;

public class ReentrantFairLockTest {

	private ReentrantFairLock lock = new ReentrantFairLock();
	
	public void m1() throws InterruptedException {
		lock.lock();
		m2();
		lock.unlock();
	}
	
	public void m2() throws InterruptedException {
		lock.lock();
		m3();
		lock.unlock();
	}
	
	public static void main(String[] args) throws InterruptedException {
		ReentrantFairLockTest t = new ReentrantFairLockTest();
		t.m1();
	}

	private void m3() throws InterruptedException {
		lock.lock();
		System.out.println("Sleeping");
		Thread.sleep(10000);
		
		lock.unlock();
	}
}
