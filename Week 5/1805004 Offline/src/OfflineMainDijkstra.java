/*
   Name : Syed Jarullah Hisham
   Roll: 1805004
   CSE'18 Section A1
 */

public class OfflineMainDijkstra {

    public static void main(String[] args) {
        FileUtils fileUtils = new FileUtils("./input.txt");
        int n = fileUtils.readInt();
        int m = fileUtils.readInt();

        WeightedDirectedGraph graph = new WeightedDirectedGraph(n);
        for (int i = 0; i < m; i++) {
            graph.addEdge(new WeightedDirectedEdge(fileUtils.readInt(), fileUtils.readInt(), Math.abs(fileUtils.readDouble())));
        }

        int source = fileUtils.readInt();
        int destination = fileUtils.readInt();

        Dijkstra dijkstra = new Dijkstra(graph, source);
        System.out.println(dijkstra.formattedOutput(destination));
    }
}