import java.util.Objects;

public class WeightedDirectedEdge implements Comparable<WeightedDirectedEdge> {

    private final int v;
    private final int w;
    private double weight;

    public WeightedDirectedEdge(int v, int w, double weight) {
        if (v < 0 || w < 0) throw new IllegalArgumentException("Negative indexed vertex");
        this.v = v;
        this.w = w;
        this.weight = weight;
    }

    public int start() {
        return v;
    }

    public int end() {
        return w;
    }

    public void setWeight(double weight) {      // for johnson algorithm
        this.weight = weight;
    }

    public double getWeight() {
        return weight;
    }

    @Override
    public int compareTo(WeightedDirectedEdge o) {
        return Double.compare(this.weight, o.weight);
    }

    @Override
    public String toString() {
        return v + "->" + w;
    }

    @Override
    public int hashCode() {
        return Objects.hash(v, w, weight);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        WeightedDirectedEdge edge = (WeightedDirectedEdge) o;
        return v == edge.v && w == edge.w && Double.compare(weight, edge.weight) == 0;
    }
}