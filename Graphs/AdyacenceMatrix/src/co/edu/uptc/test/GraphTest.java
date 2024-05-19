package co.edu.uptc.test;

import co.edu.uptc.structures.nonlinear.Graph;
import co.edu.uptc.structures.nonlinear.NodeGraph;

public class GraphTest {
    public static void main(String[] args) {
        Graph<String> undirectedGraph = new Graph<>();
        Graph<String> directedGraph = new Graph<>();

        NodeGraph<String> nodeA = new NodeGraph<>("A");
        NodeGraph<String> nodeB = new NodeGraph<>("B");
        NodeGraph<String> nodeC = new NodeGraph<>("C");
        NodeGraph<String> nodeD = new NodeGraph<>("D");


        undirectedGraph.addVertex(nodeA);
        undirectedGraph.addVertex(nodeB);
        undirectedGraph.addVertex(nodeC);
        undirectedGraph.addVertex(nodeD);

        directedGraph.addVertex(nodeA);
        directedGraph.addVertex(nodeB);
        directedGraph.addVertex(nodeC);
        directedGraph.addVertex(nodeD);

        System.out.println("Agregando arista entre A y B en el grafo no dirigido");
        undirectedGraph.addEdgeUndirected(nodeA, nodeB);
        printAdjacencyMatrix(undirectedGraph);

        System.out.println("Agregando arista entre B y C en el grafo no dirigido");
        undirectedGraph.addEdgeUndirected(nodeB, nodeC);
        printAdjacencyMatrix(undirectedGraph);

        System.out.println("Agregando arista entre C y D en el grafo no dirigido");
        undirectedGraph.addEdgeUndirected(nodeC, nodeD);
        printAdjacencyMatrix(undirectedGraph);

        System.out.println("Recorrido en anchura desde el nodo A en el grafo no dirigido");
        undirectedGraph.breadth(nodeA);

        System.out.println("\nRecorrido en anchura desde el nodo C en el grafo no dirigido");
        undirectedGraph.breadth(nodeC);

        System.out.println("\nAgregando arista dirigida de A a B en el grafo dirigido");
        directedGraph.addEdgeDirected(nodeA, nodeB);
        printAdjacencyMatrix(directedGraph);

        System.out.println("Agregando arista dirigida de B a C en el grafo dirigido");
        directedGraph.addEdgeDirected(nodeB, nodeC);
        printAdjacencyMatrix(directedGraph);

        System.out.println("Agregando arista dirigida de C a D en el grafo dirigido");
        directedGraph.addEdgeDirected(nodeC, nodeD);
        printAdjacencyMatrix(directedGraph);

        System.out.println("Recorrido en anchura desde el nodo A en el grafo dirigido");
        directedGraph.breadth(nodeA);

        System.out.println("\nRecorrido en anchura desde el nodo C en el grafo dirigido");
        directedGraph.breadth(nodeC);
    }

    private static void printAdjacencyMatrix(Graph<String> graph) {
        System.out.println("\nMatriz de adyacencia:\n");
        double[][] matrix = graph.getAdjacencyMatrix();
        for (double[] row : matrix) {
            for (double value : row) {
                System.out.print(value + " ");
            }
            System.out.println();
        }
    }
}