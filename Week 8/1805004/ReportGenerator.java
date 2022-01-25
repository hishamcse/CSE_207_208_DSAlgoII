import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public class ReportGenerator {

    private static final int TABLE_LENGTH = 10007;     // 10007, 50021, 100019
    private static final int WORD_COUNT = 10000;       // 10000, 50000, 100000
    private static final int SELECTED_COUNT = 1000;    // 1000, 5000, 10000
    private static final int WORD_LENGTH = 7;

    public static void main(String[] args) {
        RandomWordGenerator generator = new RandomWordGenerator(WORD_COUNT, WORD_LENGTH);
        HashMethods hashMethods = new HashMethods(TABLE_LENGTH);
        List<String> words;
        AbstractHashTable<String, Integer> hashTable;
        int collisionCount;
        double avg_probeCount;

        // 1 -> separate chaining; 2 -> double hashing; 3 -> custom probing
        for (int technique = 1; technique <= 3; technique++) {

            if (technique == 1) System.out.println(ColorCode.ANSI_GREEN + "Separate Chaining Technique\n ");
            else if (technique == 2) System.out.println(ColorCode.ANSI_BLUE + "Double Hashing Technique\n ");
            else System.out.println(ColorCode.ANSI_PURPLE + "Custom Probing Technique\n ");

            for (int funcNum = 1; funcNum <= 2; funcNum++) {

                if (funcNum == 1) System.out.println(ColorCode.ANSI_RESET + "Result For Hash Function 1: ");
                else System.out.println(ColorCode.ANSI_RESET + "Result For Hash Function 2: ");

                Function<String, Integer> hashFunc = funcNum == 1 ? hashMethods::HashFunc1 : hashMethods::HashFunc2;
                collisionCount = 0;
                avg_probeCount = 0.0;

                for (int i = 1; i <= 5; i++) {
                    words = new ArrayList<>();
                    hashTable = technique == 1 ? new SeparateChaining<>(TABLE_LENGTH, hashFunc)
                            : technique == 2 ? new DoubleHashing<>(TABLE_LENGTH, hashFunc, hashMethods::auxiliaryHashFunc)
                            : new CustomProbing<>(TABLE_LENGTH, hashFunc, hashMethods::auxiliaryHashFunc);

                    while (words.size() < WORD_COUNT) {
                        String str = generator.randomWord();
                        try {
                            hashTable.insert(str);
                            words.add(str);
                        } catch (StackOverflowError ignored) {
                        }
                    }

                    List<String> randomlySelected = generator.randomlySelectedWords(words, SELECTED_COUNT);

                    for (String word : randomlySelected) {
                        if (hashTable.search(word) == null) {
                            System.err.println("search unsuccessful");
                        }
                    }

                    collisionCount += hashTable.totalCollisionCount();
                    avg_probeCount += (double) hashTable.totalProbeCount() / SELECTED_COUNT;
                }

                int total_collision = collisionCount / 5;
                double avg_probe = avg_probeCount / 5;
                System.out.println(ColorCode.ANSI_CYAN + "Number Of Collisions: " + total_collision);
                System.out.println(ColorCode.ANSI_GREEN + "Average Probes: " + avg_probe);
                System.out.println();
            }
        }
    }
}