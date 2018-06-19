package graph.structures;


public class Vertex<V> {
    private int pos;
    private String label;
    private V value;

    public Vertex(int id, String label, V value) {
        this.pos = id;
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
