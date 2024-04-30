package co.edu.uptc.structures;

public class Node<T> {
    private T value;
    private Node<T> left;
    private Node<T> right;
    private int balanceFactor;

    public Node(T value) {
        this.value = value;
        this.left = null;
        this.right = null;
        this.balanceFactor = 0;
    }

    public T getValue() {
        return this.value;
    }

    public void setValue(T value) {
        this.value = value;
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
                "data=" + value +
                ", left=" + left +
                ", right=" + right +
                ", balanceFactor=" + balanceFactor +
                '}';
    }
}
