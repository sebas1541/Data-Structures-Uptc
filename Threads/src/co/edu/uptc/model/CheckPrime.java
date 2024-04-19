package co.edu.uptc.model;

public class CheckPrime implements Runnable {

    private int start;
    private int end;
    private static int primeCount = 0;

    public CheckPrime(int start, int end) {
        this.start = start;
        this.end = end;
    }

    @Override
    public void run() {
        for (int i = start; i < end; i++) {
            if (isPrime(i)) {
                synchronized (CheckPrime.class) {
                    primeCount++;
                }
            }
        }
    }

    private boolean isPrime(int number) {
        if (number <= 1) {
            return false;
        }
        for (int i = 2; i < Math.sqrt(number) + 1; i++) {
            if (number % i == 0) {
                return false;
            }
        }
        return true;
    }

    public static int getPrimeCount() {
        return primeCount;
    }
}