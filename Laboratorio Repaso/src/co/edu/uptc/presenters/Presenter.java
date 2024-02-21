package co.edu.uptc.presenters;

import co.edu.uptc.models.Product;
import co.edu.uptc.models.RetailStore;
import co.edu.uptc.models.RetailStoreManager;
import co.edu.uptc.views.View;

public class Presenter {
    private RetailStoreManager retailStoreManager;
    private View view;

    public Presenter() {
        this.retailStoreManager = new RetailStoreManager();
        this.view = new View();
    }



    public void run() {
        mainMenu();
    }

    private void mainMenu() {
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
            view.showMessage("Gestión de Tiendas\n1. Agregar Tienda\n2. Agregar Producto a Tienda\n3. Ver Productos de la Tienda\n4. Vender Artículo\n5. Regresar al Menú Principal");
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
                    break;
                default:
                    view.showMessage("Intente d enuevo");
            }
        } while (option != 5);
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


    private void viewStoreProducts() {
        String storeName = view.readString("Ingrese el nombre de la tienda: ");
        RetailStore store = retailStoreManager.findStoreByName(storeName);
        if (store != null) {
            view.showMessage(store.loadProducts().toString());
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
