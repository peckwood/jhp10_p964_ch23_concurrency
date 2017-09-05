package o6_23_8_bounded_buffer;

public class CircularBuffer implements Buffer {

	private final int[] buffer = { 0, 0, 0 };
	private int writeIndex = 0;
	private int readIndex = 0;
	private int occupiedCells = 0;

	@Override
	public void blockingPut(int value) throws InterruptedException {
		synchronized (this) {
			while (occupiedCells == buffer.length) {
				System.out.printf("Buffer is full. Producer waits.%n");
				wait();
			}
			buffer[writeIndex] = value;
			writeIndex = (writeIndex == 2 ? 0 : writeIndex + 1);
			occupiedCells++;
			displayState("Producer writes " + value);
			notifyAll();
		}
	}

	@Override
	public int blockingGet() throws InterruptedException {
		synchronized (this) {
			while (occupiedCells == 0) {
				System.out.printf("Buffer is empty. Consumer waits.%n");
				wait();
			}
			int readValue = buffer[readIndex];
			readIndex = (readIndex == 2 ? 0 : readIndex + 1);
			occupiedCells--;
			displayState("Consumer reads " + readValue);
			notifyAll();
			return readValue;
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
