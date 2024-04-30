package co.edu.uptc.test;

import co.edu.uptc.models.Cashier;
import co.edu.uptc.models.Customer;
import co.edu.uptc.structures.MyQueue;

public class Test {
    public static void main(String[] args){
        Customer customer1 = new Customer(1);
        Customer customer2 = new Customer(2);
        Customer customer3 = new Customer(3);
        Customer customer4 = new Customer(4);
        Customer customer5 = new Customer(5);

        MyQueue<Customer> customers = new MyQueue<>();
        customers.push(customer1);
        customers.push(customer2);
        customers.push(customer3);
        customers.push(customer4);
        customers.push(customer5);

        Cashier cashier = new Cashier(customers);

        cashier.start();


    }
}
