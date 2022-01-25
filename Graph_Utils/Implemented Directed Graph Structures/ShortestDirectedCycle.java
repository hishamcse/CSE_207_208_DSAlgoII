package com.Hisham.DirectedGraph;

import java.util.Stack;

public class ShortestDirectedCycle {

    private Stack<Integer> cycle;
    private int length;

    public ShortestDirectedCycle(Digraph g) {
        Digraph r = g.reverse();
        length = g.V() + 1;
        for (int v = 0; v < g.V(); v++) {
            DirectedBFSPaths bfs = new DirectedBFSPaths(r, v);
            // this should be reverse & iterate should be original.So,it will be guaranted that
            // there exists a cycle. v -> w && w -> v

            for (int w : g.adj(v)) {
                if (bfs.hasPathTo(w) && bfs.distTo(w) + 1 < length) {
                    length = bfs.distTo(w) + 1;
                    cycle = new Stack<>();
                    for (int t : bfs.pathTo(w)) {
                        cycle.push(t);
                    }
                    cycle.push(v);
                }
            }
        }
    }

    public boolean hasCycle() {
        return cycle != null;
    }

    public Iterable<Integer> cycle() {
        return cycle;
    }

    public int cycleLength() {
        return length;
    }
}
