package com.Hisham;

public class DirectedCycle {

    private final boolean[] marked;
    private boolean cycleExists;
    private final boolean[] onCycle;

    public DirectedCycle(Digraph g) {
        marked = new boolean[g.V()];
        onCycle = new boolean[g.V()];
        cycleExists = false;

        for (int v = 0; v < g.V(); v++) {
            if (!marked[v]) {
                dfs(g, v);
            }
        }
    }

    private void dfs(Digraph g, int v) {
        onCycle[v] = true;
        marked[v] = true;
        for (int w : g.adj(v)) {
            if (cycleExists) {
                return;
            } else if (!marked[w]) {
                dfs(g, w);
            } else if (onCycle[w]) {
                cycleExists = true;
                return;
            }
        }
        onCycle[v] = false;
    }

    public boolean cycleExists() {
        return cycleExists;
    }
}