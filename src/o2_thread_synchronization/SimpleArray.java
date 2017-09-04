package o2_thread_synchronization;

import java.security.SecureRandom;
import java.util.Arrays;

public class SimpleArray {
	private int[] array;
	private int writeIndex = 0;
	private static final SecureRandom randomGenerator = new SecureRandom();
	
	public SimpleArray(int size){
		array = new int[size];
	}
	
	//try remove synchronized and see what happens
	public synchronized void add(int element){
		int position = writeIndex;
		
		try {
			Thread.sleep(randomGenerator.nextInt(5000));
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		array[position] = element;
		System.out.printf("%s wrote %2d to element %d. %n", Thread.currentThread().getName(), element, position);
		writeIndex++;
		System.out.printf("next write index: %d %n", writeIndex);
	}

	@Override
	public String toString() {
		return "SimpleArray [array=" + Arrays.toString(array) + "]";
	}
	
}
