package co.edu.uptc.test;

import co.edu.uptc.structures.MyBinaryTree;

public class Test {
    public static void main(String[] args) {

        MyBinaryTree<String> tree = new MyBinaryTree<>(String::compareTo);
        tree.insert("Hola");
        tree.insert("Buenos dias");
        tree.insert("Hasta luego");


        System.out.println(tree.toString());
        System.out.println(tree.search("Hola"));

    }
}
