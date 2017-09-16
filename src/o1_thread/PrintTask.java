package o1_thread;

import java.security.SecureRandom;
import java.util.Random;

public class PrintTask implements Runnable{
	private final String taskName;
	private final int sleepTime;
	private static final SecureRandom randomGenerator = new SecureRandom();
	
	public PrintTask(String taskName) {
		this.taskName = taskName;
		sleepTime = randomGenerator.nextInt(5000);
	}

	@Override
	public void run() {
		System.out.println(taskName+" started");
		
		try {
			Thread.sleep(sleepTime);
		} catch (InterruptedException e) {
			e.printStackTrace();
			Thread.currentThread().interrupt(); // re-interrupt the thread
		}
		System.out.println(Thread.currentThread().getName()+" finished");
	}

}
