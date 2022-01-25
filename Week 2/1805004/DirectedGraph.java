import java.util.ArrayList;

public class DirectedGraph {

    private final int V;
    private final int E;
    private final ArrayList<Integer>[] adj;

    public DirectedGraph(int V, int E) {
        this.V = V;
        this.E = E;
        adj = (ArrayList<Integer>[]) new ArrayList[V];
        for (int i = 0; i < V; i++) {
            adj[i] = new ArrayList<>();
        }
    }

    public void addEdge(int v, int w) {     // v -> w
        adj[v-1].add(w-1);
    }

    public int V() {
        return V;
    }

    public int E() {
        return E;
    }

    public Iterable<Integer> adj(int v) {
        return adj[v];
    }
}