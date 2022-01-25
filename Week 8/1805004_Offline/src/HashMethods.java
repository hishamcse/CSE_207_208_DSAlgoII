
public class HashMethods {

    private final static int MAGIC_PRIME = 41;
    private final static int MAGIC_PRIME_2 = 7;
    private final static int MODULUS = (int) (1e9 + 7);

    private final int tableLength;
    private final long[] crc32_table;

    public HashMethods(int tableLength) {
        this.tableLength = AbstractHashTable.primeTableLength(tableLength);
        crc32_table = new long[256];
        populate_crc32_table();
    }

    // simple hashing (modular hashing)
    public int auxiliaryHashFunc(String str) {
        int hashVal = MAGIC_PRIME_2;

        for (int i = 0; i < str.length(); i++) {
            hashVal = (MAGIC_PRIME * hashVal + str.charAt(i)) % tableLength;
        }

        return (hashVal % tableLength + tableLength) % tableLength;
    }

    // personally customized method
    public int HashFunc1(String str) {
        int hashVal = MODULUS;
        int higherOrder;

        for (int i = 0; i < str.length(); i++) {
            higherOrder = hashVal & 0xFE000000;
            hashVal ^= (hashVal << MAGIC_PRIME_2) ^ (hashVal >> (Integer.SIZE - MAGIC_PRIME_2)) ^ str.charAt(i);
            hashVal ^= (higherOrder << (Integer.SIZE - MAGIC_PRIME_2));
            hashVal += MAGIC_PRIME;
        }

        return (hashVal % tableLength + tableLength) % tableLength;
    }

    // crc32 table for crc32 hashing
    private void populate_crc32_table() {
        long remainder;
        for (int i = 0; i < 256; i++) {
            remainder = i;
            for (int j = 0; j <= MAGIC_PRIME_2; j++) {
                if ((remainder & 1) != 0) remainder ^= 0x1db710640L;
                remainder >>= 1;
            }
            crc32_table[i] = remainder;
        }
    }

    // CRC 32 hashing
    public int HashFunc2(String str) {
        long crc32 = 0xffffffff;

        for (int i = 0; i < str.length(); i++) {
            crc32 = (crc32 >> 8) ^ this.crc32_table[(int) ((crc32 & 0xff) ^ str.charAt(i))];
        }

        crc32 ^= 0xffffffff;
        return (int) (crc32 % tableLength);
    }
}