package cfalcione.cs303.lab02;

import cfalcione.cs303.shared.*;

public class Lab02 extends Lab {

    public void main(String[] args) {
        InsertionSort<Integer> insertionSort = new InsertionSort<>();
        ReferenceSort<Integer> reference = new ReferenceSort<>();

        SortingAlgorithmBenchmark.benchmark(insertionSort);
        SortingAlgorithmBenchmark.benchmark(reference);
    }

}
