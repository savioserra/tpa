package dictionary;

import dictionary.engines.DefaultEngine;

public class EngineFactory {
    private EngineFactory() {
    }

    public static <T> HashEngine<T> createDefault() {
        return new DefaultEngine<>();
    }
}
