// Minimum spanning tree interface

public interface MSTInterface {
    double mstWeight();

    Iterable<WeightedEdge> mstEdges();

    default String formattedOutput(String str) {
        StringBuilder sb = new StringBuilder();
        sb.append("List of edges selected by ").append(str).append("'s: {");
        for (WeightedEdge edge : mstEdges()) {
            sb.append(edge).append(',');
        }
        sb.deleteCharAt(sb.length() - 1).append("}");
        return sb.toString();
    }
}
