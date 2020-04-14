package ro.ase.acs.main;

import ro.ase.acs.classes.SyncedThread;
import ro.ase.acs.classes.UnsyncedThread;

public class Main {
	
	public static void main(String[] args) {
		//Please uncomment the following lines to see
		//a race condition in action
		
		//In order to start a new thread, the start method is called
		//not run, which will cause it to be executed in the existing thread
		
//		UnsyncedThread t1 = new UnsyncedThread("Thread 1");
//		t1.start();
//		UnsyncedThread t2 = new UnsyncedThread("Thread 2");
//		t2.start();
		
		SyncedThread t3 = new SyncedThread("Thread 3");
		//objects from classes that inherit thread should be passed
		//as parameters to the Thread constructor in order to call the start method
		new Thread(t3).start();
		
		SyncedThread t4 = new SyncedThread("Thread 4");
		new Thread(t4).start();
		
		//because Runnable is a functional interface
		//it can be used with lambda expressions
		Runnable r = () -> { 
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			} 
			System.out.println("Message from another thread");
		};
		new Thread(r).start();
		
		System.out.println("Main ended");
	}
}
