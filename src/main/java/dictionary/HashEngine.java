package dictionary;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;

public abstract class HashEngine<T> {
    protected HashEngine() {
    }

    public int getHash(T key) throws IOException {
        return applyHashFunction(key);
    }

    protected byte[] toBytesStream(T object) throws IOException {
        try (ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream()) {

            try (ObjectOutput output = new ObjectOutputStream(byteArrayOutputStream)) {
                output.writeObject(object);
            }

            return byteArrayOutputStream.toByteArray();
        }
    }

    protected abstract int applyHashFunction(T key) throws IOException;
}