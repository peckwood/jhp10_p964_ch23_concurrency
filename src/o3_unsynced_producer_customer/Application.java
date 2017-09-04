package o3_unsynced_producer_customer;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Application {
	public static void main(String[] args) {

		System.out.println("Action\t\tValue\tSum of Produced\tSum of Consumed");
		System.out.printf("------\t\t-----\t---------------\t---------------%n%n");

		Buffer buffer = new UnsynchronizedBuffer();
		Producer producer = new Producer(buffer);
		Consumer consumer = new Consumer(buffer);

		ExecutorService executor = Executors.newCachedThreadPool();
		executor.execute(producer);
		executor.execute(consumer);
		executor.shutdown();
		System.out.println("main ended");
	}
}
