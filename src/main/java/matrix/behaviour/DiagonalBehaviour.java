package matrix.behaviour;

import matrix.Coordinate;

import java.util.function.Consumer;

public class DiagonalBehaviour implements Behaviour {
    /**
     * Itera pela diagonal principal de uma matriz quadrada a partir de uma posição start.
     *
     * @param collection Matriz
     * @param consumer   Função {@link Consumer} que consome os elementos {@link T}
     * @param start      Posição inicial da matriz ou offset
     * @param <T>        Tipo da matriz
     */
    private static <T> void iterDiagonal(T[][] collection, Consumer<T> consumer, Coordinate start) {
        if (collection.length > 0 && collection.length != collection[0].length)
            throw new IllegalArgumentException("Arrays must have equal sizes.");

        if (start.x < collection.length) {
            consumer.accept(collection[start.y][start.x]);
            iterDiagonal(collection, consumer, new Coordinate(start.x + 1, start.y + 1));
        }
    }

    @Override
    public <T> void process(T[][] collection, Consumer<T> consumer) {
        iterDiagonal(collection, consumer, new Coordinate(0, 0));
    }
}
