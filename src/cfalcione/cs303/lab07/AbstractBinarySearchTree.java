package cfalcione.cs303.lab07;

public abstract class AbstractBinarySearchTree<T extends Comparable<T>> {

    public AbstractBinarySearchTree() {}

    public abstract AbstractBTNode<T> getRoot();

    public abstract void setRoot(AbstractBTNode<T> node);

    public abstract AbstractBTNode<T> makeNode(T data);

    public AbstractBinarySearchTree(Iterable<T> dataIterable) {
        for (T data : dataIterable) {
            this.insert(data);
        }
    }

    public void insert(T data) {
        AbstractBTNode<T> node = this.makeNode(data);
        if (this.getRoot() == null) {
            this.setRoot(node);
            return;
        }
        AbstractBTNode<T> cursor = getRoot();
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
