package OnlineMaxFlow_Practice;

public class FattestPath {

    private FlowEdge[] edgeTo;
    private double value;
    private double[] distTo;
    private IndexMaxPQ<Double> maxPQ;

    public FattestPath(FlowNetworkGraph g, int s, int t) {
        value = excess(g, t);

        while (hasAugmentingPath(g, s, t)) {

            double bottle = Double.MAX_VALUE;
            for (int v = t; v != s; v = edgeTo[v].otherEnd(v)) {
                bottle = Math.min(bottle, edgeTo[v].residualCapacity(v));
            }

            for (int v = t; v != s; v = edgeTo[v].otherEnd(v)) {
                edgeTo[v].addResidualFlow(v, bottle);
            }
            value += bottle;
        }

    }

    private boolean hasAugmentingPath(FlowNetworkGraph g, int s, int t) {
        edgeTo = new FlowEdge[g.totalVertices()];
        distTo = new double[g.totalVertices()];
        maxPQ = new IndexMaxPQ<>(g.totalVertices());

        for (int i = 0; i < g.totalVertices(); i++) {
            distTo[i] = Double.NEGATIVE_INFINITY;
        }

        distTo[s] = Double.POSITIVE_INFINITY;
        maxPQ.insert(s, distTo[s]);
        while (!maxPQ.isEmpty()) {
            int v = maxPQ.delMax();
            for (FlowEdge e : g.adjacencyList(v)) {
                relax(e);
            }
        }
        return edgeTo[t] != null;
    }

    private void relax(FlowEdge e) {
        int v = e.start();
        int w = e.end();
        if (e.residualCapacity(w) > 0 && distTo[w] < Math.min(distTo[v], e.residualCapacity(w))) {
            distTo[w] = Math.min(distTo[v], e.residualCapacity(w));
            edgeTo[w] = e;
            if (maxPQ.contains(w)) {
                maxPQ.increaseKey(w, distTo[w]);
            } else {
                maxPQ.insert(w, distTo[w]);
            }
        }
    }

    public double value() {
        return value;
    }

    public boolean inCut(int v) {
        return distTo[v] > Double.NEGATIVE_INFINITY;
    }

    public double excess(FlowNetworkGraph g, int v) {
        double excess = 0.0;
        for (FlowEdge e : g.adjacencyList(v)) {
            if (v == e.start()) {
                excess -= e.getFlow();
            } else {
                excess += e.getFlow();
            }
        }
        return excess;
    }

    public static void main(String[] args) {

//        // create flow network with V vertices and E edges
//        int V = Integer.parseInt(StdIn.readLine());
//        int E = Integer.parseInt(StdIn.readLine());
//        int s = 0, t = V - 1;
//        FlowNetwork G = new FlowNetwork(V, E);
//        StdOut.println(G);
//
//        // compute maximum flow and minimum cut
//        FordFulkersonFattestPath maxflow = new FordFulkersonFattestPath(G, s, t);
//        StdOut.println("Max flow from " + s + " to " + t);
//        for (int v = 0; v < G.V(); v++) {
//            for (FlowEdge e : G.adj(v)) {
//                if ((v == e.from()) && e.flow() > 0)
//                    StdOut.println("   " + e);
//            }
//        }
//
//        // print min-cut
//        StdOut.print("Min cut: ");
//        for (int v = 0; v < G.V(); v++) {
//            if (maxflow.inCut(v)) StdOut.print(v + " ");
//        }
//        StdOut.println();
//
//        StdOut.println("Max flow value = " + maxflow.value());
    }
}
