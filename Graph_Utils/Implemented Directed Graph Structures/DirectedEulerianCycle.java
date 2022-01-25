package com.Hisham.DirectedGraph;

import java.util.Iterator;
import java.util.Stack;

public class DirectedEulerianCycle {

    private Stack<Integer> cycle = null;

    public DirectedEulerianCycle(Digraph g) {
        if (g.E() == 0) {
            return;
        }
        for (int v = 0; v < g.V(); v++) {
            if (g.indegree(v) != g.outdegree(v)) {
                return;
            }
        }

        Iterator<Integer>[] adj = (Iterator<Integer>[]) new Iterator[g.V()];
        for (int v = 0; v < g.V(); v++) {
            adj[v] = g.adj(v).iterator();
        }

        createCycle(g, adj);
    }


    private void createCycle(Digraph g, Iterator<Integer>[] adj) {
        int s = nonIsolatedVertex(g);
        Stack<Integer> stack = new Stack<>();
        stack.push(s);

        cycle = new Stack<>();
        while (!stack.isEmpty()) {
            int v = stack.pop();
            while (adj[v].hasNext()) {
                stack.push(v);
                v = adj[v].next();
            }
            cycle.push(v);
        }

        if (cycle.size() != g.E() + 1) {
            cycle = null;
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

    public boolean hasEulerianCycle() {
        return cycle != null;
    }

    public Iterable<Integer> cycle() {
        return cycle;
    }
}