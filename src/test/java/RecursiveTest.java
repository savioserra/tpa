import org.junit.Test;
import recursive.Recursive;

import static org.junit.Assert.*;

public class RecursiveTest {

    @Test
    public void sum() {
        assertEquals(11.5, Recursive.sum(new double[]{1, 2, 3, 5.5}, 0), 0);
        assertEquals(-11.5, Recursive.sum(new double[]{-1, -2, -3, -5.5}, 0), 0);
        assertEquals(0, Recursive.sum(new double[]{}, 0), 0);
    }

    @Test
    public void product() {
        assertEquals(10, Recursive.product(2, 5), 0);
        assertEquals(-10, Recursive.product(-2, 5), 0);
        assertEquals(-10, Recursive.product(2, -5), 0);
        assertEquals(10, Recursive.product(-2, -5), 0);
        assertEquals(0, Recursive.product(1000, 0), 0);
    }

    @Test
    public void division() {
        try {
            Recursive.division(10, 0);
        } catch (Exception e) {
            assertEquals(e.getClass(), ArithmeticException.class);
        }

        assertEquals(2, Recursive.division(10, 5), 0);
        assertEquals(-5, Recursive.division(-10, 2), 0);
        assertEquals(-5, Recursive.division(10, -2), 0);
        assertEquals(2, Recursive.division(15, 7), 0);
        assertEquals(1, Recursive.division(-5,-5), 0);
    }

    @Test
    public void sqrt() {
        assertEquals(8, Recursive.sqrt(64, 0, 200), 0);
        assertEquals(1.414, Recursive.sqrt(2,0.001, 200), 0.001);
    }

    @Test
    public void contains() {
        String[] list = new String[]{"a", "b", "c"};

        assertEquals(true, Recursive.contains(list, "c", 0));
        assertEquals(false, Recursive.contains(list, "k", 0));
        assertEquals(false, Recursive.contains(new String[]{}, "c", 0));
    }

    @Test
    public void backwards() {
        assertEquals("oiváS", Recursive.backwards("Sávio", 0));
        assertEquals("", Recursive.backwards("", 0));
    }

    @Test
    public void max() {
        assertEquals(10, Recursive.max(new double[]{1,9,3,10}, 0, null), 0);
        assertNull(null, Recursive.max(new double[]{}, 0, 5.0));
    }

    @Test
    public void min() {
        assertEquals(1, Recursive.min(new double[]{1,9,3,10}, 0, null), 0);
        assertNull(null, Recursive.min(new double[]{}, 0, 5.0));
    }

    @Test
    public void isPalindrome() {
        assertEquals(true, Recursive.isPalindrome("a", 0));
        assertEquals(true, Recursive.isPalindrome("", 0));
        assertEquals(true, Recursive.isPalindrome("ava", 0));
        assertEquals(false, Recursive.isPalindrome("av", 0));
    }

    @Test
    public void toBinary() {
        assertEquals("0", Recursive.toBinary(0));
        assertEquals("01", Recursive.toBinary(1));
        assertEquals("-010", Recursive.toBinary(-2));
        assertEquals("0100", Recursive.toBinary(4));
        assertEquals("010000001", Recursive.toBinary(129));
    }
}