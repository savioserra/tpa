package dictionary.structures;

import java.util.function.Consumer;

public interface Container<K, V> {
    /**
     * Retorna o item do dicionário que corresponde à chave fornecida. Caso o item não exista, retorna {@code null}.
     *
     * @param key Chave do item
     */
    V get(K key);

    /**
     * Adiciona um item ao dicionário caso ainda não exista uma chave para o mesmo. Caso contrário, atualiza o existente.
     *
     * @param key   Chave do item
     * @param value Valor a ser adicionado
     */
    V add(K key, V value);

    /**
     * Remove e retorna o item do dicionário que corresponde à chave fornecida. Caso o item não exista, retorna {@code null}.
     *
     * @param key Chave do item
     */
    V pop(K key);

    /**
     * Retorna a quantidade atual de itens inseridos no dicionário.
     */
    int size();

    /**
     * Itera sobre os itens existentes no dicionário.
     *
     * @param consumer Função {@link Consumer}
     */
    void forEach(Consumer<V> consumer);

    /**
     * Redimensiona o vetor de listas para o tamanho especificado.
     *
     * @param newSize Novo tamanho do vetor
     */
    void resize(int newSize);
}
