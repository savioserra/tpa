package matrix.behaviour;

import matrix.Coordinate;
import matrix.Matrix;

import java.util.function.Consumer;

public class SpiralBehaviour implements Behaviour {
    /**
     * Itera pelas bordas da matriz fornecida.
     *
     * @param matrix Matriz
     * @param consumer   Função {@link Consumer} que consome os elementos {@link T}
     * @param start      Posição de início do intervalo
     * @param end        Posição de fim do intervalo
     * @param current    Posição atual da iteração
     * @param <T>        Tipo da matriz
     */
    private static <T> void iterBorders(Matrix<T> matrix, Consumer<T> consumer, Coordinate start, Coordinate current, Coordinate end) {
        if (!start.equals(current)) {
            if (current == null)
                current = new Coordinate(start.x, start.y);
            consumer.accept(matrix.getElement(current));

            // Lateral superior
            if (current.y == start.y && current.x < end.x)
                iterBorders(matrix, consumer, start, new Coordinate(current.x + 1, current.y), end);

            if (matrix.getLineCount() > 1) {
                // Lateral direita
                if (current.x == end.x && current.y < end.y)
                    iterBorders(matrix, consumer, start, new Coordinate(current.x, current.y + 1), end);

                if (matrix.getColumnCount() > 1) {
                    // Lateral inferior
                    if (current.y == end.y && current.x > start.x)
                        iterBorders(matrix, consumer, start, new Coordinate(current.x - 1, current.y), end);

                    // Lateral esquerda
                    else if (current.x == start.x && current.y > start.y)
                        iterBorders(matrix, consumer, start, new Coordinate(current.x, current.y - 1), end);
                }
            }
        }
    }

    private static <T> void iterSpiral(Matrix<T> matrix, Consumer<T> consumer, Coordinate start, Coordinate end) {
        if (start.lesserThan(end) || start.equals(end)) {
            iterBorders(matrix, consumer, start, null, end);
            iterSpiral(matrix, consumer, new Coordinate(start.x + 1, start.y + 1), new Coordinate(end.x - 1, end.y - 1));
        }
    }

    @Override
    public <T> void process(Matrix<T> matrix, Consumer<T> consumer) {
        iterSpiral(matrix, consumer, new Coordinate(0, 0), new Coordinate(matrix.getColumnCount() - 1, matrix.getLineCount() - 1));
    }
}

