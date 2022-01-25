package com.Hisham.UndirectedGraph;

import java.util.Map;
import java.util.TreeMap;

public class StringGraph {

    private final Map<String, Integer> map;
    private final String[] keys;
    private final Graph graph;

    public StringGraph(String filename) {
        map = new TreeMap<>();
        FileInput fileInput = new FileInput(filename);
        while (!fileInput.isEmpty()) {
            String[] a = fileInput.readLine().split(" ");
            for (String s : a) {
                if (!map.containsKey(s))
                    map.put(s, map.size());
            }
        }

        keys = new String[map.size()];
        for (String s : map.keySet()) {
            keys[map.get(s)] = s;
        }

        graph = new Graph(map.size());
        fileInput = new FileInput(filename);
        while (fileInput.hasNextLine()) {
            String[] a = fileInput.readLine().split(" ");
            int v = map.get(a[0]);
            for (int i = 1; i < a.length; i++) {
                int w = map.get(a[i]);
                graph.addEdge(v, w);
            }
        }
    }

    private void validateVertex(int v) {
        int V = graph.totalVertices();
        if (v < 0 || v >= V)
            throw new IllegalArgumentException("vertex " + v + " is not between 0 and " + (V - 1));
    }

    public boolean contains(String s) {
        return map.containsKey(s);
    }

    public int indexOf(String s) {
        return map.get(s);
    }

    public String key(int v) {
        validateVertex(v);
        return keys[v];
    }

    public Graph graph() {
        return graph;
    }
}