/*
   Name : Syed Jarullah Hisham
   Roll: 1805004
   CSE'18 Section A1
 */

public class OfflineMain {

    private static final double EPSILON = 10e-4;

    public static void main(String[] args) {
        FileUtils fileUtils = new FileUtils("./in5.txt");
        int houseHolds = fileUtils.readInt();
        int roads = fileUtils.readInt();

        WeightedGraph graph = new WeightedGraph(houseHolds);
        for (int i = 0; i < roads; i++) {
            graph.addEdge(new WeightedEdge(fileUtils.readInt(), fileUtils.readInt(), fileUtils.readDouble()));
        }

        Prim prim = new Prim(graph);
        Kruskal kruskal = new Kruskal(graph);

        if (Math.abs(prim.mstWeight() - kruskal.mstWeight()) >= EPSILON) {
            throw new Error("kruskal: "+kruskal.mstWeight()+" and Prim: "+prim.mstWeight());
        }

        System.out.println("Cost of the minimum spanning tree: " + prim.mstWeight());
        System.out.println(prim.formattedOutput("Prim"));
        System.out.println(kruskal.formattedOutput("Kruskal"));
    }
}