package co.edu.uptc.structures.nonlinear;

import co.edu.uptc.structures.linear.MyQueue;

import java.util.ArrayList;
import java.util.List;

public class Graph<T> {
    private double[][] adjacencyMatrix;
    private List <NodeGraph<T>> nodes;

    public Graph() {
        this.nodes = new ArrayList<>();
        this.adjacencyMatrix = new double[0][0];
    }

    public void addVertex(NodeGraph<T> node) {
        nodes.add(node);
        resizeMatrix();
    }

    public void deleteVertex(NodeGraph<T> node) {
        int index = nodes.indexOf(node);
        if (index != -1) {
            nodes.remove(node);
            resizeMatrix();
        }
    }

    public void addEdgeUndirected(NodeGraph<T> u, NodeGraph<T> v) {
        addEdgeUndirected(u, v, 1);
    }

    public void addEdgeUndirected(NodeGraph<T> u, NodeGraph<T> v, double weight) {
        int posU = nodes.indexOf(u);
        int posV = nodes.indexOf(v);
        if (posU != -1 && posV != -1) {
            adjacencyMatrix[posU][posV] = weight;
            adjacencyMatrix[posV][posU] = weight;
        }
    }

    public void addEdgeDirected(NodeGraph<T> u, NodeGraph<T> v) {
        addEdgeDirected(u, v, 1);
    }

    public void addEdgeDirected(NodeGraph<T> u, NodeGraph<T> v, double weight) {
        int posU = nodes.indexOf(u);
        int posV = nodes.indexOf(v);
        if (posU != -1 && posV != -1) {
            adjacencyMatrix[posU][posV] = weight;
        }
    }

    public void removeEdgeDirected(NodeGraph<T> u, NodeGraph<T> v) {
        int posU = nodes.indexOf(u);
        int posV = nodes.indexOf(v);
        if (posU != -1 && posV != -1) {
            adjacencyMatrix[posU][posV] = 0;
        }
    }

    public void removeEdgeUndirected(NodeGraph<T> u, NodeGraph<T> v) {
        int posU = nodes.indexOf(u);
        int posV = nodes.indexOf(v);
        if (posU != -1 && posV != -1) {
            adjacencyMatrix[posU][posV] = 0;
            adjacencyMatrix[posV][posU] = 0;
        }
    }

    public boolean areAdjacent(NodeGraph<T> u, NodeGraph<T> v) {
        int posU = nodes.indexOf(u);
        int posV = nodes.indexOf(v);
        return posU != -1 && posV != -1 && adjacencyMatrix[posU][posV] != 0;
    }

    public double[][] getAdjacencyMatrix() {
        return adjacencyMatrix;
    }

    public void breadth(NodeGraph<T> start){
        MyQueue<NodeGraph<T>> queue = new MyQueue<>();
        List<NodeGraph<T>> visited = new ArrayList<>();

        visited.add(start);
        queue.push(start);

        while (!queue.isEmpty()){
            NodeGraph<T> node = queue.poll();
            System.out.println(node);

            int nodeIndex = nodes.indexOf(node);

            for (int i = 0; i < adjacencyMatrix[nodeIndex].length; i++) {
                if (adjacencyMatrix[nodeIndex][i] != 0){
                    NodeGraph<T> neighbourNode = nodes.get(i);
                    if (!visited.contains(neighbourNode)){
                        visited.add(neighbourNode);
                        queue.push(neighbourNode);
                    }
                }
            }
        }
    }

    private void resizeMatrix() {
        double[][] newMatrix = new double[nodes.size()][nodes.size()];
        for (int i = 0; i < adjacencyMatrix.length; i++) {
            System.arraycopy(adjacencyMatrix[i], 0, newMatrix[i], 0, adjacencyMatrix[i].length);
        }
        adjacencyMatrix = newMatrix;
    }
}
