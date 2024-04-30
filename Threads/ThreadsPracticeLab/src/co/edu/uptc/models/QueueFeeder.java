package co.edu.uptc.models;
import co.edu.uptc.structures.MyQueue;

public class QueueFeeder extends Thread {
    private MyQueue<Customer> queue;
    private int duration;


    public QueueFeeder(MyQueue<Customer> queue, int duration) {
        this.queue = queue;
        this.duration = duration;
    }

    @Override
    public void run() {
        for (int i = 1; i <= this.duration; i++) {
            Customer customer = new Customer(i);
            synchronized (queue) {
                queue.push(customer);
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
