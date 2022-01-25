import java.util.function.Function;

public class DoubleHashing<Key, Value> extends AbstractHashTable<Key, Value> {

    private final Function<Key, Value> hashFunc;
    private final Function<Key, Value> auxHash;
    private final KeyValuePair<Key>[] doubleHashedTable;

    public DoubleHashing(int tableLength, Function<Key, Value> hashFunc, Function<Key, Value> auxHash) {
        super(tableLength);
        this.hashFunc = hashFunc;
        this.auxHash = auxHash;
        doubleHashedTable = (KeyValuePair<Key>[]) new KeyValuePair[this.tableLength];
    }

    int findHashIndex(int hashVal, int auxHashVal, int iter) {
        int doubleHash = hashVal + (iter * auxHashVal) % tableLength;
        return (doubleHash % tableLength + tableLength) % tableLength;
    }

    // i -> inserted with i collisions, -1 -> not inserted, overflow -> can't find a position
    @Override
    int insert(Key key) {
        int hashVal = (int) hashFunc.apply(key);
        int auxHashVal = (int) auxHash.apply(key);
        int hashIndex;

        for (int i = 0; i < tableLength; i++) {
            hashIndex = findHashIndex(hashVal, auxHashVal, i);
            if (doubleHashedTable[hashIndex] == null) {
                doubleHashedTable[hashIndex] = new KeyValuePair<>(key, ++this.pairCount);
                this.collisionCount += i;
                return i;
            } else if (doubleHashedTable[hashIndex].key.equals(key)) {
                return -1;
            }
        }
        throw new StackOverflowError("Overflow Error!!");
    }

    @Override
    Integer search(Key key) {
        int hashVal = (int) hashFunc.apply(key);
        int auxHashVal = (int) auxHash.apply(key);
        int hashIndex;

        for (int i = 0; i < tableLength; i++) {
            this.probeCount++;
            hashIndex = findHashIndex(hashVal, auxHashVal, i);
            if (doubleHashedTable[hashIndex] != null) {
                if (doubleHashedTable[hashIndex].key.equals(key)) return doubleHashedTable[hashIndex].val;
            }
        }
        return null;
    }

    @Override
    boolean delete(Key key) {
        int hashVal = (int) hashFunc.apply(key);
        int auxHashVal = (int) auxHash.apply(key);
        int hashIndex;

        int lastNotNull = -1;
        int matchedIndex = -1;

        for (int i = 0; i < tableLength; i++) {
            hashIndex = findHashIndex(hashVal, auxHashVal, i);
            if (doubleHashedTable[hashIndex] != null) {
                lastNotNull = hashIndex;
                if (doubleHashedTable[hashIndex].key.equals(key)) matchedIndex = hashIndex;
            } else {
                break;
            }
        }

        if (matchedIndex != -1) {
            doubleHashedTable[matchedIndex] = doubleHashedTable[lastNotNull];
            doubleHashedTable[lastNotNull] = null;
//            this.pairCount--;
            return true;
        }

        return false;
    }
}