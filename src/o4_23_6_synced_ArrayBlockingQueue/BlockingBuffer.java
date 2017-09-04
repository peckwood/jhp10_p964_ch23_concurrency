package o4_23_6_synced_ArrayBlockingQueue;

import java.util.concurrent.ArrayBlockingQueue;

import o3_unsynced_producer_customer.Buffer;

public class BlockingBuffer implements Buffer{
	private final ArrayBlockingQueue<Integer> buffer = new ArrayBlockingQueue<>(1);
	
	@Override
	public void blockingPut(int value) throws InterruptedException {
		buffer.put(value);
		System.out.printf("%s%2d\t%s%d%n", "Producer writes ", value, 
				"Buffer cells occupied: ", buffer.size());
	}

	@Override
	public int blockingGet() throws InterruptedException {
		int readValue = buffer.take();
		System.out.printf("%s %2d\t%s%d%n", "Consumer reads ", readValue, "Buffer cells occupied: ", buffer.size());
		return readValue;
	}

}
