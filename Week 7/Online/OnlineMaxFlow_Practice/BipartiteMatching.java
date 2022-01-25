package OnlineMaxFlow_Practice;

import java.util.Scanner;

public class BipartiteMatching {

    private final int totalVertices;
    private final int[] mate;
    private final int cardinality;
    private final boolean[] inMinVertexCover;

    public BipartiteMatching(Graph graph) {
        Bipartite bipartite = new Bipartite(graph);
        if (!bipartite.isBipartite()) {
            throw new IllegalArgumentException("graph is not bipartite");
        }

        totalVertices = graph.totalVertices();
        int source = totalVertices;
        int destination = totalVertices + 1;

        // flowNetwork creation
        FlowNetworkGraph flowNetwork = new FlowNetworkGraph(totalVertices + 2);
        for (int v = 0; v < graph.totalVertices(); v++) {
            for (int w : graph.adjacencyList(v)) {
                if (bipartite.color(v)) {
                    flowNetwork.addEdge(new FlowEdge(v, w, Double.POSITIVE_INFINITY));
                } else {
                    flowNetwork.addEdge(new FlowEdge(w, v, Double.POSITIVE_INFINITY));
                }
            }
        }

        for (int v = 0; v < graph.totalVertices(); v++) {
            if (bipartite.color(v)) {
                flowNetwork.addEdge(new FlowEdge(source, v, 1));
            } else {
                flowNetwork.addEdge(new FlowEdge(v, destination, 1));
            }
        }

        // maxFlow algorithm
        EdmondsKarp maxFlow = new EdmondsKarp(flowNetwork, source, destination);
        cardinality = (int) maxFlow.getMaxFlow();

        // finding the values of mate
        mate = new int[totalVertices];
        for (int v = 0; v < graph.totalVertices(); v++) {
            mate[v] = -1;
            for (FlowEdge e : flowNetwork.adjacencyList(v)) {
                if (e.start() == v && e.getFlow() > 0 && e.end() != destination) {
                    int w = e.otherEnd(v);
                    mate[v] = w;
                    mate[w] = v;
                }
            }
        }

        // whether in min vertex cover or not
        inMinVertexCover = new boolean[totalVertices];
        for (int v = 0; v < graph.totalVertices(); v++) {
            if (bipartite.color(v) && !maxFlow.insideCut(v)) {
                inMinVertexCover[v] = true;
            }
            if (!bipartite.color(v) && maxFlow.insideCut(v)) {
                inMinVertexCover[v] = true;
            }
        }
    }

    public int mate(int v) {
        return mate[v];
    }

    public boolean isMatched(int v) {
        return mate[v] != -1;
    }

    public int size() {
        return cardinality;
    }

    public boolean isPerfect() {
        return cardinality * 2 == totalVertices;
    }

    public boolean inMinVertexCover(int v) {
        return inMinVertexCover[v];
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int d = scanner.nextInt();
        Graph graph = new Graph(n);
        for (int i = 0; i < d; i++) {
            graph.addEdge(scanner.nextInt(), scanner.nextInt());
        }

        BipartiteMatching matching = new BipartiteMatching(graph);

        // print maximum matching
        System.out.printf("Number of edges in max matching        = %d\n", matching.size());
        System.out.printf("Number of vertices in min vertex cover = %d\n", matching.size());
        System.out.printf("Graph has a perfect matching           = %b\n", matching.isPerfect());
        System.out.println();

        System.out.print("Max matching: ");
        for (int v = 0; v < graph.totalVertices(); v++) {
            int w = matching.mate(v);
            if (matching.isMatched(v) && v < w)  // print each edge only once
                System.out.print(v + "-" + w + " ");
        }
        System.out.println();

        // print minimum vertex cover
        System.out.print("Min vertex cover: ");
        for (int v = 0; v < graph.totalVertices(); v++)
            if (matching.inMinVertexCover(v))
                System.out.print(v + " ");
        System.out.println();
    }
}