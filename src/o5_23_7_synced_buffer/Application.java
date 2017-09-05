package o5_23_7_synced_buffer;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Application {
	public static void main(String[] args) {

		System.out.printf("%-40s%s\t\t%s%n%-40s%s%n%n", "Operation", 
				"Buffer", "Occupied", "---------","------\t\t--------");

		Buffer buffer = new SynchronizedBuffer();
		Producer producer = new Producer(buffer);
		Consumer consumer = new Consumer(buffer);

		ExecutorService executor = Executors.newCachedThreadPool();
		executor.execute(producer);
		executor.execute(consumer);
		executor.shutdown();
	}
}
