package cfalcione.cs303.lab05;

import cfalcione.cs303.lab03.SelectionSort;
import cfalcione.cs303.shared.Helpers;
import cfalcione.cs303.shared.SortingAlgorithm;

public class QuickSort<T extends Comparable<T>> extends SortingAlgorithm<T> {

    protected int algorithmCutoff = 8;
    SortingAlgorithm<T> fallback = new SelectionSort<>();


    public QuickSort() {}
    public QuickSort(int algorithmCutoff) {
        this.algorithmCutoff = algorithmCutoff;
    }

    public String name() {
        return "QuickSort";
    }

    public void sort(T[] array, int start, int end) {
        if (end <= start) return;

        if (end - start <= this.algorithmCutoff) this.fallback.sort(array, start, end);

        T pivot = findPivot(array, start, end);
        int mid = partition(array, start, end, pivot);

        sort(array, start, mid - 1);
        sort(array, mid + 1, end);
    }

    public T findPivot(T[] array, int start, int end) {
        return array[(start + end) / 2];
    }

    public int partition(T[] array, int start, int end, T pivot) {
        int i = start, j = end;
        while (i < j) {
            // align i and j with elements that should be swapped
            while (array[i].compareTo(pivot) < 0) i++;
            while (array[j].compareTo(pivot) > 0) j--;

            // bail if the loop condition is now false
            if (i >= j) break;

            Helpers.swap(array, i, j);
            // don't enter an infinite loop swapping identical elements repeatedly
            if (array[i].compareTo(array[j]) == 0) i++; // j-- would work too
        }
        return j;
    }

}
