package o7_23_9_lock_condition;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class ApplicationMultipleProducerConsumer {
	public static void main(String[] args) throws InterruptedException {

		System.out.printf("%-40s%s\t\t%s%n%-40s%s%n%n", "Operation", 
				"Buffer", "Occupied", "---------","------\t\t--------");

		Buffer buffer = new SynchronizedBuffer();
		Consumer consumer1 = new Consumer(buffer);
		Consumer consumer2 = new Consumer(buffer);
		Consumer consumer3 = new Consumer(buffer);

		Producer producer1 = new Producer(buffer);
		Producer producer2 = new Producer(buffer);
		Producer producer3 = new Producer(buffer);
		
		ExecutorService executor = Executors.newCachedThreadPool();
		long startTime = System.currentTimeMillis();
		executor.execute(producer1);
		executor.execute(producer2);
		executor.execute(producer3);
		executor.execute(consumer1);
		executor.execute(consumer2);
		executor.execute(consumer3);
		executor.shutdown();
		executor.awaitTermination(1, TimeUnit.MINUTES);
		long endTime = System.currentTimeMillis();
		System.out.println("time spent in milliseconds: "+ (endTime - startTime));
	}
}
