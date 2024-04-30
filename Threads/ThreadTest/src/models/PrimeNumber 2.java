package models;

import java.util.ArrayList;
import java.util.List;

public class PrimeNumber {

    private int start;
    private int end;

    public PrimeNumber(int start, int end) {
        this.start = start;
        this.end = end;
    }

    public Integer findPrime() {
        List<Integer> primes = new ArrayList<>();

        for (int i = this.start; i < this.end; i++) {
            if (isPrime(i)){
                primes.add(i);
            }
        }
        return primes.size();
    }

    private boolean isPrime(int number) {
        if (number <= 1){
            return false;
        }
        for (int i = 2; i <= Math.sqrt(number); i++) {
            if (number % i == 0) {
                return false;
            }
        }
        return true;
    }
}
