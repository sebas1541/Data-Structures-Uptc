package co.edu.uptc.structures.binarytrees;

import co.edu.uptc.structures.lists.Node;

import java.util.ArrayList;

public class MyAvlTree<T> {
    private Node<T> root;
    private IAvlComparator<T> comparator;

    public MyAvlTree(IAvlComparator<T> comparator) {
        this.comparator = comparator;
    }

    public boolean isEmpty() {
        return root == null;
    }

    public void insert(T value) throws Exception {
        root = insert(root, value, new Logical(false));
    }

    private Node<T> insert(Node<T> node, T value, Logical heightIncreased) throws Exception {
        if (node == null) {
            heightIncreased.setValue(true);
            return new Node<>(value);
        }

        int compareResult = comparator.isLessThan(value, node.getData()) ? -1 :
                comparator.isGreaterThan(value, node.getData()) ? 1 : 0;

        if (compareResult < 0) {
            Node<T> left = insert(node.getLeft(), value, heightIncreased);
            node.setLeft(left);

            if (heightIncreased.getValue()) {
                switch (node.getBalanceFactor()) {
                    case 1:
                        node.setBalanceFactor(0);
                        heightIncreased.setValue(false);
                        break;
                    case 0:
                        node.setBalanceFactor(-1);
                        break;
                    case -1:
                        node = balanceLeft(node);
                        heightIncreased.setValue(false);
                        break;
                }
            }
        } else if (compareResult > 0) {
            Node<T> right = insert(node.getRight(), value, heightIncreased);
            node.setRight(right);

            if (heightIncreased.getValue()) {
                switch (node.getBalanceFactor()) {
                    case -1:
                        node.setBalanceFactor(0);
                        heightIncreased.setValue(false);
                        break;
                    case 0:
                        node.setBalanceFactor(1);
                        break;
                    case 1:
                        node = balanceRight(node);
                        heightIncreased.setValue(false);
                        break;
                }
            }
        } else {
            throw new Exception("No puede haber claves repetidas");
        }
        return node;
    }

    public void delete(T value) throws Exception {
        root = delete(root, value, new Logical(false));
    }

    private Node<T> delete(Node<T> node, T value, Logical heightDecreased) throws Exception {
        if (node == null) {
            throw new Exception("Value not found in the tree");
        }

        int compareResult = comparator.isLessThan(value, node.getData()) ? -1 :
                comparator.isGreaterThan(value, node.getData()) ? 1 : 0;

        if (compareResult < 0) {
            Node<T> left = delete(node.getLeft(), value, heightDecreased);
            node.setLeft(left);

            if (heightDecreased.getValue()) {
                switch (node.getBalanceFactor()) {
                    case -1:
                        node.setBalanceFactor(0);
                        heightDecreased.setValue(true);
                        break;
                    case 0:
                        node.setBalanceFactor(1);
                        heightDecreased.setValue(false);
                        break;
                    case 1:
                        node = balanceRight(node);
                        heightDecreased.setValue(false);
                        break;
                }
            }
        } else if (compareResult > 0) {
            Node<T> right = delete(node.getRight(), value, heightDecreased);
            node.setRight(right);

            if (heightDecreased.getValue()) {
                switch (node.getBalanceFactor()) {
                    case 1:
                        node.setBalanceFactor(0);
                        heightDecreased.setValue(true);
                        break;
                    case 0:
                        node.setBalanceFactor(-1);
                        heightDecreased.setValue(false);
                        break;
                    case -1:
                        node = balanceLeft(node);
                        heightDecreased.setValue(false);
                        break;
                }
            }
        } else {
            if (node.getLeft() == null || node.getRight() == null) {
                node = (node.getLeft() != null) ? node.getLeft() : node.getRight();
                heightDecreased.setValue(true);
            } else {
                Node<T> min = findMin(node.getRight());
                node.setData(min.getData());
                Node<T> right = delete(node.getRight(), min.getData(), heightDecreased);
                node.setRight(right);

                if (heightDecreased.getValue()) {
                    switch (node.getBalanceFactor()) {
                        case 1:
                            node.setBalanceFactor(0);
                            heightDecreased.setValue(true);
                            break;
                        case 0:
                            node.setBalanceFactor(-1);
                            heightDecreased.setValue(false);
                            break;
                        case -1:
                            node = balanceLeft(node);
                            heightDecreased.setValue(false);
                            break;
                    }
                }
            }
        }
        return node;
    }

    private Node<T> findMin(Node<T> node) {
        while (node.getLeft() != null) {
            node = node.getLeft();
        }
        return node;
    }

    public T search(T value) {
        Node<T> node = root;
        while (node != null) {
            int compareResult = comparator.isLessThan(value, node.getData()) ? -1 :
                    comparator.isGreaterThan(value, node.getData()) ? 1 : 0;

            if (compareResult < 0) {
                node = node.getLeft();
            } else if (compareResult > 0) {
                node = node.getRight();
            } else {
                return node.getData();
            }
        }
        return null;
    }

    private Node<T> balanceLeft(Node<T> node) {
        Node<T> left = node.getLeft();
        if (left.getBalanceFactor() == -1) {
            node.setBalanceFactor(0);
            left.setBalanceFactor(0);
            return rotateRight(node);
        } else {
            Node<T> leftRight = left.getRight();
            switch (leftRight.getBalanceFactor()) {
                case -1:
                    node.setBalanceFactor(1);
                    left.setBalanceFactor(0);
                    break;
                case 1:
                    node.setBalanceFactor(0);
                    left.setBalanceFactor(-1);
                    break;
                case 0:
                    node.setBalanceFactor(0);
                    left.setBalanceFactor(0);
                    break;
            }
            leftRight.setBalanceFactor(0);
            node.setLeft(rotateLeft(left));
            return rotateRight(node);
        }
    }

    private Node<T> balanceRight(Node<T> node) {
        Node<T> right = node.getRight();
        if (right.getBalanceFactor() == 1) {
            node.setBalanceFactor(0);
            right.setBalanceFactor(0);
            return rotateLeft(node);
        } else {
            Node<T> rightLeft = right.getLeft();
            switch (rightLeft.getBalanceFactor()) {
                case 1:
                    node.setBalanceFactor(-1);
                    right.setBalanceFactor(0);
                    break;
                case -1:
                    node.setBalanceFactor(0);
                    right.setBalanceFactor(1);
                    break;
                case 0:
                    node.setBalanceFactor(0);
                    right.setBalanceFactor(0);
                    break;
            }
            rightLeft.setBalanceFactor(0);
            node.setRight(rotateRight(right));
            return rotateLeft(node);
        }
    }

    private Node<T> rotateRight(Node<T> node) {
        Node<T> left = node.getLeft();
        node.setLeft(left.getRight());
        left.setRight(node);
        return left;
    }

    private Node<T> rotateLeft(Node<T> node) {
        Node<T> right = node.getRight();
        node.setRight(right.getLeft());
        right.setLeft(node);
        return right;
    }

    public ArrayList<T> inOrder() {
        ArrayList<T> result = new ArrayList<>();
        inOrder(root, result);
        return result;
    }

    private void inOrder(Node<T> node, ArrayList<T> list) {
        if (node != null) {
            inOrder(node.getLeft(), list);
            list.add(node.getData());
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
            list.add(node.getData());
            preOrder(node.getLeft(), list);
            preOrder(node.getRight(), list);
        }
    }

    @Override
    public String toString() {
        return "MyAvlTree{" +
                "root=" + root +
                '}';
    }
}
