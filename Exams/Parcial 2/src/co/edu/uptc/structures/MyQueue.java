package co.edu.uptc.structures;

import java.util.List;

public class MyQueue <T> {
    private List<T> myList;

    public MyQueue() {
        myList = new MyList<>();
    }

    public void push(T t){
        myList.addFirst(t);
    }
    public T poll(){
        T data = myList.getLast();
        myList.removeLast();
        return data;
    }

    public T peek(){
        return myList.getLast();
    }

    public boolean isEmpty(){
        return myList.isEmpty();
    }



    @Override
    public String toString() {
        return "MyQueue{" +
                "myList=" + myList +
                '}';
    }

    public int size() {
        return myList.size();
    }

}