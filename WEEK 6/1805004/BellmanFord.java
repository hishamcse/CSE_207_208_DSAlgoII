import java.util.*;

public class BellmanFord implements SingleSPInterface {

    private final Queue<Integer> queue;
    private final boolean[] alreadyInQueue;
    private final WeightedDirectedEdge[] edgeTo;
    private final double[] weightTo;
    private final int[] updateCount;
    private Iterable<WeightedDirectedEdge> negativeCycle;

    public BellmanFord(WeightedDirectedGraph graph, int s) {
        int vertices = graph.totalVertices();
        queue = new LinkedList<>();
        alreadyInQueue = new boolean[vertices];
        edgeTo = new WeightedDirectedEdge[vertices];
        weightTo = new double[vertices];
        updateCount = new int[vertices];

        Arrays.fill(weightTo, Double.POSITIVE_INFINITY);

        applyBellman(graph, s);
    }

    private void applyBellman(WeightedDirectedGraph graph, int s) {
        weightTo[s] = 0;
        queue.add(s);
        alreadyInQueue[s] = true;

        while (!queue.isEmpty() && negativeCycle == null) {
            int vertex = queue.remove();
            alreadyInQueue[vertex] = false;
            relaxVertex(graph, vertex);
        }
    }

    private void relaxVertex(WeightedDirectedGraph graph, int v) {
        for (WeightedDirectedEdge edge : graph.adjacencyList(v)) {
            int w = edge.end();
            if (weightTo[v] + edge.getWeight() < weightTo[w]) {
                weightTo[w] = weightTo[v] + edge.getWeight();
                edgeTo[w] = edge;
                if (!alreadyInQueue[w]) {
                    queue.add(w);
                    alreadyInQueue[w] = true;
                    updateCount[w]++;
                    if (updateCount[w] >= graph.totalVertices()) {       // negative cycle exists
                        constructNegCycle();
                        return;
                    }
                }
            }
        }
    }

    public boolean negCycleExists() {
        return negativeCycle != null;
    }

    private void constructNegCycle() {
        WeightedDirectedGraph graph = new WeightedDirectedGraph(edgeTo.length);
        for (WeightedDirectedEdge edge : edgeTo) {
            if (edge != null) graph.addEdge(edge);
        }
        WeightedDirectedCycle directedCycle = new WeightedDirectedCycle(graph);
        negativeCycle = directedCycle.cycle();
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
        sb.append("Result for Bellman-Ford Algorithm : ").append('\n');
        if (negativeCycle != null) {
            sb.append("The graph contains a negative cycle");
        } else if (weightTo[destination] == Double.POSITIVE_INFINITY) {
            sb.append("Could not reach target");
        } else {
            sb.append("The graph does not contain a negative cycle").append('\n');
            sb.append("Shortest path cost: ").append(weightTo[destination]).append('\n');
            for (WeightedDirectedEdge edge : shortestPath(destination)) {
                sb.append(edge.start()).append(" -> ");
            }
            sb.append(destination);
        }
        return sb.toString();
    }
}