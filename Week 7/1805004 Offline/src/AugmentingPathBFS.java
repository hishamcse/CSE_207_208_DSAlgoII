import java.util.LinkedList;
import java.util.Queue;

public class AugmentingPathBFS {

    private final int totalVertices;
    private FlowEdge[] edgeTo;
    private boolean[] traversed;

    public AugmentingPathBFS(FlowNetworkGraph graph) {
        totalVertices = graph.totalVertices();
    }

    public boolean augmentingPathExists(FlowNetworkGraph graph, int source, int destination) {
        edgeTo = new FlowEdge[totalVertices];
        traversed = new boolean[totalVertices];

        Queue<Integer> queue = new LinkedList<>();
        queue.add(source);
        traversed[source] = true;

        while (!queue.isEmpty() && !traversed[destination]) {
            int vertex = queue.remove();

            for (FlowEdge edge : graph.adjacencyList(vertex)) {
                int w = edge.otherEnd(vertex);

                if (edge.residualCapacity(w) > 0 && !traversed[w]) {
                    edgeTo[w] = edge;
                    traversed[w] = true;
                    queue.add(w);
                }
            }
        }

        return traversed[destination];
    }

    public FlowEdge edgeTo(int vertex) {
        return edgeTo[vertex];
    }

    public boolean traversed(int vertex) {
        return traversed[vertex];
    }
}