package dictionary;

import java.io.IOException;
import java.util.LinkedList;

public class Dictionary<TKey, TValue> {
    private LinkedList<DictNode<TKey, TValue>>[] listArrays;
    private HashEngine<TKey> hashEngine;
    private int currentSize, maxSize;

    @SuppressWarnings("unchecked")
    public Dictionary(int size, HashEngine<TKey> engine) {
        listArrays = new LinkedList[size];
        hashEngine = engine;
        maxSize = size;

        for (int i = 0; i < listArrays.length; i++)
            listArrays[i] = new LinkedList<>();
    }

    @SuppressWarnings("unchecked")
    public Dictionary(int size) {
        listArrays = new LinkedList[size];
        hashEngine = EngineFactory.createDefault();
        maxSize = size;

        for (int i = 0; i < listArrays.length; i++)
            listArrays[i] = new LinkedList<>();
    }

    public int lenght() {
        return currentSize;
    }

    private int getMaxSize() {
        return maxSize;
    }

    private int calcHash(TKey key) throws IOException {
        return hashEngine.getHash(key) % getMaxSize();
    }

    private int calcNodePosition(TKey key, LinkedList<DictNode<TKey, TValue>> linkedList) {
        for (int i = 0; i < linkedList.size(); i++) {
            if (key.equals(linkedList.get(i).getKey()))
                return i;
        }
        return -1;
    }

    public TValue add(TKey key, TValue value) throws IOException {
        int calcPosition = calcHash(key), nodePosition = calcNodePosition(key, listArrays[calcPosition]);
        DictNode<TKey, TValue> temporaryNode = null;

        if (nodePosition != -1) {
            temporaryNode = listArrays[calcPosition].get(nodePosition);
            temporaryNode.setValue(value);
        } else {
            temporaryNode = new DictNode<>(key, value);
            listArrays[calcPosition].add(temporaryNode);
            currentSize++;
        }

        return temporaryNode.getValue();
    }

    public TValue get(TKey key) throws IOException {
        int hashPos = calcHash(key), nodePosition = calcNodePosition(key, listArrays[hashPos]);
        return nodePosition != -1 ? listArrays[hashPos].get(nodePosition).getValue() : null;
    }

    public TValue remove(TKey key) throws IOException {
        int hashPos = calcHash(key), nodePosition = calcNodePosition(key, listArrays[hashPos]);
        TValue nodeValue = null;

        if (nodePosition != -1) {
            nodeValue = listArrays[hashPos].get(nodePosition).getValue();
            listArrays[hashPos].remove(nodePosition);
            currentSize--;
        }

        return nodeValue;
    }
}