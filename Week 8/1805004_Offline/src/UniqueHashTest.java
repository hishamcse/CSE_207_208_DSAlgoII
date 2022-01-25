/*
 * Utility class to test the uniqueness percentage of hash functions
 */

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class UniqueHashTest {

    private static final int LOOP_COUNT = 10;

    public double uniquenessPercentage(List<String> words, int type) {
        HashMethods hashMethods = new HashMethods(words.size() + 7);

        List<Integer> hashes = new ArrayList<>();
        Set<Integer> uniqueHashes = new HashSet<>();

        for (String word : words) {
            int hash = type == 1 ? hashMethods.HashFunc1(word) : hashMethods.HashFunc2(word);
            hashes.add(hash);
            uniqueHashes.add(hash);
        }

        double percentage = ((double) uniqueHashes.size() / hashes.size()) * 100;

        if (percentage < 60.0) {
            System.out.println(ColorCode.ANSI_RED + type + " " + percentage);
        } else {
            System.out.println(ColorCode.ANSI_RESET + type + " " + percentage);
        }

        return percentage;
    }

    public static void main(String[] args) {
        RandomWordGenerator generator = new RandomWordGenerator(10000, 7);
        UniqueHashTest uniqueHashTest = new UniqueHashTest();

        double avgUniqueness1 = 0.0;
        double avgUniqueness2 = 0.0;
        int loop = 0;

        while (loop < LOOP_COUNT) {
            List<String> words = generator.generateUniqueWords();
            avgUniqueness1 += uniqueHashTest.uniquenessPercentage(words, 1);
            avgUniqueness2 += uniqueHashTest.uniquenessPercentage(words, 2);
            loop++;
        }

        System.out.println(ColorCode.ANSI_BLUE + " Hash 1: " + avgUniqueness1 / LOOP_COUNT);
        System.out.println(ColorCode.ANSI_BLUE + " Hash 2: " + avgUniqueness2 / LOOP_COUNT);
    }
}