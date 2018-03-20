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

        ArrayList<String> normalCase = new ArrayList<>(Arrays.asList("a", "g", "m", "s", "y"));
        ArrayList<String> output = new ArrayList<>();

        Matrix<String> emptyCollection = new Matrix<>(new String[][]{
                // empty
        }, diagonal);

        Matrix<String> collection = new Matrix<>(new String[][]{
                {"a", "b", "c", "d", "e"},
                {"f", "g", "h", "i", "j"},
                {"k", "l", "m", "n", "o"},
                {"p", "q", "r", "s", "t"},
                {"u", "v", "w", "x", "y"},
        }, diagonal);

        Matrix<String> errorCollection = new Matrix<>(new String[][]{
                {"a", "b", "c", "d", "e"},
                {"f", "g", "h", "i", "j"},
                {"k", "l", "m", "n", "o"},
        }, diagonal);

        try {
            errorCollection.forEach(System.out::println);
        } catch (Exception e) {
            assertEquals(e.getClass(), IllegalArgumentException.class);
        }

        emptyCollection.forEach(output::add);
        assertEquals(new ArrayList<String>(), output);

        collection.forEach(output::add);
        assertEquals(normalCase, output);
    }

    @Test
    public void iterSpiral() {
        Behaviour spiral = new SpiralBehaviour();

        Matrix<String> collection = new Matrix<>(new String[][]{
                {"a", "b", "c", "d",},
                {"f", "g", "h", "i",},
        }, spiral);

        Matrix<String> caseCollection = new Matrix<>(new String[][]{
                {"a"},
                {"f"},
                {"g"},
        }, spiral);

        Matrix<String> caseTwoCollection = new Matrix<>(new String[][]{
                {"a"}
        }, spiral);

        Matrix<String> caseThreeCollection = new Matrix<>(new String[][]{
                // empty
        }, spiral);

        Matrix<String> emptyCollection = new Matrix<>(new String[][]{}, spiral);
        //collection.forEach(System.out::println);
        //caseCollection.forEach(System.out::println);
        //caseTwoCollection.forEach(System.out::println);
        caseThreeCollection.forEach(System.out::println);
    }
}