package matrix.behaviour;

import matrix.Matrix;

import java.util.function.Consumer;

public interface Behaviour {
    <T> void process(Matrix<T> matrix, Consumer<T> consumer);
}
