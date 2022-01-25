import java.util.LinkedList;
import java.util.List;

public class OnlineMain {

    public static void main(String[] args) {
        FileUtils fileUtils = new FileUtils("./input.txt");
        int m = fileUtils.readInt();
        int n = fileUtils.readInt();

        int lenX = fileUtils.readInt();
        int lenY = fileUtils.readInt();

        int p = fileUtils.readInt();

        int size = lenX + lenY;

        List<FlowEdge> edgeList = new LinkedList<>();

        FlowNetworkGraph graph = new FlowNetworkGraph(size + 2);
        int source = size;
        int sink = size + 1;

        for (int i = 0; i < p; i++) {
            int x = fileUtils.readInt();
            int y = fileUtils.readInt();

            FlowEdge sx = new FlowEdge(source, x, n);
            FlowEdge yt = new FlowEdge(y + lenX, sink, n);
            FlowEdge xy = new FlowEdge(x, y + lenX, m);

            if (!edgeList.contains(sx)) graph.addEdge(new FlowEdge(source, x, n));
            if (!edgeList.contains(yt)) graph.addEdge(new FlowEdge(y + lenX, sink, n));
            if (!edgeList.contains(xy)) graph.addEdge(new FlowEdge(x, y + lenX, m));

            edgeList.add(sx);
            edgeList.add(yt);
            edgeList.add(xy);
        }

        EdmondsKarp edmondsKarp = new EdmondsKarp(graph, source, sink);
//        System.out.println(edmondsKarp.getMaxFlow());

        List<FlowEdge> list = new LinkedList<>();

        for (int v = 0; v < graph.totalVertices(); v++) {
            if (v == source || v == sink || v >= lenX) continue;
            for (FlowEdge e : graph.adjacencyList(v)) {
                for (int i = 0; i < e.getFlow(); i++) {
                    if (!list.contains(e) && v == e.start()) {
                        e.printEdge(lenX);
                    }
                }
                list.add(e);
            }
        }
    }
}
