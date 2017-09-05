package o6_23_8_bounded_buffer;

import java.security.SecureRandom;

public class Producer implements Runnable {
	private final Buffer sharedLocation;
	private static final SecureRandom randomGenerator = new SecureRandom();

	public Producer(Buffer buffer) {
		this.sharedLocation = buffer;
	}

	@Override
	public void run() {
		int sum = 0;
		for (int counter = 1; counter < 11; counter++) {
			try {
				Thread.sleep(randomGenerator.nextInt(3000));
				sharedLocation.blockingPut(counter);
				sum += counter;
			} catch (InterruptedException e) {
				e.printStackTrace();
				Thread.currentThread().interrupt();
			}
		}
		System.out.printf("Producer done producing%nTerminating Producer%n");
	}

}
