//public class CPM {
//
//    // this class cannot be instantiated
//    private CPM() { }
//
//    /**
//     *  Reads the precedence constraints from standard input
//     *  and prints a feasible schedule to standard output.
//     *
//     * @param args the command-line arguments
//     */
//    public static void main(String[] args) {
//
//        // number of jobs
//        int n = StdIn.readInt();
//
//        // source and sink
//        int source = 2*n;
//        int sink   = 2*n + 1;
//
//        // build network
//        EdgeWeightedDigraph G = new EdgeWeightedDigraph(2*n + 2);
//        for (int i = 0; i < n; i++) {
//            double duration = StdIn.readDouble();
//            G.addEdge(new DirectedEdge(source, i, 0.0));
//            G.addEdge(new DirectedEdge(i+n, sink, 0.0));
//            G.addEdge(new DirectedEdge(i, i+n,    duration));
//
//            // precedence constraints
//            int m = StdIn.readInt();
//            for (int j = 0; j < m; j++) {
//                int precedent = StdIn.readInt();
//                G.addEdge(new DirectedEdge(n+i, precedent, 0.0));
//            }
//        }
//
//        // compute longest path
//        AcyclicLP lp = new AcyclicLP(G, source);
//
//        // print results
//        StdOut.println(" job   start  finish");
//        StdOut.println("--------------------");
//        for (int i = 0; i < n; i++) {
//            StdOut.printf("%4d %7.1f %7.1f\n", i, lp.distTo(i), lp.distTo(i+n));
//        }
//        StdOut.printf("Finish time: %7.1f\n", lp.distTo(sink));
//    }
//
//}