package com.Hisham.UndirectedGraph;

import java.util.Stack;

public class DepthFirstPaths {

    private final boolean[] marked;
    private final int[] edgeTo;
    private final int s;

    public DepthFirstPaths(Graph g, int s) {
        this.s = s;
        marked = new boolean[g.totalVertices()];
        edgeTo = new int[g.totalVertices()];
        dfs(g, s);
    }

    private void dfs(Graph g, int v) {
        marked[v] = true;
        for (int w : g.adj(v)) {
            if (!marked[w]) {
                edgeTo[w] = v;
                dfs(g, w);
            }
        }
    }

    public boolean hasPathTo(int v) {
        return marked[v];
    }

    public Iterable<Integer> pathTo(int v) {
        if (!hasPathTo(v)) {
            return null;
        }
        Stack<Integer> path = new Stack<>();
        for (int x = v; x != s; x = edgeTo[x]) {
            path.push(x);
        }
        path.push(s);
        return path;
    }
}