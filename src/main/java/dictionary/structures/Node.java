package dictionary.structures;

public interface Node<K, V> {
    K getKey();

    V getValue();

    V setValue(V value);

    int getHashCode();
}
