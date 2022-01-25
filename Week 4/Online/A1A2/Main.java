import java.util.*;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int m = scanner.nextInt();

        WeightedGraph graph_Men = new WeightedGraph(n);
        WeightedGraph graph_Women = new WeightedGraph(n);

        int common = 0;

        for (int i = 0; i < m; i++) {
            int a = scanner.nextInt();
            int b = scanner.nextInt();
            int c = scanner.nextInt();
            WeightedEdge edge = new WeightedEdge(a - 1, b - 1, c);
            if (c == 3) {
                common++;
            }
            if (c == 2 || c == 3) {
                graph_Women.addEdge(edge);
            }
            if (c == 1 || c == 3) {
                graph_Men.addEdge(edge);
            }
        }

        ModifiedKruskal kruskal_men = new ModifiedKruskal(graph_Men);
        ModifiedKruskal kruskal_women = new ModifiedKruskal(graph_Women);

        if (kruskal_men.count() < n - 1 || kruskal_women.count() < n - 1) {
            System.out.println(-1);
            return;
        }

        System.out.println(m - kruskal_men.count() - kruskal_women.count() + common);
    }
}
