package graph.representations.Matrix;

import graph.shared.Edge;

public class DirectedGraphMatrix<V, E> extends GraphMatrix<V, E> {
    public DirectedGraphMatrix(int dimensionSize) {
        super(dimensionSize);
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
}