package models;

public class NumberSimpleList {
    private Node head;

    public NumberSimpleList(){
        head = null;
    }

    public void insert(int data){
        Node newNode  = new Node(data);
        if (isEmpty()){
            head = newNode;
        }else {
            Node aux = head;
            while(aux.getNext() != null){
                aux = aux.getNext();
            }
            aux.setNext(newNode);
        }
    }

    public Boolean exist(int data) {
            Node aux = head;
            while (aux != null && aux.getData() != data) {
                aux = aux.getNext();
            }
            return aux != null;
    }

    public void remove(int data){
            Node previous = null;
            Node aux = head;
            while(aux!= null && aux.getData() != data){
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
}
