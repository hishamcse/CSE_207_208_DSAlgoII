public class Main {

    public static void main(String[] args) {
        FileUtils fileUtils = new FileUtils("./input.txt");
        int n = fileUtils.readInt();
        int m = fileUtils.readInt();

        double[] cost = new double[n];
        for(int i=0;i<n;i++) {
            cost[fileUtils.readInt()] = fileUtils.readDouble();
        }

        WeightedDirectedGraph graph = new WeightedDirectedGraph(n);
        for (int i = 0; i < m; i++) {
            graph.addEdge(new WeightedDirectedEdge(fileUtils.readInt(), fileUtils.readInt(), Math.abs(fileUtils.readDouble())));
        }

        int source = fileUtils.readInt();
        int destination = fileUtils.readInt();

        ModifiedDijkstra dijkstra = new ModifiedDijkstra(graph, source, cost);
        System.out.println(dijkstra.formattedOutput(destination));
    }
}