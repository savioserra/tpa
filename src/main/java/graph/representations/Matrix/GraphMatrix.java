package graph.representations.Matrix;

import graph.shared.Edge;
import graph.shared.Graph;
import graph.shared.Vertex;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public abstract class GraphMatrix<V, E> implements Graph<V, E> {
    protected Edge<V, E>[][] matrix;
    private LinkedList<Vertex<V>> vertices;
    private LinkedList<Edge<V, E>> edges;
    private int verticesIdCounter;

    @SuppressWarnings("unchecked")
    protected GraphMatrix() {
        matrix = new Edge[10][10];
        vertices = new LinkedList<>();
        edges = new LinkedList<>();
        verticesIdCounter = 0;
    }

    @Override
    public List<String> vertices() {
        return getVertices().stream()
                .map(Vertex::getLabel)
                .collect(Collectors.toList());
    }

    @Override
    public List<String> edges() {
        return getEdges().stream()
                .map(Edge::getLabel)
                .collect(Collectors.toList());
    }

    @Override
    public List<String> incomingEdges(String vertexLabel) {
        Vertex<V> vertex = findVertexByLabel(vertexLabel);

        if (vertex != null) {
            LinkedList<String> edges = new LinkedList<>();

            for (int i = 0; i < getMatrix().length; i++) {
                Edge<V, E> edge = getMatrix()[i][vertex.getPos()];

                if (edge != null)
                    edges.add(edge.getLabel());
            }
            return edges;
        }
        return null;
    }

    @Override
    public List<String> outgoingEdges(String vertexLabel) {
        Vertex<V> vertex = findVertexByLabel(vertexLabel);

        if (vertex != null) {
            LinkedList<String> edges = new LinkedList<>();

            for (int i = 0; i < getMatrix().length; i++) {
                Edge<V, E> edge = getMatrix()[vertex.getPos()][i];

                if (edge != null)
                    edges.add(edge.getLabel());
            }
            return edges;
        }
        return null;
    }

    @Override
    public String opposite(String vertexLabel, String edgeLabel) {
        Vertex<V> vertexOrigin = findVertexByLabel(vertexLabel);
        Edge<V, E> edge = findEdgeByLabel(edgeLabel);

        if (vertexOrigin != null && edge != null) {
            if (edge.getDestination().equals(vertexOrigin))
                return edge.getOrigin().getLabel();
            if (edge.getOrigin().equals(vertexOrigin))
                return edge.getDestination().getLabel();
        }
        return null;
    }

    @Override
    public List<String> endVertices(String edgeLabel) {
        Edge<V, E> edge = findEdgeByLabel(edgeLabel);

        if (edge != null) {
            LinkedList<String> vertices = new LinkedList<>();

            vertices.add(edge.getOrigin().getLabel());
            vertices.add(edge.getDestination().getLabel());

            return vertices;
        }
        return null;
    }

    @Override
    public boolean areAdjacent(String v, String w) {
        Vertex<V> v1 = findVertexByLabel(v);
        Vertex<V> v2 = findVertexByLabel(w);

        return (v1 != null && v2 != null) &&
                (matrix[v1.getPos()][v2.getPos()] != null || matrix[v1.getPos()][v2.getPos()] != null);
    }

    @Override
    public Vertex<V> insertVertex(String vertexLabel, V vertexData) {
        Vertex<V> local = findVertexByLabel(vertexLabel);

        if (local == null) {
            if (getVertices().size() / getMatrix().length >= 0.75f)
                resize();

            local = new Vertex<>(createVertexIndex(), vertexLabel, vertexData);
            getVertices().add(local);
        } else {
            local.setData(vertexData);
        }
        return local;
    }

    @Override
    public Vertex<V> removeVertex(String vertexLabel) {
        Vertex<V> v = findVertexByLabel(vertexLabel);

        if (getVertices().contains(v)) {
            for (String edgeLabel : incomingEdges(vertexLabel)) {
                removeEdge(edgeLabel);
            }

            for (String edgeLabel : outgoingEdges(vertexLabel)) {
                removeEdge(edgeLabel);
            }

            getVertices().remove(v);
            return v;
        }
        return null;
    }

    @Override
    public Edge<V, E> removeEdge(String edgeLabel) {
        Edge<V, E> edge = findEdgeByLabel(edgeLabel);

        if (edge != null && edges.contains(edge)) {
            RemoveMatrizEdge(edge);
            getEdges().remove(edge);

            return edge;
        }
        return null;
    }

    @Override
    public Edge<V, E> insertEdge(String edgeLabel, String vertexLabelOrigin, String vertexLabelDestination, E data) throws Exception {
        Vertex<V> vertexOrigin = findVertexByLabel(vertexLabelOrigin);
        Vertex<V> vertexDestination = findVertexByLabel(vertexLabelDestination);

        if (vertexOrigin == null || vertexDestination == null)
            throw new Exception(
                    String.format("Edges must have origin and destination. Got origin: %s; destination: %s.\n" +
                            "Did you forget to add the vertices?", vertexOrigin, vertexDestination)
            );

        Edge<V, E> local = findEdgeByVertices(vertexLabelOrigin, vertexLabelDestination);

        if (local == null) {
            local = new Edge<>(edgeLabel, vertexOrigin, vertexDestination, data);
            getEdges().add(local);
        } else {
            removeEdge(edgeLabel);

            local.setOrigin(vertexOrigin);
            local.setDestination(vertexDestination);
            local.setData(data);
        }

        InsertMatrizEdge(local);
        return local;
    }

    protected abstract void InsertMatrizEdge(Edge<V, E> edge);

    protected abstract void RemoveMatrizEdge(Edge<V, E> edge);

    protected abstract Edge<V, E> findEdgeByVertices(String vertexOrigin, String vertexDestination);

    protected Vertex<V> findVertexByLabel(String vertexLabel) {
        for (Vertex<V> vertex : getVertices()) {
            if (vertex != null && vertex.getLabel().equals(vertexLabel))
                return vertex;
        }
        return null;
    }

    protected Edge<V, E> findEdgeByLabel(String edgeLabel) {
        for (Edge<V, E> edge : getEdges()) {
            if (edge != null && edge.getLabel().equals(edgeLabel))
                return edge;
        }
        return null;
    }

    protected int createVertexIndex() {
        return verticesIdCounter++;
    }

    protected LinkedList<Vertex<V>> getVertices() {
        return vertices;
    }

    protected LinkedList<Edge<V, E>> getEdges() {
        return edges;
    }

    protected Edge<V, E>[][] getMatrix() {
        return matrix;
    }

    @SuppressWarnings("unchecked")
    private void resize() {
        int newSize = (int) (getMatrix().length * 1.5);
        matrix = new Edge[newSize][newSize];
    }
}
