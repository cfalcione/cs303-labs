package cfalcione.cs303.lab07;

public class BinarySearchTree<T extends Comparable<T>> extends AbstractBinarySearchTree<T>{
    private BinaryTreeNode<T> root;

    public BinarySearchTree () {super();}

    public BinarySearchTree (Iterable<T> iterable) {
        super(iterable);
    }

    public BinaryTreeNode<T> getRoot() {    return root;    }

    public void setRoot(AbstractBTNode<T> node) {
        this.root = (BinaryTreeNode<T>) node;
    }

    public AbstractBTNode<T> makeNode(T data) {
        return new BinaryTreeNode<>(data);
    }
}
