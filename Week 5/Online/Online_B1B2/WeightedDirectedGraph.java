import java.util.ArrayList;
import java.util.List;

public class WeightedDirectedGraph {

    private final int totalVertices;
    private int totalEdges;
    private final int[] indegree;
    private final List<WeightedDirectedEdge>[] adjList;

    public WeightedDirectedGraph(int totalVertices) {
        if (totalVertices < 0) throw new IllegalArgumentException("Total vertices can't be negative");
        this.totalVertices = totalVertices;
        this.totalEdges = 0;
        this.indegree = new int[totalVertices];
        adjList = (ArrayList<WeightedDirectedEdge>[]) new ArrayList[totalVertices];
        for (int v = 0; v < totalVertices; v++) {
            adjList[v] = new ArrayList<>();
        }
    }

    public void addEdge(WeightedDirectedEdge e) {
        int v = e.start();
        int w = e.end();
        adjList[v].add(e);
        indegree[w]++;
        totalEdges++;
    }

    public int outDegree(int v) {
        return adjList[v].size();
    }

    public int inDegree(int v) {
        return indegree[v];
    }

    public int totalVertices() {
        return totalVertices;
    }

    public int totalEdges() {
        return totalEdges;
    }

    public Iterable<WeightedDirectedEdge> adjacencyList(int v) {
        return adjList[v];
    }

    public Iterable<WeightedDirectedEdge> edges() {
        List<WeightedDirectedEdge> list = new ArrayList<>();
        for (int v = 0; v < totalVertices; v++) {
            for (WeightedDirectedEdge edge : adjacencyList(v)) {
                list.add(edge);
            }
        }
        return list;
    }
}