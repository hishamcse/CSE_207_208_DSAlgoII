package com.Hisham.UndirectedGraph;

public class DiameterCenter {

    private int eccentricity;

    public int farthest(Graph g, int v) {
        BreadthFirstPaths bfs = new BreadthFirstPaths(g, v);
        int max = 0;
        int len = 0;
        for (int i = 1; i < g.totalVertices(); i++) {
            if (bfs.distTo(i) > len) {
                max = i;
                len = bfs.distTo(i);
            }
        }
        return max;
    }

    public void diameter(Graph g) {
        int max = farthest(g, 0);
        BreadthFirstPaths bfs = new BreadthFirstPaths(g, max);
        int end = farthest(g, max);

        for (int w : bfs.pathTo(end)) {
            System.out.println(w);
        }
        eccentricity = bfs.distTo(end);
    }

    public int center(Graph g) {
        int start = farthest(g, 0);
        int end = farthest(g, start);
        BreadthFirstPaths bfs1 = new BreadthFirstPaths(g, start);
        BreadthFirstPaths bfs2 = new BreadthFirstPaths(g, end);
        int res = start;

        for (int i = 0; i < g.totalVertices(); i++) {
            if (bfs1.distTo(i) == bfs2.distTo(i) || bfs1.distTo(i) == bfs2.distTo(i) + 1) {
                res = i;
            }
        }
        return res;
    }

    // extra credit
    public int radius(Graph g) {
        int len = 0;
        for (int v = 0; v < g.totalVertices(); v++) {
            BreadthFirstPaths bfs = new BreadthFirstPaths(g, v);
            for (int w = 0; w < g.totalVertices(); w++) {
                if (w != v && (bfs.distTo(w) < len || len == 0)) {
                    len = bfs.distTo(w);
                }
            }
        }
        return len;
    }

    public int eccentricity() {
        return eccentricity;
    }

    public static void main(String[] args) {
        //test 1
        Graph graph = new Graph(10);
        graph.addEdge(0, 1);
        graph.addEdge(1, 2);
        graph.addEdge(2, 3);
        graph.addEdge(2, 9);
        graph.addEdge(2, 4);
        graph.addEdge(4, 5);
        graph.addEdge(1, 6);
        graph.addEdge(6, 7);
        graph.addEdge(6, 8);

        DiameterCenter diameterCenter = new DiameterCenter();
        System.out.println("Center = " + diameterCenter.center(graph));
        System.out.println("Diameter = ");
        diameterCenter.diameter(graph);
        System.out.println("Radius = " + diameterCenter.radius(graph));
        System.out.println("Eccentricity = " + diameterCenter.eccentricity());
    }
}