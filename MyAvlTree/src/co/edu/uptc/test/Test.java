package co.edu.uptc.test;

import co.edu.uptc.structures.MyAvlTree;

import java.util.Comparator;
import java.util.List;

public class Test {

    public static void main(String[] args) {

        Comparator<Integer> comparator = Integer::compare;
        MyAvlTree<Integer> avlTree = new MyAvlTree<>(comparator);

        avlTree.insert(40);
        avlTree.insert(80);
        avlTree.insert(50);
        avlTree.insert(40);
        avlTree.insert(60);
        avlTree.insert(10);

        List<Integer> inOrderList = avlTree.inOrder();
        System.out.println("In-order: " + inOrderList);
    }
}
