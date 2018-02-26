package cfalcione.cs303.lab07;

public class BinaryTreeNode<T extends Comparable<T>> {

    private T value;
    private BinaryTreeNode<T> left;
    private BinaryTreeNode<T> right;

    public BinaryTreeNode(T value) {
        this.setValue(value);
    }

    public T getValue() {   return this.value;  }
    public BinaryTreeNode<T> getLeft() {   return left;    }
    public BinaryTreeNode<T> getRight() {   return right;    }

    public void setValue(T value) {
        this.value = value;
    }

    public void setLeft(BinaryTreeNode<T> left) {
        this.left = left;
    }

    public void setRight (BinaryTreeNode<T> right) {
        this.right = right;
    }

    public String inOrder() {
        String result = "";
        if (left != null) {
            result += left.inOrder() + " ";
        }
        result += value.toString() + " ";
        if (right != null) {
            result += right.inOrder() + " ";
        }
        return result;
    }
}
