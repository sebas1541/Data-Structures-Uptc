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

    //Punto 2

    public Product findById(int idCode){
        for (Product product : productList){
            if (product.getIdCode() == idCode){
                return product;
            }
        }return null;
    }

    //Punto 3
    //Devuelve todos los items en el supermercado
    public int quantityOfProducts(){
        int quantity = 0;
        for (Product product : productList){
            quantity += product.getQuantity();
        }
        return quantity;
    }

    //Devuelve todos los tipos de productos únicos en el supermercado
    public int numberOfProducts(){
        return productList.size();
    }

    //Punto 4

    public double totalCostOfProducts(){
        double totalCost = 0;
        for (Product product : productList){
            totalCost = totalCost + product.getPrice() * product.getQuantity();
        }
        return totalCost;
    }

    //Punto 5
    public void sellItem(int id){
        Product currentProduct = findById(id);
        if (currentProduct != null && currentProduct.getQuantity() > 0){
            currentProduct.setQuantity(currentProduct.getQuantity() - 1);
        }
    }

    public void removeProduct(int id){
        Product currentProduct = findById(id);
        productList.remove(currentProduct);
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
