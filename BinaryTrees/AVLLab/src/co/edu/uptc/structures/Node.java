package co.edu.uptc.structures;

public class Node<T> {
    private T data;
    private Node<T> left;
    private Node<T> right;
    private int balanceFactor;

    public Node(T data) {
        this.data = data;
        this.left = null;
        this.right = null;
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

    public int getBalanceFactor() {
        return balanceFactor;
    }

    public void setBalanceFactor(int balanceFactor) {
        this.balanceFactor = balanceFactor;
    }

    @Override
    public String toString() {
        return "AvlNode{" +
                "data=" + data +
                ", left=" + left +
                ", right=" + right +
                ", balanceFactor=" + balanceFactor +
                '}';
    }
}