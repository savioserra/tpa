import dictionary.Dictionary;
import dictionary.engines.DefaultEngine;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class DictionaryTest {

    @Test
    public void getSize() {
        Dictionary<String, Integer> dictionary = new Dictionary<>(100);
        assertEquals(0, dictionary.lenght());
    }

    @Test
    public void add() throws IOException {
        Dictionary<String, Integer> dictionary = new Dictionary<>(100);

        dictionary.add("0", 1);
        assertEquals(1, (int) dictionary.get("0"));

        dictionary.add("0", 2);
        assertEquals(2, (int) dictionary.get("0"));
    }

    @Test
    public void get() throws IOException {
        Dictionary<String, Integer> dictionary = new Dictionary<>(100);

        dictionary.add("0", 1);
        assertEquals(1, (int) dictionary.get("0"));

        dictionary.add("0", 2);
        assertEquals(2, (int) dictionary.get("0"));

        assertNull(dictionary.get("1"));
    }

    @Test
    public void remove() throws IOException {
        Dictionary<String, Integer> dictionary = new Dictionary<>(100, new DefaultEngine<>());

        dictionary.add("valor", 1794);
        assertEquals(1794, (int) dictionary.get("valor"));

        dictionary.remove("valor");
        assertNull(dictionary.get("valor"));
    }
}