/*
 Implementation of disjoint set using union by rank with path compression
 Amortized Complexity: O(log*n)
 */

public class DisjointSet {

    private final int[] predecessor;
    private final int[] rank;
    private int componentCount;

    public DisjointSet(int total) {
        componentCount = total;
        predecessor = new int[total];
        rank = new int[total];

        for (int i = 0; i < total; i++) {
            predecessor[i] = i;
        }
    }

    // path compression
    private int findRoot(int v) {
        while (v != predecessor[v]) {
            predecessor[v] = predecessor[predecessor[v]];
            v = predecessor[v];
        }
        return v;
    }

    // union by rank
    public void union(int v, int w) {
        int v_root = findRoot(v);
        int w_root = findRoot(w);
        if (v_root == w_root) return;

        if (rank[v_root] < rank[w_root]) {
            predecessor[v_root] = w_root;
        } else if (rank[v_root] > rank[w_root]) {
            predecessor[w_root] = v_root;
        } else {
            predecessor[w_root] = v_root;
            rank[v_root]++;
        }

        componentCount--;
    }

    public boolean sameComponent(int v, int w) {
        return findRoot(v) == findRoot(w);
    }

    public int totalComponent() {
        return componentCount;
    }
}