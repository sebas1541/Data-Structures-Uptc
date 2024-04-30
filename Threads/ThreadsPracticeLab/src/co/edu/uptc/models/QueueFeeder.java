package co.edu.uptc.models;

import co.edu.uptc.structures.MyList;

public class QueueFeeder extends Thread {
    private MyList<Customer> queue;

    public QueueFeeder(MyList<Customer> queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        for (int i = 1; i <= 60; i++) {
            Customer customer = new Customer(i);
            synchronized (queue) {
                queue.add(customer);
                System.out.println("Cliente " + customer.getId() + " entra con tiempo estimado " + customer.getServiceTime() + " minutos");
            }
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }
}
