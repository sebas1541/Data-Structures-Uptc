package co.edu.uptc.structures;

public class DoubleList<T> {
    private Node <T> head;

    public DoubleList(){
        head = null;
    }

    public void insert(T data){
        Node <T> previousNode = null;
        Node <T> newNode  = new Node(data);

        if (isEmpty()){
            head = newNode;
        }else {
            Node <T> aux = head;
            while(aux.getNext() != null){
                previousNode = aux;
                aux = aux.getNext();
            }
            aux.setNext(newNode);
            aux.setPrevious(previousNode);



        }
    }

    public Boolean exist(T data) {
        Node <T> aux = head;
        while (aux != null && !(aux.getData().equals(data))) {
            aux = aux.getNext();
        }
        return aux != null;
    }

    public void remove(T data){
        Node previous = null;
        Node aux = head;
        while(aux!= null && !(aux.getData().equals(data)) ){
            previous = aux;
            aux = aux.getNext();
        }
        if (aux== null){
            return;
        }

        if (aux == head){
            head = aux.getNext();
        } else{
            previous.setNext(aux.getNext());
        }
    }

    public boolean isEmpty(){
        return head == null;
    }

    public String show(){
        return head.toString();
    }

    public String showInverted(){
        //TO-DO
        //Recorrer la lista hasta el final, y después imprimir cada número demanera invertida para asegurarse que
        //El atributo previous está funcionando de manera correcta
        return "";
    }



}
