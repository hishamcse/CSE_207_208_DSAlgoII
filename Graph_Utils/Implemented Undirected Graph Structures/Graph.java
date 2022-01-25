package com.Hisham.UndirectedGraph;

import java.util.ArrayList;

// implements Adjacency List Representation for undirected graph
public class Graph {

    private final int totalVertices;
    private int totalEdges;
    private final ArrayList<Integer>[] adjacentLists;

    public Graph(int V) {
        this.totalVertices = V;
        this.totalEdges = 0;
        adjacentLists = (ArrayList<Integer>[]) new ArrayList[V];
        for (int i = 0; i < V; i++) {
            adjacentLists[i] = new ArrayList<>();
        }
    }

    public Graph(Graph g) {
        this.totalVertices = g.totalVertices();
        this.totalEdges = g.totalEdges();
        this.adjacentLists = (ArrayList<Integer>[]) new ArrayList[this.totalVertices];

        for (int i = 0; i < this.totalVertices(); i++) {
            adjacentLists[i] = new ArrayList<>();
        }

        for (int v = 0; v < g.totalVertices(); v++) {
            ArrayList<Integer> list = new ArrayList<>();
            for (Integer w : g.adj(v)) {
                list.add(w);
            }
            for (Integer w : list) {
                adjacentLists[v].add(w);
            }
        }
    }

    private boolean checkInvalidVertex(int v) {
        return v < 0 || v >= totalVertices;
    }

    public void addEdge(int vertex, int other) {
        if (checkInvalidVertex(vertex) || checkInvalidVertex(other)) {
            System.err.println("Invalid Vertex");
            return;
        }
        totalEdges++;
        adjacentLists[vertex].add(other);
        adjacentLists[other].add(vertex);
    }

    public int totalVertices() {
        return totalVertices;
    }

    public int totalEdges() {
        return totalEdges;
    }

    public Iterable<Integer> adj(int v) {
        return adjacentLists[v];
    }

    public int degree(int v) {
        return adjacentLists[v].size();
    }

    public String toString() {
        StringBuilder s = new StringBuilder();
        s.append(totalVertices).append(" vertices, ").append(totalEdges).append(" edges ").append("\n");
        for (int v = 0; v < totalVertices; v++) {
            s.append(v).append(": ");
            for (int w : adjacentLists[v]) {
                s.append(w).append(" ");
            }
            s.append("\n");
        }
        return s.toString();
    }
}