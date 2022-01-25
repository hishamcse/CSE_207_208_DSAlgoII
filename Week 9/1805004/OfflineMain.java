/*
   Name : Syed Jarullah Hisham
   Roll: 1805004
   CSE'18 Section A1
 */

public class OfflineMain {

    public static void main(String[] args) {
        FileUtils fileUtils = new FileUtils("./input.txt");
        BinomialMaxHeap<Integer> maxHeap = new BinomialMaxHeap<>();

        while (fileUtils.hasNextLine()) {
            String operation = fileUtils.readString();
            switch (operation) {
                case "FIN":
                    System.out.println(ColorCode.ANSI_CYAN + "FindMax returned " + maxHeap.findMax());
                    break;
                case "EXT":
                    System.out.println(ColorCode.ANSI_YELLOW + "ExtractMax returned " + maxHeap.extractMax());
                    break;
                case "INS":
                    int key = fileUtils.readInt();
                    maxHeap.insert(key);
                    System.out.println(ColorCode.ANSI_GREEN + "Inserted " + key);
                    break;
                case "INC":
                    int prevKey = fileUtils.readInt();
                    int newKey = fileUtils.readInt();
                    if (maxHeap.increase_key(prevKey, newKey)) {
                        System.out.println(ColorCode.ANSI_PURPLE + "Increased " + prevKey + ". The updated value is " + newKey + ".");
                    } else {
                        System.out.println(ColorCode.ANSI_RED + "Increment failed!! Key doesn't exist");
                    }
                    break;
                case "PRI":
                    System.out.println(ColorCode.ANSI_RESET  + "Printing Binomial Heap...");
                    System.out.println("----------------------------");
                    maxHeap.print();
                    System.out.println("----------------------------");
                    continue;
                default:
                    System.out.println(ColorCode.ANSI_RED + "Invalid operation found");
                    break;
            }
        }
    }
}