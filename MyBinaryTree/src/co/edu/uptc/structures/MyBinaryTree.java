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
        Node<T> newNode = new Node<>(data);
        if (root == null) {
            root = newNode;
        } else {
            Node<T> aux = root;
            Node<T> parent;
            while (true) {
                parent = aux;
                if (comparator.compare(data, aux.getData()) < 0) {
                    aux = aux.getLeft();
                    if (aux == null) {
                        parent.setLeft(newNode);
                        return;
                    }
                } else if (comparator.compare(data, aux.getData()) > 0) {
                    aux = aux.getRight();
                    if (aux == null) {
                        parent.setRight(newNode);
                        return;
                    }
                } else {
                    return;
                }
            }
        }
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




    @Override
    public String toString() {
        return "MyBinaryTree{" +
                "root=" + root +
                ", comparator=" + comparator +
                '}';
    }
}
