package co.edu.uptc.test;

import co.edu.uptc.structures.MyAvlTree;
import co.edu.uptc.structures.IAvlComparator;
import java.util.ArrayList;

public class Test {

    public static void main(String[] args)
    {
        IAvlComparator<Integer> integerComparator = new IAvlComparator<Integer>() {
            @Override
            public boolean isEqualTo(Integer a, Integer b) {
                return a.equals(b);
            }

            @Override
            public boolean isLessThan(Integer a, Integer b) {
                return a < b;
            }

            @Override
            public boolean isLessThanOrEqualTo(Integer a, Integer b) {
                return a <= b;
            }

            @Override
            public boolean isGreaterThan(Integer a, Integer b) {
                return a > b;
            }

            @Override
            public boolean isGreaterThanOrEqualTo(Integer a, Integer b) {
                return a >= b;
            }
        };

        MyAvlTree<Integer> avlTree = new MyAvlTree<>(integerComparator);

        try {
            avlTree.insert(20);
            avlTree.insert(25);
            avlTree.insert(15);
            avlTree.insert(10);
            avlTree.insert(18);
            avlTree.insert(30);
            avlTree.insert(5);
            avlTree.insert(35);


            ArrayList<Integer> inOrderList = avlTree.inOrder();
            System.out.println("In order: " + inOrderList);

            ArrayList<Integer> preOrderList = avlTree.preOrder();
            System.out.println("Pre order: " + preOrderList);
        } catch (Exception e) {
            System.err.println("Excepción: " + e.getMessage());
        }
    }


}
