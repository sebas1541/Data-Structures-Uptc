package co.edu.uptc.test;

import co.edu.uptc.models.Product;
import co.edu.uptc.models.RetailStore;
import co.edu.uptc.models.RetailStoreManager;

public class Test {
    public static void main(String[] args) {
        RetailStoreManager manager = new RetailStoreManager();

        RetailStore store = new RetailStore();
        manager.createStore("Exito", "Calle 10");

        Product product = new Product(1, "hola", 10, 15);
        Product product1 = new Product(2, "hola", 10, 15);
        Product product2 = new Product(3, "hola", 10, 15);
        Product product3 = new Product(10, "hola", 10, 15);
        Product product4 = new Product(6, "hola", 10, 15);
        Product product5 = new Product(4, "hola", 10, 15);
        Product product6 = new Product(5, "hola", 10, 15);
        Product product7 = new Product(8, "hola", 10, 15);
        Product product8 = new Product(7, "hola", 10, 15);
        Product product9 = new Product(9, "hola", 10, 15);


        manager.findStoreByName("Exito").addProduct(product);
        manager.findStoreByName("Exito").addProduct(product1);
        manager.findStoreByName("Exito").addProduct(product2);
        manager.findStoreByName("Exito").addProduct(product3);
        manager.findStoreByName("Exito").addProduct(product4);
        manager.findStoreByName("Exito").addProduct(product5);
        manager.findStoreByName("Exito").addProduct(product6);
        manager.findStoreByName("Exito").addProduct(product7);
        manager.findStoreByName("Exito").addProduct(product8);
        manager.findStoreByName("Exito").addProduct(product9);


        manager.findStoreByName("Exito").removeRangeOfProducts(1,7);
        System.out.println(manager.findStoreByName("Exito").loadProducts().toString());
        System.out.println(manager.findStoreByName("Exito").quantityOfProducts());
        System.out.println(manager.findStoreByName("Exito").totalCostOfProducts());
    }
}