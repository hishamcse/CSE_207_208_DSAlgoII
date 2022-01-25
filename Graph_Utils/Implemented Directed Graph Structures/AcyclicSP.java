//package com.Hisham;
//
//public class AcyclicSP {
//    private double[] distTo;         // distTo[v] = distance  of shortest s->v path
//    private DirectedEdge[] edgeTo;   // edgeTo[v] = last edge on shortest s->v path
//
//    public AcyclicSP(EdgeWeightedDigraph G, int s) {
//        distTo = new double[G.V()];
//        edgeTo = new DirectedEdge[G.V()];
//
//        validateVertex(s);
//
//        for (int v = 0; v < G.V(); v++)
//            distTo[v] = Double.POSITIVE_INFINITY;
//        distTo[s] = 0.0;
//
//        // visit vertices in topological order
//        Topological topological = new Topological(G);
//        if (!topological.hasOrder())
//            throw new IllegalArgumentException("Digraph is not acyclic.");
//        for (int v : topological.order()) {
//            for (DirectedEdge e : G.adj(v))
//                relax(e);
//        }
//    }
//
//    // relax edge e
//    private void relax(DirectedEdge e) {
//        int v = e.from(), w = e.to();
//        if (distTo[w] > distTo[v] + e.weight()) {
//            distTo[w] = distTo[v] + e.weight();
//            edgeTo[w] = e;
//        }
//    }
//}