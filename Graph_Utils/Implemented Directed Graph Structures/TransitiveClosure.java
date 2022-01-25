package com.Hisham.DirectedGraph;

public class TransitiveClosure {

    private final DirectedDFSPaths[] tc;

    public TransitiveClosure(Digraph g) {
        tc = new DirectedDFSPaths[g.V()];
        for (int v = 0; v < g.V(); v++) {
            tc[v] = new DirectedDFSPaths(g, v);
        }
    }

    public boolean reachable(int v, int w) {
        return tc[v].marked(w);
    }
}