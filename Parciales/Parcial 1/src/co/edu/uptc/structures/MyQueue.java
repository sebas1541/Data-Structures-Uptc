package co.edu.uptc.structures;
import java.util.List;

public class MyQueue <T> {
    private List<T> myList;

    public MyQueue() {
        this.myList = new MyList<T>();
    }

    public boolean isEmpty(){
        return myList.isEmpty();
    }

    public T poll(){
        return myList.removeLast();
    }

    public T peek(){
        return myList.getLast();
    }

    public void push(T t){
        myList.addFirst(t);
    }

    @Override
    public String toString() {
        return "MyQueue{" +
                "myList=" + myList +
                '}';
    }
}