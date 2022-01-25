package com.Hisham.Effecient;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int m = scanner.nextInt();
        Digraph digraph = new Digraph(n);

        for (int i = 0; i < m; i++) {
            digraph.addEdge(scanner.nextInt(), scanner.nextInt());
        }

        TopologicalSort topologicalSort = new TopologicalSort(digraph);
        DirectedDFS directedDFS = new DirectedDFS(digraph, topologicalSort.source());

        boolean flag = true;
        for (int i = 0; i < digraph.V(); i++) {
            if (!directedDFS.marked(i)) {
                flag = false;
                break;
            }
        }

        if (!flag) {
            System.out.println(-1);
        } else {
            System.out.println(topologicalSort.source());
        }
    }
}