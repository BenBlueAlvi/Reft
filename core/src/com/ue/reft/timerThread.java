package com.ue.reft;

import java.util.Timer;

public class TimerThread implements Runnable {
	
	Thread t;
	String threadName;
	int centiSeconds;

	public TimerThread(String name) {
		this.threadName = name;
	}

	@Override
	public void run() {
		while(true){
			try {
				Thread.sleep(10);
				this.centiSeconds++;
				
			} catch (InterruptedException e) {
				System.out.println("Thread " + threadName + " interrupted.");
				e.printStackTrace();
			}
			
		}
	}
	
	public void start(){
		System.out.println("Starting timer thread...");
		this.run();
		if (t == null) {
			t = new Thread(this, threadName);
			t.setDaemon(true);
			t.start();
		}
	}

}
