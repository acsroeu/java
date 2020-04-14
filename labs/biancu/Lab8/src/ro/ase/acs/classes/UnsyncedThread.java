package ro.ase.acs.classes;

import java.util.Random;

//a class which wants to start another thread
//should inherit Thread or runnable
public class UnsyncedThread extends Thread {
	private static Random random = new Random();
	private static int a = 0;
	private static int b = 0;
	
	public UnsyncedThread(String name) {
		super(name);
	}
	
	private void method() {
		System.out.printf("%s: a=%d b=%d\r\n", getName(), a, b);
		a++;
		try {
			//we are pausing the Thread for a random period
			//to simulate an ongoing operation
			Thread.sleep(random.nextInt(3000));
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		b++;
	}

	//just the code from this method
	//is executed in a new thread
	@Override
	public void run() {
		super.run();
		for(int i = 0; i < 3; i++) {
			method();
		}
	}
	
}
