package graph.representations.Matrix;

import graph.shared.Edge;

import java.util.List;

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
    protected void InsertMatrizEdge(Edge<V, E> edge) {
        getMatrix()[edge.getOrigin().getPos()][edge.getDestination().getPos()] = edge;
    }

    @Override
    protected void RemoveMatrizEdge(Edge<V, E> edge) {
        getMatrix()[edge.getOrigin().getPos()][edge.getDestination().getPos()] = null;
    }

    @Override
    public String toString() {
        StringBuilder buffer = new StringBuilder();
        for (Edge<V, E> edge : getEdges()) {
            List<String> vertices = endVertices(edge.getLabel());
            buffer.append(String.format("%s -> %s\n", vertices.get(0), vertices.get(1)));
        }

        return buffer.toString();
    }
}