package com.teddy.java;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Create two threads that produce an alternating output like
 * T1: 0
 * T2: 1
 * T1: 2 ... and so on
 * 
 * @author vidit
 */

/**
 * <code>Counter</code> class defines the counter
 */
class Counter {
	private int value = 0;

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}

	public void increment() {
		value++;
	}
}

/**
 * <code>AlternateThread</code>
 * 
 */
public class AlternateThread {
	public Counter counter = new Counter();

	public static void main(String[] args) {

		final AlternateThread at = new AlternateThread();

		// The problem statement requires 2 threads
		ExecutorService es = Executors.newFixedThreadPool(2);

		// Fire thread-1
		es.execute(new Runnable() {
			public void run() {
				at.print();
			}
		});

		// Fire thread-2
		es.execute(new Runnable() {
			public void run() {
				at.print();
			}
		});
	}

	public void print() {
		// This block of code needs to be thread safe.
		// From your operating system basics, remember the use of monitors
		// 'this' is serving as the monitor, so that two threads cannot
		// access this block of code at the same time.
		synchronized (this) {
			while (this.counter.getValue() < 10) {

				// Thread-i says
				// "My fellow thread, I am done with my job & notifying you."
				// Its use comes later, when there is some thread already
				// waiting.
				this.notify();

				// Thread-i performing its duties :)
				System.out.println(Thread.currentThread().getName() + ": "
						+ this.counter.getValue());
				this.counter.increment();

				// Thread-i says
				// "I have made use of the resources and I am going to wait 
				// like a good thread until, someone wakes me up!"
				try {
					this.wait();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
