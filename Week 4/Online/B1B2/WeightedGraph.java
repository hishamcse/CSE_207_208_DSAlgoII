import java.util.ArrayList;
import java.util.List;

public class WeightedGraph {

    private final int totalVertices;
    private int totalEdges;
    private final List<WeightedEdge>[] adjList;

    public WeightedGraph(int totalVertices) {
        if (totalVertices < 0) throw new IllegalArgumentException("Total vertices can't be negative");
        this.totalVertices = totalVertices;
        this.totalEdges = 0;
        adjList = (ArrayList<WeightedEdge>[]) new ArrayList[totalVertices];
        for (int v = 0; v < totalVertices; v++) {
            adjList[v] = new ArrayList<>();
        }
    }

    public void addEdge(WeightedEdge e) {
        int v = e.oneEnd();
        int w = e.otherEnd(v);
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

    public Iterable<WeightedEdge> adjacencyList(int v) {
        return adjList[v];
    }

    public Iterable<WeightedEdge> edges() {
        List<WeightedEdge> list = new ArrayList<>();
        for (int v = 0; v < totalVertices; v++) {
            int selfLoops = 0;
            for (WeightedEdge edge : adjacencyList(v)) {
                if (edge.otherEnd(v) > v) {
                    list.add(edge);
                }

                else if (edge.otherEnd(v) == v) {      // self loop
                    if (selfLoops % 2 == 0) list.add(edge);
                    selfLoops++;
                }
            }
        }
        return list;
    }
}