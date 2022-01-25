import java.util.ArrayList;
import java.util.List;

public class FlowNetworkGraph {

    private final int totalVertices;
    private int totalEdges;
    private final List<FlowEdge>[] adjList;

    public FlowNetworkGraph(int totalVertices) {
        this.totalVertices = totalVertices;
        this.totalEdges = 0;
        adjList = (ArrayList<FlowEdge>[]) new ArrayList[totalVertices];
        for (int v = 0; v < totalVertices; v++) {
            adjList[v] = new ArrayList<>();
        }
    }

    public void addEdge(FlowEdge e) {
        int v = e.start();
        int w = e.end();
        adjList[v].add(e);
        adjList[w].add(e);
        totalEdges++;
    }

    public int degree(int v) {
        return adjList[v].size();
    }

    public int totalVertices() {
        return totalVertices;
    }

    public int totalEdges() {
        return totalEdges;
    }

    public Iterable<FlowEdge> adjacencyList(int v) {
        return adjList[v];
    }

    public Iterable<FlowEdge> edges() {
        List<FlowEdge> list = new ArrayList<>();
        for (int v = 0; v < totalVertices; v++) {
            for (FlowEdge edge : adjacencyList(v)) {
                list.add(edge);
            }
        }
        return list;
    }
}