package graph.representations.list;

import dictionary.OpenAddressDictionary;
import graph.shared.Edge;
import graph.shared.Graph;

import java.util.List;
import java.util.stream.Collectors;

public abstract class GraphAdjacencyList<V, E> extends Graph<V, E> {
    private OpenAddressDictionary<String, VertexListAdj<V, E>> vertices;
    private OpenAddressDictionary<String, EdgeListAdj<V, E>> edges;

    protected GraphAdjacencyList() {
        vertices = new OpenAddressDictionary<>(10);
        edges = new OpenAddressDictionary<>(10);
    }

    @Override
    public List<String> vertices() {
        return getVertices().values().stream()
                .map(VertexListAdj::getLabel)
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
        VertexListAdj<V, E> vertex = getVertices().get(vertexLabel);
        return vertex != null ? vertex.incomingEdges() : null;
    }

    @Override
    public List<String> outgoingEdges(String vertexLabel) {
        VertexListAdj<V, E> vertex = getVertices().get(vertexLabel);
        return vertex != null ? vertex.outgoingEdges() : null;
    }

    @Override
    public String opposite(String vertexLabel, String edgeLabel) {
        EdgeListAdj<V, E> edge = findEdgeByLabel(edgeLabel);

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
        EdgeListAdj<V, E> edge = edges.get(edgeLabel);
        return edge != null ? new String[]{edge.getOrigin().getLabel(), edge.getDestination().getLabel()} : null;
    }

    @Override
    public boolean areAdjacent(String vertexLabelOne, String vertexLabelTwo) {
        return findEdgeByVertices(vertexLabelOne, vertexLabelTwo) != null;
    }

    @Override
    public VertexListAdj<V, E> insertVertex(String vertexLabel, V vertexData) {
        VertexListAdj<V, E> vertex = findVertexByLabel(vertexLabel);

        if (vertex != null)
            vertex.setData(vertexData);
        else {
            vertex = new VertexListAdj<>(0, vertexLabel, vertexData);
            vertices.add(vertexLabel, vertex);
        }
        return vertex;
    }

    @Override
    public VertexListAdj<V, E> removeVertex(String vertexLabel) {
        return getVertices().pop(vertexLabel);
    }

    @Override
    public EdgeListAdj<V, E> insertEdge(String edgeLabel, String vertexLabelOrigin, String vertexLabelDestination, E data) throws Exception {
        VertexListAdj<V, E> vertexOrigin = findVertexByLabel(vertexLabelOrigin);
        VertexListAdj<V, E> vertexDestination = findVertexByLabel(vertexLabelDestination);

        if (vertexOrigin == null || vertexDestination == null)
            throw new Exception(
                    String.format("Edges must have origin and destination. Got origin: %s; destination: %s.\n" +
                            "Did you forget to add the vertices?", vertexOrigin, vertexDestination)
            );

        EdgeListAdj<V, E> local = (EdgeListAdj<V, E>) findEdgeByVertices(vertexLabelOrigin, vertexLabelDestination);

        if (local == null) {
            local = new EdgeListAdj<>(edgeLabel, vertexOrigin, vertexDestination, data);
            getEdges().add(edgeLabel, local);
        } else {
            removeEdge(edgeLabel);

            local.setOrigin(vertexOrigin);
            local.setDestination(vertexDestination);
            local.setData(data);
        }

        insertEdgeStrategy(local);
        return local;
    }

    @Override
    public EdgeListAdj<V, E> removeEdge(String edgeLabel) {
        for (VertexListAdj<V, E> vertex : getVertices().values()) {
            vertex.removeEdge(edgeLabel);
        }

        return getEdges().pop(edgeLabel);
    }

    @Override
    protected VertexListAdj<V, E> findVertexByLabel(String vertexLabel) {
        return getVertices().get(vertexLabel);
    }

    @Override
    protected EdgeListAdj<V, E> findEdgeByLabel(String edgeLabel) {
        return getEdges().get(edgeLabel);
    }

    protected OpenAddressDictionary<String, VertexListAdj<V, E>> getVertices() {
        return vertices;
    }

    protected OpenAddressDictionary<String, EdgeListAdj<V, E>> getEdges() {
        return edges;
    }
}
