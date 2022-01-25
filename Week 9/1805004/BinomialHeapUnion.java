/*
 * Implementation of Union functionality of Heap
 */

public class BinomialHeapUnion<Key extends Comparable<Key>> {

    // returns the node with smaller order
    private TreeNode<Key> smallerOrderNode(TreeNode<Key> node1, TreeNode<Key> node2) {
        return (node1.order <= node2.order) ? node1 : node2;
    }

    // larger node as root of the merged heap having same order
    private void connectSameOrderTrees(TreeNode<Key> greaterRoot, TreeNode<Key> smallerRoot) {
        smallerRoot.parent = greaterRoot;
        smallerRoot.sibling = greaterRoot.child;
        greaterRoot.child = smallerRoot;
        greaterRoot.order++;
    }

    // union
    public TreeNode<Key> unionHeap(BinomialMaxHeap<Key> firstHeap, BinomialMaxHeap<Key> secondHeap) {
        TreeNode<Key> mergedRoot = meld(firstHeap, secondHeap);     // merge according to non-decreasing order
        if (mergedRoot == null) return null;

        // making sure at most 1 binomial tree for a specific order. means we have to merge same order trees
        firstHeap.root = null;
        secondHeap.root = null;

        TreeNode<Key> prevNode = null;
        TreeNode<Key> currNode = mergedRoot, nextNode = mergedRoot.sibling;

        while (nextNode != null) {
            if (currNode.order != nextNode.order || (nextNode.sibling != null && nextNode.sibling.order == currNode.order)) {
                prevNode = currNode;
                currNode = nextNode;
            } else {
                if (currNode.key.compareTo(nextNode.key) > 0) {
                    currNode.sibling = nextNode.sibling;
                    connectSameOrderTrees(currNode, nextNode);
                } else {
                    if (prevNode != null) prevNode.sibling = nextNode;
                    else mergedRoot = nextNode;
                    connectSameOrderTrees(nextNode, currNode);
                    currNode = nextNode;
                }
            }
            nextNode = currNode.sibling;
        }

        return mergedRoot;
    }

    // merge
    private TreeNode<Key> meld(BinomialMaxHeap<Key> firstHeap, BinomialMaxHeap<Key> secondHeap) {
        if (firstHeap.root == null) return secondHeap.root;
        if (secondHeap.root == null) return firstHeap.root;

        TreeNode<Key> nextPointerH1 = firstHeap.root;
        TreeNode<Key> nextPointerH2 = secondHeap.root;

        TreeNode<Key> mergedRoot = smallerOrderNode(nextPointerH1, nextPointerH2);
        nextPointerH1 = (mergedRoot == nextPointerH1) ? nextPointerH1.sibling : nextPointerH1;
        nextPointerH2 = (mergedRoot == nextPointerH2) ? nextPointerH2.sibling : nextPointerH2;

        TreeNode<Key> next = mergedRoot;

        while (nextPointerH1 != null && nextPointerH2 != null) {
            next.sibling = smallerOrderNode(nextPointerH1, nextPointerH2);
            next = next.sibling;
            nextPointerH1 = (next == nextPointerH1) ? nextPointerH1.sibling : nextPointerH1;
            nextPointerH2 = (next == nextPointerH2) ? nextPointerH2.sibling : nextPointerH2;
        }

        next.sibling = (nextPointerH1 != null) ? nextPointerH1 : nextPointerH2;
        return mergedRoot;
    }
}