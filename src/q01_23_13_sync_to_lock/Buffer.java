package q01_23_13_sync_to_lock;

public interface Buffer{
	void blockingPut(int value) throws InterruptedException;
	
	int blockingGet() throws InterruptedException;
}
