package graph.structures;


public class Vertex<V> {
    private int pos;
    private String label;
    private V data;

    public Vertex(int id, String label, V data) {
        this.pos = id;
        this.label = label;
        this.data = data;
    }

    public Vertex(int pos, String label) {
        this.pos = pos;
        this.label = label;
    }

    public int getPos() {
        return pos;
    }

    public String getLabel() {
        return label;
    }

    public V getData() {
        return data;
    }

    public void setData(V data) {
        this.data = data;
    }
}
