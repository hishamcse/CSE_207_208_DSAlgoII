package com.Hisham.Ineffecient;

public class DirectedDFS {

    private final boolean[] marked;

    public DirectedDFS(Digraph g, int s) {
        marked = new boolean[g.V()];
        dfs(g, s);
    }

    private void dfs(Digraph g, int v) {
        marked[v] = true;
        for (int w : g.adj(v)) {
            if (!marked[w]) {
                dfs(g, w);
            }
        }
    }

    public boolean marked(int v) {
        return marked[v];
    }
}