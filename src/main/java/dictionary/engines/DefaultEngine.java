package dictionary.engines;

import dictionary.HashEngine;

import java.io.IOException;

public class DefaultEngine<T> extends HashEngine<T> {

    public DefaultEngine() {
        super();
    }

    @Override
    protected int applyHashFunction(T t) throws IOException {
        int hashCode = 0;
        byte[] stream = toBytesStream(t);

        for (byte aByte : stream)
            hashCode ^= (hashCode << 5) + (hashCode >> 2) + aByte;
        return Math.abs(hashCode);
    }
}
