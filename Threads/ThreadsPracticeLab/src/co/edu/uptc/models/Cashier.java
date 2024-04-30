package co.edu.uptc.models;
import co.edu.uptc.structures.MyQueue;

public class Cashier extends Thread {
    private static int nextId = 1; // This keeps track of the next cashier ID
    private int cashierId;
    private MyQueue<Customer> customers;
    private static int totalServed = 0;

    public Cashier(MyQueue<Customer> customers) {
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
                    customer = customers.poll();
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

    public static synchronized void resetTotalServed() {
        totalServed = 0;
    }

    public static synchronized void resetNextId() {
        nextId = 1;
    }
}
