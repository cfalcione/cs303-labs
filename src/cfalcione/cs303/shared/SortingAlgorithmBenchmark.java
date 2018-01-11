package cfalcione.cs303.shared;

import java.util.Arrays;

public class SortingAlgorithmBenchmark {

    public static final int MAX_SIZE = Integer.MAX_VALUE / 2; //arbitrarily chosen
    public static final long ONE_SECOND = 1000000000L;
    public static final double TIMEOUT = 1d;

    public static void benchmark(SortingAlgorithm<Integer> algorithm) {
        System.out.println("Benchmarking " + algorithm.name() + ":");
        for (int size = 8; size <= MAX_SIZE; size *= 2) {
            Integer[] array = Helpers.getRandomArray(size);

            double time = (double) algorithm.time(array) / ONE_SECOND; // in seconds
            boolean passes = testSort(array);

            if (!passes) {
                System.out.println("\tFailed sorting " + size + "\tintegers");
                continue;
            }

            System.out.println("\tSorted " + size + "\tintegers in " + time + " seconds");
            if (time > TIMEOUT) {
                System.out.println("Timeout exceeded");
                break;
            }
        }
    }

    public static boolean testSort(Integer[] array) {
        Integer[] sortedCopy = array.clone();
        Arrays.sort(sortedCopy);
        return Helpers.areArraysEqual(array, sortedCopy);
    }

}
