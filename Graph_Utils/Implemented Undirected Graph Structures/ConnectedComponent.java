package com.Hisham.UndirectedGraph;

public class ConnectedComponent {

    private final boolean[] marked;
    private final int[] id;     // the same id indicates same connected component
    private final int[] size;   // number of vertices in a directed component
    private int count;          // number of connected components

    public ConnectedComponent(Graph g) {
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

    public ConnectedComponent(EdgeWeightedGraph g) {
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

    private void dfs(Graph g, int v) {
        id[v] = count;
        size[count]++;
        marked[v] = true;
        for (int w : g.adj(v)) {
            if (!marked[w]) {
                dfs(g, w);
            }
        }
    }

    private void dfs(EdgeWeightedGraph g, int v) {
        id[v] = count;
        size[count]++;
        marked[v] = true;
        for (Edge e : g.adj(v)) {
            int w = e.other(v);
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

    public static void main(String[] args) {
//        In in = new In(StdIn.readLine());
//        Graph G = new Graph(in);
//        ConnectedComponent cc = new ConnectedComponent(G);
//
//        // number of connected components
//        int m = cc.count();
//        StdOut.println(m + " components");
//
//        // compute list of vertices in each connected component
//        Queue<Integer>[] components = (LinkedList<Integer>[]) new Queue[m];
//        for (int i = 0; i < m; i++) {
//            components[i] = new LinkedList<>();
//        }
//        for (int v = 0; v < G.V(); v++) {
//            components[cc.id(v)].add(v);
//        }
//
//        // print results
//        for (int i = 0; i < m; i++) {
//            for (int v : components[i]) {
//                StdOut.print(v + " ");
//            }
//            StdOut.println(" connected component size = " + components[i].size());
//        }
//
//        System.out.println("isConnected(0,4) = " + cc.connected(0, 4));
//        System.out.println("isConnected(1,7) = " + cc.connected(1, 7));
//        System.out.println("size of the connected component(no 1 of element 7) = " + cc.size(7));
    }
}