package co.edu.uptc.structures;

import java.util.Arrays;

public class MyPriorityQueue <T> {
    private MyQueue<T>[] queues;

    public MyPriorityQueue(int n) {
        this.queues = new MyQueue[n];
        for (int i = 0; i < queues.length; i++) {
            queues[i] = new MyQueue<T>();
        }
    }

    public void push(T t, int priority){
        if (priority < 0 || priority > queues.length) throw new IndexOutOfBoundsException();
        queues[priority].push(t);
    }
    public T poll(){
        T data = null;
        for (MyQueue<T> queue: queues){
            if (!queue.isEmpty()){
                data = queue.poll();
                break;
            }
        }
        return data;
    }

    public T peek(){
        T data = null;
        for (MyQueue<T> queue: queues){
            if (!queue.isEmpty()){
                data = queue.peek();
                break;
            }
        }
        return data;
    }

    public Boolean isEmpty(){
        Boolean isEmpty = false;
        for (MyQueue<T> queue: queues){
            if (queue.isEmpty()){
                isEmpty = true;
            }else {
                isEmpty = false;
                break;
            }
        }
        return isEmpty;
    }

    @Override
    public String toString() {
        return "MyPriorityQueue{" +
                "queues=" + Arrays.toString(queues) +
                '}';
    }
}
