package co.edu.uptc.structures;

import java.util.Iterator;
import java.util.Spliterator;
import java.util.function.Consumer;

public class DoubleList <T> implements Iterable<T>{
    private Node<T> head;

    public DoubleList(){
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
            newNode.setPrevious(aux);
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

    public String showInverted(){
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

    public int size(){
        Node<T> aux = head;
        int count = 0;
        while (aux != null){
            count++;
            aux = aux.getNext();
        }
        return count;
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
                currentNode.getNext();
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