package com.sapient.usecases.usecase3.usinglock;

import java.util.LinkedList;
import java.util.List;

public class ProducerConsumerTest {

	public static void main(String[] args) throws InterruptedException {
		List<String> list = new LinkedList<String>();
		FairLock lock = new FairLock();
		MyCondition notEmpty = new MyCondition(lock);
		MyCondition notFull = new MyCondition(lock);
		Thread producer = new Thread(new Producer(list,10,lock,notFull,notEmpty),"producer");
		Thread consumer = new Thread(new Consumer(list,lock,notFull,notEmpty),"consumer");
		consumer.start();
		producer.start();
		Thread.sleep(100);
		//producer.interrupt();
	}
}
