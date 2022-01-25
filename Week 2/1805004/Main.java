import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int d = scanner.nextInt();
        DirectedGraph graph = new DirectedGraph(n, d);
        for (int i = 0; i < d; i++) {
            graph.addEdge(scanner.nextInt(), scanner.nextInt());
        }

        DFS finder = new DFS(graph);
        if (!finder.hasCycle()) {
            Iterable<Integer> order = finder.reversePost();
            for (int v : order) {
                System.out.print(v + " ");
            }
        } else {
            System.out.println("Not Possible");
        }
    }
}