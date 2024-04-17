package co.edu.uptc.structures;

public class AvlNode<T> {
    private T data;
    private AvlNode<T> left;
    private AvlNode<T> right;
    private int balanceFactor;

    public AvlNode(T data) {
        this.data = data;
        this.left = null;
        this.right = null;
        this.balanceFactor = 0;
    }

    public T getValue() {
        return this.data;  // Assuming the value is stored in a member named 'data'
    }

    public void setValue(T data) {
        this.data = data;
    }

    public AvlNode<T> getLeft() {
        return left;
    }

    public void setLeft(AvlNode<T> left) {
        this.left = left;
    }

    public AvlNode<T> getRight() {
        return right;
    }

    public void setRight(AvlNode<T> right) {
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
