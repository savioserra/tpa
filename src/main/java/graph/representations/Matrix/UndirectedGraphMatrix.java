package graph.representations.Matrix;

import graph.shared.Edge;

public class UndirectedGraphMatrix<V, E> extends GraphMatrix<V, E> {
    public UndirectedGraphMatrix(int dimensionSize) {
        super(dimensionSize);
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
}