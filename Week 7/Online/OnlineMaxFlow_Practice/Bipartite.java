package OnlineMaxFlow_Practice;

import java.util.Stack;

public class Bipartite {

    private final boolean[] traversed;
    private final boolean[] colored;
    private final int[] edgeTo;
    private boolean isBipartite;
    private Stack<Integer> cycle;    // odd length cycle if not bipartite

    public Bipartite(Graph g) {
        isBipartite = true;
        traversed = new boolean[g.totalVertices()];
        edgeTo = new int[g.totalVertices()];
        colored = new boolean[g.totalVertices()];
        for (int v = 0; v < g.totalVertices(); v++) {
            if (!traversed[v]) {
                dfs(g, v);
            }
        }
    }

    private void dfs(Graph g, int v) {
        traversed[v] = true;
        for (int w : g.adjacencyList(v)) {
            if (cycle != null) {
                return;
            }
            if (!traversed[w]) {
                edgeTo[w] = v;
                colored[w] = !colored[v];
                dfs(g, w);
            } else if (colored[w] == colored[v]) {
                isBipartite = false;
                cycle = new Stack<>();
                cycle.push(w);     // it will be twice because we want cycle
                for (int x = v; x != w; x = edgeTo[x]) {
                    cycle.push(x);
                }
                cycle.push(w);
            }
        }
    }

    public boolean isBipartite() {
        return isBipartite;
    }

    public Iterable<Integer> oddCycle() {
        return cycle;
    }

    public boolean color(int v) {
        return colored[v];
    }
}
