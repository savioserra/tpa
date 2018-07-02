package graph.representations.list;

import dictionary.OpenAddressDictionary;
import graph.shared.Edge;
import graph.shared.Vertex;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class VertexListAdj<V, E> extends Vertex<V> {
    private OpenAddressDictionary<String, EdgeListAdj<V, E>> incomingEdges;
    private OpenAddressDictionary<String, EdgeListAdj<V, E>> outgoingEdges;

    public VertexListAdj(int id, String label, V data) {
        super(id, label, data);
        initialize();
    }

    public VertexListAdj(int pos, String label) {
        super(pos, label);
        initialize();
    }

    public int incomingDegree() {
        return incomingEdges.size();
    }

    public int outgoingDegree() {
        return outgoingEdges.size();
    }

    public int totalDegree() {
        return outgoingDegree() + incomingDegree();
    }

    public List<String> incomingEdges() {
        return incomingEdges.values().stream()
                .map(Edge::getLabel)
                .collect(Collectors.toList());
    }

    public List<String> outgoingEdges() {
        return outgoingEdges.values().stream()
                .map(Edge::getLabel)
                .collect(Collectors.toList());
    }

    public List<String> edges() {
        return Stream.concat(outgoingEdges().stream(), incomingEdges().stream())
                .collect(Collectors.toList());
    }

    private void initialize() {
        incomingEdges = new OpenAddressDictionary<>(10);
        outgoingEdges = new OpenAddressDictionary<>(10);
    }

    public void removeEdge(String edge) {
        incomingEdges.pop(edge);
        outgoingEdges.pop(edge);
    }

    public EdgeListAdj<V, E> insertOutgoingEdge(EdgeListAdj<V, E> edgeListAdj) {
        return outgoingEdges.add(edgeListAdj.getLabel(), edgeListAdj);
    }

    public EdgeListAdj<V, E> insertIncomingEdge(EdgeListAdj<V, E> edgeListAdj) {
        return incomingEdges.add(edgeListAdj.getLabel(), edgeListAdj);
    }
}
