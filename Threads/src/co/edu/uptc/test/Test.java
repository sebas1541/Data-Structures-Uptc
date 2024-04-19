package co.edu.uptc.test;

import co.edu.uptc.model.CheckPrime;

public class Test {
    public static void main(String[] args) throws InterruptedException {
        int start = 1;
        int end = 500000;

        Thread thread1 = new Thread(new CheckPrime(start, end));
        //Thread thread2 = new Thread(new CheckPrime(end / 5, 2 * end / 5));
        //Thread thread3 = new Thread(new CheckPrime(2 * end / 5, 3 * end / 5));
        //Thread thread4 = new Thread(new CheckPrime(3 * end / 5, 4 * end / 5));
        //Thread thread5 = new Thread(new CheckPrime(4 * end / 5, end));

        long first = System.currentTimeMillis();

        thread1.start();

        while (thread1.isAlive()){
            thread1.sleep(10);
        }

        int numberOfPrimes = CheckPrime.getPrimeCount();

        long second = System.currentTimeMillis() - first;
        System.out.println(numberOfPrimes);
        System.out.println(second + " milisegundos");
        System.out.println(Runtime.getRuntime().availableProcessors());

    }
}