package q01_23_13_sync_to_lock;

import java.security.SecureRandom;

public class Consumer implements Runnable{

	private static final SecureRandom randomGenerator = new SecureRandom();
	private final Buffer sharedLocation;
	
	public Consumer(Buffer sharedLocation) {
		this.sharedLocation = sharedLocation;
	}

	@Override
	public void run() {
		int sum = 0;
		for (int counter = 1; counter < 11; counter++) {
			try {
				Thread.sleep(randomGenerator.nextInt(3000));
				sum += sharedLocation.blockingGet();
			} catch (InterruptedException e) {
				e.printStackTrace();
				Thread.currentThread().interrupt();
			}
		}
		System.out.printf("%n%s %d%n%s%n", "Consumer read values totaling", sum, "Terminating Consumer");
	}

}
