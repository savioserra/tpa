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
            behaviour.process(this, consumer);
    }

    public void setBehaviour(Behaviour behaviour) {
        this.behaviour = behaviour;
    }

    public T getElement(Coordinate coordinate) {
        return collection[coordinate.y][coordinate.x];
    }

    public int getLineCount() {
        return collection.length;
    }

    public int getColumnCount() {
        return collection[0].length;
    }
}
