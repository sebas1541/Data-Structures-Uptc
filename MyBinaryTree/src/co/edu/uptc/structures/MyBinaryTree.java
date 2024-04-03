package co.edu.uptc.structures;

import java.util.Comparator;

public class MyBinaryTree<T> {
    private Node<T> root;
    private Comparator<T> comparator;

    public MyBinaryTree(Comparator<T> comparator) {
        this.comparator = comparator;
    }

    public boolean isEmpty(){
        return root == null;
    }

    public void insert(T data) {
        root = insertRec(root, data);
    }

    private Node<T> insertRec(Node<T> current, T data) {

        if (current == null) {
            return new Node<>(data);
        }

        if (comparator.compare(data, current.getData()) < 0) {
            current.setLeft(insertRec(current.getLeft(), data));
        } else if (comparator.compare(data, current.getData()) > 0) {
            current.setRight(insertRec(current.getRight(), data));
        } else {

            return current;
        }

        return current;
    }

}
