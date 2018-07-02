package dictionary;


import dictionary.engine.HashEngine;
import dictionary.shared.Map;
import dictionary.shared.Node;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.function.Consumer;
import java.util.stream.Collectors;

/**
 * Representa um dicionário de dados composto por um vetor de {@link LinkedList}.
 *
 * @param <K> Tipo do objeto chave
 * @param <V> Tipo do objeto valor
 */
public class LinkedDictionary<K, V> extends Map<K, V> {
    protected int defaultListSizeThreshold = 2;
    private LinkedList<Node<K, V>>[] listsArray;

    public LinkedDictionary(int size, HashEngine<K> engine) {
        super(size, engine);
    }


    public LinkedDictionary(int size) {
        super(size);
    }

    @SuppressWarnings("unchecked")
    protected void initialize(int size) {
        int newSize = size < 0 ? 0 : size;

        listsArray = new LinkedList[newSize];
        for (int i = 0; i < listsArray.length; i++)
            listsArray[i] = new LinkedList<>();

        currentSize = 0;
    }

    @Override
    public List<V> values() {
        return Arrays.stream(listsArray)
                .flatMap(nodes -> nodes.stream().map(Node::getValue))
                .collect(Collectors.toList());
    }

    @Override
    public List<K> keys() {
        return Arrays.stream(listsArray)
                .map(LinkedList::stream)
                .flatMap(nodeStream -> nodeStream.map(Node::getKey))
                .collect(Collectors.toList());
    }

    protected int resolveHash(K key) {
        return hashEngine.getHash(key);
    }

    protected int compressHash(int hashCode) {
        return hashCode % listsArray.length;
    }

    protected Node<K, V> findNode(K key, int position) {
        LinkedList<Node<K, V>> linkedList = listsArray[position];

        for (Node<K, V> node : linkedList) {
            if (key.equals(node.getKey()))
                return node;
        }
        return null;
    }

    public V add(K key, V value) {
        if (listsArray.length == 0)
            resize(defaultResizeFactor);
        else if ((double) currentSize / (listsArray.length * defaultListSizeThreshold) >= defaultResizeThreshold)
            resize(listsArray.length * defaultResizeFactor);

        int hashCode = resolveHash(key);
        int compressedHash = compressHash(hashCode);
        Node<K, V> node = findNode(key, compressedHash);

        if (node == null) {
            node = new Node<>(key, value, hashCode);
            listsArray[compressedHash].add(node);
            currentSize++;
        } else {
            node.setValue(value);
        }

        return node.getValue();
    }

    public V get(K key) {
        Node<K, V> node = findNode(key, compressHash(resolveHash(key)));
        return node != null ? node.getValue() : null;
    }

    public V pop(K key) {
        int hashPos = compressHash(resolveHash(key));
        Node<K, V> node = findNode(key, hashPos);
        V nodeValue = null;

        if (node != null) {
            nodeValue = node.getValue();
            listsArray[hashPos].remove(node);
            currentSize--;
        }

        return nodeValue;
    }

    public void forEach(Consumer<V> consumer) {
        for (LinkedList<Node<K, V>> array : listsArray)
            for (Node<K, V> node : array)
                consumer.accept(node.getValue());
    }

    public void resize(int newSize) {
        if (newSize > size()) {
            LinkedList[] newArray = new LinkedList[newSize];
            for (int i = 0; i < newArray.length; i++)
                newArray[i] = new LinkedList<Node<K, V>>();

            for (LinkedList<Node<K, V>> array : listsArray)
                for (Node<K, V> node : array)
                    newArray[(node.getHashCode()) % newSize].add(node);

            listsArray = newArray;
        }
    }

    public int size() {
        return currentSize;
    }
}