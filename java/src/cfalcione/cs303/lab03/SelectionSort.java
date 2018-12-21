package cfalcione.cs303.lab03;

import cfalcione.cs303.shared.SortingAlgorithm;

public class SelectionSort<T extends Comparable<T>> extends SortingAlgorithm<T>{

    public String name() {
        return "Selection Sort";
    }

    public void sort(T[] array, int start, int end) {
        for (int i = start; i <= end; i++) {
            int min = minIndex(array, i, end);

            T temp = array[i];
            array[i] = array[min];
            array[min] = temp;
        }
    }

    private int minIndex(T[] array, int start, int end) {
        int minimum = start;
        for (int i = start; i <= end; i++) {
            if (array[i].compareTo(array[minimum]) < 0) minimum = i;
        }
        return minimum;
    }
}
