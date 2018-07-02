package graph.shared;


import java.util.List;

public abstract class Graph<VertexDataType, EdgeDataType> {
    /**
     * Returns an iterable collection of all labels of the vertices of the graph.
     */
    public abstract List<String> vertices();

    /**
     * Returns an iterable collection of all labels of the edges of the graph.
     */
    public abstract List<String> edges();

    /**
     * Returns an iterable collection of the labels of the edges incoming on vertex v.
     */
    public abstract List<String> incomingEdges(String vertexLabel);

    /**
     * Returns an iterable collection of the labels of the edges outgoing from vertex v.
     */
    public abstract List<String> outgoingEdges(String vertexLabel);

    /**
     * Returns the label of the endvertex of edge edgeLabel distinct from vertex vertexLabel; null otherwise.
     */
    public abstract String opposite(String vertexLabel, String edgeLabel);

    /**
     * Returns an array storing the labels of the end vertices of edge e.
     */
    public abstract String[] endVertices(String edgeLabel);

    /**
     * Tests whether vertices v and w are adjacent.
     */
    public abstract boolean areAdjacent(String vertexLabelOne, String vertexLabelTwo);

    /**
     * Inserts a vertex into the graph structure.
     */
    public abstract Vertex<VertexDataType> insertVertex(String vertexLabel, VertexDataType vertexData);

    /**
     * Removes a vertex into the graph structure.
     *
     * @return true if the vertex was successfully inserted; false otherwise.
     */
    public abstract Vertex<VertexDataType> removeVertex(String vertexLabel);

    /**
     * Inserts an edge.
     */
    public abstract Edge<VertexDataType, EdgeDataType> insertEdge(String edgeLabel, String vertexLabelOrigin, String vertexLabelDestination, EdgeDataType data) throws Exception;

    /**
     * Removes an edge.
     */
    public abstract Edge<VertexDataType, EdgeDataType> removeEdge(String edgeLabel);

    protected abstract Edge<VertexDataType, EdgeDataType> findEdgeByVertices(String vertexOrigin, String vertexDestination);

    protected abstract Vertex<VertexDataType> findVertexByLabel(String vertexLabel);

    protected abstract Edge<VertexDataType, EdgeDataType> findEdgeByLabel(String edgeLabel);

    protected abstract void insertEdgeStrategy(Edge<VertexDataType, EdgeDataType> edge);

    protected abstract void removeEdgeStrategy(Edge<VertexDataType, EdgeDataType> edge);
}
