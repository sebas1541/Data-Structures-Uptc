package co.edu.uptc.tests;

import co.edu.uptc.models.Cashier;
import co.edu.uptc.models.Customer;
import co.edu.uptc.structures.MyList;

public class CashierTest {

    public static void main(String[] args) {
        // Create a MyList and add some customers
        MyList<Customer> customers = new MyList<>();
        customers.add(new Customer(1)); // Random time will be assigned
        customers.add(new Customer(2)); // Random time will be assigned

        // Create a Cashier with the list of customers
        Cashier cashier = new Cashier(customers);

        // Start the cashier in a new thread
        Thread thread = new Thread(cashier);
        thread.start();

        // Wait for the cashier to finish processing
        try {
            thread.join();
        } catch (InterruptedException e) {
            System.out.println("The main thread was interrupted.");
        }

        // Check if all customers are processed
        if (cashier.getCustomers().isEmpty()) {
            System.out.println("Test Passed: All customers processed.");
        } else {
            System.out.println("Test Failed: Some customers are not processed.");
        }
    }
}
