package co.edu.uptc.models;

import co.edu.uptc.structures.MyList;

public class Cashier extends Thread {
    private static int nextId = 1;
    private int cashierId;
    private MyList<Customer> customers;
    private static int totalServed = 0;

    public Cashier(MyList<Customer> customers) {
        this.customers = customers;
        synchronized (Cashier.class) {
            this.cashierId = nextId++;
        }
    }

    @Override
    public void run() {
        while (!isInterrupted()) {
            Customer customer = null;
            synchronized (customers) {
                if (!customers.isEmpty()) {
                    customer = customers.removeLast();
                }
            }
            if (customer != null) {
                try {
                    System.out.println("Cajero " + cashierId + " atiende al cliente " + customer.getId());
                    Thread.sleep(customer.getServiceTime());
                    System.out.println("Cajero " + cashierId + " terminó de atender al cliente " + customer.getId() + " en " + customer.getServiceTime() + " minutos");
                    incrementTotalServed();
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        }
    }

    private static synchronized void incrementTotalServed() {
        totalServed++;
    }

    public static synchronized int getTotalServed() {
        return totalServed;
    }
}
