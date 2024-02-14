package co.edu.uptc.structures;

public class Node <T> {
    private T data;
    private Node next;
    private Node previous;


    public Node(T data) {
        this.data = data;
    }

    public T getData() {
        return data;
    }

    public Node getNext() {
        return next;
    }


    public void setNext(Node next) {
        this.next = next;
    }

    public Node getPrevious() {
        return previous;
    }

    public void setPrevious(Node previous) {
        this.previous = previous;
    }

    @Override
    public String toString() {
        return "Node{" +
                "data=" + data +
                 "next" + next + previous +
                '}';
    }

}
