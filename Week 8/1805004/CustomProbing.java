import java.util.function.Function;

public class CustomProbing<Key, Value> extends DoubleHashing<Key, Value> {

    private static final int AUX_CONSTANT_1 = 13;
    private static final int AUX_CONSTANT_2 = 7;

    public CustomProbing(int tableLength, Function<Key, Value> hashFunc, Function<Key, Value> auxHash) {
        super(tableLength, hashFunc, auxHash);
    }

    int findHashIndex(int hashVal, int auxHashVal, int iter) {
        int customHash = hashVal + (AUX_CONSTANT_1 * iter * auxHashVal) % tableLength;
        customHash += (AUX_CONSTANT_2 * iter * iter) % tableLength;
        return (customHash % tableLength + tableLength) % tableLength;
    }
}