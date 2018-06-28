package graph.structures;


import java.util.List;

public interface Graph<V, E> {
    /**
     * Returns an iterable collection of all the vertices of the graph.
     */
    List<Vertex<V>> getVertices();

    /**
     * Returns an iterable collection of all the edges of the graph.
     */
    List<Edge<V, E>> getEdges();

    /**
     * Returns an iterable collection of the edges incoming on vertex v.
     */
    List<Edge<V, E>> getInEdges(Vertex<V> v);

    /**
     * Returns an iterable collection of the edges outgoing from vertex v.
     */
    List<Edge<V, E>> getOutEdges(Vertex<V> v);

    /**
     * Returns the endvertex of edge e distinct from vertex v; null otherwise.
     */
    Vertex<V> getOpposite(Vertex<V> vertex, Edge<V, E> edge);

    /**
     * Returns an array storing the end vertices of edge e.
     */
    Vertex<V>[] getEndVertices(Edge<V, E> edge);

    /**
     * Tests whether vertices v and w are adjacent.
     */
    boolean areAdjacent(Vertex<V> v, Vertex<V> w);

    /**
     * Inserts a vertex into the graph structure.
     *
     * @return true if the vertex was successfully inserted; false otherwise.
     */
    Vertex<V> insertVertex(Vertex<V> vertex);

    /**
     * Removes a vertex into the graph structure.
     *
     * @return true if the vertex was successfully inserted; false otherwise.
     */
    Vertex<V> removeVertex(Vertex<V> vertex);

    /**
     * Inserts an edge.
     */
    Edge<V, E> insertEdge(Edge<V, E> edge) throws Exception;

    /**
     * Removes an edge.
     */
    Edge<V, E> removeEdge(Edge<V, E> edge);

    /**
     * Returns a new vertex given its stored data.
     */
    Vertex<V> createVertex(String label, V data);
}
