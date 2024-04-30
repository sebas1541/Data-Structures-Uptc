package co.edu.uptc.models;

import co.edu.uptc.structures.MyList;
import co.edu.uptc.structures.MyQueue;

public class Cashier extends Thread {

    private MyQueue<Customer> customers;



    public Cashier(MyQueue<Customer> customers) {
        this.customers = customers;
    }

    public void run(){
        while (!customers.isEmpty()) {
                Customer customer = customers.poll();
                try {
                    Thread.sleep(customer.getTime());

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("Customer " + customer.getId() + " served");
        }
    }









}
