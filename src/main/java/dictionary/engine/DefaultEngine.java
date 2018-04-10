package dictionary.engine;

import java.io.IOException;

public class DefaultEngine<T> extends HashEngine<T> {

    public DefaultEngine() {
        super();
    }

    @Override
    protected int applyHashFunction(T t) {
        int hashCode = 0;
        byte[] stream;

        try {
            stream = toBytesStream(t);
        } catch (IOException e) {
            stream = new byte[]{1};
        }

        for (byte aByte : stream)
            hashCode ^= (hashCode << 5) + (hashCode >> 2) + aByte;

        return Math.abs(hashCode);
    }
}
