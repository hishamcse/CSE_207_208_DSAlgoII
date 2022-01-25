import java.util.*;

public class Dijkstra implements SingleSPInterface {

    private final CustomMinPQ<Double> minPQ;
    private final WeightedDirectedEdge[] edgeTo;
    private final double[] weightTo;

    public Dijkstra(WeightedDirectedGraph graph, int s) {
        int vertices = graph.totalVertices();
        minPQ = new CustomMinPQ<>(vertices);
        edgeTo = new WeightedDirectedEdge[vertices];
        weightTo = new double[vertices];
        Arrays.fill(weightTo, Double.POSITIVE_INFINITY);

        applyDijkstra(graph, s);
    }

    private void applyDijkstra(WeightedDirectedGraph graph, int vertex) {
        weightTo[vertex] = 0;
        minPQ.insert(vertex, weightTo[vertex]);
        while (!minPQ.isEmpty()) {
            for (WeightedDirectedEdge edge : graph.adjacencyList(minPQ.deleteMin())) {
                relaxEdge(edge);
            }
        }
    }

    private void relaxEdge(WeightedDirectedEdge edge) {
        int v = edge.start();
        int w = edge.end();
        if (weightTo[v] + edge.getWeight() < weightTo[w]) {
            weightTo[w] = weightTo[v] + edge.getWeight();
            edgeTo[w] = edge;
            if (minPQ.contains(w)) {
                minPQ.decreaseKey(w, weightTo[w]);
            } else {
                minPQ.insert(w, weightTo[w]);
            }
        }
    }

    @Override
    public double shortestPathWeight(int destination) {
        return weightTo[destination];
    }

    @Override
    public Iterable<WeightedDirectedEdge> shortestPath(int destination) {
        List<WeightedDirectedEdge> path = new LinkedList<>();
        for (WeightedDirectedEdge edge = edgeTo[destination]; edge != null; edge = edgeTo[edge.start()]) {
            path.add(edge);
        }
        Collections.reverse(path);
        return path;
    }

    @Override
    public String formattedOutput(int destination) {
        StringBuilder sb = new StringBuilder();
        sb.append("Result for Dijkstra Algorithm : ").append('\n');
        if (weightTo[destination] == Double.POSITIVE_INFINITY) {
            sb.append("Couldn't reach target");
        } else {
            sb.append("Shortest path cost: ").append(weightTo[destination]).append('\n');
            for (WeightedDirectedEdge edge : shortestPath(destination)) {
                sb.append(edge.start()).append(" -> ");
            }
            sb.append(destination);
        }
        return sb.toString();
    }
}