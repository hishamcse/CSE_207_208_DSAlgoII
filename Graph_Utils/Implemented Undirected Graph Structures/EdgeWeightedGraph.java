package com.Hisham.UndirectedGraph;

import java.util.ArrayList;

public class EdgeWeightedGraph {

    private final int totalVertices;
    private int totalEdges;
    private final ArrayList<Edge>[] adj;

    public EdgeWeightedGraph(int V) {
        this.totalVertices = V;
        this.totalEdges = 0;
        adj = (ArrayList<Edge>[]) new ArrayList[V];
        for (int v = 0; v < V; v++) {
            adj[v] = new ArrayList<>();
        }
    }

    public int totalVertices() {
        return totalVertices;
    }

    public int totalEdges() {
        return totalEdges;
    }

    private void validateVertex(int v) {
        if (v < 0 || v >= totalVertices)
            throw new IllegalArgumentException("vertex " + v + " is not between 0 and " + (totalVertices -1));
    }

    public void addEdge(Edge e) {
        int v = e.either();
        int w = e.other(v);
        validateVertex(v);
        validateVertex(w);
        adj[v].add(e);
        adj[w].add(e);
        totalEdges++;
    }

    public Iterable<Edge> adj(int v) {
        validateVertex(v);
        return adj[v];
    }

    public int degree(int v) {
        validateVertex(v);
        return adj[v].size();
    }

    public Iterable<Edge> edges() {
        ArrayList<Edge> list = new ArrayList<>();
        for (int v = 0; v < totalVertices; v++) {
            int selfLoops = 0;
            for (Edge e : adj(v)) {
                if (e.other(v) > v) {
                    list.add(e);
                }
                // add only one copy of each self loop (self loops will be consecutive)
                else if (e.other(v) == v) {
                    if (selfLoops % 2 == 0) list.add(e);
                    selfLoops++;
                }
            }
        }
        return list;
    }

//    public WeightedGraph excludeEdgeGraph(WeightedGraph prev_graph, WeightedEdge edge) {
//        WeightedGraph graph = new WeightedGraph(prev_graph.totalVertices());
//        for(WeightedEdge edge1: prev_graph.edges()) {
//            if(!edge1.equals(edge)) {
//                graph.addEdge(edge1);
//            }
//        }
//        return graph;
//    }
}
