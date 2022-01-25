package com.Hisham.UndirectedGraph;

import java.util.Stack;

public class Cycle {

    private boolean[] marked;
    private int[] edgeTo;
    private Stack<Integer> cycle;

    public Cycle(Graph g) {
        if (hasSelfLoops(g) || hasParallelEdges(g)) {
            return;
        }
        marked = new boolean[g.totalVertices()];
        edgeTo = new int[g.totalVertices()];
        for (int v = 0; v < g.totalVertices(); v++) {
            if (!marked[v]) {
                dfs(g, -1, v);
            }
        }
    }

    private boolean hasSelfLoops(Graph g) {
        for (int v = 0; v < g.totalVertices(); v++) {
            for (int w : g.adj(v)) {
                if (w == v) {
                    cycle = new Stack<>();
                    cycle.push(w);
                    cycle.push(w);
                    return true;
                }
            }
        }
        return false;
    }

    private boolean hasParallelEdges(Graph g) {
        marked = new boolean[g.totalVertices()];
        for (int v = 0; v < g.totalVertices(); v++) {
            for (int w : g.adj(v)) {
                if (marked[w]) {    // that means for v,we have already marked w.so, parallel edges exist
                    cycle = new Stack<>();
                    cycle.push(v);
                    cycle.push(w);
                    cycle.push(v);
                    return true;
                }
                marked[w] = true;
            }
            for (int w : g.adj(v)) {   // again, all set to false.so,that for another v,we can check again from start.
                marked[w] = false;
            }
        }
        return false;
    }

    private void dfs(Graph g, int u, int v) {    // u - v (edgeTo[v]=u)
        marked[v] = true;
        for (int w : g.adj(v)) {
            if (cycle != null) {
                return;
            }
            if (!marked[w]) {
                edgeTo[w] = v;
                dfs(g, v, w);
            } else if (w != u) {   // whether u - v == w - v. if it is not, then cycle contains. otherwise, it is not a cycle.
                cycle = new Stack<>();
                for (int x = v; x != w; x = edgeTo[x]) {
                    cycle.push(x);
                }
                cycle.push(w);
                cycle.push(v);    // for making cycle
            }
        }
    }

    public boolean hasCycle() {
        return cycle != null;
    }

    public Iterable<Integer> cycle() {
        return cycle;
    }
}