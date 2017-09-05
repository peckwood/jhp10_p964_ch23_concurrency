package o5_23_7_synced_buffer;

public interface Buffer{
	void blockingPut(int value) throws InterruptedException;
	
	int blockingGet() throws InterruptedException;
}
