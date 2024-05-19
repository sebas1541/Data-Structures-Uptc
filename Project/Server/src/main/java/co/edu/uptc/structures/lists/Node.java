package co.edu.uptc.structures.lists;

public class Node<T> {
    private T data;
    private Node<T> left;
    private Node<T> right;
    private Node<T> next;
    private Node<T> previous;
    private int balanceFactor;

    public Node(T data) {
        this.data = data;
        this.left = null;
        this.right = null;
        this.next = null;
        this.previous = null;
        this.balanceFactor = 0;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public Node<T> getLeft() {
        return left;
    }

    public void setLeft(Node<T> left) {
        this.left = left;
    }

    public Node<T> getRight() {
        return right;
    }

    public void setRight(Node<T> right) {
        this.right = right;
    }

    public Node<T> getNext() {
        return next;
    }

    public void setNext(Node<T> next) {
        this.next = next;
    }

    public Node<T> getPrevious() {
        return previous;
    }

    public void setPrevious(Node<T> previous) {
        this.previous = previous;
    }

    public int getBalanceFactor() {
        return balanceFactor;
    }

    public void setBalanceFactor(int balanceFactor) {
        this.balanceFactor = balanceFactor;
    }

    @Override
    public String toString() {
        return "Node{" +
                "data=" + data +
                ", left=" + left +
                ", right=" + right +
                ", next=" + next +
                ", previous=" + previous +
                ", balanceFactor=" + balanceFactor +
                '}';
    }
}
