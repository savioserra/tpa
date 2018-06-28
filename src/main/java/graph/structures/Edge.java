package graph.structures;


public class Edge<V, E> {
    private String label;
    private E data;
    private Vertex<V> origin;
    private Vertex<V> destination;

    public Edge(String label, Vertex<V> origin) {
        this.label = label;
        this.origin = origin;
    }

    public Edge(String label, Vertex<V> origin, Vertex<V> destination, E data) {
        this.label = label;
        this.origin = origin;
        this.data = data;
        this.destination = destination;
    }

    public String getLabel() {
        return label;
    }

    public E getData() {
        return data;
    }

    public void setData(E data) {
        this.data = data;
    }

    public Vertex<V> getOrigin() {
        return origin;
    }

    public void setOrigin(Vertex<V> origin) {
        this.origin = origin;
    }

    public Vertex<V> getDestination() {
        return destination;
    }

    public void setDestination(Vertex<V> destination) {
        this.destination = destination;
    }
}
