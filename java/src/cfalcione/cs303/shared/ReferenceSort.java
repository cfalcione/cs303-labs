package cfalcione.cs303.shared;

import java.util.Arrays;

public class ReferenceSort<T extends Comparable<T>>  extends SortingAlgorithm<T>{

    public String name() { return "Reference"; }

    public void sort(T[] array) {
        sort(array, 0, array.length);
    }

    public void sort(T[] array, int start, int end) {
        Arrays.<T>sort(array, start, end);
    }
}
