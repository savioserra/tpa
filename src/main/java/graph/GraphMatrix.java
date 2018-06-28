package graph;

import graph.structures.Edge;
import graph.structures.Graph;
import graph.structures.Vertex;

import java.util.LinkedList;
import java.util.List;

public class GraphMatrix<V, E> implements Graph<V, E> {
    private int verticesIdCounter;

    private LinkedList<Vertex<V>> vertices;
    private LinkedList<Edge<V, E>> edges;
    private Edge<V, E>[][] matrix;

    @SuppressWarnings("unchecked")
    public GraphMatrix(int dimensionSize) {
        matrix = new Edge[dimensionSize][dimensionSize];
        vertices = new LinkedList<>();
        edges = new LinkedList<>();
        verticesIdCounter = 0;
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Vertex<V>> getVertices() {
        return (List<Vertex<V>>) vertices.clone();
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Edge<V, E>> getEdges() {
        return (List<Edge<V, E>>) edges.clone();
    }

    @Override
    public List<Edge<V, E>> getInEdges(Vertex<V> vertex) {
        LinkedList<Edge<V, E>> inEdges = new LinkedList<>();

        for (Edge<V, E> edge : edges) {
            if (edge.getDestination().equals(vertex))
                inEdges.add(edge);
        }

        return inEdges;
    }

    @Override
    public List<Edge<V, E>> getOutEdges(Vertex<V> vertex) {
        LinkedList<Edge<V, E>> outEdges = new LinkedList<>();

        for (Edge<V, E> edge : edges) {
            if (edge.getOrigin().equals(vertex))
                outEdges.add(edge);
        }

        return outEdges;
    }

    @Override
    public Vertex<V> getOpposite(Vertex<V> vertex, Edge<V, E> edge) {
        if (edge.getDestination().equals(vertex))
            return edge.getOrigin();
        if (edge.getOrigin().equals(vertex))
            return edge.getDestination();
        return null;
    }

    @Override
    @SuppressWarnings("unchecked")
    public Vertex<V>[] getEndVertices(Edge<V, E> edge) {
        return new Vertex[]{edge.getOrigin(), edge.getDestination()};
    }

    @Override
    public boolean areAdjacent(Vertex<V> v, Vertex<V> w) {
        return matrix[v.getPos()][w.getPos()] != null || matrix[w.getPos()][v.getPos()] != null;
    }

    @Override
    public Vertex<V> insertVertex(Vertex<V> vertex) {
        Vertex<V> local = findVertexByLabel(vertex.getLabel());

        if (local == null) {
            local = vertex;
            vertices.add(local);

            return vertex;
        } else {
            local.setData(vertex.getData());
            return local;
        }
    }

    @Override
    public Vertex<V> removeVertex(Vertex<V> vertex) {
        Vertex<V> v = findVertexByLabel(vertex.getLabel());
        if (vertices.contains(v)) {
            for (Edge<V, E> edge : getInEdges(v)) {
                removeEdge(edge);
            }

            for (Edge<V, E> edge : getOutEdges(v)) {
                removeEdge(edge);
            }

            vertices.remove(v);
            return v;
        }
        return null;
    }

    @Override
    public Vertex<V> createVertex(String label, V data) {
        return new Vertex<>(verticesIdCounter++, label, data);
    }

    @Override
    public Edge<V, E> insertEdge(Edge<V, E> edge) throws Exception {
        if (edge.getDestination() == null || edge.getOrigin() == null)
            throw new Exception(
                    String.format("Edges must have origin and destination. Got origin: %s; destination: %s", edge.getOrigin(), edge.getDestination())
            );

        Edge<V, E> local = findEdgeByLabel(edge.getLabel());

        if (local == null) {
            insertVertex(edge.getOrigin());
            insertVertex(edge.getDestination());

            matrix[edge.getOrigin().getPos()][edge.getDestination().getPos()] = edge;
            edges.add(edge);

            return edge;
        } else {
            removeEdge(local);
            local.setData(edge.getData());
            local.setOrigin(edge.getOrigin());
            local.setDestination(edge.getDestination());

            matrix[local.getOrigin().getPos()][local.getDestination().getPos()] = local;
            return local;
        }
    }

    @Override
    public Edge<V, E> removeEdge(Edge<V, E> edge) {
        if (edges.contains(edge)) {
            matrix[edge.getOrigin().getPos()][edge.getDestination().getPos()] = null;
            matrix[edge.getDestination().getPos()][edge.getOrigin().getPos()] = null;
            edges.remove(edge);

            return edge;
        }
        return null;
    }

    private Vertex<V> findVertexByLabel(String label) {
        for (Vertex<V> vertex : vertices) {
            if (vertex != null && vertex.getLabel().equals(label))
                return vertex;
        }
        return null;
    }

    private Edge<V, E> findEdgeByLabel(String label) {
        for (Edge<V, E> edge : edges) {
            if (edge != null && edge.getLabel().equals(label))
                return edge;
        }
        return null;
    }
}
