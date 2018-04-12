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
    protected static int DEFAULT_RESIZE_FACTOR = 2;
    protected static double DEFAULT_RESIZE_THRESHOLD = 0.75;
    protected static int DEFAULT_LIST_SIZE_THRESHOLD = 2;

    private LinkedList<Node<K, V>>[] listsArray;
    private HashEngine<K> hashEngine;
    private int currentSize;
    private int internalArraySize;

    /**
     * Inicializa um novo dicionário especificando o tamanho inicial do vetor e a {@link HashEngine} a ser utilizada.
     *
     * @param size   Tamanho inicial do vetor
     * @param engine HashEngine a ser utilizada
     */
    public Dictionary(int size, HashEngine<K> engine) {
        initialize(size);
        hashEngine = engine;
    }

    /**
     * Inicializa um novo dicionário especificando um tamanho inicial. É utilizada a {@link HashEngine} padrão.
     *
     * @param size Tamanho inicial do vetor.
     */
    public Dictionary(int size) {
        initialize(size);
        hashEngine = EngineFactory.createDefault();
    }

    @SuppressWarnings("unchecked")
    private void initialize(int size) {
        listsArray = new LinkedList[size];
        for (int i = 0; i < listsArray.length; i++)
            listsArray[i] = new LinkedList<>();

        internalArraySize = size;
        currentSize = 0;
    }

    protected int resolveHash(K key) {
        return hashEngine.getHash(key);
    }

    protected synchronized int compressHash(int hashCode) {
        return hashCode % internalArraySize;
    }

    protected synchronized int findNode(K key, LinkedList<Node<K, V>> linkedList) {
        for (int i = 0; i < linkedList.size(); i++) {
            if (key.equals(linkedList.get(i).getKey()))
                return i;
        }
        return -1;
    }

    public synchronized V add(K key, V value) {
        if (currentSize / (internalArraySize * DEFAULT_LIST_SIZE_THRESHOLD) >= DEFAULT_RESIZE_THRESHOLD)
            resize(internalArraySize * DEFAULT_RESIZE_FACTOR);

        int hashCode = resolveHash(key);
        int compressedHash = compressHash(hashCode);
        int nodePosition = findNode(key, listsArray[compressedHash]);
        Node<K, V> temporaryNode;

        if (nodePosition == -1) {
            temporaryNode = new Node<>(key, value, hashCode);
            listsArray[compressedHash].add(temporaryNode);
            currentSize++;
        } else {
            temporaryNode = listsArray[compressedHash].get(nodePosition);
            temporaryNode.setValue(value);
        }

        return temporaryNode.getValue();
    }

    public synchronized V get(K key) {
        int hashPos = compressHash(resolveHash(key));
        int nodePosition = findNode(key, listsArray[hashPos]);
        return nodePosition != -1 ? listsArray[hashPos].get(nodePosition).getValue() : null;
    }

    public synchronized V pop(K key) {
        int hashPos = compressHash(resolveHash(key));
        int nodePosition = findNode(key, listsArray[hashPos]);

        V nodeValue = null;

        if (nodePosition != -1) {
            nodeValue = listsArray[hashPos].get(nodePosition).getValue();
            listsArray[hashPos].remove(nodePosition);
            currentSize--;
        }

        return nodeValue;
    }

    public synchronized void forEach(Consumer<V> consumer) {
        for (LinkedList<Node<K, V>> array : listsArray)
            for (Node<K, V> node : array)
                consumer.accept(node.getValue());
    }

    public synchronized void resize(int newSize) {
        LinkedList[] newArray = new LinkedList[newSize];
        for (int i = 0; i < newArray.length; i++)
            newArray[i] = new LinkedList<Node<K, V>>();

        for (LinkedList<Node<K, V>> array : listsArray)
            for (Node<K, V> node : array)
                newArray[(node.getHashCode()) % newSize].add(node);

        internalArraySize = newSize;
        listsArray = newArray;
    }

    public synchronized int size() {
        return currentSize;
    }
}