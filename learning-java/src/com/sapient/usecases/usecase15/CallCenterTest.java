package com.sapient.usecases.usecase15;

import com.sapient.usecases.usecase15.CallCenter.Call;


public class CallCenterTest {

	
	public static void main(String[] args) {
		final CallCenter callCenter = new CallCenter(10, 1, 1);
		for(int i=0; i < 10; i++) {
			new Thread(new Runnable() {
				
				@Override
				public void run() {
					while(true) {
						try {
							Call call = new CallCenter.Call(String.valueOf((long)Math.random()*100000000));
							callCenter.submitCall(call);
						} catch(Exception e) {
							e.printStackTrace();
							try {
								Thread.sleep(1000);
							} catch (InterruptedException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
						}
						
					}
					
				}
			}).start();
		}
	}
}
