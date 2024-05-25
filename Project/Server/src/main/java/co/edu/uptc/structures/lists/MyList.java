package co.edu.uptc.structures.lists;

import java.io.Serializable;
import java.util.*;
import java.util.function.UnaryOperator;

public class MyList<T> implements List<T>, Iterable<T>, Serializable{
    private Node<T> head;

    public MyList() {
        this.head = null;
    }

    @Override
    public int size() {
        Node<T> aux = head;
        int count = 0;
        while (aux != null) {
            count++;
            aux = aux.getNext();
        }
        return count;
    }

    @Override
    public boolean isEmpty() {
        return head == null;
    }

    @Override
    public boolean contains(Object o) {
        if (o == null) throw new NullPointerException();
        Node<T> aux = head;
        while (aux != null) {
            if (aux.getData().equals(o)) {
                return true;
            }
            aux = aux.getNext();
        }
        return false;
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            Node<T> current = head;

            @Override
            public boolean hasNext() {
                return current != null;
            }

            @Override
            public T next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                T data = current.getData();
                current = current.getNext();
                return data;
            }
        };
    }

    @Override
    public Object[] toArray() {
        Object[] arr = new Object[size()];
        int index = 0;
        for (Node<T> aux = head; aux != null; aux = aux.getNext()) {
            arr[index++] = aux.getData();
        }
        return arr;
    }

    @Override
    public <T1> T1[] toArray(T1[] a) {
        if (a.length < size()) {
            return (T1[]) Arrays.copyOf(toArray(), size(), a.getClass());
        }
        System.arraycopy(toArray(), 0, a, 0, size());
        if (a.length > size()) {
            a[size()] = null;
        }
        return a;
    }

    @Override
    public boolean add(T t) {
        Node<T> newNode = new Node<>(t);
        if (isEmpty()) {
            head = newNode;
        } else {
            Node<T> aux = head;
            while (aux.getNext() != null) {
                aux = aux.getNext();
            }
            aux.setNext(newNode);
            newNode.setPrevious(aux);
        }
        return true;
    }

    public void addFirst(T t) {
        Node<T> newNode = new Node<>(t);
        if (head != null) {
            newNode.setNext(head);
            head.setPrevious(newNode);
        }
        head = newNode;
    }

    public T removeLast() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        Node<T> aux = head;
        while (aux.getNext() != null) {
            aux = aux.getNext();
        }
        if (aux.getPrevious() != null) {
            aux.getPrevious().setNext(null);
        } else {
            head = null;
        }
        return aux.getData();
    }

    public T getLast() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        Node<T> aux = head;
        while (aux.getNext() != null) {
            aux = aux.getNext();
        }
        return aux.getData();
    }

    @Override
    public boolean remove(Object o) {
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
            return true;
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
        throw new UnsupportedOperationException();
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
        Iterator<T> it = iterator();
        while (it.hasNext()) {
            if (!c.contains(it.next())) {
                it.remove();
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
    public T get(int index) {
        if (index < 0 || index >= size()) {
            throw new IndexOutOfBoundsException();
        }
        Node<T> aux = head;
        for (int i = 0; i < index; i++) {
            aux = aux.getNext();
        }
        return aux.getData();
    }

    @Override
    public T set(int index, T element) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void add(int index, T element) {
        throw new UnsupportedOperationException();
    }

    @Override
    public T remove(int index) {
        throw new UnsupportedOperationException();
    }

    @Override
    public int indexOf(Object o) {
        if (o == null) throw new NullPointerException();
        int index = 0;
        for (Node<T> aux = head; aux != null; aux = aux.getNext(), index++) {
            if (aux.getData().equals(o)) {
                return index;
            }
        }
        return -1;
    }

    @Override
    public int lastIndexOf(Object o) {
        if (o == null) throw new NullPointerException();
        int index = -1;
        int currentIndex = 0;
        for (Node<T> aux = head; aux != null; aux = aux.getNext(), currentIndex++) {
            if (aux.getData().equals(o)) {
                index = currentIndex;
            }
        }
        return index;
    }

    @Override
    public ListIterator<T> listIterator() {
        throw new UnsupportedOperationException();
    }

    @Override
    public ListIterator<T> listIterator(int index) {
        throw new UnsupportedOperationException();
    }

    @Override
    public List<T> subList(int fromIndex, int toIndex) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void replaceAll(UnaryOperator<T> operator) {
        Objects.requireNonNull(operator);
        final ListIterator<T> li = listIterator();
        while (li.hasNext()) {
            li.set(operator.apply(li.next()));
        }
    }

    @Override
    public void sort(Comparator<? super T> c) {
        Object[] a = this.toArray();
        Arrays.sort(a, (Comparator) c);
        ListIterator<T> i = this.listIterator();
        for (Object e : a) {
            i.next();
            i.set((T) e);
        }
    }

    @Override
    public Spliterator<T> spliterator() {
        return Spliterators.spliterator(this, Spliterator.ORDERED);
    }
}
