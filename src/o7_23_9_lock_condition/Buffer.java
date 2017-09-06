package o7_23_9_lock_condition;

public interface Buffer{
	void blockingPut(int value) throws InterruptedException;
	
	int blockingGet() throws InterruptedException;
}
