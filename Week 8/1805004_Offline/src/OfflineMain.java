/*
 * Name: Syed Jarullah Hisham
 * Roll: 1805004
 * CSE'18 Section A1
 */

import java.util.Scanner;

public class OfflineMain {

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println(ColorCode.ANSI_RESET + "Enter the value of N: ");
        int N = scanner.nextInt();
        HashMethods hashMethods = new HashMethods(N);
        AbstractHashTable<String, Integer> hashTable;

        boolean exit2 = false;
        while (!exit2) {
            printInstruction1();
            int option1 = scanner.nextInt();
            hashTable = option1 == 1 ? new SeparateChaining<>(N, hashMethods::HashFunc1)
                    : option1 == 2 ? new DoubleHashing<>(N, hashMethods::HashFunc2, hashMethods::auxiliaryHashFunc)
                    : option1 == 3 ? new CustomProbing<>(N, hashMethods::HashFunc1, hashMethods::auxiliaryHashFunc) :
                    null;
            if (option1 == 4) {
                break;
            }

            while (!exit2) {
                printInstruction2();
                int option2 = scanner.nextInt();
                assert hashTable != null;
                switch (option2) {
                    case 1:
                        insert(hashTable);
                        break;
                    case 2:
                        search(hashTable);
                        break;
                    case 3:
                        delete(hashTable);
                        break;
                    case 4:
                        exit2 = true;
                        break;
                    default:
                        System.out.println(ColorCode.ANSI_RED + "Please enter a valid option!!");
                }
            }
        }
    }

    private static void printInstruction1() {
        String sb =
                ColorCode.ANSI_BLUE + "\nChoose any one technique below:\n" +
                        ColorCode.ANSI_RESET + "1. Separate Chaining\n" +
                        "2. Double Hashing\n" +
                        "3. Custom Probing\n" +
                        "4. Press 4 to exit the process";
        System.out.println(sb);
    }

    private static void printInstruction2() {
        String sb =
                ColorCode.ANSI_GREEN + "\nChoose any one operation below:\n" +
                        ColorCode.ANSI_RESET + "1. Insert\n" +
                        "2. Search\n" +
                        "3. Delete\n" +
                        "4. Press 4 to exit the process";
        System.out.println(sb);
    }

    private static void insert(AbstractHashTable<String, Integer> hashTable) {
        System.out.println(ColorCode.ANSI_PURPLE + "Enter a string to insert");
        String str = scanner.next();
        int res;
        try {
            res = hashTable.insert(str);
            if (res == -1) System.out.println(ColorCode.ANSI_RED + "Already exists");
            else System.out.println(ColorCode.ANSI_CYAN + "Inserted successfully!!");
        } catch (StackOverflowError ignored) {
            System.out.println(ColorCode.ANSI_RED + "Cannot insert due to overflow");
        }
    }

    private static void search(AbstractHashTable<String, Integer> hashTable) {
        System.out.println(ColorCode.ANSI_PURPLE + "Enter a string to search");
        String str = scanner.next();
        Integer val = hashTable.search(str);
        if (val == null) {
            System.err.println(ColorCode.ANSI_RED + "Can't find the string");
        } else {
            System.out.println(ColorCode.ANSI_CYAN + "Value in the hashtable: " + val);
        }
    }

    private static void delete(AbstractHashTable<String, Integer> hashTable) {
        System.out.println(ColorCode.ANSI_PURPLE + "Enter a string to delete");
        String str = scanner.next();
        if (!hashTable.delete(str)) {
            System.err.println(ColorCode.ANSI_RED + "Deletion failed!!");
        } else {
            System.out.println(ColorCode.ANSI_CYAN + "Deletion successful");
        }
    }
}