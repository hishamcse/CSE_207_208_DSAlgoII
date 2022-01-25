import java.util.*;

public class ModifiedDijkstra implements SingleSPInterface {

    private final CustomMinPQ<Double> minPQ;
    private final WeightedDirectedEdge[] edgeTo;
    private final double[] weightTo;

    public ModifiedDijkstra(WeightedDirectedGraph graph, int s, double[] cost) {
        int vertices = graph.totalVertices();
        minPQ = new CustomMinPQ<>(vertices);
        edgeTo = new WeightedDirectedEdge[vertices];
        weightTo = new double[vertices];
        Arrays.fill(weightTo, Double.POSITIVE_INFINITY);

        applyDijkstra(graph, s, cost);
    }

    private void applyDijkstra(WeightedDirectedGraph graph, int vertex, double[] cost) {
        weightTo[vertex] = 0;       // 0.0
        minPQ.insert(vertex, weightTo[vertex]);
        while (!minPQ.isEmpty()) {
            for (WeightedDirectedEdge edge : graph.adjacencyList(minPQ.deleteMin())) {
                relaxEdge(edge, cost);
            }
        }
    }

    private void relaxEdge(WeightedDirectedEdge edge, double[] cost) {
        int v = edge.start();
        int w = edge.end();
        if (weightTo[v] + edge.getWeight() < weightTo[w]) {
            weightTo[w] = weightTo[v] + edge.getWeight() + cost[w];     // change
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
    public String formattedOutput(Map<Integer, String> map, int destination) {
        StringBuilder sb = new StringBuilder();
        if (weightTo[destination] == Double.POSITIVE_INFINITY) {
            sb.append("Couldn't reach target");
        } else {
            sb.append("Shortest path cost: ").append(weightTo[destination]).append('\n');
            for (WeightedDirectedEdge edge : shortestPath(destination)) {
                sb.append(map.get(edge.start())).append(" -> ");
            }
            sb.append(map.get(destination));
        }
        return sb.toString();
    }
}