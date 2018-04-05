package matrix.behaviour;

import matrix.Coordinate;
import matrix.Matrix;

import java.util.function.Consumer;

public class DiagonalBehaviour implements Behaviour {
    /**
     * Itera pela diagonal principal de uma matriz quadrada a partir de uma posição start.
     *
     * @param matrix   Matriz
     * @param consumer Função {@link Consumer} que consome os elementos {@link T}
     * @param start    Posição inicial da matriz ou offset
     * @param <T>      Tipo da matriz
     */
    private static <T> void iterDiagonal(Matrix<T> matrix, Consumer<T> consumer, Coordinate start) {
        if (matrix.getLineCount() != matrix.getColumnCount())
            throw new IllegalArgumentException("Arrays must have equal sizes.");

        if (start.x < matrix.getLineCount()) {
            consumer.accept(matrix.getElement(start));
            iterDiagonal(matrix, consumer, new Coordinate(start.x + 1, start.y + 1));
        }
    }

    @Override
    public <T> void process(Matrix<T> matrix, Consumer<T> consumer) {
        iterDiagonal(matrix, consumer, new Coordinate(0, 0));
    }
}
