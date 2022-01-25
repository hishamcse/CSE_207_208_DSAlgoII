package com.Hisham.Ineffecient;

public class Reachability {

    private final DirectedDFS[] tc;
    private int res;

    public Reachability(Digraph g) {
        res = -1;
        tc = new DirectedDFS[g.V()];
        for (int v = 0; v < g.V(); v++) {
            tc[v] = new DirectedDFS(g, v);
            if (allReachable(g, v)) {
                res = v;
                return;
            }
        }
    }

    public boolean reachable(int v, int w) {
        return tc[v].marked(w);
    }

    public boolean allReachable(Digraph g, int v) {
        for (int i = 0; i < g.V(); i++) {
            if (!reachable(v, i)) return false;
        }
        return true;
    }

    public int sourceResult() {
        return res;
    }
}