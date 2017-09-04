package o1_thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Application {
	public static void main(String[] args) {
		PrintTask task1 = new PrintTask("task1");
		PrintTask task2 = new PrintTask("task2");
		PrintTask task3 = new PrintTask("task3");
		
		ExecutorService executor = Executors.newCachedThreadPool();
		System.out.println("starting threads");
		executor.execute(task1);
		executor.execute(task2);
		executor.execute(task3);
		executor.shutdown();
		Thread.currentThread().interrupt();
		System.out.println("main thread shutdown");
		
		
	}
}
