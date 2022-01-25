package com.Hisham.UndirectedGraph;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class EulerianCycle {

    private Stack<Integer> cycle;

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

    public EulerianCycle(Graph g) {
        if (g.totalEdges() == 0) {
            return;
        }
        for (int v = 0; v < g.totalVertices(); v++) {
            if (g.degree(v) % 2 != 0) {
                return;
            }
        }

        Queue<Edge>[] adj = (LinkedList<Edge>[]) new LinkedList[g.totalVertices()];
        createLocalAdjList(g, adj);

        createCycle(g, adj);
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

    private void createCycle(Graph g, Queue<Edge>[] adj) {
        int s = nonIsolatedVertex(g);
        Stack<Integer> stack = new Stack<>();
        stack.push(s);

        cycle = new Stack<>();
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
            cycle.push(v);
        }

        if (cycle.size() != g.totalEdges() + 1) {
            cycle = null;
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

    public boolean hasEulerianCycle() {
        return cycle != null;
    }

    public Iterable<Integer> cycle() {
        return cycle;
    }
}