public abstract class AbstractHashTable<Key, Value> {

    protected int tableLength;
    protected int pairCount;
    protected int collisionCount;
    protected int probeCount;

    public AbstractHashTable(int tableLength) {
        this.tableLength = primeTableLength(tableLength);
        this.pairCount = 0;
        this.collisionCount = 0;
        this.probeCount = 0;
    }

    public static int primeTableLength(int length) {
        if (length <= 2) return 2;

        int primeLen = length % 2 == 0 ? length + 1 : length;

        while (true) {
            if (isPrime(primeLen)) return primeLen;
            primeLen += 2;
        }
    }

    private static boolean isPrime(int num) {
        if (num <= 1) return false;
        if (num <= 3) return true;
        if (num % 2 == 0 || num % 3 == 0) return false;

        for (int i = 5; i * i <= num; i = i + 6) {
            if (num % i == 0 || num % (i + 2) == 0) return false;
        }

        return true;
    }

    abstract int insert(Key key);

    abstract Integer search(Key key);

    abstract boolean delete(Key key);

    public int totalCollisionCount() {
        return this.collisionCount;
    }

    public int totalProbeCount() {
        return this.probeCount;
    }

    public int totalPairInTable() {
        return pairCount;
    }

    public int getTableLength() {
        return tableLength;
    }
}