package cfalcione.cs303.lab06;

import cfalcione.cs303.lab03.MergeSort;
import cfalcione.cs303.lab03.SelectionSort;
import cfalcione.cs303.shared.*;

import java.util.Arrays;

public class Lab06 extends Lab{

    public void main(String[] args) {

        SortingAlgorithm<Integer>[] algorithms = Helpers.makeGenericArray(
                new ReferenceSort<>(),
                new MergeSort<>(),
                new SelectionSort<>(),
                new NovelSort<>()
        );

        SortingAlgorithmBenchmark.benchmark(Arrays.asList(algorithms));
    }
}
