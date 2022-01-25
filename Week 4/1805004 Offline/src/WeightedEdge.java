public class WeightedEdge implements Comparable<WeightedEdge> {

    private final int v;
    private final int w;
    private final double weight;

    public WeightedEdge(int v, int w, double weight) {
        if (v < 0 || w < 0) throw new IllegalArgumentException("Negative indexed vertex");
        this.v = v;
        this.w = w;
        this.weight = weight;
    }

    public int oneEnd() {
        return v;
    }

    public int otherEnd(int vertex) {
        return vertex == v ? w : v;
    }

    public double getWeight() {
        return weight;
    }

    @Override
    public int compareTo(WeightedEdge o) {
        return Double.compare(this.weight, o.weight);
    }

    @Override
    public String toString() {
        return "(" + v + ',' + w + ')';
    }
}