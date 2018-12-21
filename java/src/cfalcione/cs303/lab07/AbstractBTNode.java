package cfalcione.cs303.lab07;

public abstract class AbstractBTNode<T extends Comparable<T>> {

    public AbstractBTNode (T value){
        this.setValue(value);
    }

    public AbstractBTNode (T value, AbstractBTNode<T> parent) {
        this.setValue(value);
        this.setParent(parent);
    }

    public abstract T getValue();
    public abstract AbstractBTNode<T> getParent();
    public abstract AbstractBTNode<T> getLeft();
    public abstract AbstractBTNode<T> getRight();

    public abstract void setValue (T value);
    public abstract void setParent(AbstractBTNode<T> parent);
    public abstract void setLeft(AbstractBTNode<T> left);
    public abstract void setRight(AbstractBTNode<T> right);



    public String inOrder() {
        String result = "";
        if (getLeft() != null) {
            result += getLeft().inOrder() + " ";
        }
        result += getValue().toString() + " ";
        if (getRight() != null) {
            result += getRight().inOrder() + " ";
        }
        return result;
    }

}
