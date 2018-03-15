package matrix;

public class Coordinate implements Comparable {
    public final int x;
    public final int y;

    public Coordinate(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public boolean equals(Object o) {
        return o instanceof Coordinate && compareTo(o) == 0;
    }

    @Override
    public int compareTo(Object o) {
        Coordinate other = (Coordinate) o;
        return y > other.y ? 1 : (y == other.y ? Integer.compare(x, other.x) : -1);
    }

    public boolean greaterThan(Coordinate other) {
        return compareTo(other) > 0;
    }

    public boolean lesserThan(Coordinate other) {
        return compareTo(other) < 0;
    }
}
