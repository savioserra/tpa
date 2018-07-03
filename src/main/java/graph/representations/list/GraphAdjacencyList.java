package graph.representations.list;

import dictionary.OpenAddressDictionary;
import graph.common.Edge;
import graph.common.Graph;

import java.util.List;
import java.util.stream.Collectors;

abstract class GraphAdjacencyList<V, E> extends Graph<V, E> {
    private OpenAddressDictionary<String, AdjacencyListVertex<V, E>> vertices;
    private OpenAddressDictionary<String, AdjacencyListEdge<V, E>> edges;

    protected GraphAdjacencyList() {
        vertices = new OpenAddressDictionary<>(10);
        edges = new OpenAddressDictionary<>(10);
    }

    @Override
    public List<String> vertices() {
        return getVertices().values().stream()
                .map(AdjacencyListVertex::getLabel)
                .collect(Collectors.toList());
    }

    @Override
    public List<String> edges() {
        return getEdges().values().stream()
                .map(Edge::getLabel)
                .collect(Collectors.toList());
    }

    @Override
    public List<String> incomingEdges(String vertexLabel) {
        AdjacencyListVertex<V, E> vertex = getVertices().get(vertexLabel);
        return vertex != null ? vertex.incomingEdges() : null;
    }

    @Override
    public List<String> outgoingEdges(String vertexLabel) {
        AdjacencyListVertex<V, E> vertex = getVertices().get(vertexLabel);
        return vertex != null ? vertex.outgoingEdges() : null;
    }

    @Override
    public String opposite(String vertexLabel, String edgeLabel) {
        AdjacencyListEdge<V, E> edge = findEdgeByLabel(edgeLabel);

        if (edge != null) {
            if (edge.getOrigin().getLabel().equals(vertexLabel))
                return edge.getDestination().getLabel();
            if (edge.getDestination().getLabel().equals(vertexLabel))
                return edge.getOrigin().getLabel();
        }

        return null;
    }

    @Override
    public String[] endVertices(String edgeLabel) {
        AdjacencyListEdge<V, E> edge = edges.get(edgeLabel);
        return edge != null ? new String[]{edge.getOrigin().getLabel(), edge.getDestination().getLabel()} : null;
    }

    @Override
    public boolean areAdjacent(String vertexOrigin, String vertexDestination) {
        return findEdgeByVertices(vertexOrigin, vertexDestination) != null;
    }

    @Override
    public AdjacencyListVertex<V, E> insertVertex(String vertexLabel, V vertexData) {
        AdjacencyListVertex<V, E> vertex = findVertexByLabel(vertexLabel);

        if (vertex != null)
            vertex.setData(vertexData);
        else {
            vertex = new AdjacencyListVertex<>(0, vertexLabel, vertexData);
            vertices.add(vertexLabel, vertex);
        }
        return vertex;
    }

    @Override
    public AdjacencyListVertex<V, E> removeVertex(String vertexLabel) {
        return getVertices().pop(vertexLabel);
    }

    @Override
    public AdjacencyListEdge<V, E> insertEdge(String edgeLabel, String vertexLabelOrigin, String vertexLabelDestination, E data) throws Exception {
        AdjacencyListVertex<V, E> vertexOrigin = findVertexByLabel(vertexLabelOrigin);
        AdjacencyListVertex<V, E> vertexDestination = findVertexByLabel(vertexLabelDestination);

        if (vertexOrigin == null || vertexDestination == null)
            throw new Exception(
                    String.format("Edges must have origin and destination. Got origin: %s; destination: %s.\n" +
                            "Did you forget to add the vertices?", vertexOrigin, vertexDestination)
            );

        AdjacencyListEdge<V, E> local = findEdgeByLabel(edgeLabel);

        if (local == null) {
            local = new AdjacencyListEdge<>(edgeLabel, vertexOrigin, vertexDestination, data);
        } else {
            removeEdge(edgeLabel);

            local.setOrigin(vertexOrigin);
            local.setDestination(vertexDestination);
            local.setData(data);
        }

        getEdges().add(edgeLabel, local);
        insertEdgeStrategy(local);
        return local;
    }

    @Override
    public AdjacencyListEdge<V, E> removeEdge(String edgeLabel) {
        for (AdjacencyListVertex<V, E> vertex : getVertices().values()) {
            vertex.removeEdge(edgeLabel);
        }

        return getEdges().pop(edgeLabel);
    }

    @Override
    protected AdjacencyListVertex<V, E> findVertexByLabel(String vertexLabel) {
        return getVertices().get(vertexLabel);
    }

    @Override
    protected AdjacencyListEdge<V, E> findEdgeByLabel(String edgeLabel) {
        return getEdges().get(edgeLabel);
    }

    protected OpenAddressDictionary<String, AdjacencyListVertex<V, E>> getVertices() {
        return vertices;
    }

    protected OpenAddressDictionary<String, AdjacencyListEdge<V, E>> getEdges() {
        return edges;
    }
}
