// Single Source Shortest Path Interface

public interface SingleSPInterface {

    double shortestPathWeight(int destination);

    Iterable<WeightedDirectedEdge> shortestPath(int v);

    String formattedOutput(int destination);
}
