package co.edu.uptc.models;

public class Customer {
    private int time;
    private int id;

    public Customer(int id) {
        this.time = (int) (Math.random() * 1000);
        this.id = id;
    }

    public int getTime() {
        return time;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
