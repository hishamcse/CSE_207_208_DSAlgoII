import java.util.LinkedList;
import java.util.List;

public class WeightedMatrixGraph {

    private final int totalVertices;
    private int totalEdges;
    private final WeightedDirectedEdge[][] adjMat;

    public WeightedMatrixGraph(int totalVertices) {
        this.totalVertices = totalVertices;
        this.totalEdges = 0;
        this.adjMat = new WeightedDirectedEdge[totalVertices][totalVertices];
    }

    public void addEdge(WeightedDirectedEdge e) {
        int v = e.start();
        int w = e.end();
        if (adjMat[v][w] != null) return;
        adjMat[v][w] = e;
        totalEdges++;
    }

    public int totalVertices() {
        return totalVertices;
    }

    public int totalEdges() {
        return totalEdges;
    }

    public Iterable<WeightedDirectedEdge> adjacencyList(int v) {
        List<WeightedDirectedEdge> edges = new LinkedList<>();
        for (WeightedDirectedEdge edge : adjMat[v]) {
            if (edge != null) {
                edges.add(edge);
            }
        }
        return edges;
    }
}