package dictionary.shared;


public class Node<K, V> {
    private K key;
    private V value;
    private int hashCode;

    public Node(K key, V value, int hashCode) {
        this.key = key;
        this.value = value;
        this.hashCode = hashCode;
    }

    public K getKey() {
        return key;
    }

    public V getValue() {
        return value;
    }

    public V setValue(V value) {
        this.value = value;
        return this.value;
    }

    public int getHashCode() {
        return hashCode;
    }
}