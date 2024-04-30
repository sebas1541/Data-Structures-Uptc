package co.edu.uptc.test;
import co.edu.uptc.structures.MyBinaryTree;

public class Test {
    public static void main(String[] args) {

        MyBinaryTree<Integer> arbol = new MyBinaryTree<>(Integer::compareTo);


        arbol.insert(50);
        arbol.insert(30);
        arbol.insert(70);
        arbol.insert(20);
        arbol.insert(40);
        arbol.insert(60);
        arbol.insert(80);

        System.out.println("Árbol inicial en orden: " + arbol.inOrder());
        System.out.println("Árbol inicial en preorden: " + arbol.preOrder());
        System.out.println("Árbol inicial en postorden: " + arbol.postOrder());


        System.out.println("\nBuscando el 40 en el árbol...");
        if(arbol.search(40) != null) {
            System.out.println("40 encontrado en el árbol.");
        } else {
            System.out.println("40 no está en el árbol.");
        }

        System.out.println("\nEliminando nodo hoja (20)...");
        arbol.delete(20);
        System.out.println("Árbol en orden después de eliminar 20: " + arbol.inOrder());

        System.out.println("\nEliminando nodo con un hijo (60)...");
        arbol.delete(60);
        System.out.println("Árbol en orden después de eliminar 60: " + arbol.inOrder());

        System.out.println("\nEliminando nodo con dos hijos (50)...");
        arbol.delete(50);
        System.out.println("Árbol en orden después de eliminar 50: " + arbol.inOrder());


        System.out.println("\nEstructura final del árbol en orden: " + arbol.inOrder());
        System.out.println("Estructura final del árbol en preorden: " + arbol.preOrder());
        System.out.println("Estructura final del árbol en postorden: " + arbol.postOrder());
    }

}
