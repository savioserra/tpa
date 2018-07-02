package graph.representations.list;

import graph.shared.Edge;

public class DirectedListAdjMatrix<V, E> extends GraphAdjacencyList<V, E> {
    @Override
    protected Edge<V, E> findEdgeByVertices(String vertexOrigin, String vertexDestination) {
        for (EdgeListAdj<V, E> edgeListAdj : getEdges().values()) {
            if (edgeListAdj.getOrigin().getLabel().equals(vertexOrigin) &&
                    edgeListAdj.getDestination().getLabel().equals(vertexDestination))
                return edgeListAdj;
        }
        return null;
    }

    @Override
    protected void insertEdgeStrategy(Edge<V, E> edge) {
        EdgeListAdj<V, E> edgeListAdj = (EdgeListAdj<V, E>) edge;

        edgeListAdj.getOrigin().insertOutgoingEdge(edgeListAdj);
        edgeListAdj.getDestination().insertIncomingEdge(edgeListAdj);
    }

    @Override
    protected void removeEdgeStrategy(Edge<V, E> edge) {
        EdgeListAdj<V, E> edgeListAdj = (EdgeListAdj<V, E>) edge;

        edgeListAdj.getOrigin().removeEdge(edge.getLabel());
        edgeListAdj.getDestination().removeEdge(edge.getLabel());
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
