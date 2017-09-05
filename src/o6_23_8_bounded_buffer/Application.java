package o6_23_8_bounded_buffer;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Application {
	public static void main(String[] args) {
		CircularBuffer sharedLocation = new CircularBuffer();
		Producer producer = new Producer(sharedLocation);
		Consumer consumer = new Consumer(sharedLocation);

		// display the initial state of the CircularBuffer
		sharedLocation.displayState("Initial State");

		ExecutorService executor = Executors.newCachedThreadPool();
		executor.execute(producer);
		executor.execute(consumer);
		executor.shutdown();
	}
}
