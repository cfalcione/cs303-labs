package cfalcione.cs303.lab07;

public class BinarySearchTree<T extends Comparable<T>> {
    private BinaryTreeNode<T> root;

    public BinarySearchTree () {}

    public BinaryTreeNode<T> getRoot() {    return root;    }

    public BinarySearchTree (Iterable<T> dataIterable) {
        for (T data : dataIterable) {
            this.insert(data);
        }
    }

    public void insert(T data) {
        BinaryTreeNode<T> node = new BinaryTreeNode<>(data);
        if (this.root == null) {
            this.root = node;
            return;
        }
        BinaryTreeNode<T> cursor = root;
        while( (cursor != null)  ){
            int compare = data.compareTo(cursor.getValue());
            if (compare > 0) {
                if (cursor.getRight() != null) {
                    cursor = cursor.getRight();
                    continue;
                }
                cursor.setRight(node);
                return;
            }
            if (cursor.getLeft() != null) {
                cursor = cursor.getLeft();
                continue;
            }
            cursor.setLeft(node);
            return;
        }

    }
}
