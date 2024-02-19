package co.edu.uptc.models;

public class Product {
    private int idCode;
    private String name;
    private int quantity;
    private double price;

    public Product(int idCode, String name, int quantity, double price) {
        this.idCode = idCode;
        this.name = name;
        this.quantity = quantity;
        this.price = price;
    }

    public Product(){
    }

    public int getIdCode() {
        return idCode;
    }

    public void setIdCode(int idCode) {
        this.idCode = idCode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
