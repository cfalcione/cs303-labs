package cfalcione.cs303.lab03;

import cfalcione.cs303.lab02.InsertionSort;
import cfalcione.cs303.shared.*;

import java.util.Arrays;


public class Lab03 extends Lab {

    public void main(String[] args) {
        SortingAlgorithm<Integer>[] algorithms = Helpers.makeGenericArray(
            new ReferenceSort<>(),
            new InsertionSort<>(),
            new MergeSort<>()
        );

        SortingAlgorithmBenchmark.benchmark(Arrays.asList(algorithms));
    }

}
