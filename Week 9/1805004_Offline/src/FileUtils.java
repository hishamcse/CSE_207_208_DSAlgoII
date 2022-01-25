import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Locale;
import java.util.Scanner;

public class FileUtils {

    private static final String CHARSET_NAME = "UTF-8";

    private final Scanner scanner;

    public FileUtils(String fileName) {
        try {
            File file = new File(fileName);
            FileInputStream inputStream = new FileInputStream(file);
            scanner = new Scanner(new BufferedInputStream(inputStream), CHARSET_NAME);
            scanner.useLocale(Locale.US);
        } catch (IOException e) {
            throw new IllegalArgumentException("Couldn't open " + fileName);
        }
    }

    public int readInt() {
        return scanner.nextInt();
    }

    public double readDouble() {
        return scanner.nextDouble();
    }

    public String readString() {
        return scanner.next();
    }

    public String readLine() {
        return scanner.nextLine();
    }

    public boolean hasNextLine() {
        return scanner.hasNextLine();
    }

    public boolean isEmpty() {
        return !scanner.hasNext();
    }
}