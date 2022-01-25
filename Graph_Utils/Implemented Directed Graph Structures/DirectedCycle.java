package com.Hisham.DirectedGraph;

import java.util.Stack;

public class DirectedCycle {

    private final boolean[] marked;
    private final int[] edgeTo;
    private Stack<Integer> cycle;
    private final boolean[] onCycle;

    public DirectedCycle(Digraph g) {
        marked = new boolean[g.V()];
        edgeTo = new int[g.V()];
        onCycle = new boolean[g.V()];

        for (int v = 0; v < g.V(); v++) {
            if (cycle == null && !marked[v]) {
                dfs(g, v);
            }
        }
    }

    private void dfs(Digraph g, int v) {
        onCycle[v] = true;
        marked[v] = true;
        for (int w : g.adj(v)) {
            if (cycle != null) {
                return;
            } else if (!marked[w]) {
                edgeTo[w] = v;
                dfs(g, w);
            } else if (onCycle[w]) {
                cycle = new Stack<>();
                for (int x = v; x != w; x = edgeTo[x]) {
                    cycle.push(x);
                }
                cycle.push(w);
                cycle.push(v);
            }
        }
        onCycle[v] = false;
    }

    public boolean hasCycle() {
        return cycle != null;
    }

    public Iterable<Integer> cycle() {
        return cycle;
    }
}