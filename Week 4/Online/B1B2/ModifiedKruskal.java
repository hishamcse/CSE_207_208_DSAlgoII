import java.util.LinkedList;
import java.util.Queue;

public class ModifiedKruskal implements MSTInterface {

    private final Queue<WeightedEdge> mstEdges;
    private double mstWeight;

    public ModifiedKruskal(WeightedGraph graph) {
        mstEdges = new LinkedList<>();
        mstWeight = 1;

        CustomMinPQ<WeightedEdge> priorityQueue = new CustomMinPQ<>(graph.totalEdges());
        populatePriorityQueue(graph, priorityQueue);

        DisjointSet disjointSet = new DisjointSet(graph.totalVertices());
        calculateMST(graph, priorityQueue, disjointSet);
    }

    private void populatePriorityQueue(WeightedGraph graph, CustomMinPQ<WeightedEdge> minPQ) {
        int i = 0;
        for (WeightedEdge edge : graph.edges()) {
            minPQ.insert(i++, edge);
        }
    }

    private void calculateMST(WeightedGraph graph, CustomMinPQ<WeightedEdge> minPQ, DisjointSet disjointSet) {
        while (!minPQ.isEmpty() && mstEdges.size() < graph.totalVertices() - 1) {
            WeightedEdge edge = minPQ.deleteMinKey();
            int v = edge.oneEnd();
            int w = edge.otherEnd(v);

            if (!disjointSet.sameComponent(v, w)) {
                mstEdges.add(edge);
                disjointSet.union(v, w);
                mstWeight *= edge.getWeight();
            }
        }
    }

    @Override
    public Iterable<WeightedEdge> mstEdges() {
        return mstEdges;
    }

    @Override
    public double mstWeight() {
        return mstWeight;
    }

    public int size() {
        return mstEdges.size();
    }
}