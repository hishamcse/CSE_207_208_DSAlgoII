// Single Source Shortest Path Interface

import java.util.Map;

public interface SingleSPInterface {

    double shortestPathWeight(int destination);

    Iterable<WeightedDirectedEdge> shortestPath(int v);

    String formattedOutput(Map<Integer, String> map, int destination);
}
