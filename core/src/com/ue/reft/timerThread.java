package com.ue.reft;

import java.util.Timer;

public class timerThread implements Runnable {
	
	Thread t;
	String threadName;
	int centiSeconds;

	public timerThread(String name) {
		this.threadName = name;
	}

	@Override
	public void run() {
		System.out.println("Running timer thread...");
		while(true){
			try {
				Thread.sleep(10);
				this.centiSeconds++;
				if(centiSeconds % 100 == 0){
					System.out.println(centiSeconds / 100);
				}
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
