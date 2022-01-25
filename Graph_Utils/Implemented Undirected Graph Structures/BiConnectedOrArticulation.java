package com.Hisham.UndirectedGraph;

public class BiConnectedOrArticulation {

    // low[v] indicates earliest visited vertex reachable from subtree rooted with v
    // pre[v] indicates the discovered time of visit for vertex v

    private final int[] low;
    private final int[] pre;
    private int cnt;
    private final boolean[] articulation;

    public BiConnectedOrArticulation(Graph g) {
        low = new int[g.totalVertices()];
        pre = new int[g.totalVertices()];
        articulation = new boolean[g.totalVertices()];
        cnt = 0;
        for (int v = 0; v < g.totalVertices(); v++) {
            low[v] = -1;
            pre[v] = -1;
        }
        for (int v = 0; v < g.totalVertices(); v++) {
            if (pre[v] == -1) {
                dfs(g, v, v);
            }
        }
    }

    private void dfs(Graph g, int u, int v) {
        int children = 0;
        pre[v] = cnt++;
        low[v] = pre[v];
        for (int w : g.adj(v)) {
            if (pre[w] == -1) {
                children++;
                dfs(g, v, w);

                low[v] = Math.min(low[v], low[w]);

                if (low[w] >= pre[v] && u != v) {
                    articulation[v] = true;
                }

            } else if (w != u) {
                low[v] = Math.min(low[v], pre[w]);
            }
        }
        if (u == v && children > 1) {
            articulation[v] = true;
        }
    }

    public boolean isArticulation(int v) {
        return articulation[v];
    }
}