package graph.representations.list;

import graph.shared.Edge;

import java.util.List;

public class EdgeListAdj<V, E> extends Edge<V, E> {
    public EdgeListAdj(String label, VertexListAdj<V, E> origin) {
        super(label, origin);
    }

    public EdgeListAdj(String label, VertexListAdj<V, E> origin, VertexListAdj<V, E> destination, E data) {
        super(label, origin, destination, data);
    }

    public EdgeListAdj(String label, VertexListAdj<V, E> origin, VertexListAdj<V, E> destination) {
        super(label, origin, destination);
    }

    public List<String> destinationEdges() {
        return ((VertexListAdj<V, E>) getDestination()).outgoingEdges();
    }

    @Override
    public VertexListAdj<V, E> getOrigin() {
        return (VertexListAdj<V, E>) super.getOrigin();
    }

    @Override
    public VertexListAdj<V, E> getDestination() {
        return (VertexListAdj<V, E>) super.getDestination();
    }
}
