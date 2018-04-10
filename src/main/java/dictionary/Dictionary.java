package dictionary;

import dictionary.engine.EngineFactory;
import dictionary.engine.HashEngine;
import dictionary.structures.Map;
import dictionary.structures.Node;

import java.util.LinkedList;
import java.util.function.Consumer;

/**
 * Representa um dicionário de dados composto por um vetor de {@link LinkedList}.
 *
 * @param <K> Tipo do objeto chave
 * @param <V> Tipo do objeto valor
 */
public class Dictionary<K, V> extends Map<K, V> {
    private LinkedList<Node<K, V>>[] listArrays;
    private HashEngine<K> hashEngine;
    private int currentSize;
    private int maxSize;

    /**
     * Inicializa um novo dicionário especificando o tamanho inicial do vetor e a {@link HashEngine} a ser utilizada.
     *
     * @param size   Tamanho inicial do vetor
     * @param engine HashEngine a ser utilizada
     */
    @SuppressWarnings("unchecked")
    public Dictionary(int size, HashEngine<K> engine) {
        listArrays = new LinkedList[size];
        hashEngine = engine;
        maxSize = size;

        for (int i = 0; i < listArrays.length; i++)
            listArrays[i] = new LinkedList<>();
    }

    /**
     * Inicializa um novo dicionário especificando um tamanho inicial. É utilizada a {@link HashEngine} padrão.
     *
     * @param size Tamanho inicial do vetor.
     */
    @SuppressWarnings("unchecked")
    public Dictionary(int size) {
        listArrays = new LinkedList[size];
        hashEngine = EngineFactory.createDefault();
        maxSize = size;

        for (int i = 0; i < listArrays.length; i++)
            listArrays[i] = new LinkedList<>();
    }

    protected int resolveHash(K key) {
        return hashEngine.getHash(key);
    }

    protected int compressHash(int hashCode) {
        return hashCode % maxSize;
    }

    protected int findNode(K key, LinkedList<Node<K, V>> linkedList) {
        for (int i = 0; i < linkedList.size(); i++) {
            if (key.equals(linkedList.get(i).getKey()))
                return i;
        }
        return -1;
    }

    public V add(K key, V value) {
        int hashCode = resolveHash(key);
        int compressedHash = compressHash(hashCode);
        int nodePosition = findNode(key, listArrays[compressedHash]);

        Node<K, V> temporaryNode;

        if (nodePosition != -1) {
            temporaryNode = listArrays[compressedHash].get(nodePosition);
            temporaryNode.setValue(value);
        } else {
            temporaryNode = new DictNode<>(key, value, hashCode);
            listArrays[compressedHash].add(temporaryNode);
            currentSize++;
        }

        return temporaryNode.getValue();
    }

    public V get(K key) {
        int hashPos = compressHash(resolveHash(key));
        int nodePosition = findNode(key, listArrays[hashPos]);
        return nodePosition != -1 ? listArrays[hashPos].get(nodePosition).getValue() : null;
    }

    public V pop(K key) {
        int hashPos = compressHash(resolveHash(key));
        int nodePosition = findNode(key, listArrays[hashPos]);

        V nodeValue = null;

        if (nodePosition != -1) {
            nodeValue = listArrays[hashPos].get(nodePosition).getValue();
            listArrays[hashPos].remove(nodePosition);
            currentSize--;
        }

        return nodeValue;
    }

    public void forEach(Consumer<V> consumer) {
        for (LinkedList<Node<K, V>> array : listArrays)
            for (Node<K, V> node : array)
                consumer.accept(node.getValue());
    }

    public void resize(int newSize) {
        // TODO
    }

    public int size() {
        return currentSize;
    }
}