package cfalcione.cs303.lab04;

import cfalcione.cs303.shared.SortingAlgorithm;

public class HeapSort <T extends Comparable<T>> extends SortingAlgorithm<T> {

    public String name() {
        return "Heap Sort";
    }

    public void sort(T[] array, int start, int end) {
        buildMaxHeap(array, start, end);
        for (int i = end; i > start; i--) {
            T temp = array[i];
            array[i] = array[start];
            array[start] = temp;
            maxHeapify(array, start, start, i - 1);
        }
    }

    public void buildMaxHeap(T[] array, int start, int end) {
        int mid = (start + end) / 2;
        for (int i = mid; i >= start; i--) {
            maxHeapify(array, start, i, end);
        }
    }

    private void maxHeapify(T[] array, int start, int i, int end) {
        int left = left(start, i);
        int right = right(start, i);
        int largest = i;

        if (left <= end && array[left].compareTo(array[largest]) > 0) {
            largest = left;
        }

        if (right <= end && array[right].compareTo(array[largest]) > 0) {
            largest = right;
        }

        if (largest != i) {
            T temp = array[largest];
            array[largest] = array[i];
            array[i] = temp;
            maxHeapify(array, start, largest, end);
        }
    }

    private int left(int start, int i) {
        return 2 * (i - start) + 1;
    }

    private int right(int start, int i) {
        return left(start, i) + 1;
    }
}
