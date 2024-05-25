package co.edu.uptc.structures.queues;

import co.edu.uptc.structures.lists.MyList;

import java.io.Serializable;
import java.util.NoSuchElementException;

public class MyQueue<T> implements Serializable {
    private MyList<T> myList;

    public MyQueue() {
        myList = new MyList<>();
    }

    public void push(T value) {
        myList.add(value);
    }

    public T poll() {
        if (myList.isEmpty()) {
            throw new NoSuchElementException();
        }
        T firstElement = myList.get(0);
        myList.remove(0);
        return firstElement;
    }

    public boolean isEmpty() {
        return myList.isEmpty();
    }

    public T peek() {
        if (myList.isEmpty()) {
            throw new NoSuchElementException();
        }
        return myList.get(0);
    }

    @Override
    public String toString() {
        return "MyQueue{" +
                "myList=" + myList +
                '}';
    }
}
