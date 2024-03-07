package co.edu.uptc.structures;

import java.util.*;
import java.util.function.Consumer;
import java.util.function.IntFunction;
import java.util.function.Predicate;
import java.util.function.UnaryOperator;
import java.util.stream.Stream;

public class MyList<T> implements List<T> {

    private Node<T> head;

    public MyList() {
        this.head = null;
    }


    public static <T> boolean isInstanceOf(Class<T> tclass, Object o){
        return tclass.isInstance(o);
    }
    @Override
    public int size() {
        Node<T> aux = head;
        int count = 0;
        while (aux != null){
            count++;
            aux = aux.getNext();
        }return count;
    }

    @Override
    public boolean isEmpty() {
        return head == null;
    }

    @Override
    public boolean contains(Object o) throws ClassCastException, NullPointerException{

        if (o == null) throw new NullPointerException();
        if (!isInstanceOf(o.getClass(), head.getData())) throw new ClassCastException();

        Node aux = head;
        while(aux != null && aux.getNext() != null){
            if (aux.getData().equals(o)){
                return true;
            }
            aux = aux.getNext();
        }
        return false;
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            Node<T> lastReturned = null;
            Node<T> currentNode = head;

            @Override
            public boolean hasNext() {
                return currentNode != null;
            }

            @Override
            public T next() {
                lastReturned = currentNode;
                currentNode = currentNode.getNext();
                return lastReturned.getData();
            }
        };
    }

    @Override
    public Object[] toArray() {
        Object[]arr = new Object[size()];

        int index = 0;
        for (Node<T> aux = head; aux != null; head = head.getNext()) {
            arr[index++] = head.getData();
        }
        return arr;
    }



    @Override
    public <E> E[] toArray(E[] a) {
        Iterator<T> it = iterator();
        for (int i = 0; i < a.length; i++) {
            while (it.hasNext()) {
                a[i] = (E) it.next();
            }
        }
        return a;
    }

    @Override
    public boolean add(T t) throws NullPointerException{



        Node<T> newNode = new Node<T>(t);

        if (isEmpty()){
            head = newNode;

        }else{
            Node<T> aux = head;
            while (aux.getNext() != null){
                aux = aux.getNext();
            }
            aux.setNext(newNode);
            newNode.setPrevious(aux);
        }return true;
    }

    @Override
    public boolean remove(Object o) throws NullPointerException{

        if (o == null) throw new NullPointerException();


        if (head == null) return false;

        Node<T> current = head;

        if (head.getData().equals(o)) {
            if (head.getNext() != null) {
                head = head.getNext();
                head.setPrevious(null);
            } else {
                head = null;
            }
            return false;
        }

        while (current != null && !current.getData().equals(o)) {
            current = current.getNext();
        }
        if (current == null) {
            return false;
        }
        if (current.getNext() != null) {
            current.getNext().setPrevious(current.getPrevious());
        }
        if (current.getPrevious() != null) {
            current.getPrevious().setNext(current.getNext());
        }
        return true;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        for (Object elem : c) {
            if (!this.contains(elem)) {
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean addAll(Collection<? extends T> c) {
        boolean modified = false;
        for (T elem : c) {
            if (this.add(elem)) {
                modified = true;
            }
        }
        return modified;
    }

    @Override
    public boolean addAll(int index, Collection<? extends T> c) {
        boolean modified = false;
        for (T elem : c) {
            this.add(index++, elem);
            modified = true;
        }
        return modified;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        boolean modified = false;
        for (Object elem : c) {
            while (this.contains(elem)) {
                this.remove(elem);
                modified = true;
            }
        }
        return modified;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        boolean modified = false;

        MyList<T> copy = new MyList<T>();
        copy.addAll(this);
        for (T elem : copy) {
            if (!c.contains(elem)) {
                this.remove(elem);
                modified = true;
            }
        }
        return modified;
    }

    @Override
    public void clear() {
        head = null;
    }

    @Override
    public T get(int index) throws IndexOutOfBoundsException {
        Node<T> aux = head;
        if (index < 0 || index >= size() ){
            throw new IndexOutOfBoundsException();
        }
        for (int i = 0; i < index; i++){
            aux = aux.getNext();
        }
        return aux.getData();
    }

    @Override
    public T set(int index, T element) {
        Node<T> aux = head;
        if (index < 0 || index >= size() ){
            throw new IndexOutOfBoundsException();
        }

        for (int i = 0; i < index; i++){
            aux = aux.getNext();
        }


        T auxVal = aux.getData();
        aux.setData(element);
        return auxVal;
    }

    @Override
    public void add(int index, T element) throws NullPointerException, IndexOutOfBoundsException{
        if (element == null){
            throw new NullPointerException();
        }
        if (index < 0 || index >= size()){
            throw new IndexOutOfBoundsException();
        }

        Node<T> newNode = new Node<T>(element);

        if (index == 0){
            newNode.setNext(head);
            if (head != null){
                head.setPrevious(newNode);
            }
        }else{
            Node<T> aux = head;

            for (int i = 0; i < index - 1; i++) {
                aux = aux.getNext();
            }

            if (aux.getNext() == null){
                newNode.setPrevious(aux);
            }else{
                aux.getNext().setPrevious(newNode);
                newNode.setNext(aux.getNext());
                newNode.setPrevious(aux);
            }
            aux.setNext(newNode);
        }
    }

    @Override
    public T remove(int index) throws IndexOutOfBoundsException {
        if (index < 0 || index >= size()) throw new IndexOutOfBoundsException();

        if (index == 0){
            Node<T> aux = head;
            if (head != null){
                head = head.getNext();
                if (head != null){
                    head.setPrevious(null);
                }
            }return aux.getData();
        }else{
            Node<T> aux = head;
            for (int i = 0; i < index ; i++) {
                aux = aux.getNext();
            }

            if (aux.getNext() != null){
                aux.getPrevious().setNext(aux.getNext());
                aux.getNext().setPrevious(aux.getPrevious());
            }else{
                aux.getPrevious().setNext(null);
            }
            return aux.getData();
        }
    }

    @Override
    public int indexOf(Object o) throws NullPointerException {
        if (o == null) throw new NullPointerException();
        int index = -1;

        Node<T> aux = head;

        if (head != null) {

            int lastIndex = size();

            for (int i = 0; i < lastIndex; i++) {
                if (aux.getData().equals(o)) {
                    index = i;
                    break;
                }
                aux = aux.getNext();

            }
        }
        return index;
    }

    @Override
    public int lastIndexOf(Object o) {
        if (o == null) throw new NullPointerException();
        int index = -1;

        Node<T> aux = head;

        if (head != null) {

            int lastIndex = size();
            for (int i = 0; i < lastIndex; i++) {
                if (aux.getData().equals(o)) {
                    index = i;
                }
                aux = aux.getNext();

            }
        }
        return index;
    }



    @Override
    public ListIterator<T> listIterator() {
        //No implementar
        return null;
    }

    @Override
    public ListIterator<T> listIterator(int index) {
        //No implementar
        return null;
    }

    @Override
    public MyList<T> subList(int fromIndex, int toIndex) {
        MyList<T> sublist = new MyList<T>();
        for (int i = fromIndex; i < toIndex; i++) {
            sublist.add(this.get(i));
        }
        return sublist;
    }

    @Override
    public void replaceAll(UnaryOperator<T> operator) {
        List.super.replaceAll(operator);
    }

    @Override
    public void sort(Comparator<? super T> c) {
        //No implementar
        List.super.sort(c);
    }


    @Override
    public Spliterator<T> spliterator() {
        //No implementar
        return List.super.spliterator();
    }

    @Override
    public void addFirst(T t) throws NullPointerException{
        if (t == null) throw new NullPointerException();
        Node<T> newNode = new Node<>(t);
        if (head != null){
            newNode.setNext(head);
            head.setPrevious(newNode);
        }
        head = newNode;
    }

    @Override
    public void addLast(T t) throws NullPointerException {
        if (t == null) throw new NullPointerException();

        Node<T> newNode = new Node<T>(t);

        if (head != null){
            Node <T> aux = head;

            while (aux.getNext() != null){
                aux = aux.getNext();
            }
            aux.setNext(newNode);
            newNode.setPrevious(aux);
        }else{
            head = newNode;
        }
    }

    @Override
    public T getFirst() {
        return head.getData();
    }

    @Override
    public T getLast() {
        int lastIndex = size() - 1;
        Node<T> aux = head;
        for (int i = 0; i < lastIndex; i++) {
            aux = aux.getNext();
        }
        return aux.getData();
    }

    @Override
    public T removeFirst() {

        if (head == null) throw new NoSuchElementException();

        T data = head.getData();
        head = head.getNext();

        if (head != null){
            head.setPrevious(null);
        }
        return data;
    }

    @Override
    public T removeLast() {
        if (head == null) throw new NoSuchElementException();


        if (head.getNext() == null) {
            T data = head.getData();
            head = null;
            return data;
        }

        Node<T> aux = head;
        while (aux.getNext().getNext() != null) {
            aux = aux.getNext();
        }

        T data = aux.getNext().getData();
        aux.setNext(null);
        return data;
    }


    @Override
    public MyList<T> reversed() {
        MyList<T> reversedList = new MyList<T>();
        Node<T> current = head;
        while (current != null) {

            Node<T> newNode = new Node<T>(current.getData());
            if (reversedList.head != null) {
                newNode.setNext(reversedList.head);
                reversedList.head = newNode;
            } else {
                reversedList.head = newNode;
            }
            current = current.getNext();
        }
        return reversedList;
    }


    @Override
    public <T1> T1[] toArray(IntFunction<T1[]> generator) {
        //No implementar
        return List.super.toArray(generator);
    }

    @Override
    public boolean removeIf(Predicate<? super T> filter) {
        return List.super.removeIf(filter);
    }

    @Override
    public Stream<T> stream() {
        //No implementar
        return List.super.stream();
    }

    @Override
    public Stream<T> parallelStream() {
        //No implementar
        return List.super.parallelStream();
    }

    @Override
    public void forEach(Consumer<? super T> action) {
        //No implementar
        List.super.forEach(action);
    }


    @Override
    public String toString() {
        return "MyList{" +
                "head=" + head +
                '}';
    }

    public Node<T> getHead() {
        return head;
    }

    public void setHead(Node<T> head) {
        this.head = head;
    }
}