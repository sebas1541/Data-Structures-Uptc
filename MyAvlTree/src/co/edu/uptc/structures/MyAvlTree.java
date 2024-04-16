package co.edu.uptc.structures;

import co.edu.uptc.exceptions.NodeNotFoundException;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class MyAvlTree<T> {

    private Node<T> root;
    private Comparator<T> comparator;

    public MyAvlTree(Comparator<T> comparator) {
        this.comparator = comparator;
    }

    public boolean isEmpty() {
        return root == null;
    }

    public void insert(T value) {
        root = insert(root, value);
    }

    private Node<T> insert(Node<T> n, T value) {
        if (n == null) {
            return new Node<>(value);
        }

        int compareResult = comparator.compare(value, n.getData());
        if (compareResult < 0) {
            n.setLeft(insert(n.getLeft(), value));
        } else if (compareResult > 0) {
            n.setRight(insert(n.getRight(), value));
        }
        return balance(n);
    }

    private Node<T> balance(Node<T> n) {
        if (n == null) return null;

        updateBalance(n);

        if (n.getBalanceFactor() == -2) {
            if (height(n.getLeft().getLeft()) >= height(n.getLeft().getRight())) {
                return rotateRight(n);
            } else {
                return rotateLeftRight(n);
            }
        } else if (n.getBalanceFactor() == 2) {
            if (height(n.getRight().getRight()) >= height(n.getRight().getLeft())) {
                return rotateLeft(n);
            } else {
                return rotateRightLeft(n);
            }
        }
        return n;
    }

    private void updateBalance(Node<T> n) {
        int leftHeight = height(n.getLeft());
        int rightHeight = height(n.getRight());
        n.setBalanceFactor(rightHeight - leftHeight);
    }

    private int height(Node<T> n) {
        if (n == null) return -1;
        return 1 + Math.max(height(n.getLeft()), height(n.getRight()));
    }

    private Node<T> rotateRight(Node<T> n2) {
        Node<T> n1 = n2.getLeft();
        n2.setLeft(n1.getRight());
        n1.setRight(n2);
        updateBalance(n2);
        updateBalance(n1);
        return n1;
    }

    private Node<T> rotateLeft(Node<T> n1) {
        Node<T> n2 = n1.getRight();
        n1.setRight(n2.getLeft());
        n2.setLeft(n1);
        updateBalance(n1);
        updateBalance(n2);
        return n2;
    }

    private Node<T> rotateLeftRight(Node<T> n) {
        n.setLeft(rotateLeft(n.getLeft()));
        return rotateRight(n);
    }

    private Node<T> rotateRightLeft(Node<T> n) {
        n.setRight(rotateRight(n.getRight()));
        return rotateLeft(n);
    }

    public List<T> inOrder() {
        List<T> result = new ArrayList<>();
        inOrder(root, result);
        return result;
    }

    private void inOrder(Node<T> n, List<T> result) {
        if (n != null) {
            inOrder(n.getLeft(), result);
            result.add(n.getData());
            inOrder(n.getRight(), result);
        }
    }

    public List<T> preOrder() {
        List<T> result = new ArrayList<>();
        preOrder(root, result);
        return result;
    }

    private void preOrder(Node<T> n, List<T> result) {
        if (n != null) {
            result.add(n.getData());
            preOrder(n.getLeft(), result);
            preOrder(n.getRight(), result);
        }
    }

    public List<T> postOrder() {
        List<T> result = new ArrayList<>();
        postOrder(root, result);
        return result;
    }

    private void postOrder(Node<T> n, List<T> result) {
        if (n != null) {
            postOrder(n.getLeft(), result);
            postOrder(n.getRight(), result);
            result.add(n.getData());
        }
    }

    public void delete(T data) throws NodeNotFoundException {
        root = delete(root, data);
    }

    protected Node<T> delete(Node<T> n, T data) {
        if (n == null) {
            throw new NodeNotFoundException("Sin nodos");
        } else if (comparator.compare(data, n.getData()) < 0) {
            n.setLeft(delete(n.getLeft(), data));
        } else if (comparator.compare(data, n.getData()) > 0) {
            n.setRight(delete(n.getRight(), data));
        } else {
            if (n.getLeft() == null) return n.getRight();
            else if (n.getRight() == null) return n.getLeft();
            else {
                Node<T> n1 = findReplacement(n);
                n.setData(n1.getData());
                n.setRight(delete(n.getRight(), n1.getData()));
            }
        }
        return n;
    }

    private Node<T> findReplacement(Node<T> n) {
        Node<T> replacement = n.getRight();
        while (replacement.getLeft() != null) {
            replacement = replacement.getLeft();
        }
        return replacement;
    }

    @Override
    public String toString() {
        return "MyAvlTree{" +
                "root=" + root;
    }
}
