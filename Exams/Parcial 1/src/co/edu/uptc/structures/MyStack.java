package co.edu.uptc.structures;

import java.util.List;

public class MyStack<T> {
    private List<T> myList;

    public MyStack() {
        myList = new MyList<T>();
    }

    public void push(T value){
        myList.add(value);
    }

    public T pop(){
        return myList.removeLast();
    }

    public T peek(){
        return myList.getLast();
    }

    public boolean isEmpty (){
        return myList.isEmpty();
    }
}