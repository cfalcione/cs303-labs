package cfalcione.cs303.shared;

import java.util.Iterator;

public class ArrayIterator<T> implements Iterator<T> {

    private T[] array;
    private int i;
    private int end;

    public ArrayIterator(T[] array, int start, int end) {
        this.array = array;
        this.i = start;
        this.end = end;
    }

    public boolean hasNext() {
        return i <= end;
    }

    public T next() {
        return array[i++];
    }

    public T peek() {
        return array[i];
    }
}
