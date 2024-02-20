package co.edu.uptc.models;

import co.edu.uptc.structures.DoubleList;

public class RetailStore {
    private String name;
    private String address;
    private int idCounter = 0;


    private DoubleList<Product> productList;

    public RetailStore(String name, String address){
        this.name = name;
        this.address = address;
        this.productList = new DoubleList<>();
    }
    public RetailStore(){
        this.productList = new DoubleList<>();
    }

    //Punto 1
    public void addProduct(String name, int quantity, double price){
        idCounter++;
        Product newProduct = new Product(idCounter, name, quantity, price);
        productList.insert(newProduct);
    }

    public Product findById(int idCode){
        for (Product product : productList){
            if (product.getIdCode() == idCode){
                return product;
            }
        }return null;
    }
    public int numberOfProducts(){
        return productList.size();
    }

    public double totalCostOfProducts(){
        double totalCost = 0;
        for (Product product : productList){
            totalCost = totalCost + product.getPrice();
        }
        return totalCost;
    }



    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
    public DoubleList<Product> getProductList() {
        return productList;
    }

    public void setProductList(DoubleList<Product> productList) {
        this.productList = productList;
    }


}
