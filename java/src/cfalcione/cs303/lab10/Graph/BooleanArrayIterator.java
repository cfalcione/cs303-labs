package cfalcione.cs303.lab10.Graph;

import java.util.Iterator;

public class BooleanArrayIterator implements Iterator<Integer> {

    protected boolean[] array;
    private int cursor;

    public BooleanArrayIterator(boolean[] array) {
        this.array = array;
        findNext();
    }

    @Override
    public boolean hasNext() {
        return cursor < array.length;
    }

    @Override
    public Integer next() {
        Integer result = cursor;
        findNext();
        return result;
    }

    protected void findNext() {
        int i = cursor + 1;
        while (i < array.length && !array[i]) {
            i++;
        }
        cursor = i;
    }
}
