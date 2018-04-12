import dictionary.Dictionary;
import dictionary.engine.DefaultEngine;
import org.junit.Test;

import java.util.concurrent.atomic.AtomicReference;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class DictionaryTest {

    @Test
    public void size() {
        Dictionary<String, Integer> dictionary = new Dictionary<>(100);
        assertEquals(0, dictionary.size());
    }

    @Test
    public void add() {
        Dictionary<String, Integer> dictionary = new Dictionary<>(4);

        dictionary.add("0", 0);
        assertEquals(0, (int) dictionary.get("0"));

        dictionary.add("1", 1);
        assertEquals(1, (int) dictionary.get("1"));

        dictionary.add("2", 2); // resize() ocorre aqui
        assertEquals(2, (int) dictionary.get("2"));

        dictionary.add("3", 3); // resize() ocorre aqui
        assertEquals(3, (int) dictionary.get("3"));
    }

    @Test
    public void get() {
        Dictionary<String, Integer> dictionary = new Dictionary<>(100);

        dictionary.add("0", 1);
        assertEquals(1, (int) dictionary.get("0"));

        dictionary.add("0", 2);
        assertEquals(2, (int) dictionary.get("0"));

        assertNull(dictionary.get("1"));
    }

    @Test
    public void pop() {
        Dictionary<String, Integer> dictionary = new Dictionary<>(100, new DefaultEngine<>());

        dictionary.add("valor", 1794);
        assertEquals(1794, (int) dictionary.get("valor"));

        dictionary.pop("valor");
        assertNull(dictionary.get("valor"));
    }

    @Test
    public void forEach() {
        Dictionary<Integer, Integer> dictionary = new Dictionary<>(100, new DefaultEngine<>());

        dictionary.add(1, 1);
        dictionary.add(2, 2);
        dictionary.add(3, 3);

        AtomicReference<Integer> soma = new AtomicReference<>(0);
        dictionary.forEach(i -> soma.updateAndGet(value -> value + i));

        assertEquals(6, soma.get().intValue());
    }

    @Test
    public void resize() {
        Dictionary<Integer, Integer> dictionary = new Dictionary<>(4, new DefaultEngine<>());

        dictionary.add(1, 1);
        dictionary.add(2, 2);
        dictionary.add(3, 3);
        dictionary.add(4, 1);
        dictionary.add(5, 2);
        dictionary.add(6, 3);
        assertEquals(6, dictionary.size());

        dictionary.resize(20);
        assertEquals(6, dictionary.size());
        assertEquals(3, dictionary.get(6).intValue());
    }
}