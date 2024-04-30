package co.edu.uptc.structures;

import co.edu.uptc.exceptions.NodeNotFoundException;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

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
        root = insert(root, data);
    }

    private Node<T> insert(Node<T> current, T data) {

        if (current == null) {
            return new Node<>(data);
        }

        if (comparator.compare(data, current.getData()) < 0) {
            current.setLeft(insert(current.getLeft(), data));
        } else if (comparator.compare(data, current.getData()) > 0) {
            current.setRight(insert(current.getRight(), data));
        } else {

            return current;
        }

        return current;
    }
    public T search(T data){
        Node<T> aux = root;
        while (aux != null){
            if (comparator.compare(data, aux.getData()) == 0){
                return aux.getData();
            } else if (comparator.compare(data, aux.getData()) < 0){
                aux = aux.getLeft();
            } else {
                aux = aux.getRight();
            }
        }
        return null;
    }

    public void inOrder(Node<T> node, List<T> result) {

        if (node != null) {
            inOrder(node.getLeft(), result);
            result.add(node.getData());
            inOrder(node.getRight(), result);
        }

    }

    public List<T> inOrder() {
        List<T> result = new ArrayList<>();
        inOrder(root, result);
        return result;
    }

    public void preOrder(Node<T> node, List<T> result){
        if (node != null) {
            result.add(node.getData());
            preOrder(node.getLeft(), result);
            preOrder(node.getRight(), result);
        }
    }

    public List<T> preOrder(){
        List<T> result = new ArrayList<>();
        preOrder(root, result);
        return result;
    }

    public List<T> postOrder(Node<T> node, List<T> result){
        if (node != null){
            postOrder(node.getLeft(), result);
            postOrder(node.getRight(), result);
            result.add(node.getData());
        }
        return result;
    }

    public List<T> postOrder(){
        List<T> result = new ArrayList<>();
        postOrder(root, result);
        return result;
    }


    public void delete(T data) throws NodeNotFoundException {
        root = delete(root, data);
    }

    protected Node<T> delete(Node<T> node, T data) {
        if (node == null) {
            throw new NodeNotFoundException("Sin nodos");

        } else if (comparator.compare(data, node.getData()) < 0) {
            Node<T> leftChild = delete(node.getLeft(), data);
            node.setLeft(leftChild);

        } else if (comparator.compare(data, node.getData()) > 0) {
            Node<T> rightChild = delete(node.getRight(), data);
            node.setRight(rightChild);

        } else {

            if (node.getLeft() == null && node.getRight() == null) {
                return null;
            }

            else if (node.getLeft() == null) {
                return node.getRight();

            } else if (node.getRight() == null) {
                return node.getLeft();
            }

            else {
                Node<T> replacement = findReplacement(node);
                node.setData(replacement.getData());
                node.setRight(delete(node.getRight(), replacement.getData()));
            }
        }
        return node;
    }

    private Node<T> findReplacement(Node<T> node) {

        Node<T> replacement = node.getRight();

        while (replacement.getLeft() != null) {
            replacement = replacement.getLeft();
        }

        return replacement;
    }











    private Node<T> replace(Node<T> node) {
        Node<T> parent = node;
        Node<T> replacement = node.getLeft();

        while (replacement.getRight() != null) {
            parent = replacement;
            replacement = replacement.getRight();
        }

        if (parent != node) {
            parent.setRight(replacement.getLeft());
        } else {
            node.setLeft(replacement.getLeft());
        }

        return replacement;
    }
}