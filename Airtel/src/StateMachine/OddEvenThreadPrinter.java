package StateMachine;

class OddEvenThread implements Runnable {
	int max;
	Integer isOdd = null;
	static Object lock = new Object();
	static int counter = 1;

	public OddEvenThread(int max) {
		this.max = max;
	}

	private void setThreadType() {
		if (isOdd == null) {
			isOdd = (counter % 2);
			if (isOdd == 0)
				Thread.currentThread().setName("Even Thread");
			else
				Thread.currentThread().setName("Odd Thread");
		}
	}

	@Override
	public void run() {
		while (counter < max) {
			synchronized (lock) {
				setThreadType();
				while (counter % 2 != isOdd) {
					try {
						lock.wait();
					} catch (Exception ex) {
						ex.printStackTrace();
					}
				}
				if (counter < max) {
					System.out.println(Thread.currentThread().getName() + " " + counter);
					counter++;
				}
				lock.notifyAll();
			}
		}

	}

}

public class OddEvenThreadPrinter {

	public static void main(String s[]) {
		int max = 10;
		OddEvenThread runnable1 = new OddEvenThread(10);
		OddEvenThread runnable2 = new OddEvenThread(10);

		Thread t1 = new Thread(runnable1);
		Thread t2 = new Thread(runnable2);

		t1.start();
		t2.start();
	}

}
