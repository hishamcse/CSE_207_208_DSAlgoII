import java.util.LinkedList;
import java.util.function.Function;

public class SeparateChaining<Key, Value> extends AbstractHashTable<Key, Integer> {

    private final Function<Key, Value> hashFunc;
    private final LinkedList<KeyValuePair<Key>>[] chains;

    public SeparateChaining(int tableLength, Function<Key, Value> hashFunc) {
        super(tableLength);
        this.hashFunc = hashFunc;
        chains = (LinkedList<KeyValuePair<Key>>[]) new LinkedList[this.tableLength];

        for (int i = 0; i < this.tableLength; i++) {
            chains[i] = new LinkedList<>();
        }
    }

    // 0 -> inserted, 1 -> collision, -1 -> not inserted
    @Override
    int insert(Key key) {
        int hashIndex = (int) hashFunc.apply(key);

        if (chains[hashIndex].size() == 0) {
            chains[hashIndex].addFirst(new KeyValuePair<>(key, ++this.pairCount));
            return 0;                                // insert and no collision
        }

        for (KeyValuePair<Key> pair : chains[hashIndex]) {
            if (pair.key.equals(key)) return -1;      // no insertion
        }

        chains[hashIndex].addFirst(new KeyValuePair<>(key, ++this.pairCount));
        this.collisionCount++;
        return 1;                                    // insert but collision
    }

    @Override
    Integer search(Key key) {
        int hashIndex = (int) hashFunc.apply(key);

        if (chains[hashIndex].size() != 0) {
            for (KeyValuePair<Key> pair : chains[hashIndex]) {
                this.probeCount++;
                if (pair.key.equals(key)) return pair.val;
            }
        }

        return null;    // not found
    }

    @Override
    boolean delete(Key key) {
        int hashIndex = (int) hashFunc.apply(key);

        if (chains[hashIndex].size() != 0) {
            for (KeyValuePair<Key> pair : chains[hashIndex]) {
                if (pair.key.equals(key)) {
                    chains[hashIndex].remove(pair);
//                    this.pairCount--;
                    return true;       // deleted
                }
            }
        }

        return false;       // not found
    }
}