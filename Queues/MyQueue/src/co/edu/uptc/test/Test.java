package co.edu.uptc.test;

import co.edu.uptc.structures.MyList;
import co.edu.uptc.structures.MyQueue;

import java.util.List;

public class Test {
    public static void main(String[] args) {
        MyQueue<String> myQueue = new MyQueue<String>();

        myQueue.push("Hola");
        myQueue.push("Hello");
        myQueue.push("Bonjour");
        myQueue.push("Ni Hao");

        System.out.println(myQueue.toString());
        System.out.println(myQueue.peek());
        System.out.println(myQueue.poll());
    }
}
