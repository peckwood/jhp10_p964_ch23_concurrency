package o2_thread_synchronization;

public class ArrayWriter implements Runnable{

	private final SimpleArray sharedSimpleArray;
	private final int startValue;
	
	
	
	public ArrayWriter(SimpleArray simpleArray, int startIndex) {
		this.sharedSimpleArray = simpleArray;
		this.startValue = startIndex;
	}

	@Override
	public void run() {
		for(int i=startValue;i<startValue+3;i++){
			sharedSimpleArray.add(i);
		}
	}
	
}
