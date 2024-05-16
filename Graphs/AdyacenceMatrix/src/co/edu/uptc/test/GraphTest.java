package co.edu.uptc.test;

import co.edu.uptc.structures.nonlinear.Graph;
import co.edu.uptc.structures.nonlinear.NodeGraph;

public class GraphTest {
    public static void main(String[] args) {
        Graph<String> graph = new Graph<>();

        NodeGraph<String> nodeA = new NodeGraph<>("A");
        NodeGraph<String> nodeB = new NodeGraph<>("B");
        NodeGraph<String> nodeC = new NodeGraph<>("C");

        graph.addVertex(nodeA);
        graph.addVertex(nodeB);
        graph.addVertex(nodeC);

        graph.addEdgeUndirected(nodeA, nodeB);
        graph.addEdgeDirected(nodeA, nodeC, 2.5);

        System.out.println("Matriz de Adyacencia:");
        double[][] matrix = graph.getAdjacencyMatrix();

        for (double[] row : matrix) {
            for (double value : row) {
                System.out.print(value + " ");
            }
            System.out.println();
        }

        System.out.println("A y B adyacentes? " + graph.areAdjacent(nodeA, nodeB));
        System.out.println("A y C adyacentes? " + graph.areAdjacent(nodeA, nodeC));

        graph.removeEdgeUndirected(nodeA, nodeB);
        System.out.println("A y B adyacentes después de removerlos? " + graph.areAdjacent(nodeA, nodeB));

        System.out.println("Recorrido en anchura del grafo:");
        graph.breadth(nodeA);

    }
}
