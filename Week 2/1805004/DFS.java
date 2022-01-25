import java.util.Stack;

public class DFS {

    private final boolean[] marked;
    private boolean cycleExists;
    private final boolean[] onCycle;
    private final Stack<Integer> postorder;  // vertices in postorder

    public DFS(DirectedGraph g) {
        marked = new boolean[g.V()];
        onCycle = new boolean[g.V()];
        postorder = new Stack<>();
        cycleExists = false;

        for (int v = 0; v < g.V(); v++) {
            if (!cycleExists && !marked[v]) {
                dfs(g, v);
            }
        }
    }

    private void dfs(DirectedGraph g, int v) {
        onCycle[v] = true;
        marked[v] = true;
        for (int w : g.adj(v)) {
            if (cycleExists) {
                return;
            } else if (!marked[w]) {
                dfs(g, w);
            } else if (onCycle[w]) {
                cycleExists = true;
            }
        }
        onCycle[v] = false;
        postorder.add(v + 1);
    }

    public boolean hasCycle() {
        return cycleExists;
    }

    public Iterable<Integer> reversePost() {
        return postorder;
    }
}