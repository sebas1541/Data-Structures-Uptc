package co.edu.uptc.test;

import co.edu.uptc.models.Cashier;
import co.edu.uptc.models.Customer;
import co.edu.uptc.models.QueueFeeder;
import co.edu.uptc.structures.MyQueue;

public class Test {
    public static void main(String[] args) {
        MyQueue<Customer> queue = new MyQueue<>();
        int numCashiers = 3;

        QueueFeeder feeder = new QueueFeeder(queue, 60);
        feeder.start();

        Cashier[] cashiers = new Cashier[numCashiers];
        for (int i = 0; i < numCashiers; i++) {
            cashiers[i] = new Cashier(queue);
            cashiers[i].start();
        }

        try {
            feeder.join();
            Thread.sleep(100);
            for (Cashier cashier : cashiers) {
                cashier.interrupt();
                cashier.join();
            }
        } catch (InterruptedException e) {
        }

        System.out.println("Numero de clientes atendidos: " + Cashier.getTotalServed());
    }
}
