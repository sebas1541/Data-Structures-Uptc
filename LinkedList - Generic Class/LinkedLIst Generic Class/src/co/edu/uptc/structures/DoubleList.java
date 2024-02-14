package co.edu.uptc.structures;

public class DoubleList<T> {
    private Node <T> head;

    public DoubleList(){
        head = null;
    }

    public void insert(T object){
        Node <T> newNode  = new Node(object);

        if (isEmpty()){
            head = newNode;
        }else {
            Node <T> aux = head;
            while(aux.getNext() != null){
                aux = aux.getNext();
            }
            aux.setNext(newNode);
            newNode.setPrevious(aux);


        }
    }

    public Boolean exist(T object) {
        Node <T> aux = head;
        while (aux != null && !(aux.getData().equals(object))) {
            aux = aux.getNext();
        }
        return aux != null;
    }

    public void remove(T object){
        Node previous = null;
        Node aux = head;
        while(aux!= null && !(aux.getData().equals(object)) ){
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
            aux.getNext().setPrevious(previous);
        }
    }

    public boolean isEmpty(){
        return head == null;
    }

    public String show(){
        return head.toString();
    }

    public String showInverted(){
        if (isEmpty()) {
            return "La lista está vacía";
        }
        StringBuilder sb = new StringBuilder();
        Node<T> aux = head;

        while (aux.getNext() != null) {
            aux = aux.getNext();
        }
        while (aux != null) {
            sb.append(aux.getData()).append(" ");
            aux = aux.getPrevious();
        }
        return sb.toString().trim();
    }




}
