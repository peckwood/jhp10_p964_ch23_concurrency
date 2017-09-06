package o7_23_9_lock_condition;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class SynchronizedBuffer implements Buffer {

	Lock lock = new ReentrantLock();
	Condition canWrite = lock.newCondition();
	Condition canRead = lock.newCondition();

	private int buffer = -1;
	private boolean occupied = false;

	@Override
	public void blockingPut(int value) throws InterruptedException {
		lock.lock();
		canWrite.signal();
		try {
			while (occupied) {
				System.out.println("Producer tries to write.");
				displayState("Buffer full. Producer waits.");
				canWrite.await();
			}
			buffer = value;
			occupied = true;

			displayState("Producer writes " + buffer);
			canRead.signalAll();
		} finally {
			lock.unlock();
		}
	}

	@Override
	public int blockingGet() throws InterruptedException {
		lock.lock();
		try{
			while (!occupied) {
				System.out.println("Consumer tries to read.");
				displayState("Buffer empty. Consumer waits.");
				canRead.await();
			}
			occupied = false;
			displayState("Consumer reads " + buffer);
			canWrite.signalAll();
			return buffer;
		}finally{
			lock.unlock();
		}
		
		
	}

	private void displayState(String operation) {
		try {
			lock.lock();
			System.out.printf("%-40s%d\t\t%b%n%n", operation, buffer, occupied);
		} finally {
			lock.unlock();
		}
	}

}
