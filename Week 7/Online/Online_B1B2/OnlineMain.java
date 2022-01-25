public class OnlineMain {

    public static void main(String[] args) {
        FileUtils fileUtils = new FileUtils("./input.txt");
        int n = fileUtils.readInt();
        int m = fileUtils.readInt();

        FlowNetworkGraph graph = new FlowNetworkGraph(n);
        for (int i = 0; i < m; i++) {
            graph.addEdge(new FlowEdge(fileUtils.readInt(), fileUtils.readInt(), fileUtils.readDouble()));
        }

        int s = fileUtils.readInt();
        int t = fileUtils.readInt();

        EdmondsKarp edmondsKarp = new EdmondsKarp(graph, s, t);
//        System.out.println(edmondsKarp.getMaxFlow());

        System.out.println("Min cut: ");
        for (int v = 0; v < graph.totalVertices(); v++) {
            if (edmondsKarp.insideCut(v)) {
                for(FlowEdge edge: graph.adjacencyList(v)) {
                    if(!edmondsKarp.insideCut(edge.end())) {
                        System.out.println(edge);
                    }
                }
            }
        }
    }
}