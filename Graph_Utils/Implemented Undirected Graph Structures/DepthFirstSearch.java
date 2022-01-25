package com.Hisham.UndirectedGraph;

public class DepthFirstSearch {

    private final boolean[] marked;
    private int count;

    public DepthFirstSearch(Graph g, int s) {
        count = 0;
        marked = new boolean[g.totalVertices()];
        dfs(g, s);
    }

    private void dfs(Graph g, int v) {
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