import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class RandomWordGenerator {

    private final int wordCount;
    private final int wordLength;
    private final List<Character> alphabets;
    Random random;

    public RandomWordGenerator(int wordCount, int wordLength) {
        this.wordCount = wordCount;
        this.wordLength = wordLength;
        alphabets = new ArrayList<>();
        random = new Random();

        for (char ch = 'a'; ch <= 'z'; ch++) {
            alphabets.add(ch);
        }
    }

    public String randomWord() {
        Collections.shuffle(alphabets);
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < wordLength; i++) {
            sb.append(alphabets.get(random.nextInt('z' - 'a' + 1)));
        }

        return sb.toString();
    }

    // may have duplicate words
    public List<String> generateWords() {
        List<String> allWords = new ArrayList<>();

        for (int i = 0; i < wordCount; i++) {
            allWords.add(randomWord());
        }

        return allWords;
    }

    // all the generated words must be unique
    public List<String> generateUniqueWords() {
        List<String> allWords = new ArrayList<>();

        while (allWords.size() != wordCount) {
            String str = randomWord();
            if (allWords.contains(str)) continue;
            allWords.add(str);
        }

        return allWords;
    }

    // randomly take count number words among generated list
    public List<String> randomlySelectedWords(List<String> words, int count) {
        List<String> selected = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            selected.add(words.get(random.nextInt(wordCount)));
        }
        return selected;
    }

    // for checking the generator
    public static void main(String[] args) {
        RandomWordGenerator generator = new RandomWordGenerator(10000, 7);
        System.out.println(generator.generateWords());
        System.out.println(generator.generateUniqueWords());
    }
}