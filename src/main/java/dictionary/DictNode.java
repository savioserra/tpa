package dictionary;


class DictNode<TKey, TValue> {
    private TKey key;
    private TValue value;

    DictNode(TKey key, TValue value) {
        this.key = key;
        this.value = value;
    }

    public TKey getKey() {
        return key;
    }

    public TValue getValue() {
        return value;
    }

    public void setValue(TValue value) {
        this.value = value;
    }
}
