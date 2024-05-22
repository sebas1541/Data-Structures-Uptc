package co.edu.uptc.structures.stacks;

import java.util.Iterator;
import java.util.LinkedList;

public class MyStack<T> {
    private LinkedList<T> stack;

    public MyStack() {
        stack = new LinkedList<>();
    }

    public void push(T item) {
        stack.push(item);
    }

    public T pop() {
        return stack.pop();
    }

    public boolean isEmpty() {
        return stack.isEmpty();
    }

    public Iterator<T> iterator() {
        return stack.iterator();
    }

    public boolean contains(T item) {
        return stack.contains(item);
    }

    public void remove(T item) {
        stack.remove(item);
    }

    public LinkedList<T> toList() {
        return new LinkedList<>(stack);
    }
}
