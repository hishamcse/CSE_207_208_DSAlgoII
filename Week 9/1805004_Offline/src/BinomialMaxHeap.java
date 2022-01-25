import java.util.HashMap;

public class BinomialMaxHeap<Key extends Comparable<Key>> {

    protected TreeNode<Key> root;
    private final BinomialHeapUnion<Key> heapUnion;
    private final HashMap<Key, TreeNode<Key>> keyTreeNodeHashMap;

    public BinomialMaxHeap() {
        this.root = null;
        heapUnion = new BinomialHeapUnion<>();
        keyTreeNodeHashMap = new HashMap<>();
    }

    /////////////////////////////// Helper methods //////////////////////////////

    // exchange keys between firstNode and secondNode
    private void swap(TreeNode<Key> firstNode, TreeNode<Key> secondNode) {
        Key key = firstNode.key;
        firstNode.key = secondNode.key;
        secondNode.key = key;
    }

    // bubbleUp heapify to maintain the maxheap property
    private void bottomUpHeapify(TreeNode<Key> node) {
        TreeNode<Key> ascendant = node.parent;
        while (ascendant != null && node.key.compareTo(ascendant.key) > 0) {
            swap(node, ascendant);
            node = ascendant;
            ascendant = ascendant.parent;
        }
    }

    // remove the node having the maximum key
    private TreeNode<Key> removeMaxNode() {
        TreeNode<Key> maxNode = this.root;
        TreeNode<Key> prevNode = null;
        TreeNode<Key> currNode = maxNode;
        TreeNode<Key> nextNode = currNode.sibling;

        while (nextNode != null) {
            if (nextNode.key.compareTo(maxNode.key) > 0) {
                prevNode = currNode;
                maxNode = nextNode;
            }
            currNode = nextNode;
            nextNode = nextNode.sibling;
        }

        if(prevNode != null) prevNode.sibling = maxNode.sibling;
        if(maxNode == this.root) this.root = maxNode.sibling;
        return maxNode;
    }

    // creation of new binomial heap which excludes the root having the maximum key
    private void createHeapWithoutMaxNode(TreeNode<Key> maxNode) {
        TreeNode<Key> currNode = (maxNode.child != null) ? maxNode.child : maxNode;
        TreeNode<Key> prevNode, nextNode;

        if (maxNode.child != null) {
            maxNode.child = null;
            prevNode = null;
            nextNode = currNode.sibling;

            while (nextNode != null) {
                currNode.sibling = prevNode;
                prevNode = currNode;
                currNode = nextNode;
                nextNode = nextNode.sibling;
            }

            currNode.sibling = prevNode;
            BinomialMaxHeap<Key> newHeap = new BinomialMaxHeap<>();
            newHeap.root = currNode;
            this.root = heapUnion.unionHeap(this, newHeap);
        }
    }

    /////////////////////////////// Offline methods //////////////////////////////

    // 1
    private TreeNode<Key> findMaxNode() {
        TreeNode<Key> maxNode = this.root;
        TreeNode<Key> nextNode = maxNode.sibling;

        while (nextNode != null) {
            if (nextNode.key.compareTo(maxNode.key) > 0) maxNode = nextNode;
            nextNode = nextNode.sibling;
        }

        return maxNode;
    }

    public Key findMax() {
        if (this.root == null) return null;

        TreeNode<Key> maxNode = findMaxNode();
        return maxNode.key;
    }

    // 2
    public Key extractMax() {
        if (this.root == null) return null;

        TreeNode<Key> maxNode = removeMaxNode();
        createHeapWithoutMaxNode(maxNode);
        keyTreeNodeHashMap.remove(maxNode.key, maxNode);
        return maxNode.key;
    }

    // 3
    public void insert(Key key) {
        TreeNode<Key> newNode = new TreeNode<>(key);
        BinomialMaxHeap<Key> newHeap = new BinomialMaxHeap<>();
        newHeap.root = newNode;
        this.root = heapUnion.unionHeap(this, newHeap);
        keyTreeNodeHashMap.put(key, newNode);
    }

    // 4
    public boolean increase_key(Key prevKey, Key newKey) {
        if(this.root == null) return false;

        TreeNode<Key> prevNode = keyTreeNodeHashMap.get(prevKey);
        if (prevNode == null) return false;

        prevNode.key = newKey;
        bottomUpHeapify(prevNode);

        keyTreeNodeHashMap.remove(prevKey, prevNode);
        keyTreeNodeHashMap.put(newKey, prevNode);
        return true;
    }

    // 5
    public void print() {
        if(this.root == null) return;
        System.out.println(this);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        TreeNode<Key> current = root;
        while (current != null) {
            sb.append(current);
            current = current.sibling;
        }
        sb.deleteCharAt(sb.length() - 1);
        return sb.toString();
    }
}