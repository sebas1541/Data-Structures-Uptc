public class Main {
    public static void main(String[] args) throws InterruptedException {
        long time1 = System.currentTimeMillis();

        int[] results = new int[5];

        MyThread thread1 = new MyThread(2, 99999, results, 0);
        MyThread thread2 = new MyThread(100000, 199999, results, 1);
        MyThread thread3 = new MyThread(200000, 299999, results, 2);
        MyThread thread4 = new MyThread(300000, 399999, results, 3);
        MyThread thread5 = new MyThread(400000, 500000, results, 4);

        thread1.start();
        thread2.start();
        thread3.start();
        thread4.start();
        thread5.start();

        thread1.join();
        thread2.join();
        thread3.join();
        thread4.join();
        thread5.join();

        int totalPrimes = 0;
        for (int count : results) {
            totalPrimes += count;
        }

        System.out.println(totalPrimes);
        System.out.println(System.currentTimeMillis() - time1 + " milisegundos");
    }
}
