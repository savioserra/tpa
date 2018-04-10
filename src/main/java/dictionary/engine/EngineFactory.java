package dictionary.engine;

public class EngineFactory {
    public static <T> HashEngine<T> createDefault() {
        return new DefaultEngine<>();
    }
}
