public class OfflineMainBellman {

    public static void main(String[] args) {
        FileUtils fileUtils = new FileUtils("./input.txt");
        int n = fileUtils.readInt();
        int m = fileUtils.readInt();

        WeightedDirectedGraph graph = new WeightedDirectedGraph(n);
        for (int i = 0; i < m; i++) {
            graph.addEdge(new WeightedDirectedEdge(fileUtils.readInt(), fileUtils.readInt(), fileUtils.readDouble()));
        }

        int source = fileUtils.readInt();
        int destination = fileUtils.readInt();

        BellmanFord bellmanFord = new BellmanFord(graph, source);
        System.out.println(bellmanFord.formattedOutput(destination));
    }
}
