//public class Arbitrage {
//
//    // this class cannot be instantiated
//    private Arbitrage() { }
//
//    /**
//     *  Reads the currency exchange table from standard input and
//     *  prints an arbitrage opportunity to standard output (if one exists).
//     *
//     * @param args the command-line arguments
//     */
//    public static void main(String[] args) {
//
//        // V currencies
//        int V = StdIn.readInt();
//        String[] name = new String[V];
//
//        // create complete network
//        EdgeWeightedDigraph G = new EdgeWeightedDigraph(V);
//        for (int v = 0; v < V; v++) {
//            name[v] = StdIn.readString();
//            for (int w = 0; w < V; w++) {
//                double rate = StdIn.readDouble();
//                DirectedEdge e = new DirectedEdge(v, w, -Math.log(rate));
//                G.addEdge(e);
//            }
//        }
//
//        // find negative cycle
//        BellmanFordSP spt = new BellmanFordSP(G, 0);
//        if (spt.hasNegativeCycle()) {
//            double stake = 1000.0;
//            for (DirectedEdge e : spt.negativeCycle()) {
//                StdOut.printf("%10.5f %s ", stake, name[e.from()]);
//                stake *= Math.exp(-e.weight());
//                StdOut.printf("= %10.5f %s\n", stake, name[e.to()]);
//            }
//        }
//        else {
//            StdOut.println("No arbitrage opportunity");
//        }
//    }
//
//}