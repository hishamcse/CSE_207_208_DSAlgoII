package com.Hisham.DirectedGraph;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class DirectedBFSPaths {   //for both single and multiple sources

    private final boolean[] marked;
    private final int[] edgeTo;
    private final int[] distTo;   // number of edges shortest s-v path

    public DirectedBFSPaths(Digraph g, int s) {
        marked = new boolean[g.V()];
        edgeTo = new int[g.V()];
        distTo = new int[g.V()];
        bfs(g, s);
    }

    public DirectedBFSPaths(Digraph g, Iterable<Integer> sources) {
        marked = new boolean[g.V()];
        edgeTo = new int[g.V()];
        distTo = new int[g.V()];
        for (int v = 0; v < g.V(); v++) {
            distTo[v] = Integer.MAX_VALUE;
        }
        bfs(g, sources);
    }

    private void bfs(Digraph g, int s) {
        Queue<Integer> queue = new LinkedList<>();
        for (int v = 0; v < g.V(); v++) {
            distTo[v] = Integer.MAX_VALUE;
        }
        queue.add(s);
        marked[s] = true;
        distTo[s] = 0;
        while (!queue.isEmpty()) {
            int v = queue.remove();
            for (int w : g.adj(v)) {
                if (!marked[w]) {
                    queue.add(w);
                    marked[w] = true;
                    edgeTo[w] = v;
                    distTo[w] = distTo[v] + 1;
                }
            }
        }
    }

    private void bfs(Digraph g, Iterable<Integer> sources) {
        Queue<Integer> queue = new LinkedList<>();
        for (int s : sources) {
            marked[s] = true;
            distTo[s] = 0;
            queue.add(s);
        }
        while (!queue.isEmpty()) {
            int v = queue.remove();
            for (int w : g.adj(v)) {
                if (!marked[w]) {
                    queue.add(w);
                    marked[w] = true;
                    edgeTo[w] = v;
                    distTo[w] = distTo[v] + 1;
                }
            }
        }
    }

    public boolean hasPathTo(int v) {   // has shortest path??
        return marked[v];
    }

    public int distTo(int v) {   // shortest path length for vertex v
        return distTo[v];
    }

    public Iterable<Integer> pathTo(int v) {
        if (!hasPathTo(v)) {
            return null;
        }
        Stack<Integer> stack = new Stack<>();
        int x;
        for (x = v; distTo(x) != 0; x = edgeTo[x]) {
            stack.push(x);
        }
        stack.push(x);
        return stack;
    }
}