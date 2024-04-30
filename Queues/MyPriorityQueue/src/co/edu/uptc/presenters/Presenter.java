package co.edu.uptc.presenters;

import co.edu.uptc.models.DocumentPrint;
import co.edu.uptc.models.PrinterManager;
import co.edu.uptc.views.View;

import javax.print.Doc;

public class Presenter {

    private PrinterManager printerManager;
    private View view;

    public Presenter() {
        this.printerManager = new PrinterManager(5);
        this.view = new View();
    }

    public void createFiles(){
        DocumentPrint document = new DocumentPrint(10, "Jelow", "doc");
        DocumentPrint document1 = new DocumentPrint(110, "Hola", "pdf");
        DocumentPrint document2 = new DocumentPrint(20, "Bonjour", "xlsx");

        printerManager.sendFileToPrint(document);
        printerManager.sendFileToPrint(document1);
        printerManager.sendFileToPrint(document2);
    }



    public void run() {
        mainMenu();
    }

    private void mainMenu() {
        createFiles();
        int option;
        do {
            view.showMessage("Menú Principal\n1. Agregar Archivo \n2. Poner en cola\n3. Imprimir\n4. Salir");
            option = view.readInt("Elige una opción: ");
            switch (option) {
                case 1:
                    createFiles();
                    break;
                case 2:
                    addFileToQueue();
                    break;
                case 3:
                    printFile();
                    break;
                default:
                    view.showMessage("Intente de nuevo");
            }
        } while (option != 4);
    }



    private void addFileToQueue(){
        int numPages = view.readInt("Ingrese el número de 'paginas");
        String fileName = view.readString("Nombre del archivo");
        String fileExtension = view.readString("Ingrse la extension de archivo");
        DocumentPrint documentPrint = new DocumentPrint(numPages, fileName, fileExtension);
        printerManager.sendFileToPrint(documentPrint);
    }

    public void printFile(){
        DocumentPrint documentPrint = printerManager.printFIle();
        view.showMessage(formatProductDetails(documentPrint));
    }





    /**
     * Método utilitario para imprimir productos
     * Se usan cadenas de formato dependiendo si es
     * entero, string o double
     * @return un string de productos para implementar en la vista.
     */


    private String formatProductDetails(DocumentPrint document) {
        return String.format(
                "Detalles del Archivo:\n" +
                        "-----------------\n" +
                        "Numero de páginas:    %d\n" +
                        "Nombre:       %s\n" +
                        "Extensión:   %s\n",
                document.getNumOfPages(), document.getName(), document.getExtension());
    }





}