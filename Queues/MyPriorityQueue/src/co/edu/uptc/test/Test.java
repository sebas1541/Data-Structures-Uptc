package co.edu.uptc.test;

import co.edu.uptc.models.DocumentPrint;
import co.edu.uptc.models.PrinterManager;

public class Test {
    public static void main(String[] args) {

        DocumentPrint document = new DocumentPrint(10, "Jelow", "doc");
        DocumentPrint document1 = new DocumentPrint(110, "Hola", "pdf");
        DocumentPrint document2 = new DocumentPrint(20, "Bonjour", "xlsx");

        PrinterManager printerManager = new PrinterManager(5);
        printerManager.sendFileToPrint(document);
        printerManager.sendFileToPrint(document1);
        printerManager.sendFileToPrint(document2);

        System.out.println(printerManager.printFIle());
        System.out.println(printerManager.printFIle());
        System.out.println(printerManager.printFIle());


    }
}
