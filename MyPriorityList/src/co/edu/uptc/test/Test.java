package co.edu.uptc.test;

import co.edu.uptc.structures.MyPriorityQueue;

public class Test {
    public static void main(String[] args) {
        MyPriorityQueue<String> list = new MyPriorityQueue<>(3);

        list.push("Hola", 0);
        list.push("Yorley", 0);
        list.push("Pejelagarto", 1);
        System.out.println(list.toString());

        System.out.println(list.poll());
        System.out.println(list.peek());
        System.out.println(list.poll());
        System.out.println(list.peek());
        System.out.println(list.isEmpty());

    }
}
