package matrix.behaviour;

import java.util.function.Consumer;

public interface Behaviour {
    <T> void process(T[][] collection, Consumer<T> consumer);
}
