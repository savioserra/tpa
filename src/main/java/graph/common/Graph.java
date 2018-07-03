package graph.common;


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
    public boolean areAdjacent(String vertexOrigin, String vertexDestination) {
        return findEdgeByVertices(vertexOrigin, vertexDestination) != null;
    }

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

    /**
     * Retrieves an edge matching given endpoints; null otherwise.
     */
    protected abstract Edge<VertexDataType, EdgeDataType> findEdgeByVertices(String vertexOrigin, String vertexDestination);

    /**
     * Retrieves a vertex matching the given label.
     */
    protected abstract Vertex<VertexDataType> findVertexByLabel(String vertexLabel);

    /**
     * Retrieves an edge matching the given label.
     */
    protected abstract Edge<VertexDataType, EdgeDataType> findEdgeByLabel(String edgeLabel);

    /**
     * Executes the insert operation of an edge on the underlying graph.
     */
    protected abstract void insertEdgeStrategy(Edge<VertexDataType, EdgeDataType> edge);

    protected abstract void removeEdgeStrategy(Edge<VertexDataType, EdgeDataType> edge);

    public abstract String toString();
}
