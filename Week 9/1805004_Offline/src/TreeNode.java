public class TreeNode<Key extends Comparable<Key>> implements Comparable<TreeNode<Key>> {

    Key key;
    int order;
    TreeNode<Key> parent, child, sibling;    // parent, left child, next to child

    public TreeNode(Key key) {
        this.key = key;
        this.order = 0;
    }

    @Override
    public int compareTo(TreeNode<Key> o) {
        return this.key.compareTo(o.key);
    }

    private void levelWiseString(StringBuilder[] builders, int level) {
        builders[level].append(" ").append(this.key);

        if (this.order != 0) {
            TreeNode<Key> current = this.child;
            while (current != null) {
                current.levelWiseString(builders, level + 1);
                current = current.sibling;
            }
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Binomial Tree, B").append(this.order).append('\n');
        StringBuilder[] builders = new StringBuilder[this.order + 1];
        for(int i=0;i<=this.order;i++) builders[i] = new StringBuilder();

        levelWiseString(builders, 0);
        for (int level = 0; level <= this.order; level++) {
            sb.append("Level ").append(level).append(" : ")
                    .append(builders[level].toString()).append('\n');
        }

        return sb.toString();
    }
}