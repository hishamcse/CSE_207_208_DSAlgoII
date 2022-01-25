package com.Hisham.DirectedGraph;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class DepthFirstOrder {   // determine pre, post and reverse post order

    private final boolean[] marked;          // marked[v] = has v been marked in dfs?
    private final int[] pre;                 // pre[v]    = preorder  number of v
    private final int[] post;                // post[v]   = postorder number of v
    private final Queue<Integer> preorder;   // vertices in preorder
    private final Queue<Integer> postorder;  // vertices in postorder
    private int preCounter;                  // counter or preorder numbering
    private int postCounter;                 // counter for postorder numbering

    public DepthFirstOrder(Digraph g) {
        marked = new boolean[g.V()];
        pre = new int[g.V()];
        post = new int[g.V()];
        preCounter = 0;
        postCounter = 0;
        preorder = new LinkedList<>();
        postorder = new LinkedList<>();

        for (int v = 0; v < g.V(); v++) {
            if (!marked[v]) {
                dfs(g, v);
            }
        }
    }

    public DepthFirstOrder(EdgeWeightedDigraph G) {
        pre = new int[G.V()];
        post = new int[G.V()];
        postorder = new LinkedList<>();
        preorder = new LinkedList<>();
        marked = new boolean[G.V()];
        for (int v = 0; v < G.V(); v++)
            if (!marked[v]) dfs(G, v);
    }

    private void dfs(Digraph g, int v) {
        marked[v] = true;
        preorder.add(v);
        pre[v] = preCounter++;
        for (int w : g.adj(v)) {
            if (!marked[w]) {
                dfs(g, w);
            }
        }
        postorder.add(v);
        post[v] = postCounter++;
    }

    private void dfs(EdgeWeightedDigraph g, int v) {
        marked[v] = true;
        preorder.add(v);
        pre[v] = preCounter++;
        for (DirectedEdge e : g.adj(v)) {
            int w = e.to();
            if (!marked[w]) {
                dfs(g, w);
            }
        }
        postorder.add(v);
        post[v] = postCounter++;
    }

    public int pre(int v) {
        return pre[v];
    }

    public int post(int v) {
        return post[v];
    }

    public Iterable<Integer> pre() {
        return preorder;
    }

    public Iterable<Integer> post() {
        return postorder;
    }

    public Iterable<Integer> reversePost() {
        Stack<Integer> reverse = new Stack<>();
        for (int v : postorder) {
            reverse.push(v);
        }
        return reverse;
    }
}