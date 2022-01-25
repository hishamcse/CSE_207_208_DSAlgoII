public class TestEdmondsKarp {

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
        System.out.println(edmondsKarp.getMaxFlow());

        for (int v = 0; v < graph.totalVertices(); v++) {
            for (FlowEdge e : graph.adjacencyList(v)) {
                if ((v == e.start()))
                    System.out.println(e);
            }
        }

        System.out.println("Min cut: ");
        for (int v = 0; v < graph.totalVertices(); v++) {
            if (edmondsKarp.insideCut(v)) {
                for(FlowEdge edge: graph.adjacencyList(v)) {
                    if(!edmondsKarp.insideCut(edge.otherEnd(v))) {
                        System.out.println(edge);
                    }
                }
            }
        }
    }
}