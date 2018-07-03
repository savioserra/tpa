package graph.representations.list;

import graph.common.Edge;

public class UndirectedGraphAdjacencyList<V, E> extends GraphAdjacencyList<V, E> {
    @Override
    protected Edge<V, E> findEdgeByVertices(String vertexOrigin, String vertexDestination) {
        for (AdjacencyListEdge<V, E> edge : getEdges().values()) {
            if ((edge.getOrigin().getLabel().equals(vertexOrigin) && edge.getDestination().getLabel().equals(vertexDestination)) ||
                    (edge.getOrigin().getLabel().equals(vertexDestination) && edge.getDestination().getLabel().equals(vertexOrigin)))
                return edge;
        }
        return null;
    }

    @Override
    protected void insertEdgeStrategy(Edge<V, E> edge) {
        AdjacencyListEdge<V, E> listEdge = (AdjacencyListEdge<V, E>) edge;

        listEdge.getOrigin().insertOutgoingEdge(listEdge);
        listEdge.getOrigin().insertIncomingEdge(listEdge);

        listEdge.getDestination().insertIncomingEdge(listEdge);
        listEdge.getDestination().insertOutgoingEdge(listEdge);
    }

    @Override
    protected void removeEdgeStrategy(Edge<V, E> edge) {
        AdjacencyListEdge<V, E> listEdge = (AdjacencyListEdge<V, E>) edge;

        listEdge.getOrigin().removeEdge(listEdge.getLabel());
        listEdge.getDestination().removeEdge(listEdge.getLabel());
    }

    @Override
    public String toString() {
        StringBuilder buffer = new StringBuilder();
        for (Edge<V, E> edge : getEdges().values()) {
            String[] vertices = endVertices(edge.getLabel());
            buffer.append(String.format("%s -- %s\n", vertices[0], vertices[1]));
        }

        return buffer.toString();
    }
}
