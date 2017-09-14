package q01_23_13_sync_to_lock;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class CircularBuffer implements Buffer {
	private final Lock lock = new ReentrantLock();
	private final Condition bufferNotfull= lock.newCondition();
	private final Condition bufferNotEmpty= lock.newCondition();
	
	
	private final int[] buffer = { 0, 0, 0 };
	private int writeIndex = 0;
	private int readIndex = 0;
	private int occupiedCells = 0;

	@Override
	public void blockingPut(int value) throws InterruptedException {
		lock.lock();
		try {
			while (occupiedCells == buffer.length) {
				System.out.printf("Buffer is full. Producer waits.%n");
				bufferNotfull.await();
			}
			buffer[writeIndex] = value;
			writeIndex = (writeIndex == 2 ? 0 : writeIndex + 1);
			occupiedCells++;
			displayState("Producer writes " + value);
			bufferNotEmpty.signalAll();
		}finally {
			lock.unlock();
		}
		
	}

	@Override
	public int blockingGet() throws InterruptedException {
		lock.lock();
		try {
			while (occupiedCells == 0) {
				System.out.printf("Buffer is empty. Consumer waits.%n");
				bufferNotEmpty.await();
			}
			int readValue = buffer[readIndex];
			readIndex = (readIndex == 2 ? 0 : readIndex + 1);
			occupiedCells--;
			displayState("Consumer reads " + readValue);
			bufferNotfull.signalAll();
			return readValue;
		} finally {
			lock.unlock();
		}
	}

	// display current operation and buffer state
	public synchronized void displayState(String operation) {
		// output operation and number of occupied buffer cells
		System.out.printf("%s%s%d)%n%s", operation, " (buffer cells occupied: ", occupiedCells, "buffer cells:  ");

		for (int value : buffer)
			System.out.printf(" %2d  ", value); // output values in buffer

		System.out.printf("%n               ");

		for (int i = 0; i < buffer.length; i++)
			System.out.print("---- ");

		System.out.printf("%n               ");

		for (int i = 0; i < buffer.length; i++) {
			if (i == writeIndex && i == readIndex)
				System.out.print(" WR"); // both write and read index
			else if (i == writeIndex)
				System.out.print(" W   "); // just write index
			else if (i == readIndex)
				System.out.print("  R  "); // just read index
			else
				System.out.print("     "); // neither index
		}

		System.out.printf("%n%n");
	}

}
