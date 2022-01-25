import java.util.*;

public class B1B2Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int m = scanner.nextInt();

        WeightedGraph graph = new WeightedGraph(n);

        for (int i = 0; i < m; i++) {
            int a = scanner.nextInt();
            int b = scanner.nextInt();
            int c = scanner.nextInt();
            WeightedEdge edge = new WeightedEdge(a,b,c);
            graph.addEdge(edge);

        }

        ModifiedKruskal kruskal = new ModifiedKruskal(graph);
        System.out.println(kruskal.mstWeight());
        System.out.println(kruskal.mstEdges());
    }
}
