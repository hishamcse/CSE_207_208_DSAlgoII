package com.Hisham;

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

        DirectedCycle directedCycle = new DirectedCycle(digraph);
        if (directedCycle.cycleExists()) {
            System.out.println("No");
        } else {
            System.out.println("Yes");
        }
    }
}