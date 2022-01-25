/*
   Implementation of FloydWarshall algorithm (V^3)
 */

import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class FloydWarshall implements AllSPInterface {

    private final WeightedDirectedEdge[][] edgeTo;
    private final double[][] distanceMat;
    private boolean negCycleExists;
    private final int totalVertices;

    public FloydWarshall(WeightedMatrixGraph graph) {
        this.totalVertices = graph.totalVertices();
        edgeTo = new WeightedDirectedEdge[totalVertices][totalVertices];
        distanceMat = new double[totalVertices][totalVertices];

        Arrays.stream(distanceMat).forEach(arr -> Arrays.fill(arr, Double.POSITIVE_INFINITY));

        for (int vertex = 0; vertex < totalVertices; vertex++) {
            for (WeightedDirectedEdge edge : graph.adjacencyList(vertex)) {
                int v = edge.start();
                int w = edge.end();
                edgeTo[v][w] = edge;
                distanceMat[v][w] = edge.getWeight();
            }
            edgeTo[vertex][vertex] = null;
            distanceMat[vertex][vertex] = 0;
        }

        updateDistanceMatrix(distanceMat);
    }

    private void updateDistanceMatrix(double[][] distanceMat) {
        for (int k = 0; k < totalVertices; k++) {
            for (int v = 0; v < totalVertices; v++) {
                if(edgeTo[v][k] == null) continue;
                for (int w = 0; w < totalVertices; w++) {
                    if (distanceMat[v][k] + distanceMat[k][w] < distanceMat[v][w]) {
                        distanceMat[v][w] = distanceMat[v][k] + distanceMat[k][w];
                        edgeTo[v][w] = edgeTo[k][w];
                    }
                }
                if (distanceMat[v][v] < 0) {
                    negCycleExists = true;
                    return;
                }
            }
        }
    }

    @Override
    public double shortestPathWeight(int source, int destination) {
        return distanceMat[source][destination];
    }

    @Override
    public Iterable<WeightedDirectedEdge> shortestPath(int source, int destination) {
        List<WeightedDirectedEdge> path = new LinkedList<>();
        for (WeightedDirectedEdge edge = edgeTo[source][destination]; edge != null; edge = edgeTo[source][edge.start()]) {
            path.add(edge);
        }
        Collections.reverse(path);
        return path;
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
