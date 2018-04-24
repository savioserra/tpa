package dictionary;

import dictionary.engine.HashEngine;
import dictionary.structures.Map;
import dictionary.structures.Node;

import java.util.Objects;
import java.util.function.Consumer;
import java.util.function.Predicate;

public class OpenAddressDictionary<K, V> extends Map<K, V> {
    private Node<K, V>[] nodesArray;

    public OpenAddressDictionary(int size) {
        super(size);
    }

    public OpenAddressDictionary(int size, HashEngine engine) {
        super(size, engine);
    }

    private int travelArray(int startPosition, Node<K, V>[] nodesArray, Predicate<Node<K, V>> condition) {
        for (int i = startPosition; i < nodesArray.length; i++) {
            if (condition.test(nodesArray[i]))
                return i;
        }

        for (int i = 0; i < startPosition; i++) {
            if (condition.test(nodesArray[i]))
                return i;
        }

        return -1;
    }

    private int getNodePosition(K key, Node<K,V>[] nodesArray) {
        return travelArray(compressHash(resolveHash(key)), nodesArray, node -> node != null && key.equals(node.getKey()));
    }

    @Override
    protected int resolveHash(K key) {
        return hashEngine.getHash(key);
    }

    @Override
    protected Node<K, V> findNode(K key, int position) {
        int nodePos = travelArray(position, nodesArray, node -> key.equals(node.getKey()));
        return nodePos != -1 ? nodesArray[nodePos] : null;
    }

    @Override
    protected int compressHash(int hashCode) {
        return hashCode % nodesArray.length;
    }

    @Override
    protected void initialize(int size) {
        int newSize = size < 0 ? 0 : size;
        nodesArray = new Node[newSize];
        currentSize = 0;
    }

    @Override
    public V get(K key) {
        int nodePos = getNodePosition(key, nodesArray);
        return nodePos != -1 ? nodesArray[nodePos].getValue() : null;
    }

    @Override
    public V add(K key, V value) {
        if (nodesArray.length == 0)
            resize(defaultResizeFactor);
        else if ((double) size() / nodesArray.length >= defaultResizeThreshold)
            resize(nodesArray.length * defaultResizeFactor);

        int hashCode = resolveHash(key);
        int compressedHash = compressHash(hashCode);
        int nodePos = getNodePosition(key, nodesArray);

        if (nodePos != -1) {
            nodesArray[nodePos].setValue(value);
        } else {
            nodePos = travelArray(compressedHash, nodesArray, Objects::isNull);
            nodesArray[nodePos] = new Node<>(key, value, hashCode);
            currentSize++;
        }

        return nodesArray[nodePos].getValue();
    }

    @Override
    public V pop(K key) {
        int nodePos = getNodePosition(key, nodesArray);
        V nodeValue = null;

        if (nodePos != -1) {
            nodeValue = nodesArray[nodePos].getValue();
            nodesArray[nodePos] = null;
            currentSize--;
        }

        return nodeValue;
    }

    @Override
    public int size() {
        return currentSize;
    }

    @Override
    public void forEach(Consumer<V> consumer) {
        for (Node<K, V> node : nodesArray)
            if (node != null)
                consumer.accept(node.getValue());
    }

    @Override
    public void resize(int newSize) {
        if (newSize > size()) {
            Node<K, V>[] tempArray = new Node[newSize];

            int nodePos;
            for (Node<K, V> node : nodesArray) {
                if (node != null) {
                    nodePos = travelArray(node.getHashCode() % newSize, tempArray, Objects::isNull);
                    tempArray[nodePos] = node;
                }
            }

            nodesArray = tempArray;
        }
    }
}
