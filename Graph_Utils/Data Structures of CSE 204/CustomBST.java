/*
 * User defined Binary Search Tree Implementation
 */

public class CustomBST {

    private BSTNode root;                              // root of the tree
    private boolean exist = false;                     // to identify whether the result of the operation exists or not

    public boolean insertItem(int item) {                         // 1. true if insertion successful otherwise false
        BSTNode node = new BSTNode(item);
        if (root == null) {
            root = node;
            return true;
        }

        BSTNode current = root;
        BSTNode predecessor = null;
        while (current != null) {
            predecessor = current;
            if (item < current.item) current = current.leftChild;
            else if (item > current.item) current = current.rightChild;
            else return false;                                    // already item exists. so, no insertion possible
        }

        if (item < predecessor.item) predecessor.leftChild = node;
        else predecessor.rightChild = node;
        return true;
    }

    public boolean searchItem(int item) {                         // 2. true if searching successful otherwise false
        if (root == null) return false;

        BSTNode current = root;
        while (current != null) {
            if (item < current.item) current = current.leftChild;
            else if (item > current.item) current = current.rightChild;
            else return true;                                    // item found
        }
        return false;
    }

    public int getInOrderSuccessor(int item) {                   // 3. if not found, then return MAX Integer possible
        if (root == null) return Integer.MAX_VALUE;
        exist = false;                                   // checks whether the item exists or not

        BSTNode current = root;
        BSTNode required = null;
        while (current != null) {
            if (item < current.item) {
                required = current;
                current = current.leftChild;
            } else {
                if (item == current.item) exist = true;
                current = current.rightChild;
            }
        }

        if (required == null || !exist) return Integer.MAX_VALUE;
        return required.item;
    }

    public int getInOrderPredecessor(int item) {                // 4. if not found, then return MIN Integer possible
        if (root == null) return Integer.MIN_VALUE;
        exist = false;                                  // checks whether the item exists or not

        BSTNode current = root;
        BSTNode required = null;
        while (current != null) {
            if (item > current.item) {
                required = current;
                current = current.rightChild;
            } else {
                if (item == current.item) exist = true;
                current = current.leftChild;
            }
        }

        if (required == null || !exist) return Integer.MIN_VALUE;
        return required.item;
    }

    public boolean deleteItem(int item) {                       // 5. true if deletion successful, otherwise false
        if (root == null) return false;

        exist = false;
        root = delete(root, item);
        return exist;
    }

    private BSTNode delete(BSTNode current, int item) {        // helper recursive method for deletion
        if (current == null) return null;

        if (item < current.item) current.leftChild = delete(current.leftChild, item);
        else if (item > current.item) current.rightChild = delete(current.rightChild, item);
        else {
            exist = true;
            if (current.leftChild == null) return current.rightChild;
            if (current.rightChild == null) return current.leftChild;

            BSTNode node = current;
            current = minNode(node.rightChild);
            current.rightChild = deleteMin(node.rightChild);
            current.leftChild = node.leftChild;
        }
        return current;
    }

    private BSTNode deleteMin(BSTNode current) {                  // helper method for minimum item deletion
        if (current.leftChild == null) return current.rightChild;
        current.leftChild = deleteMin(current.leftChild);
        return current;
    }

    public int getItemDepth(int item) {                           // 6. returns depth or -1 if not found
        if (root == null) return -1;
        exist = false;
        int depth = depth(root, item);
        return (exist) ? depth : -1;
    }

    private int depth(BSTNode current, int item) {               // private helper method to find depth
        if (current == null) return -1;
        if (current.item == item) {
            exist = true;
            return 0;
        }
        if (item < current.item) return 1 + depth(current.leftChild, item);
        else return 1 + depth(current.rightChild, item);
    }

    public int getMaxItem() {                                // 7. returns the maximum otherwise MIN Integer if tree is empty
        if (root == null) return Integer.MIN_VALUE;
        return maxNode(root).item;
    }

    private BSTNode maxNode(BSTNode current) {                      // helper to find largest number w.r.t a node
        while (current.rightChild != null) {
            current = current.rightChild;
        }
        return current;
    }

    public int getMinItem() {                                // 8. returns the minimum otherwise MAX Integer if tree is empty
        if (root == null) return Integer.MAX_VALUE;
        return minNode(root).item;
    }

    private BSTNode minNode(BSTNode current) {                      // helper to find smallest number w.r.t a node
        while (current.leftChild != null) {
            current = current.leftChild;
        }
        return current;
    }

    public int getHeight() {                                        // 9. height of the tree
        if (root == null) return -1;
        return height(root);
    }

    private int height(BSTNode current) {
        if (current == null) return -1;
        return 1 + Math.max(height(current.leftChild), height(current.rightChild));
    }

    public void printInOrder() {                                     // 10. Inorder traversal
        InOrderTraversal(root);
    }

    private void InOrderTraversal(BSTNode current) {
        if (current == null) return;
        InOrderTraversal(current.leftChild);
        System.out.print(current.item + " ");
        InOrderTraversal(current.rightChild);
    }

    public void printPreOrder() {                                     // 11. PreOrder traversal
        preOrderTraversal(root);
    }

    private void preOrderTraversal(BSTNode current) {
        if (current == null) return;
        System.out.print(current.item + " ");
        preOrderTraversal(current.leftChild);
        preOrderTraversal(current.rightChild);
    }

    public void printPostOrder() {                                     // 12. PostOrder traversal
        postOrderTraversal(root);
    }

    private void postOrderTraversal(BSTNode current) {
        if (current == null) return;
        postOrderTraversal(current.leftChild);
        postOrderTraversal(current.rightChild);
        System.out.print(current.item + " ");
    }

    public int getSize() {                                             // 13. Size of the tree
        return size(root);
    }

    private int size(BSTNode current) {
        if (current == null) return 0;
        return 1 + size(current.leftChild) + size(current.rightChild);
    }
}