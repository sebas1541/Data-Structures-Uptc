package co.edu.uptc.structures;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Spliterator;
import java.util.function.Consumer;

public class DoubleList<T> implements Iterable<T> {
    private Node <T> head;

    public DoubleList(){
        head = null;
    }

    public void insert(T data){
        Node <T> newNode  = new Node(data);

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

    /**

    public Boolean exist(T data) {
        Node <T> aux = head;
        while (aux != null && !(aux.getData().equals(data))) {
            aux = aux.getNext();
        }
        return aux != null;
    }

     */

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

    @Override
    public Iterator<T> iterator() {
        Iterator<T> iterator;
        iterator = new Iterator<T>() {

            Node<T> current = head;
            @Override
            public boolean hasNext() {
                return current != null;
            }

            @Override
            public T next() {
                T data = current.getData();
                current = current.getNext();
                return data;
            }
        };




        return iterator;
    }

    @Override
    public void forEach(Consumer<? super T> action) {
        Iterable.super.forEach(action);
    }

    @Override
    public Spliterator<T> spliterator() {
        return Iterable.super.spliterator();
    }
}
