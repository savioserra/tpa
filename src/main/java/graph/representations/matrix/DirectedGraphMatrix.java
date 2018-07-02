package graph.representations.matrix;

import graph.shared.Edge;

public class DirectedGraphMatrix<V, E> extends GraphMatrix<V, E> {
    public DirectedGraphMatrix() {
        super();
    }

    @Override
    protected Edge<V, E> findEdgeByVertices(String vertexOrigin, String vertexDestination) {
        for (Edge<V, E> edge : getEdges()) {
            if (edge.getOrigin().getLabel().equals(vertexOrigin) && edge.getDestination().getLabel().equals(vertexDestination))
                return edge;
        }
        return null;
    }

    @Override
    protected void insertEdgeStrategy(Edge<V, E> edge) {
        getMatrix()[edge.getOrigin().getPos()][edge.getDestination().getPos()] = edge;
    }

    @Override
    protected void removeEdgeStrategy(Edge<V, E> edge) {
        getMatrix()[edge.getOrigin().getPos()][edge.getDestination().getPos()] = null;
    }

    @Override
    public String toString() {
        StringBuilder buffer = new StringBuilder();
        for (Edge<V, E> edge : getEdges()) {
            String[] vertices = endVertices(edge.getLabel());
            buffer.append(String.format("%s -> %s\n", vertices[0], vertices[1]));
        }

        return buffer.toString();
    }
}