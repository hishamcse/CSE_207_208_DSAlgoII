public class Connected {

    private final boolean[] marked;
    private final int[] id;     // the same id indicates same connected component
    private final int[] size;   // number of vertices in a directed component
    private int count;          // number of connected components

    public Connected(WeightedGraph g) {
        count = 0;
        marked = new boolean[g.totalVertices()];
        id = new int[g.totalVertices()];
        size = new int[g.totalVertices()];
        for (int v = 0; v < g.totalVertices(); v++) {
            if (!marked[v]) {
                dfs(g, v);
                count++;
            }
        }
    }

    private void dfs(WeightedGraph g, int v) {
        id[v] = count;
        size[count]++;
        marked[v] = true;
        for (WeightedEdge e : g.adjacencyList(v)) {
            int w = e.otherEnd(v);
            if (!marked[w]) {
                dfs(g, w);
            }
        }
    }

    public int count() {
        return count;
    }

    public int id(int v) {
        return id[v];
    }

    public int size(int v) {
        return size[id[v]];
    }

    public boolean connected(int v, int w) {
        return id[v] == id[w];
    }
}
