package o2_thread_synchronization;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Application {
	public static void main(String[] args) throws InterruptedException{
		SimpleArray simpleArray = new SimpleArray(6);
		
		ArrayWriter writer1 = new ArrayWriter(simpleArray, 10);
		ArrayWriter writer2 = new ArrayWriter(simpleArray, 1000);
		
		ExecutorService executor = Executors.newCachedThreadPool();
		executor.execute(writer1);
		executor.execute(writer2);
		executor.shutdown();
		executor.awaitTermination(1, TimeUnit.MINUTES);
		System.out.println(simpleArray);
		System.out.println("main shutdown");
	}
}