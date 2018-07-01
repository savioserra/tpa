package graph.representations.Matrix;

import graph.shared.Edge;

import java.util.List;

public class UndirectedGraphMatrix<V, E> extends GraphMatrix<V, E> {
    public UndirectedGraphMatrix() {
        super();
    }

    @Override
    protected void InsertMatrizEdge(Edge<V, E> edge) {
        getMatrix()[edge.getOrigin().getPos()][edge.getDestination().getPos()] = edge;
        getMatrix()[edge.getDestination().getPos()][edge.getOrigin().getPos()] = edge;
    }

    @Override
    protected void RemoveMatrizEdge(Edge<V, E> edge) {
        getMatrix()[edge.getOrigin().getPos()][edge.getDestination().getPos()] = null;
        getMatrix()[edge.getDestination().getPos()][edge.getOrigin().getPos()] = null;
    }

    @Override
    protected Edge<V, E> findEdgeByVertices(String vertexOrigin, String vertexDestination) {
        for (Edge<V, E> edge : getEdges()) {
            if (edge.getOrigin().getLabel().equals(vertexOrigin) && edge.getDestination().getLabel().equals(vertexDestination) ||
                    edge.getOrigin().getLabel().equals(vertexDestination) && edge.getDestination().getLabel().equals(vertexOrigin))
                return edge;
        }
        return null;
    }

    @Override
    public String toString() {
        StringBuilder buffer = new StringBuilder();
        for (Edge<V, E> edge : getEdges()) {
            List<String> vertices = endVertices(edge.getLabel());
            buffer.append(String.format("%s -- %s\n", vertices.get(0), vertices.get(1)));
        }

        return buffer.toString();
    }
}