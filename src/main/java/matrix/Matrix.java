package matrix;

import matrix.behaviour.Behaviour;

import java.util.function.Consumer;

public class Matrix<T> {
    private Behaviour behaviour;
    private T[][] collection;

    public Matrix(T[][] collection, Behaviour behaviour) {
        this.behaviour = behaviour;
        this.collection = collection;
    }

    public void forEach(Consumer<T> consumer) {
        if (collection.length > 0)
            behaviour.process(collection, consumer);
    }

    public void setBehaviour(Behaviour behaviour) {
        this.behaviour = behaviour;
    }
}
