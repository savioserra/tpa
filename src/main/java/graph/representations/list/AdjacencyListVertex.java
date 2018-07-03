package graph.representations.list;

import dictionary.OpenAddressDictionary;
import graph.common.Edge;
import graph.common.Vertex;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

class AdjacencyListVertex<V, E> extends Vertex<V> {
    private OpenAddressDictionary<String, AdjacencyListEdge<V, E>> incomingEdges;
    private OpenAddressDictionary<String, AdjacencyListEdge<V, E>> outgoingEdges;

    public AdjacencyListVertex(int id, String label, V data) {
        super(id, label, data);
        initialize();
    }

    public AdjacencyListVertex(int pos, String label) {
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

    public AdjacencyListEdge<V, E> insertOutgoingEdge(AdjacencyListEdge<V, E> adjacencyListEdge) {
        return outgoingEdges.add(adjacencyListEdge.getLabel(), adjacencyListEdge);
    }

    public AdjacencyListEdge<V, E> insertIncomingEdge(AdjacencyListEdge<V, E> adjacencyListEdge) {
        return incomingEdges.add(adjacencyListEdge.getLabel(), adjacencyListEdge);
    }
}
