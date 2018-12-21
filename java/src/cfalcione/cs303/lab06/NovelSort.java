package cfalcione.cs303.lab06;

import cfalcione.cs303.lab03.SelectionSort;
import cfalcione.cs303.shared.Helpers;
import cfalcione.cs303.shared.SortingAlgorithm;

public class NovelSort<T extends Comparable<T>> extends SortingAlgorithm<T> {

    public String name() {
        return "Novel Sort";
    }

    public void sort(T[] array, int start, int end) {
        while (start < end) {
            int smallest = minIndex(array, start, end);
            Helpers.swap(array, start, smallest);
            int largest = maxIndex(array, start, end);
            Helpers.swap(array, end, largest);
            start++;
            end--;
        }
    }

    private int minIndex(T[] array, int start, int end) {
        int minimum = start;
        for (int i = start; i <= end; i++) {
            if (array[i].compareTo(array[minimum]) < 0) minimum = i;
        }
        return minimum;
    }

    private int maxIndex(T[] array, int start, int end) {
        int maximum = start;
        for (int i = start; i <= end; i++) {
            if (array[i].compareTo(array[maximum]) >= 0) maximum = i;
        }
        return maximum;
    }
}
