package com.sapient.usecases.usecase3;

import java.util.LinkedList;
import java.util.List;

public class ProducerConsumerTest {

	public static void main(String[] args) throws InterruptedException {
		List<String> list = new LinkedList<String>();
		Thread producer = new Thread(new Producer(list,10),"producer");
		Thread consumer = new Thread(new Consumer(list),"consumer");
		consumer.start();
		producer.start();
		Thread.sleep(100);
		producer.interrupt();
	}
}
