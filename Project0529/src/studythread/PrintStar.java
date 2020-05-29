package studythread;

public class PrintStar {

	public static void main(String[] args) {

		Thread thread1 = new Thread() {
			@Override
			public void run() {
				while (true) {
					try {
						Thread.sleep(500);
						System.out.println("¡Ù");
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		};

		Thread thread2 = new Thread() {
			@Override
			public void run() {
				while (true) {
					try {
						Thread.sleep(100);
						System.out.println("¡Ú");
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		};
		thread1.start();
		thread2.start();

	}

}
