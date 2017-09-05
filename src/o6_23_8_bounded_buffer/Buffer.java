package o6_23_8_bounded_buffer;

public interface Buffer{
	void blockingPut(int value) throws InterruptedException;
	
	int blockingGet() throws InterruptedException;
}
