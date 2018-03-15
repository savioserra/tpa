package matrix.behaviour;

import matrix.Coordinate;

import java.util.function.Consumer;

public class SpiralBehaviour implements Behaviour {
    /**
     * Itera pelas bordas da matriz fornecida.
     *
     * @param collection Matriz
     * @param consumer   Função {@link Consumer} que consome os elementos {@link T}
     * @param start      Posição de início do intervalo
     * @param end        Posição de fim do intervalo
     * @param current    Posição atual da iteração
     * @param <T>        Tipo da matriz
     */
    private static <T> void iterBorders(T[][] collection, Consumer<T> consumer, Coordinate start, Coordinate current, Coordinate end) {
        if (!start.equals(current)) {
            if (current == null)
                current = new Coordinate(start.x, start.y);
            consumer.accept(collection[current.y][current.x]);

            // Lateral superior
            if (current.y == start.y && current.x < end.x)
                iterBorders(collection, consumer, start, new Coordinate(current.x + 1, current.y), end);

            if (collection.length > 1) {
                // Lateral direita
                if (current.x == end.x && current.y < end.y)
                    iterBorders(collection, consumer, start, new Coordinate(current.x, current.y + 1), end);

                    // Lateral inferior
                else if (current.y == end.y && current.x > start.x)
                    iterBorders(collection, consumer, start, new Coordinate(current.x - 1, current.y), end);

                    // Lateral esquerda
                else if (current.x == start.x && current.y > start.y)
                    iterBorders(collection, consumer, start, new Coordinate(current.x, current.y - 1), end);
            }
        }
    }

    private static <T> void iterSpiral(T[][] collection, Consumer<T> consumer, Coordinate start, Coordinate end) {
        if (collection.length > 0 && start.lesserThan(end) || start.equals(end)) {
            iterBorders(collection, consumer, start, null, end);
            iterSpiral(collection, consumer, new Coordinate(start.x + 1, start.y + 1), new Coordinate(end.x - 1, end.y - 1));
        }
    }

    @Override
    public <T> void process(T[][] collection, Consumer<T> consumer) {
        iterSpiral(collection, consumer, new Coordinate(0, 0), new Coordinate(collection[0].length - 1, collection.length - 1));
    }
}

