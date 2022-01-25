package com.Hisham.DirectedGraph;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Random;

public class AdjMatrixDiGraph {

    private final int V;
    private int E;
    private final boolean[][] adj;

    public AdjMatrixDiGraph(int V){
        this.V=V;
        this.E=0;
        adj=new boolean[this.V][this.V];
    }

    public AdjMatrixDiGraph(int V,int E){
        this(V);
        Random random = new Random();
        while (this.E!=E){
            int v= random.nextInt(V);
            int w= random.nextInt(V);
            addEdge(v,w);
        }
    }

    public int V(){
        return V;
    }

    public int E(){
        return E;
    }

    public void addEdge(int v,int w){   // v -> w
        if(!adj[v][w]) E++;
        adj[v][w]=true;
    }

    public boolean contains(int V,int E){
        return adj[V][E];
    }

    public Iterable<Integer> adj(int v){
        return new AdjIterator(v);
    }

    private class AdjIterator implements Iterator<Integer>,Iterable<Integer>{
        private final int v;
        private int w=0;

        public AdjIterator(int v){
            this.v=v;
        }

        @Override
        public Iterator<Integer> iterator() {
            return this;
        }

        @Override
        public boolean hasNext() {
            while (w<V){
                if(adj[v][w]){
                    return true;
                }
                w++;
            }
            return false;
        }

        @Override
        public Integer next() {
            if(hasNext()){
                return w++;
            }
            throw new NoSuchElementException();
        }

        public void remove(){
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
}