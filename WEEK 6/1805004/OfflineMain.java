/*
   Name : Syed Jarullah Hisham
   Roll: 1805004
   CSE'18 Section A1
 */

public class OfflineMain {

    public static void main(String[] args) {
//        FileUtils fileUtils = new FileUtils("./input.txt");
//        int n = fileUtils.readInt();
//        int m = fileUtils.readInt();
//
//        WeightedDirectedGraph graph = new WeightedDirectedGraph(n);
//        for (int i = 0; i < m; i++) {
//            graph.addEdge(new WeightedDirectedEdge(fileUtils.readInt() - 1, fileUtils.readInt() - 1, fileUtils.readDouble()));
//        }
//
//        Johnson johnson = new Johnson(graph);
//        System.out.println(johnson.formattedOutput());

        FileUtils fileUtils = new FileUtils("./input.txt");
        int n = fileUtils.readInt();
        int m = fileUtils.readInt();

        WeightedMatrixGraph graph = new WeightedMatrixGraph(n);
        for (int i = 0; i < m; i++) {
            graph.addEdge(new WeightedDirectedEdge(fileUtils.readInt() - 1, fileUtils.readInt() - 1, fileUtils.readDouble()));
        }

        FloydWarshall floydWarshall = new FloydWarshall(graph);
        System.out.println(floydWarshall.formattedOutput());
    }
}
