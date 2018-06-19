package graph.structures;


public class Edge<V> {
    private int pos;
    private String label;
    private V value;

    public Edge(int pos, String label) {
        this.pos = pos;
        this.label = label;
    }

    public Edge(int pos, String label, V value) {
        this.pos = pos;
        this.label = label;
        this.value = value;
    }

    public int getPos() {
        return pos;
    }

    public String getLabel() {
        return label;
    }

    public V getValue() {
        return value;
    }

    public void setValue(V value) {
        this.value = value;
    }
}
