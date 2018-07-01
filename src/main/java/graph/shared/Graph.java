package graph.shared;


import java.util.List;

public interface Graph<VertexDataType, EdgeDataType> {
    /**
     * Returns an iterable collection of all labels of the vertices of the graph.
     */
    List<String> vertices();

    /**
     * Returns an iterable collection of all labels of the edges of the graph.
     */
    List<String> edges();

    /**
     * Returns an iterable collection of the labels of the edges incoming on vertex v.
     */
    List<String> incomingEdges(String vertexLabel);

    /**
     * Returns an iterable collection of the labels of the edges outgoing from vertex v.
     */
    List<String> outgoingEdges(String vertexLabel);

    /**
     * Returns the label of the endvertex of edge edgeLabel distinct from vertex vertexLabel; null otherwise.
     */
    String opposite(String vertexLabel, String edgeLabel);

    /**
     * Returns an array storing the labels of the end vertices of edge e.
     */
    String[] endVertices(String edgeLabel);

    /**
     * Tests whether vertices v and w are adjacent.
     */
    boolean areAdjacent(String vertexLabelOne, String vertexLabelTwo);

    /**
     * Inserts a vertex into the graph structure.
     */
    Vertex<VertexDataType> insertVertex(String vertexLabel, VertexDataType vertexData);

    /**
     * Removes a vertex into the graph structure.
     *
     * @return true if the vertex was successfully inserted; false otherwise.
     */
    Vertex<VertexDataType> removeVertex(String vertexLabel);

    /**
     * Inserts an edge.
     */
    Edge<VertexDataType, EdgeDataType> insertEdge(String edgeLabel, String vertexLabelOrigin, String vertexLabelDestination, EdgeDataType data) throws Exception;

    /**
     * Removes an edge.
     */
    Edge<VertexDataType, EdgeDataType> removeEdge(String edgeLabel);
}
