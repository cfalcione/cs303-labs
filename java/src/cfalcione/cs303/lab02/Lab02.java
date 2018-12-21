package cfalcione.cs303.lab02;

import cfalcione.cs303.shared.*;

import java.util.Arrays;

public class Lab02 extends Lab {

    public void main(String[] args) {
        SortingAlgorithm<Integer>[] algorithms = Helpers.makeGenericArray(
            new ReferenceSort<>(),
            new InsertionSort<>()
        );

        SortingAlgorithmBenchmark.benchmark(Arrays.asList(algorithms));
    }

}
