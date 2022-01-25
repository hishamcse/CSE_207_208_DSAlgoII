//package com.Hisham.UndirectedGraph;
//
//import java.util.ArrayList;
//
//public class SecondBestMST {
//
//    public static void main(String[] args) {
//        FileUtils fileUtils = new FileUtils("./mst.in");
//        int houseHolds = fileUtils.readInt();
//        int roads = fileUtils.readInt();
//
//        WeightedGraph graph = new WeightedGraph(houseHolds);
//        for (int i = 0; i < roads; i++) {
//            graph.addEdge(new WeightedEdge(fileUtils.readInt(), fileUtils.readInt(), fileUtils.readDouble()));
//        }
//
//        double temp = Double.POSITIVE_INFINITY;
//        Iterable<WeightedEdge> answer = new ArrayList<>();
//        Kruskal kruskal = new Kruskal(graph);
//        for(WeightedEdge edge: kruskal.mstEdges()) {
//            WeightedGraph new_graph = graph.excludeEdgeGraph(graph, edge);
//            Kruskal kruskal1 = new Kruskal(new_graph);
//            if((temp > kruskal1.mstWeight()) && (kruskal1.mstWeight() > kruskal.mstWeight())) {
//                temp = kruskal1.mstWeight();
//                answer = kruskal1.mstEdges();
//            }
//        }
//
//        System.out.println(temp);
//        System.out.println(answer);
//    }
//}
