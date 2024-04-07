package co.edu.uptc.test;

import co.edu.uptc.structures.MyBinaryTree;
import java.util.List;

public class Test {
    public static void main(String[] args) {

        MyBinaryTree<Integer> tree = new MyBinaryTree<>(Integer::compareTo);
        tree.insert(10);
        tree.insert(5);
        tree.insert(20);
        tree.insert(3);
        tree.insert(4);
        tree.insert(15);

        List<Integer> list =  tree.postOrder();
        for (int i = 0; i < list.size(); i++) {
            System.out.println(1+i + ". Nodo: " + list.get(i));
        }

    }
}
