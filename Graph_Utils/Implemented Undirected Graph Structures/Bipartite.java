package com.Hisham.UndirectedGraph;

import java.util.Stack;

public class Bipartite {

    private final boolean[] marked;
    private final boolean[] color;
    private final int[] edgeTo;
    private boolean isBipartite;
    private Stack<Integer> cycle;    // odd length cycle if not bipartite

    public Bipartite(Graph g) {
        isBipartite = true;
        marked = new boolean[g.totalVertices()];
        edgeTo = new int[g.totalVertices()];
        color = new boolean[g.totalVertices()];
        for (int v = 0; v < g.totalVertices(); v++) {
            if (!marked[v]) {
                dfs(g, v);
            }
        }
    }

    private void dfs(Graph g, int v) {
        marked[v] = true;
        for (int w : g.adj(v)) {
            if (cycle != null) {
                return;
            }
            if (!marked[w]) {
                edgeTo[w] = v;
                color[w] = !color[v];
                dfs(g, w);
            } else if (color[w] == color[v]) {
                isBipartite = false;
                cycle = new Stack<>();
                cycle.push(w);     // it will be twice because we want cycle
                for (int x = v; x != w; x = edgeTo[x]) {
                    cycle.push(x);
                }
                cycle.push(w);
            }
        }
    }

    public boolean isBipartite() {
        return isBipartite;
    }

    public Iterable<Integer> oddCycle() {
        return cycle;
    }

    public boolean color(int v) {
        if (!isBipartite()) {
            throw new UnsupportedOperationException("not allowed");
        }
        return color[v];
    }
}