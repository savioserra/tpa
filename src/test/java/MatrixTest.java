import matrix.Matrix;
import matrix.behaviour.Behaviour;
import matrix.behaviour.DiagonalBehaviour;
import matrix.behaviour.SpiralBehaviour;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.Assert.*;

public class MatrixTest {

    @Test
    public void iterDiagonal() {
        Behaviour diagonal = new DiagonalBehaviour();

        Matrix<String> collection = new Matrix<>(new String[][]{
                {"a", "b", "c", "d", "e"},
                {"f", "g", "h", "i", "j"},
                {"k", "l", "m", "n", "o"},
                {"p", "q", "r", "s", "t"},
                {"u", "v", "w", "x", "y"},
        }, diagonal);

        Matrix<String> emptyCollection = new Matrix<>(new String[][]{}, diagonal);
        collection.forEach(System.out::println);
    }

    @Test
    public void iterSpiral() {
        Behaviour spiral = new SpiralBehaviour();

        Matrix<String> collection = new Matrix<>(new String[][]{
                {"a", "b", "c", "d", },
                {"f", "g", "h", "i", },
        }, spiral);

        Matrix<String> emptyCollection = new Matrix<>(new String[][]{}, spiral);
        collection.forEach(System.out::println);
    }
}