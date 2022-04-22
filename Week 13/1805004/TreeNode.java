public class TreeNode {

    public int item;
    public TreeNode leftChild, rightChild;
    public int height;

    public TreeNode(int item) {
        this.item = item;
        this.height = 0;
    }
}