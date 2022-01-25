import java.util.Stack;

public class WeightedDirectedCycle {

    private final boolean[] visited;
    private final WeightedDirectedEdge[] edgeTo;
    private Stack<WeightedDirectedEdge> cycle;
    private final boolean[] onCycle;

    public WeightedDirectedCycle(WeightedDirectedGraph graph) {
        int vertices = graph.totalVertices();
        visited = new boolean[vertices];
        edgeTo = new WeightedDirectedEdge[vertices];
        onCycle = new boolean[vertices];

        for (int v = 0; v < vertices; v++) {
            if (!visited[v]) {
                dfs(graph, v);
            }
        }
    }

    private void dfs(WeightedDirectedGraph graph, int v) {
        onCycle[v] = true;
        visited[v] = true;
        for (WeightedDirectedEdge e : graph.adjacencyList(v)) {
            int w = e.end();
            if (cycle != null) {
                return;
            } else if (!visited[w]) {
                edgeTo[w] = e;
                dfs(graph, w);
            } else if (onCycle[w]) {
                cycle = new Stack<>();
                WeightedDirectedEdge edge;
                for (edge = e; edge.start() != w; edge = edgeTo[edge.start()]) {
                    cycle.push(edge);
                }
                cycle.push(edge);
                return;
            }
        }
        onCycle[v] = false;
    }

    public boolean hasCycle() {
        return cycle != null;
    }

    public Iterable<WeightedDirectedEdge> cycle() {
        return cycle;
    }
}