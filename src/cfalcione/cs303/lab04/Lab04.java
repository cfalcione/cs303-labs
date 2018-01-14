package cfalcione.cs303.lab04;

import cfalcione.cs303.lab02.InsertionSort;
import cfalcione.cs303.lab03.MergeSort;
import cfalcione.cs303.shared.*;

import java.util.Arrays;


public class Lab04 extends Lab {

    public void main(String[] args) {
        SortingAlgorithm<Integer>[] algorithms = Helpers.makeGenericArray(
            new ReferenceSort<>(),
            new InsertionSort<>(),
            new MergeSort<>(),
            new HeapSort<>()
        );

        SortingAlgorithmBenchmark.benchmark(Arrays.asList(algorithms));
    }

}
