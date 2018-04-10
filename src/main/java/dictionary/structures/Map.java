package dictionary.structures;

import java.util.LinkedList;

public abstract class Map<K, V> implements Container<K, V> {
    /**
     * Calcula o hash da chave fornecida.
     */
    protected abstract int resolveHash(K key);

    /**
     * Retorna a posição de um nó em uma determinada lista caso exista. Retorna {@code -1} c.c.
     *
     * @param key   Chave que corresponde ao item
     * @param nodes Conjunto onde a busca deve ocorrer
     */
    protected abstract int findNode(K key, LinkedList<Node<K, V>> nodes);

    /**
     * Aplica um fator de compressão para o hash fornecido e retorna o valor resultante.
     */
    protected abstract int compressHash(int hashCode);
}
