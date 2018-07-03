package graph.representations.list;

import graph.common.Edge;

import java.util.List;

class AdjacencyListEdge<V, E> extends Edge<V, E> {
    public AdjacencyListEdge(String label, AdjacencyListVertex<V, E> origin) {
        super(label, origin);
    }

    public AdjacencyListEdge(String label, AdjacencyListVertex<V, E> origin, AdjacencyListVertex<V, E> destination, E data) {
        super(label, origin, destination, data);
    }

    public AdjacencyListEdge(String label, AdjacencyListVertex<V, E> origin, AdjacencyListVertex<V, E> destination) {
        super(label, origin, destination);
    }

    public List<String> destinationEdges() {
        return ((AdjacencyListVertex<V, E>) getDestination()).outgoingEdges();
    }

    @Override
    public AdjacencyListVertex<V, E> getOrigin() {
        return (AdjacencyListVertex<V, E>) super.getOrigin();
    }

    @Override
    public AdjacencyListVertex<V, E> getDestination() {
        return (AdjacencyListVertex<V, E>) super.getDestination();
    }
}
