package o5_23_7_synced_buffer;

public class SynchronizedBuffer implements Buffer{

	private int buffer = -1;
	private boolean occupied = false;
	
	@Override
	public synchronized void blockingPut(int value) throws InterruptedException {
		while(occupied){
			System.out.println("Producer tries to write."); // for demo only
			displayState("Buffer full. Producer waits."); // for demo only
			wait();
		}
		buffer = value;
		occupied = true;
		displayState("Producer writes " + buffer); // for demo only
		notifyAll();
	}

	@Override
	public synchronized int blockingGet() throws InterruptedException {
		while(!occupied){
			System.out.println("Consumer tries to read."); // for demo only
			displayState("Buffer empty. Consumer waits."); // for demo only
			wait();
		}
		occupied = false;
		displayState("Consumer reads " + buffer); // for demo only
		notifyAll();
		return buffer;
	}
	
	   private synchronized void displayState(String operation)
	   {
	      System.out.printf("%-40s%d\t\t%b%n%n", operation, buffer, 
	         occupied);
	   } 

}
