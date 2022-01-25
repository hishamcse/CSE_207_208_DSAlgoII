package com.Hisham.UndirectedGraph;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Random;
import java.util.Scanner;

public class AdjMatrixGraph {

    private final int V;
    private int E;
    private final boolean[][] adj;

    public AdjMatrixGraph(int V) {
        this.V = V;
        this.E = 0;
        adj = new boolean[this.V][this.V];
    }

    public AdjMatrixGraph(int V, int E) {
        this(V);

        Random random = new Random();
        while (this.E != E) {
            int v = random.nextInt(V);
            int w = random.nextInt(V);
            addEdge(v, w);
        }
    }

    public int Vertices() {
        return V;
    }

    public int Edges() {
        return E;
    }

    public void addEdge(int v, int w) {
        if (!adj[v][w]) E++;
        adj[v][w] = true;
        adj[w][v] = true;
    }

    public boolean contains(int V, int E) {
        return adj[V][E];
    }

    public Iterable<Integer> adj(int v) {
        return new AdjIterator(v);
    }

    private class AdjIterator implements Iterator<Integer>, Iterable<Integer> {
        private final int v;
        private int w = 0;

        public AdjIterator(int v) {
            this.v = v;
        }

        @Override
        public Iterator<Integer> iterator() {
            return this;
        }

        @Override
        public boolean hasNext() {
            while (w < V) {
                if (adj[v][w]) {
                    return true;
                }
                w++;
            }
            return false;
        }

        @Override
        public Integer next() {
            if (hasNext()) {
                return w++;
            }
            throw new NoSuchElementException();
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }
    }

    public String toString() {
        StringBuilder s = new StringBuilder();
        s.append(V).append(" ").append(E);
        s.append("\n");
        for (int v = 0; v < V; v++) {
            s.append(v).append(": ");
            for (int w : adj(v)) {
                s.append(w).append(" ");
            }
            s.append("\n");
        }
        return s.toString();
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int V = scanner.nextInt();
        int E = scanner.nextInt();
        AdjMatrixGraph G = new AdjMatrixGraph(V, E);
        System.out.println(G);
        System.out.println(G.Vertices());
        System.out.println(G.Edges());
        System.out.println(G.contains(1, 7));
    }
}