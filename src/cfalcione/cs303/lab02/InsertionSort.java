package cfalcione.cs303.lab02;

import cfalcione.cs303.shared.SortingAlgorithm;

public class InsertionSort <T extends Comparable<T>> extends SortingAlgorithm<T> {

    public String name() { return "Insertion Sort"; }

    public void sort(T[] array) {
        if (array.length < 2) return;
        for (int i = 1; i < array.length; i++) {
            //assume that array is sorted on [0, i)
            int insertIndex = findInsertIndex(array, array[i], 0, i);
            insert(array, array[i], insertIndex, i);
        }
    }

    public int findInsertIndex(T[] array, T target, int start, int end) {
        int i = start;
        while (i <= end && i < array.length &&
                target.compareTo(array[i]) > 0
        ) {
            i++;
        }
        return i;
    }

    public void insert(T[] array, T element, int index, int end) {
        for (int i = index; i <= end && i < array.length; i++) {
            T temp = array[i];
            array[i] = element;
            element = temp;
        }
    }
}
