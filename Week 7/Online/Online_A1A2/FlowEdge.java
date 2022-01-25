import java.util.Objects;

public class FlowEdge {

    private final int v;
    private final int w;
    private final double capacity;
    private double flow;

    public FlowEdge(int v, int w, double capacity) {
        this.v = v;
        this.w = w;
        this.capacity = capacity;
        this.flow = 0.0;
    }

    public int start() {
        return v;
    }

    public int end() {
        return w;
    }

    public double getCapacity() {
        return capacity;
    }

    public double getFlow() {
        return flow;
    }

    public int oneEnd() {
        return v;
    }

    public int otherEnd(int vertex) {
        return vertex == v ? w : v;
    }

    public double residualCapacity(int vertex) {
        return vertex == v ? flow : capacity - flow;             // backward ?: forward
    }

    public void addResidualFlow(int vertex, double val) {
        flow = vertex == v ? flow - val : flow + val;            // backward ?: forward
    }

    public void printEdge(int lenX) {
        System.out.println("(" + v + "," + (w - lenX) + ")");
    }

    @Override
    public String toString() {
        return "(" + v + "," + w + ")";
    }

    @Override
    public int hashCode() {
        return Objects.hash(v, w, capacity, flow);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FlowEdge flowEdge = (FlowEdge) o;
        return v == flowEdge.v && w == flowEdge.w &&
                Double.compare(flowEdge.capacity, capacity) == 0 && Double.compare(flowEdge.flow, flow) == 0;
    }
}