package co.edu.uptc.structures;

import java.util.Iterator;
import java.util.Spliterator;
import java.util.function.Consumer;

public class SimpleList <T> implements Iterable<T> {
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

    public String show() {
        if (head == null) {
            return "The list is empty";
        }

        StringBuilder sb = new StringBuilder();
        Node<T> current = head;
        while (current != null) {
            sb.append(current.toString()).append("\n");
            current = current.getNext();
        }
        return sb.toString();
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            Node<T> currentNode = head;
            @Override
            public boolean hasNext() {
                return currentNode != null;
            }

            @Override
            public T next() {
                T data = currentNode.getData();
                currentNode = currentNode.getNext();
                return data;
            }
        };
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
