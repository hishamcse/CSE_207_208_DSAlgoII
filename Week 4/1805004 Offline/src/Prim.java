import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class Prim implements MSTInterface {

    private final CustomMinPQ<Double> minPQ;
    private final WeightedEdge[] edgeTo;
    private final boolean[] visited;
    private final double[] weightTo;

    public Prim(WeightedGraph graph) {
        int vertices = graph.totalVertices();
        minPQ = new CustomMinPQ<>(vertices);
        edgeTo = new WeightedEdge[vertices];
        visited = new boolean[vertices];
        weightTo = new double[vertices];
        Arrays.fill(weightTo, Double.POSITIVE_INFINITY);

        for (int v = 0; v < vertices; v++) {
            if (!visited[v]) {
                applyPrim(graph, v);
            }
        }
    }

    private void applyPrim(WeightedGraph graph, int vertex) {
        weightTo[vertex] = 0;
        minPQ.insert(vertex, weightTo[vertex]);
        while (!minPQ.isEmpty()) {
            traverseAndUpdate(graph, minPQ.deleteMin());
        }
    }

    private void traverseAndUpdate(WeightedGraph graph, int v) {
        visited[v] = true;
        for (WeightedEdge edge : graph.adjacencyList(v)) {
            int w = edge.otherEnd(v);
            if (!visited[w]) {
                if (edge.getWeight() < weightTo[w]) {
                    weightTo[w] = edge.getWeight();
                    edgeTo[w] = edge;
                    if (minPQ.contains(w)) {
                        minPQ.decreaseKey(w, weightTo[w]);
                    } else {
                        minPQ.insert(w, weightTo[w]);
                    }
                }
            }
        }
    }

    @Override
    public Iterable<WeightedEdge> mstEdges() {
        Queue<WeightedEdge> mstEdges = new LinkedList<>();
        for (WeightedEdge edge : edgeTo) {
            if (edge != null) {
                mstEdges.add(edge);
            }
        }
        return mstEdges;
    }

    @Override
    public double mstWeight() {
        double mstWeight = 0;
        for (WeightedEdge edge : mstEdges()) {
            mstWeight += edge.getWeight();
        }
        return mstWeight;
    }
}