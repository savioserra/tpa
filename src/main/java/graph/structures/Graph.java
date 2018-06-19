package graph.structures;


import java.util.LinkedList;

public abstract class Graph<V, E> {
    public abstract int getTotalVertexes();
    public abstract int getTotalEdges();
    public abstract LinkedList<Vertex> getVertexes();
    public abstract LinkedList<Edge> getEdges();
}
