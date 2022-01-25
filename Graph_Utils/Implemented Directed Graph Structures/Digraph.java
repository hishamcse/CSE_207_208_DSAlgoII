package com.Hisham.DirectedGraph;

import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class Digraph {

    private final int V;
    private int E;
    private final ArrayList<Integer>[] adj;
    private final int[] indegree;

    public Digraph(int V) {
        this.V = V;
        this.E = 0;
        indegree = new int[V];
        adj = (ArrayList<Integer>[]) new ArrayList[V];
        for (int i = 0; i < V; i++) {
            adj[i] = new ArrayList<>();
        }
    }

    public Digraph(FileInput in) {
        try {
            this.V = in.readInt();
            this.adj = (ArrayList<Integer>[]) new ArrayList[this.V];
            this.indegree = new int[this.V];
            for (int i = 0; i < this.V; i++) {
                this.adj[i] = new ArrayList<>();
            }
            int E = in.readInt();
            for (int i = 0; i < E; i++) {
                int v = in.readInt();
                int w = in.readInt();
                addEdge(v, w);
            }
        } catch (NoSuchElementException e) {
            throw new IllegalArgumentException();
        }
    }

    public void addEdge(int v, int w) {     // v -> w
        E++;
        adj[v].add(w);
        indegree[w]++;
    }

    public int V() {
        return V;
    }

    public int E() {
        return E;
    }

    public Iterable<Integer> adj(int v) {
        return adj[v];
    }

    public int outdegree(int v) {
        return adj[v].size();
    }

    public int indegree(int v) {
        return indegree[v];
    }

    public Digraph reverse() {
        Digraph reverse = new Digraph(V);
        for (int v = 0; v < V; v++) {
            for (int w : adj(v)) {
                reverse.addEdge(w, v);
            }
        }
        return reverse;
    }

    public String toString() {
        StringBuilder s = new StringBuilder();
        s.append(V).append(" vertices, ").append(E).append(" edges ").append("\n");
        for (int v = 0; v < V; v++) {
            s.append(v).append(": ");
            for (int w : adj[v]) {
                s.append(w).append(" ");
            }
            s.append("\n");
        }
        return s.toString();
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        FileInput in = new FileInput(scanner.next());
        Digraph G = new Digraph(in);
        System.out.println(G);

        for (int v = 0; v < G.V(); v++) {
            for (int w : G.adj(v)) {
                System.out.println(v + " -> " + w);
            }
        }
    }
}