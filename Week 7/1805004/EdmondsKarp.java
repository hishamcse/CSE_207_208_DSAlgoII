/*
 * Implementation of Ford Fulkerson method using
 * The Shortest Augmenting Path Heuristic (Edmonds Karp Algorithm)
 */

public class EdmondsKarp {

    private double maxFlow;
    private final AugmentingPathBFS augmentingPathBFS;

    public EdmondsKarp(FlowNetworkGraph graph, int source, int destination) {
        augmentingPathBFS = new AugmentingPathBFS(graph);

        maxFlow = 0.0;
        while (augmentingPathBFS.augmentingPathExists(graph, source, destination)) {
            double bottleneck = bottleneck_capacity(source, destination);
            addAugmentFlow(source, destination, bottleneck);
            maxFlow += bottleneck;
        }
    }

    private double bottleneck_capacity(int source, int destination) {
        double val = Double.POSITIVE_INFINITY;

        int v = destination;
        while (v != source) {
            val = Math.min(val, augmentingPathBFS.edgeTo(v).residualCapacity(v));
            v = augmentingPathBFS.edgeTo(v).otherEnd(v);
        }

        return val;
    }

    private void addAugmentFlow(int source, int destination, double bottleneck) {
        int v = destination;
        while (v != source) {
            augmentingPathBFS.edgeTo(v).addResidualFlow(v, bottleneck);
            v = augmentingPathBFS.edgeTo(v).otherEnd(v);
        }
    }

    public boolean insideCut(int vertex) {
        return augmentingPathBFS.traversed(vertex);
    }

    public double getMaxFlow() {
        return maxFlow;
    }
}