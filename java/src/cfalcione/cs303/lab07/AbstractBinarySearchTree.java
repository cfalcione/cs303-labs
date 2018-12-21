package cfalcione.cs303.lab07;

public abstract class AbstractBinarySearchTree<T extends Comparable<T>> {

    public AbstractBinarySearchTree() {}

    public abstract AbstractBTNode<T> getRoot();

    public abstract boolean isNull(AbstractBTNode<T> node);

    public abstract void setRoot(AbstractBTNode<T> node);

    public abstract AbstractBTNode<T> makeNode(T data);

    public AbstractBinarySearchTree(Iterable<T> dataIterable) {
        for (T data : dataIterable) {
            this.insert(data);
        }
    }

    public AbstractBTNode<T> insert(T data) {
        AbstractBTNode<T> node = this.makeNode(data);
        if (isNull(getRoot())) {
            this.setRoot(node);
            return node;
        }
        AbstractBTNode<T> cursor = getRoot();
        while( (cursor != null)  ){
            int compare = data.compareTo(cursor.getValue());
            if (compare > 0) {
                if (!isNull(cursor.getRight())) {
                    cursor = cursor.getRight();
                    continue;
                }
                cursor.setRight(node);
                node.setParent(cursor);
                return cursor;
            }
            if (!isNull(cursor.getLeft()) ) {
                cursor = cursor.getLeft();
                continue;
            }
            cursor.setLeft(node);
            node.setParent(cursor);
            return cursor;
        }
        //this should never execute
        return null;
    }
}
