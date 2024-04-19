package co.edu.uptc.structures;

import java.util.ArrayList;

public class MyAvlTree<T> {
    private Node<T> root;
    private IAvlComparator<T> comparator;

    public MyAvlTree(IAvlComparator<T> comparator) {
        this.root = null;
        this.comparator = comparator;
    }

    public Node<T> getRoot() {
        return root;
    }

    public void insert(T value) throws Exception {
        Logical heightIncreased = new Logical(false);
        root = insert(root, value, heightIncreased);
    }

    private Node<T> insert(Node<T> node, T value, Logical heightIncreased) throws Exception {
        if (node == null) {
            node = new Node<>(value);
            heightIncreased.setLogical(true);
            return node;
        }

        if (comparator.isLessThan(value, node.getValue())) {
            Node<T> left = insert(node.getLeft(), value, heightIncreased);
            node.setLeft(left);
            if (heightIncreased.getValue()) {
                switch (node.getBalanceFactor()) {
                    case 1:
                        node.setBalanceFactor(0);
                        heightIncreased.setLogical(false);
                        break;
                    case 0:
                        node.setBalanceFactor(-1);
                        break;
                    case -1:
                        return rotateForInsert(node, heightIncreased);
                }
            }
        } else if (comparator.isGreaterThan(value, node.getValue())) {
            Node<T> right = insert(node.getRight(), value, heightIncreased);
            node.setRight(right);
            if (heightIncreased.getValue()) {
                switch (node.getBalanceFactor()) {
                    case -1:
                        node.setBalanceFactor(0);
                        heightIncreased.setLogical(false);
                        break;
                    case 0:
                        node.setBalanceFactor(1);
                        break;
                    case 1:
                        return rotateForInsert(node, heightIncreased);
                }
            }
        } else {
            throw new Exception("Llaves duplicdas no permitidas");
        }
        return node;
    }

    private Node<T> rotateForInsert(Node<T> node, Logical heightIncreased) {
        heightIncreased.setLogical(false);
        if (node.getBalanceFactor() == -1) {
            return node.getLeft().getBalanceFactor() == -1 ? rotateRight(node) : doubleRotateLeftRight(node);
        } else {
            return node.getRight().getBalanceFactor() == 1 ? rotateLeft(node) : doubleRotateRightLeft(node);
        }
    }

    private Node<T> rotateRight(Node<T> node) {
        Node<T> left = node.getLeft();
        node.setLeft(left.getRight());
        left.setRight(node);
        node.setBalanceFactor(0);
        left.setBalanceFactor(0);
        return left;
    }

    private Node<T> rotateLeft(Node<T> node) {
        Node<T> right = node.getRight();
        node.setRight(right.getLeft());
        right.setLeft(node);
        node.setBalanceFactor(0);
        right.setBalanceFactor(0);
        return right;
    }

    private Node<T> doubleRotateLeftRight(Node<T> node) {
        node.setLeft(rotateLeft(node.getLeft()));
        return rotateRight(node);
    }

    private Node<T> doubleRotateRightLeft(Node<T> node) {
        node.setRight(rotateRight(node.getRight()));
        return rotateLeft(node);
    }

    public void delete(T value) throws Exception {
        Logical heightDecreased = new Logical(false);
        root = delete(root, value, heightDecreased);
    }

    private Node<T> delete(Node<T> node, T value, Logical heightDecreased) throws Exception {
        if (node == null) {
            throw new Exception("Valor no encontrado");
        }

        if (comparator.isLessThan(value, node.getValue())) {
            Node<T> left = delete(node.getLeft(), value, heightDecreased);
            node.setLeft(left);
            if (heightDecreased.getValue()) {
                return balanceAfterDeletion(node, heightDecreased);
            }
        } else if (comparator.isGreaterThan(value, node.getValue())) {
            Node<T> right = delete(node.getRight(), value, heightDecreased);
            node.setRight(right);
            if (heightDecreased.getValue()) {
                return balanceAfterDeletion(node, heightDecreased);
            }
        } else {
            if (node.getLeft() == null || node.getRight() == null) {
                Node<T> temp = node.getLeft() != null ? node.getLeft() : node.getRight();
                node = temp;
                heightDecreased.setLogical(true);
            } else {
                Node<T> temp = getMinimumNode(node.getRight());
                node.setValue(temp.getValue());
                node.setRight(delete(node.getRight(), temp.getValue(), heightDecreased));
            }
        }
        return node;
    }

    private Node<T> balanceAfterDeletion(Node<T> node, Logical heightDecreased) {
        int balance = node.getBalanceFactor();
        if (balance == 0) {
            node.setBalanceFactor(balance < 0 ? 1 : -1);
            heightDecreased.setLogical(false);
        } else {
            node.setBalanceFactor(0);
            if (balance == -1) {
                return node.getLeft().getBalanceFactor() <= 0 ? rotateRight(node) : doubleRotateLeftRight(node);
            } else {
                return node.getRight().getBalanceFactor() >= 0 ? rotateLeft(node) : doubleRotateRightLeft(node);
            }
        }
        return node;
    }

    private Node<T> getMinimumNode(Node<T> node) {
        Node<T> current = node;
        while (current.getLeft() != null) {
            current = current.getLeft();
        }
        return current;
    }

    public ArrayList<T> inOrder() {
        ArrayList<T> result = new ArrayList<>();
        inOrder(root, result);
        return result;
    }

    private void inOrder(Node<T> node, ArrayList<T> list) {
        if (node != null) {
            inOrder(node.getLeft(), list);
            list.add(node.getValue());
            inOrder(node.getRight(), list);
        }
    }

    public ArrayList<T> preOrder() {
        ArrayList<T> result = new ArrayList<>();
        preOrder(root, result);
        return result;
    }

    private void preOrder(Node<T> node, ArrayList<T> list) {
        if (node != null) {
            list.add(node.getValue());
            preOrder(node.getLeft(), list);
            preOrder(node.getRight(), list);
        }
    }
}
