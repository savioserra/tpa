package dictionary;

import dictionary.OpenAddressDictionary;
import dictionary.engine.DefaultEngine;
import org.junit.Test;

import java.util.concurrent.atomic.AtomicReference;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class OpenAddressDictionaryTest {

    @Test
    public void size() {
        OpenAddressDictionary<String, Integer> dictionary = new OpenAddressDictionary<>(100);
        assertEquals(0, dictionary.size());
    }

    @Test
    public void add() {
        OpenAddressDictionary<String, Integer> dictionary = new OpenAddressDictionary<>(0);

        dictionary.add("0", 0);
        assertEquals(0, (int) dictionary.get("0"));

        dictionary.add("1", 1);
        assertEquals(1, (int) dictionary.get("1"));

        dictionary.add("1", 2);
        assertEquals(2, (int) dictionary.get("1"));

        dictionary.add("3", 3);
        assertEquals(3, (int) dictionary.get("3"));

        assertNull(dictionary.get("AAAAAAA"));
    }

    @Test
    public void get() {
        OpenAddressDictionary<String, Integer> dictionary = new OpenAddressDictionary<>(100);

        dictionary.add("0", 1);
        assertEquals(1, (int) dictionary.get("0"));

        dictionary.add("0", 2);
        assertEquals(2, (int) dictionary.get("0"));

        assertNull(dictionary.get("1"));
    }

    @Test
    public void pop() {
        OpenAddressDictionary<String, Integer> dictionary = new OpenAddressDictionary<>(100, new DefaultEngine<>());

        dictionary.add("valor", 1794);
        assertEquals(1794, (int) dictionary.get("valor"));

        dictionary.pop("valor");
        assertNull(dictionary.get("valor"));
    }

    @Test
    public void forEach() {
        OpenAddressDictionary<Integer, Integer> dictionary = new OpenAddressDictionary<>(100, new DefaultEngine<>());

        dictionary.add(1, 1);
        dictionary.add(2, 2);
        dictionary.add(3, 3);

        AtomicReference<Integer> soma = new AtomicReference<>(0);
        dictionary.forEach(i -> soma.updateAndGet(value -> value + i));

        assertEquals(6, soma.get().intValue());
    }

    @Test
    public void resize() {
        OpenAddressDictionary<Integer, Integer> dictionary = new OpenAddressDictionary<>(0, new DefaultEngine<>());

        dictionary.add(1, 1);
        dictionary.add(2, 2);
        dictionary.add(3, 3);
        dictionary.add(4, 1);
        dictionary.add(5, 2);
        dictionary.add(6, 3);
        assertEquals(6, dictionary.size());

        dictionary.resize(20);
        dictionary.resize(2);
        assertEquals(6, dictionary.size());
        assertEquals(3, dictionary.get(6).intValue());
    }
}