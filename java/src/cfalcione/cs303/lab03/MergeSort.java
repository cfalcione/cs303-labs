package cfalcione.cs303.lab03;

import cfalcione.cs303.lab02.InsertionSort;
import cfalcione.cs303.shared.ArrayIterator;
import cfalcione.cs303.shared.SortingAlgorithm;

import java.util.Deque;
import java.util.LinkedList;

public class MergeSort <T extends Comparable<T>> extends SortingAlgorithm<T> {

    private int algorithmCutoff = 8;

    public MergeSort(){}

    public MergeSort(int algorithmCutoff) {
        this.algorithmCutoff = algorithmCutoff;
    }

    SortingAlgorithm<T> fallback = new InsertionSort<>();

    public String name() {
        return "Merge Sort";
    }


    public void sort(T[] array, int start, int end) {
        int length = end - start;
        if (length < 2) return;
        if (length < algorithmCutoff)
            fallback.sort(array, start, end);

        int mid = (start + end) / 2;
        sort(array, start, mid);
        sort(array, mid + 1, end);
        merge(array, start, mid, end);
    }

    public void merge(T[] array, int start, int mid, int end) {
        Deque<T> mergedList = buildMergedList(array, start, mid, end);
        for (int i = start; i <= end; i++) {
            array[i] = mergedList.pop();
        }
    }

    //Assumes that left and right are each sorted
    protected Deque<T> buildMergedList(T[] array, int start, int mid, int end) {
        ArrayIterator<T> left = new ArrayIterator<>(array, start, mid);
        ArrayIterator<T> right = new ArrayIterator<>(array, mid + 1, end);
        Deque<T> result = new LinkedList<>();

        while(left.hasNext() && right.hasNext()) {
            ArrayIterator<T> smaller = min(left, right);
            result.add(smaller.next());
        }

        while (left.hasNext()) {
            result.add(left.next());
        }

        while (right.hasNext()) {
            result.add(right.next());
        }

        return result;
    }

    private ArrayIterator<T> min(ArrayIterator<T> a, ArrayIterator<T> b) {
        int compare = a.peek().compareTo(b.peek());
        if (compare > 0) return b;
        return a;
    }

}
