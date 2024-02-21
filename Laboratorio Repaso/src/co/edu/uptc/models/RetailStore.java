package co.edu.uptc.models;

import co.edu.uptc.exceptions.SingleIdException;
import co.edu.uptc.structures.DoubleList;

import java.util.ArrayList;
import java.util.Iterator;

public class RetailStore {
    private String name;
    private String address;
    private DoubleList<Product> productList;

    public RetailStore(String name, String address){
        this.name = name;
        this.address = address;
        this.productList = new DoubleList<>();
    }
    public RetailStore(){
        this.productList = new DoubleList<>();
    }

    public void addProduct(Product product) throws SingleIdException {
        Iterator<Product> iterator = productList.iterator();
        while (iterator.hasNext()){
            if(iterator.next().getIdCode() == product.getIdCode()){
               throw new SingleIdException("Los artículos no pueden tener el mismo ID");
            }
        }
        productList.insert(product);

    }

    public void removeRangeOfProducts(int min, int max) {
        if (productList.isEmpty()){
            return;
        }

       for (Product product : productList){
           if (product.getIdCode() >= min && product.getIdCode() <= max){
               productList.remove(product);
           }
       }
    }

    public ArrayList<Product> loadProducts(){
        Iterator<Product> iterator = productList.iterator();
        ArrayList<Product> productArrayList=new ArrayList<Product>();
        while (iterator.hasNext()){
            productArrayList.add(iterator.next());
        }
        return productArrayList;
    }


    public Product findById(int idCode){
        for (Product product : productList){
            if (product.getIdCode() == idCode){
                return product;
            }
        }return null;
    }

    public int quantityOfProducts(){
        int quantity = 0;
        for (Product product : productList){
            quantity += product.getQuantity();
        }
        return quantity;
    }


    public int numberOfProducts(){
        return productList.size();
    }

    public double totalCostOfProducts(){
        double totalCost = 0;
        for (Product product : productList){
            totalCost = totalCost + product.getPrice() * product.getQuantity();
        }
        return totalCost;
    }


    public void sellItem(int id){
        Product currentProduct = findById(id);
        if (currentProduct != null && currentProduct.getQuantity() > 0){
            currentProduct.setQuantity(currentProduct.getQuantity() - 1);
        }
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
