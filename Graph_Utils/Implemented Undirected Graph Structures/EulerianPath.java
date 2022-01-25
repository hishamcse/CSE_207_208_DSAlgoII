package com.Hisham.UndirectedGraph;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class EulerianPath {

    private Stack<Integer> path;

    private static class Edge {
        private final int v;
        private final int w;
        private boolean isUsed;

        public Edge(int v, int w) {
            this.v = v;
            this.w = w;
            isUsed = false;
        }

        public int other(int s) {
            if (s == v) {
                return w;
            } else if (s == w) {
                return v;
            } else {
                throw new IllegalArgumentException("argument illegal");
            }
        }
    }

    public EulerianPath(Graph g) {
        int odd = 0;
        int s = nonIsolatedVertex(g);
        for (int v = 0; v < g.totalVertices(); v++) {
            if (g.degree(v) % 2 != 0) {
                odd++;
                s = v;      // because we need an odd length vertex to start the eulerian path
            }
        }

        if (odd > 2) {
            return;
        }
        if (s == -1) {     // if there is no vertex having at least one edge
            s = 0;
        }

        Queue<Edge>[] adj = (LinkedList<Edge>[]) new LinkedList[g.totalVertices()];
        createLocalAdjList(g, adj);

        createPath(g, adj, s);
    }

    // create local view of adjacency lists, to iterate one vertex at a time
    // the helper Edge data type is used to avoid exploring both copies of an edge v-w
    private void createLocalAdjList(Graph g, Queue<Edge>[] adj) {
        for (int v = 0; v < g.totalVertices(); v++) {
            adj[v] = new LinkedList<>();
        }
        for (int v = 0; v < g.totalVertices(); v++) {
            int selfLoops = 0;
            for (int w : g.adj(v)) {
                if (v == w) {
                    if (selfLoops % 2 == 0) {
                        Edge e = new Edge(v, w);
                        adj[v].add(e);
                        adj[w].add(e);
                    }
                    selfLoops++;
                } else if (v < w) {
                    Edge e = new Edge(v, w);
                    adj[v].add(e);
                    adj[w].add(e);
                }
            }
        }
    }

    private void createPath(Graph g, Queue<Edge>[] adj, int s) {
        Stack<Integer> stack = new Stack<>();
        stack.push(s);

        path = new Stack<>();
        while (!stack.isEmpty()) {
            int v = stack.pop();
            while (!adj[v].isEmpty()) {
                Edge edge = adj[v].remove();
                if (edge.isUsed) {
                    continue;
                }
                edge.isUsed = true;
                stack.push(v);
                v = edge.other(v);
            }
            path.push(v);
        }

        if (path.size() != g.totalEdges() + 1) {
            path = null;
        }
    }

    private int nonIsolatedVertex(Graph g) {
        for (int v = 0; v < g.totalVertices(); v++) {
            if (g.degree(v) > 0) {
                return v;
            }
        }
        return -1;
    }

    public boolean hasEulerianPath() {
        return path != null;
    }

    public Iterable<Integer> path() {
        return path;
    }
}