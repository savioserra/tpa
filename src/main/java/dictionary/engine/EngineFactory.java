package dictionary.engine;

public class EngineFactory {
    private EngineFactory() {
    }

    public static <T> HashEngine<T> createDefault() {
        return new DefaultEngine<>();
    }
}
