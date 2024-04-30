package co.edu.uptc.models;

import co.edu.uptc.structures.MyQueue;

public class QueueFeeder extends Thread {
    private MyQueue<Customer> queue;
    private int duration;
    private boolean stopSimulation;

    public QueueFeeder(MyQueue<Customer> queue, int duration) {
        this.queue = queue;
        this.duration = duration;
        this.stopSimulation = false;
    }

    @Override
    public void run() {
        for (int i = 1; i <= this.duration; i++) {
            Customer customer = new Customer(i);
            synchronized (queue) {
                queue.push(customer);
                System.out.println("Cliente " + customer.getId() + " entra con tiempo estimado " + customer.getServiceTime() + " minutos");
            }

            if (i == this.duration) {
                this.stopSimulation = true;
                break;
            }

            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }

    public boolean shouldStopSimulation() {
        return this.stopSimulation;
    }
}