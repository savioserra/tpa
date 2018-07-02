package dictionary.shared;

import dictionary.engine.EngineFactory;
import dictionary.engine.HashEngine;

import java.util.List;

public abstract class Map<K, V> implements Container<K, V> {
    /**
     * Indica o fator a ser utilizado para aumentar a lista de conteúdos armazenadas por este dicionário.
     */
    protected int defaultResizeFactor = 2;

    /**
     * Indica a porcentagem de ocupação deste dicionário necessária para ocorrer um {@link #resize(int)}.
     */
    protected double defaultResizeThreshold = 0.75;

    /**
     * {@link HashEngine} utilizada por este dicionário.
     */
    protected HashEngine<K> hashEngine;

    /**
     * Representa a quantidade atual de itens armazenados neste dicionário.
     */
    protected int currentSize;

    /**
     * Inicializa um novo {@link Map} especificando um tamanho inicial. É utilizada a {@link HashEngine} padrão.
     *
     * @param size Tamanho inicial do vetor.
     */
    protected Map(int size) {
        initialize(size);
        hashEngine = EngineFactory.createDefault();
    }

    /**
     * Inicializa um novo {@link Map} especificando o tamanho inicial do vetor e a {@link HashEngine} a ser utilizada.
     *
     * @param size   Tamanho inicial do vetor
     * @param engine HashEngine a ser utilizada
     */
    protected Map(int size, HashEngine engine) {
        initialize(size);
        hashEngine = engine;
    }

    /**
     * Calcula o hash da chave fornecida.
     */
    protected abstract int resolveHash(K key);

    /**
     * Realiza uma busca na estrutura de armazenamento deste {@link Map} e retorna o nó de {@code key} correspondente.
     * Retorna {@code null} c.c.
     *
     * @param key      Chave que corresponde ao item
     * @param position Posição onde deve ser realizada a consulta. Normalmente, corresponde ao hash comprimido.
     */
    protected abstract Node<K, V> findNode(K key, int position);

    /**
     * Aplica um fator de compressão para o hash fornecido e retorna o valor resultante.
     */
    protected abstract int compressHash(int hashCode);

    /**
     * Aloca os recursos necessários para este {@link Map}.
     *
     * @param size Capacidade de armazenamento inicial
     */
    protected abstract void initialize(int size);

    /**
     * Retorna um objeto iterável de todos os elementos armazenados.
     */
    public abstract List<V> values();

    /**
     * Retorna um objeto iterável de todas as chaves armazenadas.
     */
    public abstract List<K> keys();
}
