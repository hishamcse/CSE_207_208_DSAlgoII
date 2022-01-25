package com.Hisham.Ineffecient;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int m = scanner.nextInt();
        Digraph digraph = new Digraph(n);

        for(int i=0;i<m;i++) {
            digraph.addEdge(scanner.nextInt(),scanner.nextInt());
        }

        Reachability reachability = new Reachability(digraph);
        System.out.println(reachability.sourceResult());
    }
}