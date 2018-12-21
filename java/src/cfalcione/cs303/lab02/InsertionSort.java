package cfalcione.cs303.lab02;

import cfalcione.cs303.shared.SortingAlgorithm;

public class InsertionSort <T extends Comparable<T>> extends SortingAlgorithm<T> {

    public String name() { return "Insertion Sort"; }

    public void sort(T[] array, int start, int end) {
        if (end - start < 2) return;
        for (int i = start + 1; i <= end; i++) {
            int insertIndex = findInsertIndex(array, array[i], start, i);
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
