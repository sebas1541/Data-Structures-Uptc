package models;

public class MyThread extends Thread {

    private int start;
    private int end;
    private int[] results;
    private int index;

    public MyThread(int start, int end, int[] results, int index) {
        this.start = start;
        this.end = end;
        this.results = results;
        this.index = index;
    }

    @Override
    public void run() {
        results[index] = checkPrime();
    }

    public int checkPrime() {
        PrimeNumber primeNumber = new PrimeNumber(start, end);
        return primeNumber.findPrime();
    }
}
