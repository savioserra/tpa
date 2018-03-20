import matrix.Matrix;
import matrix.behaviour.Behaviour;
import matrix.behaviour.DiagonalBehaviour;
import matrix.behaviour.SpiralBehaviour;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

import static org.junit.Assert.*;

public class MatrixTest {

    @Test
    public void iterDiagonal() {
        Behaviour diagonal = new DiagonalBehaviour();

        ArrayList<String> normalCase = new ArrayList<>(Arrays.asList("a", "g", "m", "s", "y"));

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

        ArrayList<String> output = new ArrayList<>();

        emptyCollection.forEach(output::add);
        assertEquals(new ArrayList<String>(), output);

        collection.forEach(output::add);
        assertEquals(normalCase, output);
    }

    @Test
    public void iterSpiral() {
        Behaviour spiral = new SpiralBehaviour();
        ArrayList<String> output;

        Matrix<String> collectionOne = new Matrix<>(new String[][]{
                {"a", "b", "c"},
                {"f", "g", "h"},
        }, spiral);

        Matrix<String> collectionTwo = new Matrix<>(new String[][]{
                {"a"},
                {"f"},
                {"g"},
        }, spiral);

        Matrix<String> collectionThree = new Matrix<>(new String[][]{
                {"a"}
        }, spiral);

        Matrix<String> collectionFour = new Matrix<>(new String[][]{
                // empty
        }, spiral);

        output = new ArrayList<>();
        collectionOne.forEach(output::add);
        assertEquals(new ArrayList<>(Arrays.asList("a", "b", "c", "h", "g", "f")), output);

        output = new ArrayList<>();
        collectionTwo.forEach(output::add);
        assertEquals(new ArrayList<>(Arrays.asList("a", "f", "g")), output);

        output = new ArrayList<>();
        collectionThree.forEach(output::add);
        assertEquals(new ArrayList<>(Collections.singletonList("a")), output);

        output = new ArrayList<>();
        collectionFour.forEach(output::add);
        assertEquals(new ArrayList<String>(), output);
    }
}