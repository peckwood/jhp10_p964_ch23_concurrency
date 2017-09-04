package o3_unsynced_producer_customer;

public interface Buffer{
	void blockingPut(int value) throws InterruptedException;
	
	int blockingGet() throws InterruptedException;
}
