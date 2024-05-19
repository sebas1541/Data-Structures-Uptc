package co.edu.uptc.structures.stacks;

import co.edu.uptc.structures.lists.MyList;

import java.util.Iterator;
import java.util.List;

public class MyStack<T> implements Iterable<T> {
    private MyList<T> myList;

    public MyStack() {
        myList = new MyList<>();
    }

    public void push(T value) {
        myList.add(value);
    }

    public T pop() {
        return myList.removeLast();
    }

    public T peek() {
        return myList.getLast();
    }

    public boolean isEmpty() {
        return myList.isEmpty();
    }

    @Override
    public Iterator<T> iterator() {
        return myList.iterator();
    }

    @Override
    public String toString() {
        return "MyStack{" +
                "myList=" + myList +
                '}';
    }
}
