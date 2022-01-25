import java.util.HashMap;
import java.util.Map;

public class Main {

    public static void main(String[] args) {
        FileUtils fileUtils = new FileUtils("./input.txt");
        int n = fileUtils.readInt();
        int m = fileUtils.readInt();

        int c = 0;
        Map<String, Integer> strInt = new HashMap<>();
        Map<Integer, String> intStr = new HashMap<>();
        double[] cost = new double[n];
        for(int i=0;i<n;i++) {
            String str = fileUtils.readString();
            intStr.put(c, str);
            strInt.put(str, c);
            cost[c] = fileUtils.readDouble();
            c++;
        }

        WeightedDirectedGraph graph = new WeightedDirectedGraph(n);
        for (int i = 0; i < m; i++) {
            int v = strInt.get(fileUtils.readString());
            int w = strInt.get(fileUtils.readString());
            double weight = fileUtils.readDouble();
            graph.addEdge(new WeightedDirectedEdge(v, w, Math.abs(weight)));
            graph.addEdge(new WeightedDirectedEdge(w, v, Math.abs(weight)));     // bidirectional
        }

        int source = strInt.get(fileUtils.readString());
        int destination = strInt.get(fileUtils.readString());

        ModifiedDijkstra dijkstra = new ModifiedDijkstra(graph, source, cost);
        System.out.println(dijkstra.formattedOutput(intStr, destination));
    }
}