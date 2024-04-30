package co.edu.uptc.models;

import co.edu.uptc.structures.MyPriorityQueue;

public class PrinterManager {
    MyPriorityQueue<DocumentPrint> documentQueue;

    public PrinterManager(int n) {
        this.documentQueue = new MyPriorityQueue<>(n);
    }

    public void createFiles(){

    }

    public void sendFileToPrint(DocumentPrint document){

        if (document.getNumOfPages() > 25) documentQueue.push(document, 4);
        if (document.getNumOfPages() > 20 && document.getNumOfPages() <= 25) documentQueue.push(document, 3);
        if (document.getNumOfPages() > 15 && document.getNumOfPages() <= 20) documentQueue.push(document, 2);
        if (document.getNumOfPages() > 10 && document.getNumOfPages() <= 15) documentQueue.push(document, 1);
        if (document.getNumOfPages() <= 10) documentQueue.push(document, 0);

    }

    public MyPriorityQueue<DocumentPrint> getDocumentQueue() {
        return documentQueue;
    }

    public DocumentPrint printFIle(){
        return documentQueue.poll();
    }

}
