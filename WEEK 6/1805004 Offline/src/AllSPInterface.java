public interface AllSPInterface {

    double shortestPathWeight(int source, int destination);

    Iterable<WeightedDirectedEdge> shortestPath(int source, int destination);

    String formattedOutput();
}
