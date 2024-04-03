package co.edu.uptc.test;

import co.edu.uptc.structures.MyBinaryTree;

import java.util.Comparator;

public class Test {
    public static void main(String[] args) {
        // Explicitly declare the comparator to avoid any confusion
        Comparator<Integer> comparator = Comparator.naturalOrder();
        MyBinaryTree<Integer> tree = new MyBinaryTree<>(comparator);

        System.out.println("Revisnado método isEmpty(): " + tree.isEmpty());

        tree.insert(10);
        tree.insert(5);
        tree.insert(15);
        tree.insert(2);
        tree.insert(7);
        tree.insert(12);
        tree.insert(17);

        System.out.println("Revisnado método isEmpty(): " + tree.isEmpty());
    }
}
