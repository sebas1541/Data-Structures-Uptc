package co.edu.uptc.test;

import co.edu.uptc.models.Product;
import co.edu.uptc.models.RetailStore;
import co.edu.uptc.models.RetailStoreManager;

public class Test {
    public static void main(String[] args) {
        RetailStoreManager manager = new RetailStoreManager();

        RetailStore store = new RetailStore();

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



        store.addProduct(product);
        store.addProduct(product1);
        store.addProduct(product2);
        store.addProduct(product3);
        store.addProduct(product4);
        store.addProduct(product5);
        store.addProduct(product6);
        store.addProduct(product7);
        store.addProduct(product8);
        store.addProduct(product9);


        store.removeRangeOfProducts(1,7);
        System.out.println(store.getProductList().show());
    }
}