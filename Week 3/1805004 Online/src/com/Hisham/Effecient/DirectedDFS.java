package com.Hisham.Effecient;

import java.util.Stack;

public class DirectedDFS {

    private final boolean[] marked;
    private final Stack<Integer> reversePost;

    public DirectedDFS(Digraph g) {
        marked = new boolean[g.V()];
        reversePost = new Stack<>();
        for (int v = 0; v < g.V(); v++) {
            if (!marked[v]) {
                dfs(g, v);
            }
        }
    }

    public DirectedDFS(Digraph g, int s) {
        marked = new boolean[g.V()];
        reversePost = new Stack<>();
        dfs(g, s);
    }

    private void dfs(Digraph g, int v) {
        marked[v] = true;
        for (int w : g.adj(v)) {
            if (!marked[w]) {
                dfs(g, w);
            }
        }
        reversePost.push(v);
    }

    public boolean marked(int v) {
        return marked[v];
    }

    public Stack<Integer> reversePost() {
        return reversePost;
    }
}