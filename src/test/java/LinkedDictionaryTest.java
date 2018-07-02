import dictionary.LinkedDictionary;
import dictionary.engine.DefaultEngine;
import org.junit.Test;

import java.util.concurrent.atomic.AtomicReference;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class LinkedDictionaryTest {

    @Test
    public void size() {
        LinkedDictionary<String, Integer> dictionary = new LinkedDictionary<>(100);
        assertEquals(0, dictionary.size());
    }

    @Test
    public void add() {
        LinkedDictionary<String, Integer> dictionary = new LinkedDictionary<>(0);

        dictionary.add("0", 0);
        assertEquals(0, (int) dictionary.get("0"));

        dictionary.add("1", 1);
        assertEquals(1, (int) dictionary.get("1"));

        dictionary.add("2", 2); // resize() ocorre aqui
        assertEquals(2, (int) dictionary.get("2"));

        dictionary.add("3", 3); // resize() ocorre aqui
        assertEquals(3, (int) dictionary.get("3"));

        System.out.println(dictionary.values());
        System.out.println(dictionary.keys());
    }

    @Test
    public void get() {
        LinkedDictionary<String, Integer> dictionary = new LinkedDictionary<>(100);

        dictionary.add("0", 1);
        assertEquals(1, (int) dictionary.get("0"));

        dictionary.add("0", 2);
        assertEquals(2, (int) dictionary.get("0"));

        assertNull(dictionary.get("1"));
    }

    @Test
    public void pop() {
        LinkedDictionary<String, Integer> dictionary = new LinkedDictionary<>(100, new DefaultEngine<>());

        dictionary.add("valor", 1794);
        assertEquals(1794, (int) dictionary.get("valor"));

        dictionary.pop("valor");
        assertNull(dictionary.get("valor"));
    }

    @Test
    public void forEach() {
        LinkedDictionary<Integer, Integer> dictionary = new LinkedDictionary<>(100, new DefaultEngine<>());

        dictionary.add(1, 1);
        dictionary.add(2, 2);
        dictionary.add(3, 3);

        AtomicReference<Integer> soma = new AtomicReference<>(0);
        dictionary.forEach(i -> soma.updateAndGet(value -> value + i));

        assertEquals(6, soma.get().intValue());
    }

    @Test
    public void resize() {
        LinkedDictionary<Integer, Integer> dictionary = new LinkedDictionary<>(4, new DefaultEngine<>());

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