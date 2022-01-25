package com.Hisham.Effecient;

public class TopologicalSort {

    private final int source;

    public TopologicalSort(Digraph g) {
        DirectedDFS d = new DirectedDFS(g);
        source = d.reversePost().peek();
    }

    public int source() {
        return source;
    }
}