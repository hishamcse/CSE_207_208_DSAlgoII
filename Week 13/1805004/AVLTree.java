/*
 * Implementation of AVL Tree
 */

public class AVLTree implements BalancedTree {

    private TreeNode root;
    boolean balanced = true;

    public AVLTree() {
        this.root = null;
    }

    private int height(TreeNode node) {
        return node == null ? -1 : node.height;
    }

    private void fixHeight(TreeNode node) {
        node.height = 1 + Math.max(height(node.leftChild), height(node.rightChild));
    }

    private int heightInvariant(TreeNode node) {
        return height(node.leftChild) - height(node.rightChild);
    }

    private TreeNode leftRotation(TreeNode node) {
        TreeNode current = node.rightChild;
        node.rightChild = current.leftChild;
        current.leftChild = node;
        fixHeight(node);
        fixHeight(current);
        return current;
    }

    private TreeNode rightRotation(TreeNode node) {
        TreeNode current = node.leftChild;
        node.leftChild = current.rightChild;
        current.rightChild = node;
        fixHeight(node);
        fixHeight(current);
        return current;
    }

    private OperationResult balanceHeight(TreeNode node) {
        if (heightInvariant(node) > 1) {
            balanced = false;
            if (heightInvariant(node.leftChild) < 0) {
                node.leftChild = leftRotation(node.leftChild);
            }

            node = rightRotation(node);
        } else if (heightInvariant(node) < -1) {
            balanced = false;
            if (heightInvariant(node.rightChild) > 0) {
                node.rightChild = rightRotation(node.rightChild);
            }

            node = leftRotation(node);
        }

        return new OperationResult(node, balanced);
    }

    @Override
    public boolean insertItem(int item) {          // true means height balanced after insertion
        OperationResult result = insert(root, item);
        balanced = true;
        root = result.resNode;
        return result.balanced;
    }

    private OperationResult insert(TreeNode node, int item) {
        if (node == null) return new OperationResult(new TreeNode(item), true);

        if (item < node.item) {
            node.leftChild = insert(node.leftChild, item).resNode;
        } else if (item > node.item) {
            node.rightChild = insert(node.rightChild, item).resNode;
        } else {
            return new OperationResult(node, true);
        }

        fixHeight(node);
        return balanceHeight(node);
    }

    private TreeNode minNode(TreeNode current) {
        while (current.leftChild != null) {
            current = current.leftChild;
        }
        return current;
    }

    private TreeNode deleteMin(TreeNode current) {
        if (current.leftChild == null) return current.rightChild;
        current.leftChild = deleteMin(current.leftChild);
        fixHeight(current);
        return balanceHeight(current).resNode;
    }

    @Override
    public boolean deleteItem(int item) {      // true means height balanced after deletion
        if (root == null) return false;

        OperationResult result = delete(root, item);
        balanced = true;
        root = result.resNode;
        return result.balanced;
    }

    private OperationResult delete(TreeNode current, int item) {
        if (current == null) return new OperationResult(null, true);

        if (item < current.item) {
            current.leftChild = delete(current.leftChild, item).resNode;
        } else if (item > current.item) {
            current.rightChild = delete(current.rightChild, item).resNode;
        } else {
            if (current.leftChild == null) return new OperationResult(current.rightChild, true);
            if (current.rightChild == null) return new OperationResult(current.leftChild, true);
            
            TreeNode node = current;
            current = minNode(node.rightChild);
            current.rightChild = deleteMin(node.rightChild);
            current.leftChild = node.leftChild;
        }

        fixHeight(current);
        return balanceHeight(current);
    }

    @Override
    public boolean searchItem(int item) {      // true if found
        if (root == null) return false;

        TreeNode current = root;
        while (current != null) {
            if (item < current.item) current = current.leftChild;
            else if (item > current.item) current = current.rightChild;
            else return true;
        }

        return false;
    }

    public void printTree() {
        preOrderTraversal(root);
    }

    private void preOrderTraversal(TreeNode current) {
        if (current == null) return;
        System.out.print(current.item);
        if (current.leftChild != null || current.rightChild != null) {
            System.out.print("(");
            preOrderTraversal(current.leftChild);
            System.out.print(")");
            System.out.print("(");
            preOrderTraversal(current.rightChild);
            System.out.print(")");
        }
    }
}