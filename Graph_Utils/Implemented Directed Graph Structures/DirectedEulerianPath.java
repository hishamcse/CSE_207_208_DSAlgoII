package com.Hisham.DirectedGraph;

import java.util.Iterator;
import java.util.Stack;

public class DirectedEulerianPath {

    private Stack<Integer> path = null;

    public DirectedEulerianPath(Digraph g) {
        int s = nonIsolatedVertex(g);
        int odd = 0;
        for (int v = 0; v < g.V(); v++) {
            if (g.outdegree(v) > g.indegree(v)) {
                odd += (g.outdegree(v) - g.indegree(v));
                s = v;
            }
        }

        if (odd > 1) {
            return;
        }
        if (s == -1) {     // if there is no vertex having at least one edge
            s = 0;
        }

        Iterator<Integer>[] adj = (Iterator<Integer>[]) new Iterator[g.V()];
        for (int v = 0; v < g.V(); v++) {
            adj[v] = g.adj(v).iterator();
        }

        createPath(g, adj, s);
    }


    private void createPath(Digraph g, Iterator<Integer>[] adj, int s) {
        Stack<Integer> stack = new Stack<>();
        stack.push(s);

        path = new Stack<>();
        while (!stack.isEmpty()) {
            int v = stack.pop();
            while (adj[v].hasNext()) {
                stack.push(v);
                v = adj[v].next();
            }
            path.push(v);
        }

        if (path.size() != g.E() + 1) {
            path = null;
        }
    }

    private int nonIsolatedVertex(Digraph g) {
        for (int v = 0; v < g.V(); v++) {
            if (g.outdegree(v) > 0) {
                return v;
            }
        }
        return -1;
    }

    public boolean hasEulerianPath() {
        return path != null;
    }

    public Iterable<Integer> path() {
        return path;
    }
}