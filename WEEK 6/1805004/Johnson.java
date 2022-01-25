import java.util.Arrays;

/*
   Implementation of Johnson algorithm (V^2logV + VE)
 */

public class Johnson implements AllSPInterface {

    private final double[][] distanceMat;
    private final double[] vertexWeight;
    private boolean negCycleExists;
    private final WeightedDirectedGraph directedGraph;

    public Johnson(WeightedDirectedGraph graph) {
        directedGraph = graph;
        int vertices = graph.totalVertices();
        distanceMat = new double[vertices][vertices];
        vertexWeight = new double[vertices + 1];
        Arrays.stream(distanceMat).forEach(arr -> Arrays.fill(arr, Double.POSITIVE_INFINITY));

        WeightedDirectedGraph modifiedGraph = new WeightedDirectedGraph(vertices + 1);
        for (WeightedDirectedEdge edge : graph.edges()) {
            modifiedGraph.addEdge(edge);
        }

        for (int v = 0; v < vertices; v++) {
            modifiedGraph.addEdge(new WeightedDirectedEdge(vertices, v, 0));
        }

        applyBellman(modifiedGraph, vertices);
        if (negCycleExists) return;

        reWeightEdges(modifiedGraph);

        applyDijkstra(modifiedGraph);
    }

    private void applyBellman(WeightedDirectedGraph modifiedGraph, int temp_source) {
        BellmanFord bellmanFord = new BellmanFord(modifiedGraph, temp_source);
        if (bellmanFord.negCycleExists()) {
            negCycleExists = true;
            return;
        }

        for (int v = 0; v < temp_source; v++) {
            vertexWeight[v] = bellmanFord.shortestPathWeight(v);
        }
    }

    private void reWeightEdges(WeightedDirectedGraph modifiedGraph) {
        for (WeightedDirectedEdge edge : modifiedGraph.edges()) {
            edge.setWeight(edge.getWeight() + vertexWeight[edge.start()] - vertexWeight[edge.end()]);
        }
    }

    private void applyDijkstra(WeightedDirectedGraph modifiedGraph) {
        Dijkstra dijkstra;
        for (int v = 0; v < modifiedGraph.totalVertices() - 1; v++) {
            dijkstra = new Dijkstra(modifiedGraph, v, vertexWeight);
            for (int w = 0; w < modifiedGraph.totalVertices() - 1; w++) {
                distanceMat[v][w] = dijkstra.retrievedSPWeight(w);
            }
        }
    }

    @Override
    public double shortestPathWeight(int source, int destination) {
        return distanceMat[source][destination];
    }

    @Override
    public Iterable<WeightedDirectedEdge> shortestPath(int source, int destination) {
        BellmanFord bellmanFord = new BellmanFord(directedGraph, source);
        return bellmanFord.shortestPath(destination);
    }

    @Override
    public String formattedOutput() {
        StringBuilder sb = new StringBuilder();
        if (negCycleExists) {
            sb.append("Contains negative cycle");
        } else {
            sb.append("Shortest distance matrix").append('\n').append('\n');
            for (double[] distArr : distanceMat) {
                for (double element : distArr) {
                    if (element == Double.POSITIVE_INFINITY) {
                        sb.append("INF").append("\t");
//                        sb.append("INF\t\t");
                    } else {
                        sb.append((int) element).append("\t");
//                        sb.append(String.format("%.2f\t", element));
                    }
                }
                sb.append('\n');
            }
        }
        return sb.toString();
    }
}