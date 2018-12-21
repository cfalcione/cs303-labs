package cfalcione.cs303.lab07;

public class BinaryTreeNode<T extends Comparable<T>> extends AbstractBTNode<T> {

    private T value;
    private AbstractBTNode<T> parent;
    private AbstractBTNode<T> left;
    private AbstractBTNode<T> right;

    public BinaryTreeNode (T data) {
        super(data);
    }

    public T getValue() {   return this.value;  }

    public AbstractBTNode<T> getParent() {
        return null;
    }

    public AbstractBTNode<T> getLeft() {   return left;    }
    public AbstractBTNode<T> getRight() {   return right;    }

    public void setValue(T value) {
        this.value = value;
    }

    public void setParent(AbstractBTNode<T> parent) {
        this.parent = parent;
    }

    public void setLeft(AbstractBTNode<T> left) {
        this.left = left;
    }

    public void setRight (AbstractBTNode<T> right) {
        this.right = right;
    }
}
