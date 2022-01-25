package com.Hisham.DirectedGraph;

public class DirectedDFSSearch {   //for both single and multiple sources

    private final boolean[] marked;
    private int count;

    public DirectedDFSSearch(Digraph g, int s) {
        count = 0;
        marked = new boolean[g.V()];
        dfs(g, s);
    }

    public DirectedDFSSearch(Digraph G, Iterable<Integer> sources) {
        marked = new boolean[G.V()];
        for (int v : sources) {
            if (!marked[v]) dfs(G, v);
        }
    }

    private void dfs(Digraph g, int v) {
        count++;
        marked[v] = true;
        for (int w : g.adj(v)) {
            if (!marked[w]) {
                dfs(g, w);
            }
        }
    }

    public int count() {
        return count;
    }

    public boolean marked(int v) {
        return marked[v];
    }
}