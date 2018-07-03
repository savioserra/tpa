package graph.representations.list;

import graph.common.Edge;

public class DirectedGraphAdjacencyList<V, E> extends GraphAdjacencyList<V, E> {
    @Override
    protected Edge<V, E> findEdgeByVertices(String vertexOrigin, String vertexDestination) {
        for (AdjacencyListEdge<V, E> adjacencyListEdge : getEdges().values()) {
            if (adjacencyListEdge.getOrigin().getLabel().equals(vertexOrigin) &&
                    adjacencyListEdge.getDestination().getLabel().equals(vertexDestination))
                return adjacencyListEdge;
        }
        return null;
    }

    @Override
    protected void insertEdgeStrategy(Edge<V, E> edge) {
        AdjacencyListEdge<V, E> adjacencyListEdge = (AdjacencyListEdge<V, E>) edge;

        adjacencyListEdge.getOrigin().insertOutgoingEdge(adjacencyListEdge);
        adjacencyListEdge.getDestination().insertIncomingEdge(adjacencyListEdge);
    }

    @Override
    protected void removeEdgeStrategy(Edge<V, E> edge) {
        AdjacencyListEdge<V, E> adjacencyListEdge = (AdjacencyListEdge<V, E>) edge;

        adjacencyListEdge.getOrigin().removeEdge(edge.getLabel());
        adjacencyListEdge.getDestination().removeEdge(edge.getLabel());
    }

    @Override
    public String toString() {
        StringBuilder buffer = new StringBuilder();
        for (Edge<V, E> edge : getEdges().values()) {
            String[] vertices = endVertices(edge.getLabel());
            buffer.append(String.format("%s -> %s\n", vertices[0], vertices[1]));
        }

        return buffer.toString();
    }
}
