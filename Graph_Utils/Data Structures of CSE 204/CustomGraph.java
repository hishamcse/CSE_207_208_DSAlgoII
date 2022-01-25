/*
 * Implementation Of Graph API
 */

import java.util.ArrayList;

public class CustomGraph {

    private final int totalVertices;
    private final ArrayList<Integer>[] adjacencyList;

    public CustomGraph(int totalVertices) {
        this.totalVertices = totalVertices;
        adjacencyList = (ArrayList<Integer>[]) new ArrayList[totalVertices];
        for (int i = 0; i < totalVertices; i++) {
            adjacencyList[i] = new ArrayList<>();
        }
    }

    private boolean checkInvalidVertex(int v) {
        return v < 0 || v >= totalVertices;
    }

    public void edgeAddition(int vertex, int other) {
        if (checkInvalidVertex(vertex) || checkInvalidVertex(other)) {
            System.err.println("Invalid Vertex");
            return;
        }
        adjacencyList[vertex].add(other);
        adjacencyList[other].add(vertex);
    }

    public int totalVertices() {
        return totalVertices;
    }

    public ArrayList<Integer> adjacencyList(int vertex) {
        return adjacencyList[vertex];
    }
}