package co.edu.uptc.models;

public class Customer {
    private int id;
    private int serviceTime;

    public Customer(int id) {
        this.id = id;
        this.serviceTime = (int) (Math.random() * 10) + 1;
    }

    public int getId() {
        return id;
    }

    public int getServiceTime() {
        return serviceTime;
    }
}
