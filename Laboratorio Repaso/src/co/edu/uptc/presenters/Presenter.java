package co.edu.uptc.presenters;

import co.edu.uptc.models.Product;
import co.edu.uptc.models.RetailStore;
import co.edu.uptc.models.RetailStoreManager;
import co.edu.uptc.views.View;

import java.util.ArrayList;

public class Presenter {
    private RetailStoreManager retailStoreManager;
    private View view;

    public Presenter() {
        this.retailStoreManager = new RetailStoreManager();
        this.view = new View();
    }

    public void createFixedStores(){
        retailStoreManager.createStore("Exito Viva Tunja", "Calle X");
        retailStoreManager.createStore("Exito Centro Tunja", "Calle Y");

        Product product1 = new Product(2, "Manzana", 11, 800);
        Product product2 = new Product(3, "Leche", 22,  5000);
        Product product3 = new Product(10, "Cereal", 18, 4000);
        Product product4 = new Product(6, "Pan", 15, 500);

        retailStoreManager.findStoreByName("Exito Viva Tunja").addProduct(product1);
        retailStoreManager.findStoreByName("Exito Centro Tunja").addProduct(product2);
        retailStoreManager.findStoreByName("Exito Viva Tunja").addProduct(product3);
        retailStoreManager.findStoreByName("Exito Centro Tunja").addProduct(product4);
    }


    public void run() {
        mainMenu();
    }

    private void mainMenu() {
        createFixedStores();
        int option;
        do {
            view.showMessage("Menú Principal\n1. Administrar Tiendas\n2. Ver Valor de la Cadena\n3. Salir");
            option = view.readInt("Elige una opción: ");
            switch (option) {
                case 1:
                    manageStores();
                    break;
                case 2:
                    viewChainValue();
                    break;
                case 3:
                    view.showMessage("Saliendo del programa");
                    break;
                default:
                    view.showMessage("Intente de nuevo");
            }
        } while (option != 3);
    }

    private void manageStores() {
        int option;
        do {
            view.showMessage("Gestión de Tiendas\n1. Agregar Tienda\n2. Agregar Producto a Tienda\n3. Ver Productos de la Tienda\n4. Vender Artículo\n5.Remover Articulos en rango\n6.Número de artículos\n7.Ver info de artículo con Id \n8. Regresar al Menú Principal");
            option = view.readInt("Elige una opción: ");
            switch (option) {
                case 1:
                    createStore();
                    break;
                case 2:
                    addProductToStore();
                    break;
                case 3:
                    viewStoreProducts();
                    break;
                case 4:
                    sellItem();
                    break;
                case 5:
                    removeRange();
                    break;
                case 6:
                    numberOfArticles();
                    break;
                case 7:
                    viewProductInfoById();
                    break;
                default:
                    view.showMessage("Intente de nuevo");
            }
        } while (option != 8);
    }

    private void createStore() {
        String name = view.readString("Ingrese el nombre de la tienda: ");
        String address = view.readString("Ingrese la dirección de la tienda: ");
        retailStoreManager.createStore(name, address);
        view.showMessage("¡Tienda agregada exitosamente!");
    }




    private void addProductToStore() {

        String storeName = view.readString("Ingrese el nombre de la tienda donde añadir el producto: ");
        RetailStore store = retailStoreManager.findStoreByName(storeName);
        if (store != null) {
            String name = view.readString("Ingrese el nombre del producto: ");
            int quantity = view.readInt("Ingrese la cantidad del producto: ");
            double price = view.readInt("Ingrese el precio del producto: ");
            int id = view.readInt("Ingrese el código del producto");
            Product currentProduct = new Product(id, name, quantity, price);
            store.addProduct(currentProduct);
            view.showMessage("¡Producto añadido exitosamente!");
        } else {
            view.showMessage("Tienda no encontrada.");
        }
    }

    private void removeRange(){
        int min =  view.readInt("Ingrese el valor mínimo del rango");
        int max = view.readInt("Ingrease el valor máximo del rango");
        String storeName = view.readString("Ingrese el nombre de la tienda: ");
        RetailStore store = retailStoreManager.findStoreByName(storeName);
        if (store != null) {
            store.removeRangeOfProducts(min, max);
            view.showMessage("El rango de productos fue eliminado");
        } else {
            view.showMessage("Tienda no encontrada.");
        }
    }

    /**
     * Clase utilitaria para imprimor productos
     * Se usan cadenas de formato dependiendo si es
     * entero, string o double
     * @param product Es el producto para formatear.
     * @return un string de productos para implementar en la vista.
     */
    private String formatProductDetails(Product product) {
        return String.format(
                "Detalles de Producto:\n" +
                        "-----------------\n" +
                        "Código ID:    %d\n" +
                        "Nombre:       %s\n" +
                        "Cantidad:   %d\n" +
                        "Precio:      $%.2f\n",
                product.getIdCode(), product.getName(), product.getQuantity(), product.getPrice());
    }

    private void viewStoreProducts() {
        String storeName = view.readString("Ingrese el nombre de la tienda: ");
        RetailStore store = retailStoreManager.findStoreByName(storeName);
        if (store != null) {
            ArrayList<Product> products = store.loadProducts();
            StringBuilder productsDetails = new StringBuilder();
            for (Product product : products) {
                productsDetails.append(formatProductDetails(product)).append("\n");
            }
            view.showMessage(productsDetails.toString());
        } else {
            view.showMessage("Tienda no encontrada.");
        }
    }

    private void viewProductInfoById(){
        String storeName = view.readString("Ingrese el nombre de la tienda donde añadir el producto: ");
        RetailStore store = retailStoreManager.findStoreByName(storeName);
        if (store != null) {
            int id = view.readInt("Ingrese el código del producto");
            Product currentProduct = store.findById(id);
            view.showMessage(formatProductDetails(currentProduct));
        } else {
            view.showMessage("Tienda no encontrada.");
        }
    }



    private void numberOfArticles() {
        String storeName = view.readString("Ingrese el nombre de la tienda: ");
        RetailStore store = retailStoreManager.findStoreByName(storeName);
        if (store != null) {
            view.showMessage(String.valueOf(store.numberOfProducts()));
        } else {
            view.showMessage("Tienda no encontrada.");
        }
    }




    private void sellItem() {
        String storeName = view.readString("Ingrese el nombre de la tienda: ");
        RetailStore store = retailStoreManager.findStoreByName(storeName);
        if (store != null) {
            int productId = view.readInt("Ingrese el ID del producto a vender: ");
            store.sellItem(productId);
            view.showMessage("¡Producto vendido exitosamente!");
        } else {
            view.showMessage("Tienda no encontrada.");
        }
    }

    private void viewChainValue() {
        double chainValue = retailStoreManager.seeChainValue();
        view.showMessage("Valor de los articulos" + chainValue);
    }

}
