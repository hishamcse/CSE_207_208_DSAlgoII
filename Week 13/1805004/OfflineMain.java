/*
   Name : Syed Jarullah Hisham
   Roll: 1805004
   CSE'18 Section A1
 */

public class OfflineMain {

    public static void main(String[] args) {
        FileUtils fileUtils = new FileUtils("./input.txt");
        AVLTree avlTree = new AVLTree();

        while (fileUtils.hasNextLine()) {
            String operation = fileUtils.readString();
            int key;
            boolean balanced;
            switch (operation) {
                case "F":
                    String str = avlTree.searchItem(fileUtils.readInt()) ? "True" : "False";
                    System.out.println(ColorCode.ANSI_PURPLE + str);
                    break;
                case "I":
                    key = fileUtils.readInt();
                    balanced = avlTree.insertItem(key);
                    if (!balanced) {
                        System.out.print(ColorCode.ANSI_CYAN + "Height invariant violated.\nAfter rebalancing: ");
                    }
                    System.out.print(ColorCode.ANSI_RESET);
                    avlTree.printTree();
                    System.out.println();
                    break;
                case "D":
                    key = fileUtils.readInt();
                    balanced = avlTree.deleteItem(key);
                    if (!balanced) {
                        System.out.print(ColorCode.ANSI_CYAN + "Height invariant violated.\nAfter rebalancing: ");
                    }
                    System.out.print(ColorCode.ANSI_RESET);
                    avlTree.printTree();
                    System.out.println();
                    break;
                default:
                    System.out.println(ColorCode.ANSI_RED + "Invalid operation found");
                    break;
            }
        }
    }
}