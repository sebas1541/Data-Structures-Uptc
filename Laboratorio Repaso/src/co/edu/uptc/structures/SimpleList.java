package co.edu.uptc.structures;

public class SimpleList <T> {
    private Node<T> head;

    public SimpleList(Node<T> head) {
        this.head = null;
    }
    public SimpleList() {
        this.head = null;
    }

    public void insert(T data){
        Node<T> newNode = new Node<T>(data);
        if (isEmpty()){
            head = newNode;
        }else{
            Node<T> aux = head;
            while (aux.getNext() != null){
                aux = aux.getNext();
            }
            aux.setNext(newNode);
        }
    }

    public Boolean exist(){
        return null;
    }
    public void remove(T data){
        Node <T> previous = null;
        Node <T> aux = head;
        while (aux.getNext() != null && !(aux.getData().equals(data))){
            previous = aux;
            aux = aux.getNext();
        }
        if (aux == null){
            return;
        }if (aux == head){
            head = aux.getNext();
        }else {
            previous.setNext(aux.getNext());
            aux.getNext().setPrevious(previous);
        }
    }

    public Boolean isEmpty(){
        return head == null;
    }

    public String show(){
        return null;
    }
}
